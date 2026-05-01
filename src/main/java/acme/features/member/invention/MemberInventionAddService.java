
package acme.features.member.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.realms.members.Member;

@Service
public class MemberInventionAddService extends AbstractService<Member, Invention> {

	@Autowired
	private MemberInventionRepository	repository;

	private Integer						userAccountId;

	private Project						project;

	private Invention					invention;


	@Override
	public void load() {
		int inventionId = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionByInventionId(inventionId);

	}

	@Override
	public void authorise() {
		boolean status;
		Integer inventionId;

		inventionId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		status = this.invention.getInventor().isPrincipal() && super.getRequest().getPrincipal().isAuthenticated() && this.repository.findProjectWithUserAccount(this.userAccountId).contains(this.project)
			&& this.repository.isInventionInProjectWhereUserIsMember(inventionId, this.userAccountId);
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.invention);
	}

	@Override
	public void execute() {
		this.repository.save(this.invention);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

}
