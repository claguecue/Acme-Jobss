
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audits.Audit;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.finalMode = true and j.deadline > CURRENT_TIMESTAMP")
	Collection<Job> findManyActives();

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findDutyByJobId(int jobId);

	@Query("select a from Audit a where a.job.id = ?1")
	Collection<Audit> findAuditByJobId(int jobId);

}
