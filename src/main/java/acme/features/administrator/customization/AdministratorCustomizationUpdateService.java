
package acme.features.administrator.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customizations.Customization;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomizationUpdateService implements AbstractUpdateService<Administrator, Customization> {

	@Autowired
	AdministratorCustomizationRepository repository;


	@Override
	public boolean authorise(final Request<Customization> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Customization> request, final Customization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Customization> request, final Customization entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamThreshold", "spamWords", "activeID");

	}

	@Override
	public Customization findOne(final Request<Customization> request) {
		assert request != null;

		Customization result;

		result = this.repository.findMain();

		return result;
	}

	@Override
	public void validate(final Request<Customization> request, final Customization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("spamWords")) {
			boolean okEndWords = !entity.getSpamWords().endsWith(" ") || !entity.getSpamWords().endsWith(",") || !entity.getSpamWords().endsWith(", ");
			errors.state(request, okEndWords, "spamWords", "errors.administrator.customization.spamWords.wrongEndWords", "End with blank space or comma not valid");
		}
	}

	@Override
	public void update(final Request<Customization> request, final Customization entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
