
package acme.features.manager.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategies.Strategy;
import acme.realms.managers.Manager;

@Service
public class ManagerStrategyListService extends AbstractService<Manager, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerStrategyRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Project						project;

	private Collection<Strategy>		strategies;

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
		Integer projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		this.strategies = this.repository.findStrategysByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment");
	}
}
