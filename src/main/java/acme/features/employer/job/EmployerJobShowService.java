
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.audits.Audit;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	// Internal state -------------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractShowService<Employer, Job> interface -------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();

		result = job.getFinalMode() || !job.getFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Duty> duties = this.repository.findDutyByJobId(entity.getId());
		Collection<Audit> audits = this.repository.findAuditByJobId(entity.getId());
		Collection<Application> apps = this.repository.findApplicationByJobId(entity.getId());

		request.unbind(entity, model, "id", "reference", "deadline", "title", "salary", "moreInfo", "description", "finalMode");
		model.setAttribute("listDutyEmpty", duties.isEmpty());
		model.setAttribute("listAuditEmpty", audits.isEmpty());
		model.setAttribute("listAppEmpty", apps.isEmpty());
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
