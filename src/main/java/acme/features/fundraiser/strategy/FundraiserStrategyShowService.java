
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
public class FundraiserStrategyShowService extends AbstractService<Fundraiser, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Collection<Project>				projects;

	private Strategy						strategy;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int strategyId;

		strategyId = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(strategyId);
		if (this.strategy != null) {
			this.projects = this.repository.findProjectsByUserAccountId(this.strategy.getFundraiser().getUserAccount().getId());
			if (this.strategy.getProject() != null && !this.projects.contains(this.strategy.getProject()))
				this.projects.add(this.strategy.getProject());
		}
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Fundraiser.class) && this.strategy != null && this.strategy.getFundraiser().isPrincipal();

		super.setAuthorised(status);
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
