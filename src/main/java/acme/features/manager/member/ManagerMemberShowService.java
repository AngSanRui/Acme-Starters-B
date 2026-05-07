
package acme.features.manager.member;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Service
public class ManagerMemberShowService extends AbstractService<Manager, Member> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerMemberRepository	repository;

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
		int managerId = this.repository.findManagerIdByAccountId(userAccountId);

		Collection<Member> members = new ArrayList<Member>();
		Collection<Project> proyectos = this.repository.findAllProjectsByManagerId(managerId);

		for (Project p : proyectos)
			members.addAll(this.repository.findAllMembersByProjectId(p.getId()));

		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && //
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
