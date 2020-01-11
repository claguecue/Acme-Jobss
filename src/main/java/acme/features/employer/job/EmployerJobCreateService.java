
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractCreateService<Employer, Job> interface ---------------------------------------

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
	public Job instantiate(final Request<Job> request) {
		Job result;
		result = new Job();

		Principal principal;
		int userAccountId;
		Employer employer;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		employer = this.repository.findOneEmployerByUserAccount(userAccountId);

		result.setEmployer(employer);
		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isFuture, isPositive, isEuro;

		// Validación del reference unique
		Boolean unique = null;
		unique = this.repository.findReferenceOfJob(entity.getReference()) != null;
		errors.state(request, !unique, "reference", "errors.job.reference.unique", "The reference must be unique");

		// Validación de fecha futura
		if (!errors.hasErrors("deadline")) {
			Date fechaActual, fecha;
			fechaActual = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaActual);
			calendar.add(Calendar.DAY_OF_WEEK, 7);
			fecha = calendar.getTime();

			isFuture = entity.getDeadline().after(fecha);
			errors.state(request, isFuture, "deadline", "errors.job.deadline.future", "Deadline must be in future and within a week");
		}

		// Validación dinero positivo
		if (!errors.hasErrors("salary")) {
			isPositive = entity.getSalary().getAmount() > 0;
			errors.state(request, isPositive, "salary", "errors.job.salary.money.amount-positive", "The amount must be positive");
		}

		// Validación moneda
		if (!errors.hasErrors("salary")) {
			isEuro = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, isEuro, "salary", "errors.job.salary.money.euro", "The money must be in euro '€' / 'EUR'");
		}

	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		this.repository.save(entity);
	}
}
