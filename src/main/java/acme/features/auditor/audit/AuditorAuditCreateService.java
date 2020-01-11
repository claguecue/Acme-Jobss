
package acme.features.auditor.audit;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditCreateService implements AbstractCreateService<Auditor, Audit> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	AuditorAuditRepository repository;


	// AbstractCreateService<Auditor, Audit> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body");
		model.setAttribute("id", entity.getJob().getId());
	}

	@Override
	public Audit instantiate(final Request<Audit> request) {
		Audit result;
		Job job;
		int idJob, idAuditor;
		Auditor auditor;

		result = new Audit();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		idAuditor = request.getPrincipal().getActiveRoleId();
		auditor = this.repository.findOneAuditorById(idAuditor);
		result.setAuditor(auditor);

		idJob = request.getModel().getInteger("id");
		job = this.repository.findJobForThisAudit(idJob);

		if (job != null) {
			result.setJob(job);
		}

		return result;
	}

	@Override
	public void validate(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Audit> request, final Audit entity) {
		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);
	}

}
