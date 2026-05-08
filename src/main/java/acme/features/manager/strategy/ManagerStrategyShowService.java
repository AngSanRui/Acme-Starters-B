
package acme.features.manager.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.realms.managers.Manager;

@Service
public class ManagerStrategyShowService extends AbstractService<Manager, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerStrategyRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Strategy					strategy;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.strategy.getProject().getManager().getId() == this.managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer strategyId;

		strategyId = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyByStrategyId(strategyId);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
	}

}
