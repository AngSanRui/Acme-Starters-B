
package acme.features.authenticated.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Authenticated;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Donation;
import acme.entities.sponsorship.DonationKind;
import acme.realms.sponsorship.Sponsor;

@Service
public class AuthenticatedDonationShowService extends AbstractService<Authenticated, Donation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedDonationRepository	repository;

	private Donation						donation;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.donation = this.repository.findDonationById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		int sponsorId = this.repository.findSponsorByAccountId(super.getRequest().getPrincipal().getAccountId());

		//status = this.donation != null && !this.donation.getSponsorship().getDraftMode();
		status = super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class) && this.donation.getSponsorship().getSponsor().getId() == sponsorId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(DonationKind.class, this.donation.getKind());
		tuple = super.unbindObject(this.donation, "name", "notes", "money", "kind");

		tuple.put("statuses", choices);
	}
}
