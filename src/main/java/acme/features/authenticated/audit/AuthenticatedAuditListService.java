
package acme.features.authenticated.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditListService implements AbstractListService<Authenticated, Audit> {

	// Internal state ---------------------------------------------------------------

	@Autowired
	AuthenticatedAuditRepository repository;


	// AbstractListService<Authenticated, Audit> interface ---------------------------------

	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "job");
	}

	@Override
	public Collection<Audit> findMany(final Request<Audit> request) {
		assert request != null;

		Collection<Audit> result;
		int jobId;

		String[] cadena = request.getServletRequest().getQueryString().trim().split("=");
		jobId = Integer.parseInt(cadena[1]);

		result = this.repository.findManyByJobId(jobId);

		return result;
	}

}
