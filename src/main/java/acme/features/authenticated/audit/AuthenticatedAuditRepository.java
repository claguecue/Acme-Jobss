
package acme.features.authenticated.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audits.Audit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id = ?1")
	Audit findOneAuditById(int id);

	@Query("select a from Audit a where a.job.id = ?1")
	Collection<Audit> findManyByJobId(int jobId);

}
