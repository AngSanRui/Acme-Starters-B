
package acme.features.any.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.members.Member;

@Service
public class AnyMemberShowService extends AbstractService<Any, Member> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyMemberRepository	repository;

	private Member				member;

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
		Collection<Project> proyectos = this.repository.findAllProjectsByMemberId(this.member.getId());

		status = this.member != null && //
			proyectos.stream().anyMatch(p -> p.getDraftMode());//tiene algún proyecto publicado

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
