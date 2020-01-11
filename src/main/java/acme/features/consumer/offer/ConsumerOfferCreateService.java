
package acme.features.consumer.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "limitDate", "creationMoment", "descriptionText", "minAmount", "maxAmount", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		assert request != null;

		Offer result;
		Date today;
		today = new Date(System.currentTimeMillis() - 1);
		result = new Offer();
		result.setCreationMoment(today);

		return result;

	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;


		boolean isDuplicated, isFuture, isEuro, isAccepted;
		Date today = new Date(System.currentTimeMillis() - 1);

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "provider.request.error.must-accept");

		if (!errors.hasErrors("ticker")) {
			isDuplicated = this.repository.findOneOfferByTicker(entity.getTicker()) != null;
			errors.state(request, !isDuplicated, "ticker", "consumer.offer.error.duplicated");
		}

		if (!errors.hasErrors("limitDate")) {
			isFuture = entity.getLimitDate().after(today);
			errors.state(request, isFuture, "limitDate", "consumer.offer.error.no-future");

		}

		if (!errors.hasErrors("minAmount")) {
			isEuro = entity.getMinAmount().getCurrency().equals("EUR") || entity.getMinAmount().getCurrency().equals("€");
			errors.state(request, isEuro, "minAmount", "consumer.offer.error.no-euro");
		}

		if (!errors.hasErrors("maxAmount")) {
			isEuro = entity.getMaxAmount().getCurrency().equals("EUR") || entity.getMaxAmount().getCurrency().equals("€");
			errors.state(request, isEuro, "maxAmount", "consumer.offer.error.no-euro");
		}

		if (!errors.hasErrors("minAmount")) {
			isEuro = entity.getMinAmount().getAmount() < entity.getMaxAmount().getAmount();
			errors.state(request, isEuro, "minAmount", "consumer.offer.error.smaller");
		}

		if (!errors.hasErrors("maxAmount")) {
			isEuro = entity.getMinAmount().getAmount() < entity.getMaxAmount().getAmount();
			errors.state(request, isEuro, "minAmount", "consumer.offer.error.bigger");
		}

	}
	//	}
	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);

	}

}
