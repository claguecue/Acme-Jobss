
package acme.features.authenticated.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Job;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/job/")
public class AuthenticatedJobController extends AbstractController<Authenticated, Job> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	private AuthenticatedJobShowService		showService;

	@Autowired
	private AuthenticatedJobListMineService	listMineService;


	// Constructors --------------------------------------------------------------------

	@PostConstruct
	private void initialisate() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}

}
