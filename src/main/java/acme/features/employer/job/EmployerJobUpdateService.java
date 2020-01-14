
package acme.features.employer.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractUpdateService<Employer, Job> interface ---------------------------------------

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

		Boolean isFuture, isPositive, isEuro, hasDescription, sumDutys;

		// Validación de cuándo puede ser finalMode
		if (entity.getFinalMode()) {
			if (!errors.hasErrors("finalMode")) {
				hasDescription = !entity.getDescription().isEmpty();
				errors.state(request, hasDescription, "finalMode", "errors.job.is.finalMode.description", "Is finalMode when has a descriptor");
			}

			if (!errors.hasErrors("finalMode")) {
				sumDutys = this.sumDutys(entity.getId());
				errors.state(request, sumDutys, "finalMode", "errors.job.is.finalMode.sumDuty", "Is finalMode when the duties sum up to 100% the weekly workload");
			}

			// Validación de Spam
			if (!errors.hasErrors("reference")) {
				Boolean isSpam = this.spam(entity.getReference());
				errors.state(request, !isSpam, "reference", "errors.job.description.spam", "Contain spam words");
			}

			if (!errors.hasErrors("title")) {
				Boolean isSpam = this.spam(entity.getTitle());
				errors.state(request, !isSpam, "title", "errors.job.description.spam", "Contain spam words");
			}

			if (!errors.hasErrors("description")) {
				Boolean isSpam = this.spam(entity.getDescription());
				errors.state(request, !isSpam, "description", "errors.job.description.spam", "Contain spam words");
			}

			if (!errors.hasErrors("moreInfo")) {
				Boolean isSpam = this.spam(entity.getMoreInfo());
				errors.state(request, !isSpam, "moreInfo", "errors.job.description.spam", "Contain spam words");
			}

		}

		// Validación de fecha futura
		if (!errors.hasErrors("deadline")) {
			Date fechaActual;
			fechaActual = new Date();
			isFuture = entity.getDeadline().after(fechaActual);
			errors.state(request, isFuture, "deadline", "errors.job.deadline.future", "Deadline must be in future");
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
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	private boolean sumDutys(final Integer idJob) {
		Boolean result;
		Collection<Duty> duties;
		Double sum = 0.0;
		duties = this.repository.findDutyByJobId(idJob);
		for (Duty d : duties) {
			Double percentage = 0.0;
			percentage = d.getPercentage();
			sum = sum + percentage;
		}
		if (sum == 100.00) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private Boolean spam(final String string) {
		Boolean result = false;
		String spam = this.repository.findCustomization().getSpamWords();
		String[] listaSpam = spam.split(",");
		for (String palabra : listaSpam) {
			if (string.contains(palabra)) {
				result = true;
			}
		}
		return result;
	}

}
