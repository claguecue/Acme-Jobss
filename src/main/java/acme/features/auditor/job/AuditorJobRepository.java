
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audits.Audit;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int id);

	@Query("select j from Job j where j.id in (select a.job.id from Audit a where a.auditor.id = ?1)")
	Collection<Job> findJobByAuditorId(int auditorId);

	@Query("select j from Job j where j.id not in (select a.job.id from Audit a where a.auditor.id = ?1)")
	Collection<Job> findNoJobByAuditorId(int auditorId);

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findDutyByJobId(int jobId);

	@Query("select a from Audit a where a.job.id = ?1")
	Collection<Audit> findAuditByJobId(int jobId);

}
