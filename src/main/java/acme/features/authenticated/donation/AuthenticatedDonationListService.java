
package acme.features.authenticated.donation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Donation;
import acme.entities.sponsorship.Sponsorship;

@Service
public class AuthenticatedDonationListService extends AbstractService<Authenticated, Donation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedDonationRepository	repository;

	@SuppressWarnings("unused")
	private Sponsorship						sponsorship;
	private Collection<Donation>			donations;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int sponsorshipId;
		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		this.sponsorship = this.repository.findSponsorshipById(sponsorshipId);
		this.donations = this.repository.findDonationsBySponsorshipId(sponsorshipId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.sponsorship != null && !this.sponsorship.getDraftMode() && super.getRequest().getPrincipal().isAuthenticated();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.donations, "name", "notes", "money", "kind");

	}

}
