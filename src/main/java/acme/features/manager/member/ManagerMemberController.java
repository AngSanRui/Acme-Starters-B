
package acme.features.manager.member;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Controller
public class ManagerMemberController extends AbstractController<Manager, Member> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerMemberListService.class);
		super.addBasicCommand("show", ManagerMemberShowService.class);
		super.addBasicCommand("create", ManagerMemberCreateService.class);

	}
}
