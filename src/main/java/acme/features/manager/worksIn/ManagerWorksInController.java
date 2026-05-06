
package acme.features.manager.worksIn;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.projects.WorksIn;
import acme.realms.managers.Manager;

@Controller
public class ManagerWorksInController extends AbstractController<Manager, WorksIn> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("create", ManagerWorksInCreateService.class);
	}
}
