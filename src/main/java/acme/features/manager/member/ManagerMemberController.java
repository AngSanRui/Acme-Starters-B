
package acme.features.manager.member;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.features.manager.project.ManagerProjectCreateService;
import acme.features.manager.project.ManagerProjectListService;
import acme.features.manager.project.ManagerProjectShowService;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Controller
public class ManagerMemberController extends AbstractController<Manager, Member> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerProjectListService.class);
		super.addBasicCommand("show", ManagerProjectShowService.class);
		super.addBasicCommand("create", ManagerProjectCreateService.class);

	}
}
