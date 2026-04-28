
package acme.features.member.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.members.Member;

@Service
public class MemberCampaignShowService extends AbstractService<Member, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberCampaignRepository	repository;

	private Integer						userAccountId;

	private Campaign					campaign;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer campaignId;

		campaignId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		status = super.getRequest().getPrincipal().isAuthenticated() && this.repository.isCampaignIdInProjectWhereUserIsMember(campaignId, this.userAccountId);
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
