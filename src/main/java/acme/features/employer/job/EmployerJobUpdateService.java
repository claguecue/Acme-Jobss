
package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customizations.Customization;
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

		Boolean isFuture, isPositive, isEuro, hasDescription, sumDutys, isSpam;

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

			if (!errors.hasErrors("finalMode")) {
				isSpam = this.esSpam(entity.getId());
				errors.state(request, !isSpam, "finalMode", "errors.job.is.finalMode.spam", "Is finalMode when it’s not considered spam");
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

	private boolean esSpam(final Integer idJob) {
		Boolean result = false;
		Job job = this.repository.findOneJobById(idJob);
		Customization customization = this.repository.findCustomization();
		String[] words = customization.getSpamWords().trim().split(",");
		Collection<String> collectionWords = new ArrayList<String>();
		for (String w : words) {
			collectionWords.add(w);
		}

		for (String cw : collectionWords) {
			if (job.getTitle().contains(cw) || job.getMoreInfo().contains(cw) || job.getDescription().contains(cw)) {
				result = true;
			}
		}
		return result;
	}

}
