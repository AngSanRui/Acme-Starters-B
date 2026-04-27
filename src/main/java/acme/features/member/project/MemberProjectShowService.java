
package acme.features.member.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.members.Member;

@Service
public class MemberProjectShowService extends AbstractService<Member, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberProjectRepository	repository;

	private Project					project;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		//Se necesita algo para mostrar?
		status = super.getRequest().getPrincipal().isAuthenticated();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "ticker", "title", "description", "keyWords", "startMoment", "endMoment", "moreInfo", "draftMode");
	}

}
