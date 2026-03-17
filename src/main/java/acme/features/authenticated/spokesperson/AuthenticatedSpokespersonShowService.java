
package acme.features.authenticated.spokesperson;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.realms.campaign.Spokesperson;

public class AuthenticatedSpokespersonShowService extends AbstractService<Authenticated, Spokesperson> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuthenticatedSpokespersonRepository	repository;

	private Spokesperson						spokesperson;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("spokespersonId", int.class);
		this.spokesperson = this.repository.findSpokespersonById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.spokesperson != null && this.getRequest().getPrincipal().isAuthenticated();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.spokesperson, "cv", "achievements", "licensed");
	}

}
