
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;

@Service
public class AnySponsorshipProjectListService extends AbstractService<Any, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnySponsorshipRepository	repository;

	private Collection<Sponsorship>		sponsorships;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer projectId;

		projectId = this.getRequest().getData("projectId", int.class);
		status = this.repository.findProjectByProjectId(projectId) != null && !this.repository.findProjectByProjectId(projectId).getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = this.getRequest().getData("projectId", int.class);
		this.sponsorships = this.repository.findSponsorshipByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "ticker", "name", "startMoment", "endMoment");
	}

}
