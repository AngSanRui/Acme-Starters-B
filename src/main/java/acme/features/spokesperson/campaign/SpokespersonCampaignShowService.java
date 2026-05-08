
package acme.features.spokesperson.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.projects.Project;
import acme.realms.campaign.Spokesperson;

@Service
public class SpokespersonCampaignShowService extends AbstractService<Spokesperson, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private Collection<Project>				projects;

	private Campaign						campaign;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
		this.projects = this.repository.findProjectsByUserAccountId(this.campaign.getSpokesperson().getUserAccount().getId());
		if (this.campaign.getProject() != null)
			this.projects.add(this.campaign.getProject());
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
		Tuple tuple;
		SelectChoices choices;

		Project visible = null;

		if (this.campaign.getProject() != null)
			visible = this.campaign.getProject();

		choices = SelectChoices.from(this.projects, "title", visible);

		tuple = super.unbindObject(this.campaign, "spokesperson", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "effort");
		tuple.put("project", choices);
	}

}
