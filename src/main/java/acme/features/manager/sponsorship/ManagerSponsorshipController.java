
package acme.features.manager.sponsorship;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.managers.Manager;

@Controller
public class ManagerSponsorshipController extends AbstractController<Manager, Sponsorship> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerSponsorshipListService.class);
		super.addBasicCommand("show", ManagerSponsorshipShowService.class);
	}
}
