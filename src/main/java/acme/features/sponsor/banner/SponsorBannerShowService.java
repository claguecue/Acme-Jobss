
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorBannerShowService implements AbstractShowService<Sponsor, NonCommercialBanner> {

	@Autowired
	SponsorBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		boolean result;
		int nonCommercialBannerId;
		NonCommercialBanner nonCommercialBanner;
		Sponsor sponsor;
		Principal principal;

		nonCommercialBannerId = request.getModel().getInteger("id");
		nonCommercialBanner = this.repository.findOneBannerById(nonCommercialBannerId);
		sponsor = nonCommercialBanner.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();

		return result;
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
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {
		assert request != null;

		NonCommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneBannerById(id);

		return result;
	}

}
