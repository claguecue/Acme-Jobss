
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EmployerDutyRepository repository;


	// AbstractCreateService<Employer, Duty> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "dutyDescription", "percentage");
		model.setAttribute("id", entity.getJob().getId());
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		Duty result;
		Job job;
		int idJob;

		result = new Duty();
		idJob = request.getModel().getInteger("id");
		job = this.repository.findJobForThisDuty(idJob);

		if (job != null) {
			result.setJob(job);
		}

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		this.repository.save(entity);
	}

}
