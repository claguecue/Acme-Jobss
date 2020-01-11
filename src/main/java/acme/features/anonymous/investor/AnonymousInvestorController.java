
package acme.features.anonymous.investor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investors.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/investor/")
public class AnonymousInvestorController extends AbstractController<Anonymous, Investor> {

	@Autowired
	private AnonymousInvestorListService		listService;

	@Autowired
	private AnonymousInvestorShowService		showService;

	@Autowired
	private AnonymousInvestorListMainService	listMainService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MAIN, BasicCommand.LIST, this.listMainService);
	}

}
