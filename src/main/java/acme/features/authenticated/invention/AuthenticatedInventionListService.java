
package acme.features.authenticated.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;

@Service
public class AuthenticatedInventionListService extends AbstractService<Authenticated, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInventionRepository	repository;

	private Collection<Invention>				inventions;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		this.inventions = this.repository.findAllPublishedInvention();
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.getRequest().getPrincipal().isAuthenticated();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "name", "ticker", "startMoment", "endMoment");
	}

}
