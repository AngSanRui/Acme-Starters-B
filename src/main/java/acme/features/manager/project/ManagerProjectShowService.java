
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;

@Service
public class ManagerProjectShowService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Project						project;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.project != null && this.project.getManager().getId() == this.managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "ticker", "title", "description", "keyWords", "startMoment", "endMoment", "moreInfo", "draftMode");
	}

}
