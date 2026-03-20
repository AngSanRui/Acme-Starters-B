
package acme.features.authenticated.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.realms.strategy.Fundraiser;

@Service
public class AuthenticatedStrategyListService extends AbstractService<Authenticated, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedStrategyRepository	repository;

	private Collection<Strategy>			strategies;

	// AbstractService interface -------------------------------------------


	@SuppressWarnings("unused")
	@Override
	public void load() {
		int userId = super.getRequest().getPrincipal().getAccountId();
		int fundraiserId = this.repository.findFundraiserByAccountId(userId);
		this.strategies = this.repository.findAllPublishedStrategies();
	}

	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRealmOfType(Fundraiser.class);
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "fundraiser", "ticker", "name", "description");

	}

}
