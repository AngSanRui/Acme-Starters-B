
package acme.features.manager.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.managers.Manager;

@Service
public class ManagerSponsorshipShowService extends AbstractService<Manager, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerSponsorshipRepository	repository;

	private Integer							userAccountId;

	private Integer							managerId;

	private Sponsorship						sponsorship;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer sponsorshipId;

		sponsorshipId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		status = super.getRequest().getPrincipal().isAuthenticated() && this.sponsorship.getProject().getManager().getId() == this.managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer sponsorshipId;

		sponsorshipId = super.getRequest().getData("id", int.class);
		this.sponsorship = this.repository.findSponsorshipBySponsorshipId(sponsorshipId);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "totalMoney", "draftMode");
	}

}
