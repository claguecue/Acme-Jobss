
package acme.features.employer.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.orems.Orem;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerOremDeleteService implements AbstractDeleteService<Employer, Orem> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EmployerOremRepository repository;


	// AbstractDeleteService<Employer, Orem> interface ---------------------------------------

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

		request.bind(entity, errors, "job");
	}

	@Override
	public void unbind(final Request<Orem> request, final Orem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text", "marker");
	}

	@Override
	public Orem findOne(final Request<Orem> request) {
		assert request != null;

		Orem result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneOremById(id);

		return result;
	}

	@Override
	public void validate(final Request<Orem> request, final Orem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Orem> request, final Orem entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
