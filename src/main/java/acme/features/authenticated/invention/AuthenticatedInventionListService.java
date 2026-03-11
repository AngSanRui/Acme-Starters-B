
package acme.features.authenticated.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.inventor.Inventor;

@Service
public class AuthenticatedInventionListService extends AbstractService<Authenticated, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInventionRepository	repository;

	private Collection<Invention>				inventions;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int userId;
		int inventorId;
		userId = super.getRequest().getPrincipal().getAccountId();
		inventorId = this.repository.findInventorByAccountId(userId);
		this.inventions = this.repository.findInventionsByInventorId(inventorId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Inventor.class);
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment");
	}
}
