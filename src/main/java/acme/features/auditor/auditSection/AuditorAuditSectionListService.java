
package acme.features.auditor.auditSection;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.entities.auditReports.AuditSection;
import acme.realms.auditors.Auditor;

@Service
public class AuditorAuditSectionListService extends AbstractService<Auditor, AuditSection> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorAuditSectionRepository	repository;

	private AuditReport						auditReport;
	private Collection<AuditSection>		auditSections;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int auditReportId;

		auditReportId = super.getRequest().getData("auditReportId", int.class);
		this.auditReport = this.repository.findAuditReportById(auditReportId);
		this.auditSections = this.repository.findAuditSectionsByAuditReportId(auditReportId);
	}

	@Override
	public void authorise() {
		boolean status;

		// que el audit report sea del auditor de la sesion iniciada
		status = this.auditReport != null && this.auditReport.getAuditor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		boolean showCreate;

		showCreate = this.auditReport.getDraftMode() && this.auditReport.getAuditor().isPrincipal();

		super.unbindObjects(this.auditSections, "name", "notes", "hours", "kind");
		super.unbindGlobal("auditReportId", this.auditReport.getId());
		super.unbindGlobal("showCreate", showCreate);

	}

}
