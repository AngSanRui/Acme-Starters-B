
package acme.features.authenticated.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;

@Service
public class AuthenticatedInventionShowService extends AbstractService<Authenticated, Invention> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuthenticatedInventionRepository	repository;

	private Invention							invention;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.invention != null && !this.invention.getDraftMode() && this.getRequest().getPrincipal().isAuthenticated();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "inventor", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "cost");
	}
}
