
package acme.features.manager.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.managers.Manager;
import acme.realms.members.Member;

@Service
public class ManagerInventionShowService extends AbstractService<Manager, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerInventionRepository	repository;

	private Integer						userAccountId;

	private Integer						managerId;

	private Invention					invention;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		status = super.getRequest().getPrincipal().hasRealmOfType(Member.class) && this.invention.getProject().getManager().getId() == this.managerId;
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
