
package acme.features.auditor.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.audits.Audit;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit/")
public class AuditorAuditController extends AbstractController<Auditor, Audit> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	private AuditorAuditListService		listService;

	@Autowired
	private AuditorAuditShowService		showService;

	@Autowired
	private AuditorAuditCreateService	createService;

	@Autowired
	private AuditorAuditUpdateService	updateService;

	@Autowired
	private AuditorAuditDeleteService	deleteService;

	@Autowired
	private AuditorAuditListMainService	listMainService;


	// Constructors --------------------------------------------------------------------

	@PostConstruct
	private void initialisate() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addCustomCommand(CustomCommand.LIST_MAIN, BasicCommand.LIST, this.listMainService);

	}

}
