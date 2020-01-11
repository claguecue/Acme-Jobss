
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.jobs.Job;
import acme.entities.orems.Orem;
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

		Collection<Orem> orems = this.repository.findOremsByJob(request.getModel().getInteger("id"));

		request.unbind(entity, model, "referenceNumber", "statement", "skills", "qualifications", "answer", "marker", "password");
		model.setAttribute("id", request.getModel().getInteger("id"));
		model.setAttribute("listOremEmpty", orems.isEmpty());
	}

	@Override
	public Application instantiate(final Request<Application> request) {

		Application result;
		Principal principal;

		int accountId, idJob;
		principal = request.getPrincipal();
		accountId = principal.getActiveRoleId();
		idJob = request.getModel().getInteger("id");
		result = new Application();

		Collection<Orem> orems = this.repository.findOremsByJob(idJob);
		Boolean hasOrems = !orems.isEmpty();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		result.setStatus(ApplicationStatus.PENDING);
		result.setWorker(this.repository.findOneWorkerById(accountId));
		result.setJob(this.repository.findOneJobById(idJob));
		if (!hasOrems) {
			result.setAnswer(null);
			result.setMarker(null);
			result.setPassword(null);
		}

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

		Collection<Orem> problems = this.repository.findOremsByJob(entity.getJob().getId());
		if (!problems.isEmpty()) {
			if (!errors.hasErrors("answer")) {
				Boolean hasAnswer = null;
				hasAnswer = !request.getModel().getString("answer").isEmpty();
				errors.state(request, hasAnswer, "answer", "worker.application.error.answer");
			}

			if (!errors.hasErrors("password")) {
				Boolean hasMarkerPassword, hasMarker, hasPassword = null;
				hasMarker = !request.getModel().getString("marker").isEmpty();
				hasPassword = !request.getModel().getString("password").isEmpty();
				if (!hasMarker && hasPassword) {
					hasMarkerPassword = false;
				} else {
					hasMarkerPassword = true;
				}
				errors.state(request, hasMarkerPassword, "password", "worker.application.error.password");
			}
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);
	}

}
