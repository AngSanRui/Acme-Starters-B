/*
 * EmployerJobListService.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.sponsor.donation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorship.Donation;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.sponsorship.Sponsor;

@Service
public class SponsorDonationListService extends AbstractService<Sponsor, Donation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorDonationRepository	repository;

	private Collection<Donation>		donations;
	private Sponsorship					sponsorship;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		status = this.sponsorship != null && super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class) && this.sponsorship.getSponsor().isPrincipal();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int sponsorshipId;
		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		this.sponsorship = this.repository.findSponsorshipById(sponsorshipId);
		this.donations = this.repository.findDonationsBySponsorshipId(sponsorshipId);
	}

	@Override
	public void unbind() {
		boolean showCreate;
		super.unbindObjects(this.donations, "name", "notes", "money", "kind");

		showCreate = this.sponsorship.getDraftMode() && this.sponsorship.getSponsor().isPrincipal();
		super.unbindGlobal("sponsorshipId", this.sponsorship.getId());
		super.unbindGlobal("showCreate", showCreate);
	}

}
