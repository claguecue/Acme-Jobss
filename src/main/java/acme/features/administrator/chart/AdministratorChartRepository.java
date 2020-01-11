
package acme.features.administrator.chart;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChartRepository extends AbstractRepository {

	@Query("select c.sector, count(c) from CompanyRecord c group by c.sector order by c.sector")
	Collection<Object[]> getCompanyBySector();

	@Query("select i.sector, count(i) from Investor i group by i.sector order by i.sector")
	Collection<Object[]> getInvestorBySector();

	//	@Query("select 1.0 * count(a)/ (select count(b) from Application b) from Application a" + " where a.status = acme.entities.applications.ApplicationStatus.PENDING")
	//	Double ratioOfPendingApplications();
	//
	//	@Query("select 1.0 * count(a)/ (select count(b) from Application b) from Application a" + " where a.status = acme.entities.applications.ApplicationStatus.ACCEPTED")
	//	Double ratioOfAcceptedApplications();
	//
	//	@Query("select 1.0 * count(a)/ (select count(b) from Application b) from Application a" + " where a.status = acme.entities.applications.ApplicationStatus.REJECTED")
	//	Double ratioOfRejectedApplications();

	@Query("select j.finalMode, count(j) from Job j group by j.finalMode order by j.finalMode")
	Collection<Object[]> getJobByFinalMode();

	@Query("select a.status, count(a) from Application a group by a.status order by a.status")
	Collection<Object[]> getApplicationByStatus();

}
