
package acme.features.spokesperson.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.campaign.Spokesperson;

@Service
public class SpokespersonCampaignListService extends AbstractService<Spokesperson, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private Collection<Campaign>			campaigns;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Spokesperson.class);
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int userId;
		int spokespersonId;

		userId = super.getRequest().getPrincipal().getAccountId();
		spokespersonId = this.repository.findSpokespersonByAccountId(userId);
		this.campaigns = this.repository.findCampaignsBySpokespersonId(spokespersonId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "spokesperson", "ticker", "name", "startMoment", "endMoment");
	}

}
