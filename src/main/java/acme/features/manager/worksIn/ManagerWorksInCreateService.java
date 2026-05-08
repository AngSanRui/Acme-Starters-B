
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
public class ManagerWorksInCreateService extends AbstractService<Manager, WorksIn> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerWorksInRepository	repository;

	private Collection<Member>			miembros;

	private WorksIn						relacion;

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
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.project != null && this.project.getDraftMode() && this.project.getManager().getId() == managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));
		this.miembros = this.repository.findAllMembers();

		this.relacion = this.newObject(WorksIn.class);
		this.relacion.setProject(this.project);
	}

	@Override
	public void bind() {
		super.bindObject(this.relacion, "member", "role");
	}

	@Override
	public void validate() {
		super.validateObject(this.relacion);
	}

	@Override
	public void execute() {
		this.repository.save(this.relacion);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		SelectChoices roles;
		SelectChoices members;
		Member miembroAzar = this.miembros.stream().findFirst().get();

		roles = SelectChoices.from(Role.class, Role.INVENTOR);
		members = SelectChoices.from(this.miembros, "userAccount.username", miembroAzar);

		tuple = super.unbindObject(this.relacion, "member", "role");
		tuple.put("members", members);
		tuple.put("roles", roles);
		tuple.put("projectId", this.project.getId());

	}
}
