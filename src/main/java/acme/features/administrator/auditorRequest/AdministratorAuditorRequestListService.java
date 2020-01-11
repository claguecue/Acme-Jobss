
package acme.features.administrator.auditorRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuditorRequestListService implements AbstractListService<Administrator, AuditorRequest> {

	// Internal state ------------------------------------------------------------------------

	@Autowired
	private AdministratorAuditorRequestRepository repository;


	// AbstractListService<Administrator, AuditorRequest> interface --------------------------

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		boolean result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);
		result = userAccount.hasRole(Administrator.class);

		return result;
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement", "status");

	}

	@Override
	public Collection<AuditorRequest> findMany(final Request<AuditorRequest> request) {
		assert request != null;

		Collection<AuditorRequest> result;

		result = this.repository.findManyAll();

		return result;
	}

}
