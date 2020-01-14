//
//package acme.features.administrator.auditorRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.auditorRequests.AuditorRequest;
//import acme.framework.components.Errors;
//import acme.framework.components.Model;
//import acme.framework.components.Request;
//import acme.framework.entities.Administrator;
//import acme.framework.entities.Principal;
//import acme.framework.entities.UserAccount;
//import acme.framework.services.AbstractUpdateService;
//
//@Service
//public class AdministratorAuditorRequestRejectService implements AbstractUpdateService<Administrator, AuditorRequest> {
//
//	// Internal state ---------------------------------------------------------------------------------------
//
//	@Autowired
//	private AdministratorAuditorRequestRepository repository;
//
//
//	// AbstractUpdateService<Administrator, AuditorRequest> interface ---------------------------------------
//
//	@Override
//	public boolean authorise(final Request<AuditorRequest> request) {
//		assert request != null;
//
//		boolean result;
//		Principal principal;
//		int userAccountId;
//		UserAccount userAccount;
//
//		principal = request.getPrincipal();
//		userAccountId = principal.getAccountId();
//		userAccount = this.repository.findOneUserAccountById(userAccountId);
//		result = userAccount.hasRole(Administrator.class);
//
//		return result;
//	}
//
//	@Override
//	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//
//		request.bind(entity, errors);
//
//	}
//
//	@Override
//	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
//		assert request != null;
//		assert entity != null;
//		assert model != null;
//
//		request.unbind(entity, model, "firm", "statement");
//		model.setAttribute("username", entity.getUserAccount().getUsername());
//
//	}
//
//	@Override
//	public AuditorRequest findOne(final Request<AuditorRequest> request) {
//		assert request != null;
//
//		int id;
//		AuditorRequest result;
//
//		id = request.getModel().getInteger("id");
//		result = this.repository.findOneById(id);
//
//		return result;
//	}
//
//	@Override
//	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//
//	}
//
//	@Override
//	public void update(final Request<AuditorRequest> request, final AuditorRequest entity) {
//		assert request != null;
//		assert entity != null;
//
//		entity.setStatus("Rejected");
//
//		this.repository.save(entity);
//
//	}
//
//}
