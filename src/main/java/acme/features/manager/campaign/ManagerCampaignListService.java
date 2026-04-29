
package acme.features.manager.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.projects.Project;
import acme.realms.managers.Manager;

@Service
public class ManagerCampaignListService extends AbstractService<Manager, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerCampaignRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Project						project;

	private Collection<Campaign>		campaigns;

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
		this.campaigns = this.repository.findCampaignsByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment");
	}

}
