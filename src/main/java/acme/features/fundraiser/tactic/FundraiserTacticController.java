
package acme.features.fundraiser.tactic;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;

import acme.client.controllers.AbstractController;
import acme.entities.strategies.Tactic;
import acme.realms.strategy.Fundraiser;

public class FundraiserTacticController extends AbstractController<Fundraiser, Tactic> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", FundraiserTacticListService.class);
		super.addBasicCommand("show", FundraiserTacticShowService.class);
	}

}
