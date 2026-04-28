
package acme.features.member.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.members.Member;

@Service
public class MemberSponsorshipShowService extends AbstractService<Member, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberSponsorshipRepository	repository;

	private Integer						userAccountId;

	private Sponsorship					sponsorship;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer sponsorshipId;

		sponsorshipId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		status = super.getRequest().getPrincipal().isAuthenticated() && this.repository.isSponsorshipInProjectWhereUserIsMember(sponsorshipId, this.userAccountId);
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
