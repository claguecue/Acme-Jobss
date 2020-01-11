
package acme.features.employer.problem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.orems.Orem;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerOremRepository extends AbstractRepository {

	@Query("select o from Orem o where o.id = ?1")
	Orem findOneOremById(int id);

	@Query("select o from Orem o where o.job.id = ?1")
	Collection<Orem> findManyByJobId(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobForThisOrem(int jobId);

	@Query("select o.job from Orem o where o.id = ?1")
	Job findJob(int problemId);

}
