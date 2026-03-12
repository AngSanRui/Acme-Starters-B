
package acme.features.any.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.realms.strategy.Fundraiser;

public class AnyFundraiserShowService extends AbstractService<Any, Fundraiser> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyFundraiserRepository	repository;

	private Fundraiser				fundraiser;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.fundraiser = this.repository.findFundraiserById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.fundraiser != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.fundraiser, "bank", "statement", "agent");
		tuple.put("userName", this.fundraiser.getUserAccount().getUsername());

	}

}
