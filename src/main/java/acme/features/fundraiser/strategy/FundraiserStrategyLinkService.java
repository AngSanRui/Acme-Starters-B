
package acme.features.fundraiser.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategies.Strategy;
import acme.realms.strategy.Fundraiser;

@Service
public class FundraiserStrategyLinkService extends AbstractService<Fundraiser, Strategy> {

	//Internal state ---------------------------------------------------------

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Collection<Project>				projects;

	private Strategy						strategy;

	//AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
		this.projects = this.repository.findProjectsByUserAccountId(this.strategy.getFundraiser().getUserAccount().getId());
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.strategy != null && this.strategy.getFundraiser().isPrincipal();
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "project");
	}

	@Override
	public void validate() {
		super.validateObject(this.strategy);
	}

	@Override
	public void execute() {
		this.repository.save(this.strategy);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		SelectChoices choices = null;
		Project visible = null;

		if (this.strategy.getProject() != null)
			visible = this.strategy.getProject();

		choices = SelectChoices.from(this.projects, "title", visible);
		tuple = super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
		tuple.put("fundraiserId", this.strategy.getFundraiser().getId());
		tuple.put("project", choices);
	}

}
