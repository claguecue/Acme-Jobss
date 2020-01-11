
package acme.features.authenticated.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.orems.Orem;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedOremShowService implements AbstractShowService<Authenticated, Orem> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AuthenticatedOremRepository repository;


	// AbstractShowService<Authenticated, Orem> interface -------------------------------

	@Override
	public boolean authorise(final Request<Orem> request) {
		assert request != null;

		return true;
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

}
