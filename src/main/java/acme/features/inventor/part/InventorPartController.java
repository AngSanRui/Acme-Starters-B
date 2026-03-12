
package acme.features.inventor.part;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.invention.Part;
import acme.realms.inventor.Inventor;

@Controller
public class InventorPartController extends AbstractController<Inventor, Part> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", InventorPartListService.class);
		super.addBasicCommand("show", InventorPartShowService.class);
	}
}
