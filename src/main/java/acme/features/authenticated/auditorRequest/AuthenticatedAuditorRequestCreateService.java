
package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	// Internal state ----------------------------------------------------------------

	@Autowired
	private AuthenticatedAuditorRequestRepository repository;


	// AbstractCreateService<Authenticated, AuditorRequest> interface ----------------

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		boolean result;
		Principal principal;

		principal = request.getPrincipal();
		result = !principal.hasRole(Auditor.class);

		return result;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement", "status");

		Principal principal;
		int userAccountId;
		AuditorRequest existing;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		existing = this.repository.findOneAuditorRequestByUserAccountId(userAccountId);

		if (existing != null) {
			if (existing.getStatus().equals("Rejected")) {
				model.setAttribute("alreadyRejected", true);
			} else if (existing.getStatus().equals("Accepted")) {
				model.setAttribute("alreadyAccepted", true);
			} else {
				model.setAttribute("alreadyRejected", false);
			}
			model.setAttribute("alreadyRequested", true);
		} else {
			model.setAttribute("alreadyRequested", false);
		}

		PrincipalHelper.handleUpdate();

	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new AuditorRequest();
		result.setStatus("Pending");
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus("Pending");
		this.repository.save(entity);

	}

}
