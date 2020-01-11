
package acme.features.authenticated.problem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.orems.Orem;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedOremListService implements AbstractListService<Authenticated, Orem> {

	// Internal state ---------------------------------------------------------------

	@Autowired
	AuthenticatedOremRepository repository;


	// AbstractListService<Authenticated, Orem> interface ---------------------------------

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

		request.unbind(entity, model, "text", "marker", "job");
	}

	@Override
	public Collection<Orem> findMany(final Request<Orem> request) {
		assert request != null;

		Collection<Orem> result;
		int jobId;

		String[] cadena = request.getServletRequest().getQueryString().trim().split("=");
		jobId = Integer.parseInt(cadena[1]);

		result = this.repository.findManyByJobId(jobId);

		return result;
	}

}
