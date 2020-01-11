
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	// Attributes ------------------------------------------------------------------------------------

	@Autowired
	private AuditorJobRepository repository;


	// AbstractListService<Auditor, AuditRecord> interface ---------------------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Duty> duties = this.repository.findDutyByJobId(entity.getId());
		Collection<Audit> audits = this.repository.findAuditByJobId(entity.getId());

		request.unbind(entity, model, "id", "reference", "deadline", "title", "salary", "moreInfo", "description", "finalMode");
		model.setAttribute("listDutyEmpty", duties.isEmpty());
		model.setAttribute("listAuditEmpty", audits.isEmpty());

	}

	@Override
	public Job findOne(final Request<Job> request) {

		Job result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findJobById(id);
		return result;
	}

}
