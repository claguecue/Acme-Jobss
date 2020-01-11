
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id = ?1")
	Audit findOneAuditById(int auditId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobForThisAudit(int jobId);

	@Query("select a.job from Audit a where a.id = ?1")
	Job findJob(int dutyId);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findOneAuditorById(int id);

	@Query("select a from Audit a where a.job.id = ?1")
	Collection<Audit> findManyByJobId(int jobId);

}
