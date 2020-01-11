
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	// Control check

	Double						ratioOfJobsHaveAOrem;
	Double						ratioOfOremsIncludeAMarker;
	Double						ratioOfApplicationsIncludeAMarkerWithPassword;

	// -------------------------------------------------------------------------

	Integer						numberOfAnnouncements;
	Integer						numberOfCompanyRecords;
	Integer						numberOfInvestorRecords;

	Double						minimumRewardActiveRequests;
	Double						maximumRewardActiveRequests;
	Double						averageRewardActiveRequests;
	Double						stddevRewardActiveRequests;

	Double						minimumRewardActiveOffersMin;
	Double						maximumRewardActiveOffersMin;
	Double						averageRewardActiveOffersMin;
	Double						stddevRewardActiveOffersMin;

	Double						minimumRewardActiveOffersMax;
	Double						maximumRewardActiveOffersMax;
	Double						averageRewardActiveOffersMax;
	Double						stddevRewardActiveOffersMax;

	Double						averageNumberofJobsPerEmployer;
	Double						averageNumberofApplicationsPerWorker;
	Double						averageNumberofApplicationsPerEmployer;

}
