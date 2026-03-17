
package acme.features.authenticated.inventor;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.realms.inventor.Inventor;

public class AuthenticatedInventorShowService extends AbstractService<Authenticated, Inventor> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuthenticatedInventorRepository	repository;

	private Inventor						inventor;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("inventorId", int.class);
		this.inventor = this.repository.findInventorById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.inventor != null && this.getRequest().getPrincipal().isAuthenticated();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.inventor, "bio", "keyWords", "licensed");
	}

}
