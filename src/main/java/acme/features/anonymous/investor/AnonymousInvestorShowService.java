
package acme.features.anonymous.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investors.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousInvestorShowService implements AbstractShowService<Anonymous, Investor> {

	@Autowired
	private AnonymousInvestorRepository repository;


	@Override
	public boolean authorise(final Request<Investor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Investor> request, final Investor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investorName", "sector", "investingStatement", "starsNumber");
	}

	@Override
	public Investor findOne(final Request<Investor> request) {
		assert request != null;

		Investor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
