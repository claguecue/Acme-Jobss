
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	private AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer numberOfAnnouncements = this.repository.numberOfAnnouncements();
		Integer numberOfCompanyRecords = this.repository.numberOfCompanyRecords();
		Integer numberOfInvestorRecords = this.repository.numberOfInvestorRecords();

		// Active Requests
		Double minimumRewardActiveRequests = this.repository.minimumRewardActiveRequests();
		Double maximumRewardActiveRequests = this.repository.maximumRewardActiveRequests();
		Double averageRewardActiveRequests = this.repository.averageRewardActiveRequests();
		Double stddevRewardActiveRequests = this.repository.stddevRewardActiveRequests();

		// Active Offers
		Double minimumRewardActiveOffersMin = this.repository.minimumRewardActiveOffersMin();
		Double maximumRewardActiveOffersMin = this.repository.maximumRewardActiveOffersMin();
		Double averageRewardActiveOffersMin = this.repository.averageRewardActiveOffersMin();
		Double stddevRewardActiveOffersMin = this.repository.stddevRewardActiveOffersMin();

		Double minimumRewardActiveOffersMax = this.repository.minimumRewardActiveOffersMax();
		Double maximumRewardActiveOffersMax = this.repository.maximumRewardActiveOffersMax();
		Double averageRewardActiveOffersMax = this.repository.averageRewardActiveOffersMax();
		Double stddevRewardActiveOffersMax = this.repository.stddevRewardActiveOffersMax();

		entity.setNumberOfAnnouncements(numberOfAnnouncements);
		entity.setNumberOfCompanyRecords(numberOfCompanyRecords);
		entity.setNumberOfInvestorRecords(numberOfInvestorRecords);

		entity.setMinimumRewardActiveRequests(minimumRewardActiveRequests);
		entity.setMaximumRewardActiveRequests(maximumRewardActiveRequests);
		entity.setAverageRewardActiveRequests(averageRewardActiveRequests);
		entity.setStddevRewardActiveRequests(stddevRewardActiveRequests);

		entity.setMinimumRewardActiveOffersMin(minimumRewardActiveOffersMin);
		entity.setMaximumRewardActiveOffersMin(maximumRewardActiveOffersMin);
		entity.setAverageRewardActiveOffersMin(averageRewardActiveOffersMin);
		entity.setStddevRewardActiveOffersMin(stddevRewardActiveOffersMin);

		entity.setMinimumRewardActiveOffersMax(minimumRewardActiveOffersMax);
		entity.setMaximumRewardActiveOffersMax(maximumRewardActiveOffersMax);
		entity.setAverageRewardActiveOffersMax(averageRewardActiveOffersMax);
		entity.setStddevRewardActiveOffersMax(stddevRewardActiveOffersMax);

		Double averageNumberofJobsPerEmployer = this.repository.averageNumberofJobsPerEmployer();
		Double averageNumberofApplicationsPerWorker = this.repository.averageNumberofApplicationsPerWorker();
		Double averageNumberofApplicationsPerEmployer = this.repository.averageNumberofApplicationsPerEmployer();

		entity.setAverageNumberofJobsPerEmployer(averageNumberofJobsPerEmployer);
		entity.setAverageNumberofApplicationsPerWorker(averageNumberofApplicationsPerWorker);
		entity.setAverageNumberofApplicationsPerEmployer(averageNumberofApplicationsPerEmployer);

		// Control check -------------------------------------------------------------------------------------------

		Double ratioOfJobsHaveAOrem = this.repository.ratioOfJobsHaveAOrem();
		Double ratioOfOremsIncludeAMarker = this.repository.ratioOfOremsIncludeAMarker();
		Double ratioOfApplicationsIncludeAMarkerWithPassword = this.repository.ratioOfApplicationsIncludeAMarkerWithPassword();

		entity.setRatioOfJobsHaveAOrem(ratioOfJobsHaveAOrem);
		entity.setRatioOfOremsIncludeAMarker(ratioOfOremsIncludeAMarker);
		entity.setRatioOfApplicationsIncludeAMarkerWithPassword(ratioOfApplicationsIncludeAMarkerWithPassword);

		request.unbind(entity, model, "numberOfAnnouncements", "numberOfCompanyRecords", "numberOfInvestorRecords", "minimumRewardActiveRequests", "maximumRewardActiveRequests", "averageRewardActiveRequests", "stddevRewardActiveRequests",
			"minimumRewardActiveOffersMin", "maximumRewardActiveOffersMin", "averageRewardActiveOffersMin", "stddevRewardActiveOffersMin", "minimumRewardActiveOffersMax", "maximumRewardActiveOffersMax", "averageRewardActiveOffersMax",
			"stddevRewardActiveOffersMax", "averageNumberofJobsPerEmployer", "averageNumberofApplicationsPerWorker", "averageNumberofApplicationsPerEmployer", "ratioOfJobsHaveAOrem", "ratioOfOremsIncludeAMarker",
			"ratioOfApplicationsIncludeAMarkerWithPassword");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		Dashboard d = new Dashboard();
		return d;
	}

}
