
package acme.features.administrator.auditorRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRequestRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select ar from AuditorRequest ar")
	Collection<AuditorRequest> findManyAll();

	@Query("select ar from AuditorRequest ar where ar.id = ?1")
	AuditorRequest findOneById(int id);

}
