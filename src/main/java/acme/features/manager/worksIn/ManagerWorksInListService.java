
package acme.features.manager.worksIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.projects.WorksIn;
import acme.realms.managers.Manager;

@Service
public class ManagerWorksInListService extends AbstractService<Manager, WorksIn> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerWorksInRepository	repository;

	private Collection<WorksIn>			relaciones;

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
		status = this.project != null && super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.project.getManager().getId() == managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId = super.getRequest().getData("projectId", Integer.class);
		this.project = this.repository.findProjectById(projectId);
		this.relaciones = this.repository.findWorksInProjectById(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.relaciones, "member.userAccount.username", "role");
	}

}
