
package acme.features.authenticated.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditShowService implements AbstractShowService<Authenticated, Audit> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AuthenticatedAuditRepository repository;


	// AbstractShowService<Authenticated, Audit> interface -------------------------------

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

		request.unbind(entity, model, "title", "creationMoment", "body", "auditor.firm");
	}

	@Override
	public Audit findOne(final Request<Audit> request) {
		assert request != null;

		Audit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditById(id);

		return result;
	}

}
