
package acme.features.member.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.members.Member;

@Service
public class MemberInventionShowService extends AbstractService<Member, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberInventionRepository	repository;

	private Integer						userAccountId;

	private Invention					invention;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer inventionId;

		inventionId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		status = super.getRequest().getPrincipal().isAuthenticated() && this.repository.isInventionInProjectWhereUserIsMember(inventionId, this.userAccountId);
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer inventionId;

		inventionId = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionByInventionId(inventionId);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "inventor", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "cost");
	}
}
