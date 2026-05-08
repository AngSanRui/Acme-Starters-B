
package acme.features.manager.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.managers.Manager;

@Service
public class ManagerCampaignShowService extends AbstractService<Manager, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerCampaignRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Campaign					campaign;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.campaign != null && this.managerId == this.campaign.getProject().getManager().getId();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer campaignId;

		campaignId = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignByCampaignId(campaignId);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "spokesperson", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "effort");
	}

}
