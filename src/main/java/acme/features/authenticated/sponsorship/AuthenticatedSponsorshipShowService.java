
package acme.features.authenticated.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.sponsorship.Sponsor;

@Service
public class AuthenticatedSponsorshipShowService extends AbstractService<Authenticated, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSponsorshipRepository	repository;

	private Sponsorship							sponsorship;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.sponsorship = this.repository.findSponsorshipById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int sponsorId = this.repository.findSponsorByAccountId(super.getRequest().getPrincipal().getAccountId());
		status = this.sponsorship != null && super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class) && this.sponsorship.getSponsor().getId() == sponsorId;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "sponsor", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "totalMoney", "monthsActive");
	}
}
