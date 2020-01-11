
package acme.features.employer.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.orems.Orem;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerOremCreateService implements AbstractCreateService<Employer, Orem> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EmployerOremRepository repository;


	// AbstractCreateService<Employer, Orem> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<Orem> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Orem> request, final Orem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Orem> request, final Orem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text", "marker");
		model.setAttribute("id", entity.getJob().getId());
	}

	@Override
	public Orem instantiate(final Request<Orem> request) {
		Orem result;
		Job job;
		int idJob;

		result = new Orem();
		idJob = request.getModel().getInteger("id");
		job = this.repository.findJobForThisOrem(idJob);

		if (job != null) {
			result.setJob(job);
		}

		return result;
	}

	@Override
	public void validate(final Request<Orem> request, final Orem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Orem> request, final Orem entity) {
		this.repository.save(entity);
	}

}
