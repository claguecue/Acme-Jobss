
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	SponsorCommercialBannerRepository repository;


	// AbstractCreateService<Sponsor, CommercialBanner> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL");
		request.unbind(entity, model, "creditCardNumber", "holderName", "expirationMonth", "expirationYear", "cvv");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner result;
		Sponsor sponsor;
		Principal principal;
		int userAccountId;

		result = new CommercialBanner();
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repository.findTheSponsor(userAccountId);

		result.setSponsor(sponsor);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("cvv")) {
			Boolean haveCreditCard = true;
			String consulta = this.repository.findCreditCard(entity.getSponsor().getId());
			if (consulta.isEmpty() || consulta == "" || consulta == null) {
				haveCreditCard = false;
			}
			errors.state(request, haveCreditCard, "cvv", "sponsor.noHaveCreditCard", "Sponsor haven't got credit card");
		}

		// Validación del Spam
		if (!errors.hasErrors("picture")) {
			Boolean isSpam = this.spam(entity.getPicture());
			errors.state(request, !isSpam, "picture", "errors.job.description.spam", "Contain spam words");
		}

		if (!errors.hasErrors("slogan")) {
			Boolean isSpam = this.spam(entity.getSlogan());
			errors.state(request, !isSpam, "slogan", "errors.job.description.spam", "Contain spam words");
		}

		if (!errors.hasErrors("targetURL")) {
			Boolean isSpam = this.spam(entity.getTargetURL());
			errors.state(request, !isSpam, "targetURL", "errors.job.description.spam", "Contain spam words");
		}

		if (!errors.hasErrors("holderName")) {
			Boolean isSpam = this.spam(entity.getHolderName());
			errors.state(request, !isSpam, "holderName", "errors.job.description.spam", "Contain spam words");
		}
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		this.repository.save(entity);
	}

	private Boolean spam(final String string) {
		Boolean result = false;
		String spam = this.repository.findCustomization().getSpamWords();
		String[] listaSpam = spam.trim().split(",");
		for (String palabra : listaSpam) {
			if (string.contains(palabra)) {
				result = true;
			}
		}
		return result;
	}

}
