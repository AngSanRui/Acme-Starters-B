
package acme.features.member.member;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.members.Member;

@Controller
public class MemberMemberController extends AbstractController<Member, Member> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", MemberMemberListService.class);
		super.addBasicCommand("show", MemberMemberShowService.class);

	}
}
