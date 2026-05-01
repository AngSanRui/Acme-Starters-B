
package acme.features.member.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategies.Strategy;
import acme.realms.members.Member;

@Service
public class MemberStrategyAddService extends AbstractService<Member, Strategy> {

	@Autowired
	private MemberStrategyRepository	repository;

	private Integer						userAccountId;

	private Project						project;

	private Strategy					strategy;


	@Override
	public void load() {

		int strategyId = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyByStrategyId(strategyId);

	}

	@Override
	public void authorise() {
		boolean status;
		Integer strategyId;

		strategyId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));
		status = this.strategy.getFundraiser().isPrincipal() && super.getRequest().getPrincipal().isAuthenticated() && this.repository.findProjectWithUserAccount(this.userAccountId).contains(this.project)
			&& this.repository.isStrategyIdInProjectWhereUserIsMember(strategyId, this.userAccountId);
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
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
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

}
