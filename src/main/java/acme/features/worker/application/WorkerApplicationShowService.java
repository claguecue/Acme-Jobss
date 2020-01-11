
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.orems.Orem;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerApplicationShowService implements AbstractShowService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Orem> orems = this.repository.findOremsByJob(entity.getJob().getId());

		Boolean hasPassword = true;
		String password = this.repository.findPasswordOfApp(request.getModel().getInteger("id"));
		if (password == null || password.isEmpty()) {
			hasPassword = false;
		}

		Boolean hasMarker = true;
		String marker = this.repository.findMarkerOfApp(request.getModel().getInteger("id"));
		if (marker == null || marker.isEmpty()) {
			hasMarker = false;
		}

		request.unbind(entity, model, "referenceNumber", "creationMoment", "status");
		request.unbind(entity, model, "statement", "skills", "qualifications", "answer", "marker", "password", "job.reference", "worker.userAccount.username", "employer.userAccount.username");
		model.setAttribute("listOremEmpty", orems.isEmpty());
		model.setAttribute("noHasPassword", !hasPassword);
		model.setAttribute("hasMarker", hasMarker);
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
