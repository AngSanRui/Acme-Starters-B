
package acme.features.fundraiser.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.Tactic;
import acme.entities.strategies.TacticKind;
import acme.realms.strategy.Fundraiser;

@Service
public class FundraiserTacticCreateService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	private FundraiserTacticRepository	repository;

	private Strategy					strategy;
	private Tactic						tactic;


	@Override
	public void load() {

		int strategyId = super.getRequest().getData("strategyId", int.class);
		this.strategy = this.repository.findStrategyById(strategyId);

		this.tactic = super.newObject(Tactic.class);
		this.tactic.setStrategy(this.strategy);
		this.tactic.setDraftMode(true);
	}

	@Override
	public void authorise() {
		boolean status;

		int strategyId = super.getRequest().getData("strategyId", int.class);
		this.strategy = this.repository.findStrategyById(strategyId);

		status = this.getRequest().getPrincipal().hasRealmOfType(Fundraiser.class) && this.strategy != null && this.tactic.getStrategy().getDraftMode() && this.tactic.getStrategy().getFundraiser().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.tactic);
	}

	@Override
	public void execute() {
		this.repository.save(this.tactic);
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		choices = SelectChoices.from(TacticKind.class, this.tactic.getKind());
		Tuple tuple = super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "kind", "draftMode");

		tuple.put("strategyId", super.getRequest().getData("strategyId", int.class));
		tuple.put("kind", choices);
		tuple.put("readonly", !this.strategy.getDraftMode());
	}
}
