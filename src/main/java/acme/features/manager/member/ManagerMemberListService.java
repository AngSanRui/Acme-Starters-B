
package acme.features.manager.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Service
public class ManagerMemberListService extends AbstractService<Manager, Member> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerMemberRepository	repository;

	private Integer					userAccountId;

	private Integer					managerId;

	private Project					project;

	private Collection<Member>		members;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.project.getManager().getId() == this.managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		//TODO
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.members, null);
	}

}
