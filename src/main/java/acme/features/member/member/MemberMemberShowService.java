
package acme.features.member.member;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.members.Member;

@Service
public class MemberMemberShowService extends AbstractService<Member, Member> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberMemberRepository	repository;

	private Member					member;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.member = this.repository.findMemberById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int userAccountId = super.getRequest().getPrincipal().getAccountId();
		Member member = this.repository.findMemberByAccountId(userAccountId);

		Collection<Member> members = Collections.emptySet();
		Collection<Project> proyectos = this.repository.findAllProjectsByMemberId(member.getId());

		for (Project p : proyectos)
			members.addAll(this.repository.findAllMembersByProjectId(p.getId()));

		status = super.getRequest().getPrincipal().hasRealmOfType(Member.class) && //
			this.member != null && //
			members.contains(this.member);

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.member, "userAccount.username");
		tuple.put("userAccount", this.member.getUserAccount().getUsername());
		tuple.put("fullName", this.member.getUserAccount().getIdentity().getFullName());
		tuple.put("email", this.member.getUserAccount().getIdentity().getEmail());

	}
}
