
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Employer employer;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		employer = application.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.getStatus().equals(ApplicationStatus.PENDING)) {
			model.setAttribute("isPending", true);
		}

		model.setAttribute("worker", entity.getWorker().getIdentity().getFullName());
		model.setAttribute("status", entity.getStatus().toString());
		model.setAttribute("job", entity.getJob().getTitle());

		//		request.unbind(entity, model, "referenceNumber", "creationMoment", "status", "rejectJustification", "statement", "skills", "qualifications", "job.reference", "worker.userAccount.username", "employer.userAccount.username");

		request.unbind(entity, model, "referenceNumber", "creationMoment", "statement", "skills", "qualifications", "rejectJustification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;
	}

}
