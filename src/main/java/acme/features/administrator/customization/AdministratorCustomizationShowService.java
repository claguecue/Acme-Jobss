/*
 * AdministratorCustomizationShowService.java
 *
 * Copyright (c) 2019 Aureliano Piqueras, based on Rafael Corchuelo's DP Starter project.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customizations.Customization;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCustomizationShowService implements AbstractShowService<Administrator, Customization> {

	@Autowired
	private AdministratorCustomizationRepository repository;


	@Override
	public boolean authorise(final Request<Customization> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Customization> request, final Customization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "spamWords", "spamThreshold", "activeID");

	}

	@Override
	public Customization findOne(final Request<Customization> request) {
		assert request != null;
		Customization result;
		result = this.repository.findMain();
		return result;
	}

}
