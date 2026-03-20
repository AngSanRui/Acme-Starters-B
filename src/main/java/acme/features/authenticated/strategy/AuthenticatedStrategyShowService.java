
package acme.features.authenticated.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.realms.strategy.Fundraiser;

@Service
public class AuthenticatedStrategyShowService extends AbstractService<Authenticated, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedStrategyRepository	repository;

	private Strategy						strategy;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int fundraiserId = this.repository.findFundraiserByAccountId(super.getRequest().getPrincipal().getAccountId());

		status = this.strategy != null && super.getRequest().getPrincipal().hasRealmOfType(Fundraiser.class) && this.strategy.getFundraiser().getId() == fundraiserId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
		int strategyId = this.strategy.getId();

		Double expectedPercentage = this.repository.expectedPercentageByStrategyId(strategyId);
		Double monthsActive = this.strategy.getMonthsActive();

		tuple.put("expectedPercentage", expectedPercentage);
		tuple.put("monthsActive", monthsActive);
		tuple.put("fundraiserId", this.strategy.getFundraiser().getId());
		tuple.put("readonly", true);

	}
}
