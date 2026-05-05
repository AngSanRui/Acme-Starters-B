
package acme.features.manager.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Service
public class ManagerMemberListService extends AbstractService<Manager, Member> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerMemberRepository	repository;

	private Project					project;

	//private Collection<UserAccount>	userAccounts;

	private Collection<Member>		members;
	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int userAccountId;
		int managerId;

		userAccountId = super.getRequest().getPrincipal().getAccountId();
		managerId = this.repository.findManagerIdByAccountId(userAccountId);
		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));
		//Chequea que el rol sea manager, que el proyecto exista y que el proyecto pertenezca al manager
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.project != null && this.project.getManager().getId() == managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		//this.userAccounts = this.repository.findAllUserAccountsByProjectId(super.getRequest().getData("projectId", int.class));
		this.members = this.repository.findAllMembersByProjectId(super.getRequest().getData("projectId", int.class));

	}

	@Override
	public void unbind() {
		//		super.unbindObjects(this.userAccounts, "username");
		//	super.unbindGlobal("username", );
		//		super.unbindGlobal("auditReportId", this.auditReport.getId());
		//		super.unbindGlobal("showCreate", showCreate);
		for (Member member : this.members) {
			Tuple tuple;

			//			tuple = super.unbindObject(worksFor, "roles", "contractor.description", "contractor.moreInfo");
			//			tuple.put("contractor", worksFor.getContractor().getName());

			tuple = super.unbindObject(member, "userAccount.username");
			tuple.put("userAccount", member.getUserAccount().getUsername());
		}
	}

}
