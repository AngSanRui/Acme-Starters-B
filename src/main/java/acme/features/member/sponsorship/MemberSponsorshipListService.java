
package acme.features.member.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.members.Member;

@Service
public class MemberSponsorshipListService extends AbstractService<Member, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberSponsorshipRepository	repository;

	private Collection<Sponsorship>		sponsorships;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().isAuthenticated();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		this.sponsorships = this.repository.findSponsorshipsByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "ticker", "name", "startMoment", "endMoment");
	}
}
