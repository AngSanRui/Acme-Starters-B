
package acme.features.spokesperson.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.Milestone;
import acme.entities.campaign.MilestoneKind;
import acme.realms.campaign.Spokesperson;

@Service
public class SpokespersonMilestoneCreateService extends AbstractService<Spokesperson, Milestone> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SpokespersonMilestoneRepository	repository;

	private Milestone						milestone;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int campaignId;
		Campaign campaign;

		campaignId = super.getRequest().getData("campaignId", int.class);
		campaign = this.repository.findCampaignById(campaignId);

		this.milestone = super.newObject(Milestone.class);
		this.milestone.setCampaign(campaign);
	}

	@Override
	public void authorise() {
		boolean status;
		int campaignId;
		Campaign campaign;

		campaignId = super.getRequest().getData("campaignId", int.class);
		campaign = this.repository.findCampaignById(campaignId);

		status = super.getRequest().getPrincipal().hasRealmOfType(Spokesperson.class) && campaign != null && this.milestone.getCampaign().getDraftMode() && this.milestone.getCampaign().getSpokesperson().isPrincipal();
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.milestone, "title", "achievements", "effort", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.milestone);
	}

	@Override
	public void execute() {
		this.repository.save(this.milestone);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		tuple = super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
		SelectChoices kinds;

		kinds = SelectChoices.from(MilestoneKind.class, MilestoneKind.CONVERSION);

		tuple.put("campaignId", super.getRequest().getData("campaignId", int.class));
		tuple.put("draftMode", this.milestone.getCampaign().getDraftMode());
		tuple.put("kinds", kinds);
	}

}
