
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		boolean result;
		int threadId;
		Thread thread;
		Authenticated authenticated;
		Principal principal;

		threadId = request.getModel().getInteger("id");
		thread = this.repository.findOneThreadById(threadId);
		authenticated = thread.getAuthenticated();
		principal = request.getPrincipal();

		result = authenticated.getUserAccount().getId() == principal.getAccountId();

		return result;

	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Message> messages = this.repository.findTheMessagesForThisThread(entity.getId());

		request.unbind(entity, model, "title", "creationMoment");
		model.setAttribute("messagesEmpty", messages.isEmpty());

	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneThreadById(id);
		//result.getMessage().size();
		return result;
	}

}
