
package acme.features.spokesperson.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.campaign.Milestone;
import acme.entities.campaign.MilestoneKind;
import acme.realms.campaign.Spokesperson;

@Service
public class SpokespersonMilestoneDeleteService extends AbstractService<Spokesperson, Milestone> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SpokespersonMilestoneRepository	repository;

	private Milestone						milestone;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int milestoneId;

		milestoneId = super.getRequest().getData("id", int.class);
		this.milestone = this.repository.findMilestoneById(milestoneId);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.milestone != null && this.milestone.getCampaign().getDraftMode() && this.milestone.getCampaign().getSpokesperson().isPrincipal();
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.milestone, "title", "achievements", "effort", "kind");
	}

	@Override
	public void validate() {
		;
	}

	@Override
	public void execute() {
		this.repository.delete(this.milestone);
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(MilestoneKind.class, this.milestone.getKind());
		tuple = super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
		tuple.put("campaignId", this.milestone.getCampaign().getId());
		tuple.put("draftMode", this.milestone.getCampaign().getDraftMode());
		tuple.put("kinds", choices);
	}

}
