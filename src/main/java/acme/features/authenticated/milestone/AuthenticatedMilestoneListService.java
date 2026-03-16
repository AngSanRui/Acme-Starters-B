
package acme.features.authenticated.milestone;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.Milestone;
import acme.realms.campaign.Spokesperson;

@Service
public class AuthenticatedMilestoneListService extends AbstractService<Authenticated, Milestone> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMilestoneRepository	repository;
	private Campaign							campaign;
	private Collection<Milestone>				milestones;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int campaignId;

		campaignId = super.getRequest().getData("campaignId", int.class);
		this.campaign = this.repository.findCampaignById(campaignId);
		this.milestones = this.repository.findMilestoneByCampaignId(campaignId);
	}

	@Override
	public void authorise() {
		boolean status;
		int userId;
		int spokespersonId;

		userId = super.getRequest().getPrincipal().getAccountId();
		spokespersonId = this.repository.findSpokespersonByAccountId(userId);
		status = this.campaign != null && this.campaign.getSpokesperson().getId() == spokespersonId && super.getRequest().getPrincipal().hasRealmOfType(Spokesperson.class);

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "achievements", "effort", "kind");

	}
}
