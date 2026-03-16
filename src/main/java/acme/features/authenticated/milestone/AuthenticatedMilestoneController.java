
package acme.features.authenticated.milestone;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Authenticated;
import acme.client.controllers.AbstractController;
import acme.entities.campaign.Milestone;

@Controller
public class AuthenticatedMilestoneController extends AbstractController<Authenticated, Milestone> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AuthenticatedMilestoneListService.class);
		super.addBasicCommand("show", AuthenticatedMilestoneShowService.class);
	}

}
