
package acme.features.sponsor.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorNonCommercialBannerCreateService implements AbstractCreateService<Sponsor, NonCommercialBanner> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	// AbstractCreateService<Sponsor, NonCommercialBanner> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL");
		request.unbind(entity, model, "jingle");
	}

	@Override
	public NonCommercialBanner instantiate(final Request<NonCommercialBanner> request) {
		NonCommercialBanner result;
		Sponsor sponsor;
		Principal principal;
		int userAccountId;

		result = new NonCommercialBanner();
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repository.findTheSponsor(userAccountId);

		result.setSponsor(sponsor);

		return result;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

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

		if (!errors.hasErrors("jingle")) {
			Boolean isSpam = this.spam(entity.getJingle());
			errors.state(request, !isSpam, "jingle", "errors.job.description.spam", "Contain spam words");
		}
	}

	@Override
	public void create(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
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
