
package acme.features.administrator.commercialBanner;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCommercialBannerCreateService implements AbstractCreateService<Administrator, CommercialBanner> {

	// Internal state ---------------------------------------------------------------------------------

	@Autowired
	AdministratorCommercialBannerRepository repository;


	// AbstractCreateService<Administrator, CommercialBanner> interface --------------------------------------

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

		request.unbind(entity, model, "picture", "slogan", "targetURL", "creditCardNumber", "holderName", "expirationYear", "expirationMonth", "cvv");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner result;

		result = new CommercialBanner();

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors()) {
			boolean isNotExpiredYear, isNotExpired;
			Calendar today, expCalendar;
			Integer year, month, day;
			year = entity.getExpirationYear();
			month = entity.getExpirationMonth() - 1;
			day = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
			today = new GregorianCalendar();
			expCalendar = new GregorianCalendar();
			expCalendar.set(year, month, day);
			Integer todayYear = today.get(Calendar.YEAR);
			isNotExpiredYear = todayYear <= year;
			errors.state(request, isNotExpiredYear, "expirationYear", "administrator.commercial-banner.expirationYear.expired");
			if (isNotExpiredYear) {
				isNotExpired = expCalendar.after(today);
				errors.state(request, isNotExpired, "expirationMonth", "administrator.commercial-banner.expirationMonth.expired");
			}

		}
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
