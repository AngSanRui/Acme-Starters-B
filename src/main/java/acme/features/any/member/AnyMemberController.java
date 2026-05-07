
package acme.features.any.member;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Any;
import acme.client.controllers.AbstractController;
import acme.realms.members.Member;

@Controller
public class AnyMemberController extends AbstractController<Any, Member> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AnyMemberListService.class);
		super.addBasicCommand("show", AnyMemberShowService.class);

	}
}
