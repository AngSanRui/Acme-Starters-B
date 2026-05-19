
package acme.features.authenticated.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;

@Service
public class AuthenticatedSponsorshipListService extends AbstractService<Authenticated, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSponsorshipRepository	repository;

	private Collection<Sponsorship>				sponsorships;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		status = this.getRequest().getPrincipal().isAuthenticated();
		super.setAuthorised(status);
	}

	@SuppressWarnings("unused")
	@Override
	public void load() {
		int userId;
		userId = super.getRequest().getPrincipal().getAccountId();
		this.sponsorships = this.repository.findAllPublishedSponsorships();
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "sponsor", "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
