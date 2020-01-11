
package acme.features.authenticated.problem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.orems.Orem;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/orem/")
public class AuthenticatedOremController extends AbstractController<Authenticated, Orem> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	private AuthenticatedOremShowService	showService;

	@Autowired
	private AuthenticatedOremListService	listService;


	// Constructors --------------------------------------------------------------------

	@PostConstruct
	private void initialisate() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}

}
