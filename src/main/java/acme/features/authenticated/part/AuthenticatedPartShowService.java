
package acme.features.authenticated.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Authenticated;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.invention.Part;
import acme.entities.invention.PartKind;

@Service
public class AuthenticatedPartShowService extends AbstractService<Authenticated, Part> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedPartRepository	repository;

	private Part						part;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.part = this.repository.findPartById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.part != null && this.part.getInvention().getInventor().getId() == this.repository.findInventorByAccountId(super.getRequest().getPrincipal().getAccountId());
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(PartKind.class, this.part.getKind());
		tuple = super.unbindObject(this.part, "name", "description", "cost", "kind");

		tuple.put("statuses", choices);
	}
}
