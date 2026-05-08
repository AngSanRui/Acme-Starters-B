
package acme.features.any.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;

@Service
public class AnyCampaignProjectListService extends AbstractService<Any, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyCampaignRepository	repository;

	private Collection<Campaign>	campaigns;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer projectId;

		projectId = this.getRequest().getData("projectId", int.class);
		status = this.repository.findProjectByProjectId(projectId) != null && !this.repository.findProjectByProjectId(projectId).getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = this.getRequest().getData("projectId", int.class);
		this.campaigns = this.repository.findCampaignByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment");
	}

}
