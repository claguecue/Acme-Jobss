
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Job job = this.repository.findOneJobById(request.getModel().getInteger("id"));
		Date now = new Date();

		return job.getDeadline().after(now) || job == null || job.getFinalMode() == false;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "statement", "skills", "qualifications");
		model.setAttribute("id", request.getModel().getInteger("id"));
	}

	@Override
	public Application instantiate(final Request<Application> request) {

		Application result;
		Principal principal;

		//		int accountId, idJob;

		int idJob;
		principal = request.getPrincipal();
		//		accountId = principal.getActiveRoleId();
		idJob = request.getModel().getInteger("id");
		result = new Application();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		int workerId = principal.getAccountId();//
		Worker worker = this.repository.findOneWorkerByUserAccountId(workerId);//
		String qualifications = worker.getQualifications();//
		String skills = worker.getSkills();//
		result.setCreationMoment(moment);
		result.setStatus(ApplicationStatus.PENDING);
		//		result.setWorker(this.repository.findOneWorkerById(accountId));
		result.setWorker(worker);
		result.setJob(this.repository.findOneJobById(idJob));
		result.setSkills(skills);
		result.setQualifications(qualifications);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("referenceNumber")) {
			Boolean unique = null;
			unique = this.repository.findApplicationByReferenceNumber(entity.getReferenceNumber()) != null;
			errors.state(request, !unique, "referenceNumber", "worker.application.error.duplicatedReference");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);
	}

}
