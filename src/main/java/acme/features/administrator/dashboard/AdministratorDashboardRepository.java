
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Integer numberOfAnnouncements();

	@Query("select count(cr) from CompanyRecord cr")
	Integer numberOfCompanyRecords();

	@Query("select count(i) from Investor i")
	Integer numberOfInvestorRecords();

	// Active Requests
	@Query("select min(r.reward.amount) from Request r where r.deadline > CURRENT_TIMESTAMP")
	Double minimumRewardActiveRequests();

	@Query("select max(r.reward.amount) from Request r where r.deadline > CURRENT_TIMESTAMP")
	Double maximumRewardActiveRequests();

	@Query("select avg(r.reward.amount) from Request r where r.deadline > CURRENT_TIMESTAMP")
	Double averageRewardActiveRequests();

	@Query("select stddev(r.reward.amount) from Request r where r.deadline > CURRENT_TIMESTAMP")
	Double stddevRewardActiveRequests();

	// Active Offers
	@Query("select min(o.minAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double minimumRewardActiveOffersMin();

	@Query("select max(o.minAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double maximumRewardActiveOffersMin();

	@Query("select avg(o.minAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double averageRewardActiveOffersMin();

	@Query("select stddev(o.minAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double stddevRewardActiveOffersMin();

	@Query("select min(o.maxAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double minimumRewardActiveOffersMax();

	@Query("select max(o.maxAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double maximumRewardActiveOffersMax();

	@Query("select avg(o.maxAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double averageRewardActiveOffersMax();

	@Query("select stddev(o.maxAmount.amount) from Offer o where o.limitDate > CURRENT_TIMESTAMP")
	Double stddevRewardActiveOffersMax();

	//average jobs-employer
	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberofJobsPerEmployer();

	//	average applications por worker
	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberofApplicationsPerWorker();

	@Query("select avg(select count(a) from Application a where a.job.employer.id = e.id) from Employer e")
	Double averageNumberofApplicationsPerEmployer();

	// Control check ----------------------------------------------------------------------------------

	@Query("select 1.0 * count(j)/(select count(j1) from Job j1) from Job j where j.id in(select o.job.id from Orem o)")
	Double ratioOfJobsHaveAOrem();

	@Query("select 1.0 * count(o)/(select count(o1) from Orem o1 ) from Orem o where o.marker != null")
	Double ratioOfOremsIncludeAMarker();

	@Query("select 1.0 * count(a)/(select count(a1) from Application a1) from Application a where a.password != null")
	Double ratioOfApplicationsIncludeAMarkerWithPassword();

}
