
package acme.features.authenticated.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.sponsorship.Sponsor;

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
		status = super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class);
		super.setAuthorised(status);
	}

	@SuppressWarnings("unused")
	@Override
	public void load() {
		int userId;
		int sponsorId;
		userId = super.getRequest().getPrincipal().getAccountId();
		sponsorId = this.repository.findSponsorByAccountId(userId);
		this.sponsorships = this.repository.findAllPublishedSponsorships();
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "sponsor", "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
