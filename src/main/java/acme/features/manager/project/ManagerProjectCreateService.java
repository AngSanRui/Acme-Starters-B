
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;

@Service
public class ManagerProjectCreateService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository	repository;

	private Project						project;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = this.getRequest().getPrincipal().hasRealmOfType(Manager.class);

		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Manager manager;

		manager = (Manager) super.getRequest().getPrincipal().getActiveRealm();

		this.project = super.newObject(Project.class);
		this.project.setDraftMode(true);
		this.project.setManager(manager);
	}

	@Override
	public void bind() {
		super.bindObject(this.project, "ticker", "title", "description", "keyWords", "startMoment", "endMoment", "moreInfo");

	}

	@Override
	public void validate() {
		super.validateObject(this.project);
	}

	@Override
	public void execute() {
		this.repository.save(this.project);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "ticker", "title", "description", "keyWords", "startMoment", "endMoment", "moreInfo");

	}

}
