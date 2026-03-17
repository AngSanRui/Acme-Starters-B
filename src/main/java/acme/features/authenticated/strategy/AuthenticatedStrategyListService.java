
package acme.features.authenticated.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;

@Service
public class AuthenticatedStrategyListService extends AbstractService<Authenticated, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedStrategyRepository	repository;

	private Collection<Strategy>			strategies;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		this.strategies = this.repository.findAllPublishedStrategies();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "description");

	}

}
