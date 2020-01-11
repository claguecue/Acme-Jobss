
package acme.features.employer.problem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.orems.Orem;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/orem/")
public class EmployerOremController extends AbstractController<Employer, Orem> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	private EmployerOremShowService		showService;

	@Autowired
	private EmployerOremListService		listService;

	@Autowired
	private EmployerOremCreateService	createService;

	@Autowired
	private EmployerOremUpdateService	updateService;

	@Autowired
	private EmployerOremDeleteService	deleteService;


	// Constructors --------------------------------------------------------------------

	@PostConstruct
	private void initialisate() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
