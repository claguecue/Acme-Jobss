
package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditorRequestShowService implements AbstractShowService<Authenticated, AuditorRequest> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AuthenticatedAuditorRequestRepository repository;


	// AbstractShowService<Authenticated, AuditorRequest> interface -------------------------------

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement", "status");
	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		result = this.repository.findOneAuditorRequestByUserAccountId(userAccountId);

		return result;
	}

}
