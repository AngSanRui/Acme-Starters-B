
package acme.features.member.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.realms.members.Member;

@Service
public class MemberInventionListService extends AbstractService<Member, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberInventionRepository	repository;

	private Integer						userAccountId;

	private Project						project;

	private Collection<Invention>		inventions;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.project = this.repository.findProjectById(super.getRequest().getData("projectId", int.class));
		status = super.getRequest().getPrincipal().isAuthenticated() && this.repository.findProjectWithUserAccount(this.userAccountId).contains(this.project);
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		this.inventions = this.repository.findInventionsByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment");
	}

}
