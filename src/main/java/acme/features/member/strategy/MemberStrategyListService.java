
package acme.features.member.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategies.Strategy;
import acme.realms.members.Member;

@Service
public class MemberStrategyListService extends AbstractService<Member, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberStrategyRepository	repository;

	private Integer						userAccountId;

	private Project						project;

	private Collection<Strategy>		strategies;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));
		status = super.getRequest().getPrincipal().isAuthenticated() && this.repository.findProjectWithUserAccount(this.userAccountId).contains(this.project);
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		this.strategies = this.repository.findStrategysByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment");
	}
}
