
package acme.features.manager.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;

@Service
public class ManagerInventionListService extends AbstractService<Manager, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerInventionRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Project						project;

	private Collection<Invention>		inventions;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));
		status = this.project != null && super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.project.getManager().getId() == this.managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		this.inventions = this.repository.findInventionsByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment");
	}

}
