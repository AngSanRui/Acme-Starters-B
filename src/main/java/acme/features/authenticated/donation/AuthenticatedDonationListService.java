
package acme.features.authenticated.donation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Donation;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.sponsorship.Sponsor;

@Service
public class AuthenticatedDonationListService extends AbstractService<Authenticated, Donation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedDonationRepository	repository;

	private Sponsorship						sponsorship;
	private Collection<Donation>			donations;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int sponsorshipId;
		int userId;
		int sponsorId;
		userId = super.getRequest().getPrincipal().getAccountId();
		sponsorId = this.repository.findSponsorByAccountId(userId);
		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		this.sponsorship = this.repository.findSponsorshipById(sponsorshipId);
		this.donations = this.repository.findDonationsBySponsorshipId(sponsorshipId);
	}

	@Override
	public void authorise() {
		boolean status;

		//status = this.sponsorship != null && !this.sponsorship.getDraftMode();
		status = super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class);

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.donations, "name", "notes", "money", "kind");

	}

}
