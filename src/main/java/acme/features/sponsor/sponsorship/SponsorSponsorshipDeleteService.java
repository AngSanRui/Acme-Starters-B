/*
 * EmployerJobDeleteService.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Donation;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.sponsorship.Sponsor;

@Service
public class SponsorSponsorshipDeleteService extends AbstractService<Sponsor, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorSponsorshipRepository	repository;

	private Sponsorship						sponsorship;

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

		status = this.sponsorship != null && this.sponsorship.getDraftMode() && this.sponsorship.getSponsor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		;
	}

	@Override
	public void execute() {
		Collection<Donation> sponsorships;

		sponsorships = this.repository.findDonationsBySponsorshipId(this.sponsorship.getId());
		this.repository.deleteAll(sponsorships);
		this.repository.delete(this.sponsorship);
	}

	@Override
	public void unbind() {
		int sponsorId;
		Tuple tuple;

		sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();

		tuple = super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
	}

}
