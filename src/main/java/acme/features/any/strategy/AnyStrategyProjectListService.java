
package acme.features.any.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;

@Service
public class AnyStrategyProjectListService extends AbstractService<Any, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyStrategyRepository	repository;

	private Collection<Strategy>	strategys;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer projectId;

		projectId = this.getRequest().getData("projectId", int.class);
		status = !this.repository.findProjectByProjectId(projectId).getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = this.getRequest().getData("projectId", int.class);
		this.strategys = this.repository.findStrategyByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategys, "ticker", "name", "startMoment", "endMoment");
	}
}
