
package acme.features.inventor.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.realms.inventor.Inventor;

@Service
public class InventorInventionShowService extends AbstractService<Inventor, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InventorInventionRepository	repository;

	private Collection<Project>			projects;

	private Invention					invention;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int inventionId;

		inventionId = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionsById(inventionId);
		if (this.invention != null) {
			this.projects = this.repository.findProjectsByUserAccountId(this.invention.getInventor().getUserAccount().getId());
			if (this.invention.getProject() != null && !this.projects.contains(this.invention.getProject()))
				this.projects.add(this.invention.getProject());
		}
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Inventor.class) && this.invention != null && this.invention.getInventor().isPrincipal();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		SelectChoices choices = null;
		Project visible = null;

		if (this.invention.getProject() != null)
			visible = this.invention.getProject();

		choices = SelectChoices.from(this.projects, "title", visible);

		Tuple tuple;
		tuple = super.unbindObject(this.invention, //
			"ticker", "startMoment", "endMoment", "name", //
			"description", "moreInfo", "draftMode", "monthsActive", "cost");
		tuple.put("project", choices);
	}
}
