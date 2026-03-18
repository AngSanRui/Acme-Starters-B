
package acme.features.authenticated.tactic;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Authenticated;
import acme.client.controllers.AbstractController;
import acme.entities.strategies.Tactic;

@Controller
public class AuthenticatedTacticController extends AbstractController<Authenticated, Tactic> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AuthenticatedTacticListService.class);
		super.addBasicCommand("show", AuthenticatedTacticShowService.class);
	}
}
