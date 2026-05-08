
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.strategies.Strategy;
import acme.realms.managers.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository	repository;

	private Project						project;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = this.project != null && this.project.getDraftMode() && this.project.getManager().isPrincipal();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void bind() {
		super.bindObject(this.project, "ticker", "title", "description", "keyWords", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		;
	}

	@Override
	public void execute() {
		Collection<Invention> inventions = this.repository.findInventionsByProjectId(this.project.getId());
		if (!inventions.isEmpty())
			for (Invention i : inventions) {
				i.setProject(null);
				this.repository.save(i);
			}

		Collection<Campaign> campaigns = this.repository.findCampaignsByProjectId(this.project.getId());
		if (!campaigns.isEmpty())
			for (Campaign c : campaigns) {
				c.setProject(null);
				this.repository.save(c);
			}

		Collection<Sponsorship> sponsorships = this.repository.findSponsorshipsByProjectId(this.project.getId());
		if (!sponsorships.isEmpty())
			for (Sponsorship s : sponsorships) {
				s.setProject(null);
				this.repository.save(s);
			}

		Collection<AuditReport> auditReports = this.repository.findAuditReportsByProjectId(this.project.getId());
		if (!auditReports.isEmpty())
			for (AuditReport a : auditReports) {
				a.setProject(null);
				this.repository.save(a);
			}

		Collection<Strategy> strategys = this.repository.findStrategysByProjectId(this.project.getId());
		if (!strategys.isEmpty())
			for (Strategy t : strategys) {
				t.setProject(null);
				this.repository.save(t);
			}

		this.repository.delete(this.project);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "ticker", "title", "description", "keyWords", "startMoment", "endMoment", "moreInfo");
	}
}
