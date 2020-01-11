
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	// AbstractShowService<Employer, Message> interface -------------------------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;
		int messageId;
		Message message;
		Authenticated authenticated;
		Principal principal;

		messageId = request.getModel().getInteger("id");
		message = this.repository.findOneMessageById(messageId);
		authenticated = message.getThread().getAuthenticated();
		principal = request.getPrincipal();

		result = authenticated.getUserAccount().getId() == principal.getAccountId();

		return result;

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "body", "tags");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageById(id);

		return result;
	}

}
