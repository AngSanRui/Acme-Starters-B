
package acme.features.authenticated.invention;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Authenticated;
import acme.client.controllers.AbstractController;
import acme.entities.invention.Invention;

@Controller
public class AuthenticatedInventionController extends AbstractController<Authenticated, Invention> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AuthenticatedInventionListService.class);
		super.addBasicCommand("show", AuthenticatedInventionShowService.class);
	}

}
