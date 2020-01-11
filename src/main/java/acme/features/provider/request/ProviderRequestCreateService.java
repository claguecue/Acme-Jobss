/*
 * AnonymousRequestCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.provider.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, Request> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ProviderRequestRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "creationMoment", "deadline", "text", "reward", "ticker");
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		assert request != null;

		Request result;
		Date today;
		today = new Date(System.currentTimeMillis() - 1);
		result = new Request();
		result.setCreationMoment(today);

		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean isDuplicated, isFuture, isPositive, isEuro, isAccepted;
		Date today = new Date(System.currentTimeMillis() - 1);

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "provider.request.error.must-accept");

		if (!errors.hasErrors("ticker")) {
			isDuplicated = this.repository.findOneRequestByTicker(entity.getTicker()) != null;
			errors.state(request, !isDuplicated, "ticker", "provider.request.error.duplicated");
		}
		if (!errors.hasErrors("deadline")) {
			isFuture = entity.getDeadline().after(today);
			errors.state(request, isFuture, "deadline", "provider.request.error.no-future");
		}
		if (!errors.hasErrors("reward")) {
			isPositive = entity.getReward().getAmount() > 0;
			errors.state(request, isPositive, "reward", "provider.request.error.negative-amount");

			isEuro = entity.getReward().getCurrency().equals("EUR") || entity.getReward().getCurrency().equals("€");
			errors.state(request, isEuro, "reward", "provider.request.error.no-euro");
		}

	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
	}

}
