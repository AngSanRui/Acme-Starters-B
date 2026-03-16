
package acme.features.spokesperson.milestone;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaign.Milestone;
import acme.realms.campaign.Spokesperson;

@Controller
public class SpokespersonMilestoneController extends AbstractController<Spokesperson, Milestone> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", SpokespersonMilestoneListService.class);
		super.addBasicCommand("show", SpokespersonMilestoneShowService.class);
	}

}
