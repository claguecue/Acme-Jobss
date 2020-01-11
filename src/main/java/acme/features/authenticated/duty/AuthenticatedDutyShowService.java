
package acme.features.authenticated.duty;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDutyShowService implements AbstractShowService<Authenticated, Duty> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AuthenticatedDutyRepository repository;


	// AbstractShowService<Employer, Duty> interface -------------------------------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result = false;
		int dutyId;
		Duty duty;
		Job job;
		Date dateActual, dateProperty;

		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneDutyById(dutyId);
		job = duty.getJob();

		dateActual = new Date();
		dateProperty = job.getDeadline();

		//		result = job.getStatus() == JobStatus.PUBLISHED && dateProperty.after(dateActual);
		result = job.getFinalMode() && dateProperty.after(dateActual);

		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "dutyDescription", "percentage");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);

		return result;
	}

}
