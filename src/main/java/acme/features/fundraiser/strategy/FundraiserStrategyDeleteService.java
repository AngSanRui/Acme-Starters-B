
package acme.features.fundraiser.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.Tactic;
import acme.realms.strategy.Fundraiser;

@Service
public class FundraiserStrategyDeleteService extends AbstractService<Fundraiser, Strategy> {

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Strategy						strategy;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		boolean status = false;

		if (this.strategy != null) {
			int userAccountId;
			Fundraiser fundraiser;

			userAccountId = super.getRequest().getPrincipal().getAccountId();
			fundraiser = this.repository.findFundraiserByUserAccountId(userAccountId);

			status = this.strategy.getDraftMode() && this.strategy.getFundraiser().getId() == fundraiser.getId();
		}

		super.setAuthorised(status);
	}

	@Override
	public void bind() {

	}

	@Override
	public void validate() {

	}

	@Override
	public void execute() {
		Collection<Tactic> tactics = this.repository.findAllTacticsByStrategyId(this.strategy.getId());
		this.repository.deleteAll(tactics);

		this.repository.delete(this.strategy);
	}

	@Override
	public void unbind() {
	}
}
