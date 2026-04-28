
package acme.features.member.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.members.Member;

@Service
public class MemberProjectListService extends AbstractService<Member, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberProjectRepository	repository;

	private Integer					userAccountId;

	private Collection<Project>		projects;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().isAuthenticated();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.projects = this.repository.findProjectWithUserAccount(this.userAccountId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.projects, "ticker", "title", "startMoment", "endMoment");
	}

}
