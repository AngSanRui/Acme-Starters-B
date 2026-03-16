
package acme.features.authenticated.campaign;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Authenticated;
import acme.client.controllers.AbstractController;
import acme.entities.campaign.Campaign;

@Controller
public class AuthenticatedCampaignController extends AbstractController<Authenticated, Campaign> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AuthenticatedCampaignListService.class);
		super.addBasicCommand("show", AuthenticatedCampaignShowService.class);
	}

}
