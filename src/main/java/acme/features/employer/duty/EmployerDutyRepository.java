
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyByJobId(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobForThisDuty(int jobId);

	@Query("select d.job from Duty d where d.id = ?1")
	Job findJob(int dutyId);

}
