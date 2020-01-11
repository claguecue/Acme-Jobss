
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.orems.Orem;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerJobShowService implements AbstractShowService<Worker, Job> {

	// Internal state -------------------------------------------------------------

	@Autowired
	WorkerJobRepository repository;


	// AbstractShowService<Worker, Job> interface -------------------------------

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
		Collection<Orem> orems = this.repository.findOremByJobId(entity.getId());

		request.unbind(entity, model, "id", "reference", "deadline", "title", "salary", "moreInfo", "description", "finalMode");
		model.setAttribute("listDutyEmpty", duties.isEmpty());
		model.setAttribute("listAuditEmpty", audits.isEmpty());
		model.setAttribute("listOremEmpty", orems.isEmpty());
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

}
