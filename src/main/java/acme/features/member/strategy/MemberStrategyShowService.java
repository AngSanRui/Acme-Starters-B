
package acme.features.member.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.realms.members.Member;

@Service
public class MemberStrategyShowService extends AbstractService<Member, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberStrategyRepository	repository;

	private Strategy					strategy;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().isAuthenticated();
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
