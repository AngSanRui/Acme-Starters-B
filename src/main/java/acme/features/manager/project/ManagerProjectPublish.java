
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.entities.auditReports.AuditReportRepository;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.CampaignRepository;
import acme.entities.invention.Invention;
import acme.entities.invention.InventionRepository;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.sponsorship.SponsorshipRepository;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.StrategyRepository;
import acme.realms.managers.Manager;

public class ManagerProjectPublish extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository	repository;
	@Autowired
	private AuditReportRepository		arRepository;
	@Autowired
	private SponsorshipRepository		sponsorshipRepository;
	@Autowired
	private InventionRepository			iRepository;
	@Autowired
	private CampaignRepository			cRepository;
	@Autowired
	private StrategyRepository			strategyRepository;

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
		this.project.setDraftMode(false);
		super.validateObject(this.project);

		super.state(this.validateAllComposites(), "*", "acme.validation.invalidComposites.message");
	}

	private Boolean validateAllComposites() {
		boolean res = true;
		Collection<Invention> inventions = this.repository.findInventionsByProjectId(this.project.getId());
		if (!inventions.isEmpty())
			for (Invention i : inventions)
				if (res && Boolean.TRUE.equals(i.getDraftMode()))
					res = res && !this.iRepository.getParts(i.getId()).isEmpty();

		Collection<Campaign> campaigns = this.repository.findCampaignsByProjectId(this.project.getId());
		if (!campaigns.isEmpty())
			for (Campaign c : campaigns)
				if (res && Boolean.TRUE.equals(c.getDraftMode()))
					res = res && this.cRepository.milestonesPerCampaign(c.getId()) >= 1;

		Collection<Sponsorship> sponsorships = this.repository.findSponsorshipsByProjectId(this.project.getId());
		if (!sponsorships.isEmpty())
			for (Sponsorship s : sponsorships)
				if (res && Boolean.TRUE.equals(s.getDraftMode()))
					res = res && !this.sponsorshipRepository.getDonations(s.getId()).isEmpty();

		Collection<AuditReport> auditReports = this.repository.findAuditReportsByProjectId(this.project.getId());
		if (!auditReports.isEmpty())
			for (AuditReport a : auditReports)
				if (res && Boolean.TRUE.equals(a.getDraftMode()))
					res = res && !this.arRepository.getAuditSections(a.getId()).isEmpty();

		Collection<Strategy> strategys = this.repository.findStrategysByProjectId(this.project.getId());
		if (!strategys.isEmpty())
			for (Strategy t : strategys)
				if (res && Boolean.TRUE.equals(t.getDraftMode()))
					res = res && this.strategyRepository.getNumTactics(t.getId()) >= 1;
		return res;
	}

	@Override
	public void execute() {
		Collection<Invention> inventions = this.repository.findInventionsByProjectId(this.project.getId());
		if (!inventions.isEmpty())
			for (Invention i : inventions) {
				i.setDraftMode(false);
				this.repository.save(i);
			}

		Collection<Campaign> campaigns = this.repository.findCampaignsByProjectId(this.project.getId());
		if (!campaigns.isEmpty())
			for (Campaign c : campaigns) {
				c.setDraftMode(false);
				this.repository.save(c);
			}

		Collection<Sponsorship> sponsorships = this.repository.findSponsorshipsByProjectId(this.project.getId());
		if (!sponsorships.isEmpty())
			for (Sponsorship s : sponsorships) {
				s.setDraftMode(false);
				this.repository.save(s);
			}

		Collection<AuditReport> auditReports = this.repository.findAuditReportsByProjectId(this.project.getId());
		if (!auditReports.isEmpty())
			for (AuditReport a : auditReports) {
				a.setDraftMode(false);
				this.repository.save(a);
			}

		Collection<Strategy> strategys = this.repository.findStrategysByProjectId(this.project.getId());
		if (!strategys.isEmpty())
			for (Strategy t : strategys) {
				t.setDraftMode(false);
				this.repository.save(t);
			}

		this.project.setDraftMode(false);
		this.repository.save(this.project);
	}

	@Override
	public void unbind() {
		this.project.setDraftMode(true);
		super.unbindObject(this.project, "ticker", "title", "description", "keyWords", "startMoment", "endMoment", "moreInfo", "draftMode");
	}

}
