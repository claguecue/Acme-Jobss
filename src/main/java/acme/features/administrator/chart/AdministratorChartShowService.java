
package acme.features.administrator.chart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.ApplicationStatus;
import acme.forms.Chart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChartShowService implements AbstractShowService<Administrator, Chart> {

	// Internal state

	@Autowired
	private AdministratorChartRepository repository;


	// AbstractListService<Administrator, Announcement> interface

	@Override
	public boolean authorise(final Request<Chart> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Chart> request, final Chart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		//"jobStatus"
		request.unbind(entity, model, "companySector", "investorSector", "jobFinalMode", "applicationStatus");
	}

	@Override
	public Chart findOne(final Request<Chart> request) {
		Chart result;
		result = new Chart();

		Collection<Object[]> companyBySectorCollection = this.repository.getCompanyBySector();
		Collection<Object[]> investorBySectorCollection = this.repository.getInvestorBySector();

		Collection<Object[]> applicationByStatusCollection = this.repository.getApplicationByStatus();

		Collection<Object[]> jobByStatusCollection = this.repository.getJobByFinalMode();

		Map<String, Long> companyBySectorMap = new HashMap<String, Long>();
		Map<String, Long> investorBySectorMap = new HashMap<String, Long>();
		Map<ApplicationStatus, Long> applicationByStatusMap = new HashMap<ApplicationStatus, Long>();
		Map<Boolean, Long> jobByFinalModeMap = new HashMap<Boolean, Long>();

		for (Object[] obj : companyBySectorCollection) {
			companyBySectorMap.put((String) obj[0], (Long) obj[1]);
		}
		result.setCompanySector(companyBySectorMap);

		for (Object[] obj : investorBySectorCollection) {
			investorBySectorMap.put((String) obj[0], (Long) obj[1]);
		}
		result.setInvestorSector(investorBySectorMap);

		for (Object[] obj : applicationByStatusCollection) {
			applicationByStatusMap.put((ApplicationStatus) obj[0], (Long) obj[1]);
		}
		result.setApplicationStatus(applicationByStatusMap);

		for (Object[] obj : jobByStatusCollection) {
			jobByFinalModeMap.put((Boolean) obj[0], (Long) obj[1]);
		}
		result.setJobFinalMode(jobByFinalModeMap);

		return result;

	}

}
