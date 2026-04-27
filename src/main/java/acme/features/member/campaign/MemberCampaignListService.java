
package acme.features.member.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.members.Member;

@Service
public class MemberCampaignListService extends AbstractService<Member, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberCampaignRepository	repository;

	private Collection<Campaign>		campaigns;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().isAuthenticated();
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
