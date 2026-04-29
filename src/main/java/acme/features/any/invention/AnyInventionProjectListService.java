
package acme.features.any.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;

@Service
public class AnyInventionProjectListService extends AbstractService<Any, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyInventionRepository	repository;

	private Collection<Invention>	inventions;

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
		this.inventions = this.repository.findInventionByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment");
	}

}
