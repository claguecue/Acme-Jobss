
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorCommercialBannerDeleteService implements AbstractDeleteService<Sponsor, CommercialBanner> {

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
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		CommercialBanner result;

		result = this.repository.findOneCommercialBannerById(request.getModel().getInteger("id"));

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;
		this.repository.delete(entity);
	}

}
