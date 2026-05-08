
package acme.features.manager.invention;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.invention.Invention;
import acme.realms.managers.Manager;

@Controller
public class ManagerInventionController extends AbstractController<Manager, Invention> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerInventionListService.class);
		super.addBasicCommand("show", ManagerInventionShowService.class);
	}
}
