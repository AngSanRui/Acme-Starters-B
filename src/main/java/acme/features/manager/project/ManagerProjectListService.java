
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;

@Service
public class ManagerProjectListService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Collection<Project>			projects;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class);
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		this.projects = this.repository.findProjectByManagerId(this.managerId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.projects, "ticker", "title", "startMoment", "endMoment");
	}

}
