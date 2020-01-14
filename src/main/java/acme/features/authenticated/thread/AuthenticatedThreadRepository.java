
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.entities.threads.Thread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	@Query("select t from Thread t where t.id = ?1")
	Thread findOneThreadById(int id);

	@Query("select t from Thread t where t.authenticated.id = ?1")
	Collection<Thread> findManyByAuthenticatedId(int authenticatedId);

	@Query("select m from Message m where m.thread.id = ?1")
	Collection<Message> findTheMessagesForThisThread(int threadId);

}
