
package acme.features.fundraiser.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.realms.strategy.Fundraiser;

@Service
public class FundraiserStrategyShowService extends AbstractService<Fundraiser, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Strategy						strategy;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int fundraiserId;

		fundraiserId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.strategy = this.repository.findStrategyById(fundraiserId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = true;
		//super.getRequest().getPrincipal().hasRealm(this.whine.getCustomer());

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
