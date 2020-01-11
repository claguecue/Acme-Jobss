
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.audits.Audit;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EmployerJobRepository	repository;

	@Autowired
	EmployerDutyRepository	employerDutyRepository;


	// AbstractDeleteService<Employer, Job> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "employer");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "description", "moreInfo", "finalMode");
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

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Validación para cuando existen applications
		Collection<Application> applications;
		applications = this.repository.findApplicationByJobId(entity.getId());

		if (applications != null && !applications.isEmpty()) {
			errors.state(request, false, "reference", "errors.job.hasApplications", "A job can be deleted as long as no worker has applied for it");
		}

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Collection<Duty> duties = this.repository.findDutyByJobId(entity.getId());
		this.employerDutyRepository.deleteAll(duties);
		Collection<Audit> audits = this.repository.findAuditByJobId(entity.getId());
		this.repository.deleteAll(audits);

		this.repository.delete(entity);
	}

}
