
package acme.features.administrator.auditorRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/auditor-request/")
public class AdministratorAuditorRequestController extends AbstractController<Administrator, AuditorRequest> {

	// Internal state -------------------------------------------------------

	@Autowired
	private AdministratorAuditorRequestListService		listService;

	@Autowired
	private AdministratorAuditorRequestShowService		showService;

	//	@Autowired
	//	private AdministratorAuditorRequestRejectService	rejectService;

	@Autowired
	private AdministratorAuditorRequestAcceptService	acceptService;


	// Constructors ---------------------------------------------------------

	@PostConstruct
	private void initialise() {

		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		//		super.addCustomCommand(CustomCommand.REJECT, BasicCommand.UPDATE, this.rejectService);
		super.addCustomCommand(CustomCommand.ACCEPT, BasicCommand.UPDATE, this.acceptService);

	}

}
