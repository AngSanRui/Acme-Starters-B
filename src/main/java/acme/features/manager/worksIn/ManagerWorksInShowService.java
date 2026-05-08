
package acme.features.manager.worksIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.projects.Role;
import acme.entities.projects.WorksIn;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Service
public class ManagerWorksInShowService extends AbstractService<Manager, WorksIn> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerWorksInRepository	repository;

	private WorksIn						relacion;

	private Collection<Member>			miembros;

	private Project						project;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int userAccountId;
		int managerId;

		userAccountId = super.getRequest().getPrincipal().getAccountId();
		managerId = this.repository.findManagerIdByAccountId(userAccountId);
		//Chequea que el rol sea manager, que el proyecto exista y que el proyecto pertenezca al manager
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.relacion != null && this.project.getManager().getId() == managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer worksIn = super.getRequest().getData("id", Integer.class);
		this.relacion = this.repository.findWorksInById(worksIn);
		this.project = this.relacion.getProject();
		this.miembros = this.repository.findMemberProjectById(this.project.getId());
	}

	@Override
	public void unbind() {
		//Tuple tuple;
		//tuple = super.unbindObject(this.relacion, "role");
		//tuple.put("member", this.relacion.getMember().getUserAccount().getUsername());
		//tuple.put("id", this.relacion.getId());

		Tuple tuple;
		SelectChoices roles;
		SelectChoices members;

		roles = SelectChoices.from(Role.class, this.relacion.getRole());
		members = SelectChoices.from(this.miembros, "userAccount.username", this.relacion.getMember());

		tuple = super.unbindObject(this.relacion, "member", "role");
		tuple.put("members", members);
		tuple.put("roles", roles);
		//tuple.put("projectId", this.project.getId());
	}

}
