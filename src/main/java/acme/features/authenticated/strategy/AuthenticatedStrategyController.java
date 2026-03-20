
package acme.features.authenticated.strategy;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Authenticated;
import acme.client.controllers.AbstractController;
import acme.entities.strategies.Strategy;

@Controller
public class AuthenticatedStrategyController extends AbstractController<Authenticated, Strategy> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AuthenticatedStrategyListService.class);
		super.addBasicCommand("show", AuthenticatedStrategyShowService.class);
	}
}
