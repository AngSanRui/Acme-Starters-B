
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
public class AnyMemberListService extends AbstractService<Any, Member> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyMemberRepository	repository;

	private Project				project;

	private Collection<Member>	members;
	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));

		status = this.project != null && //
			!this.project.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void load() {
		this.members = this.repository.findAllMembersByProjectId(super.getRequest().getData("projectId", int.class));
	}

	@Override
	public void unbind() {
		for (Member member : this.members) {
			Tuple tuple;
			tuple = super.unbindObject(member, "userAccount.username");
			tuple.put("userAccount", member.getUserAccount().getUsername());
		}
	}

}
