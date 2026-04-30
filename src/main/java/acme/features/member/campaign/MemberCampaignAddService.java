
package acme.features.member.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.projects.Project;
import acme.realms.members.Member;

@Service
public class MemberCampaignAddService extends AbstractService<Member, Campaign> {

	@Autowired
	private MemberCampaignRepository	repository;

	private Integer						userAccountId;

	private Project						project;

	private Campaign					campaign;


	@Override
	public void load() {
		int campaignId = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignByCampaignId(campaignId);

	}

	@Override
	public void authorise() {
		boolean status;
		Integer campaignId;

		campaignId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		status = this.campaign.getSpokesperson().isPrincipal() && super.getRequest().getPrincipal().isAuthenticated() && this.repository.findProjectWithUserAccount(campaignId).contains(this.project)
			&& this.repository.isCampaignIdInProjectWhereUserIsMember(campaignId, this.userAccountId);
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.campaign);
	}

	@Override
	public void execute() {
		this.repository.save(this.campaign);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

}
