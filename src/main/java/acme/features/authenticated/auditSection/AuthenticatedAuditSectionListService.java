
package acme.features.authenticated.auditSection;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.entities.auditReports.AuditSection;

@Service
public class AuthenticatedAuditSectionListService extends AbstractService<Authenticated, AuditSection> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuditSectionRepository	repository;

	private AuditReport							auditReport;
	private Collection<AuditSection>			auditSections;

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

		status = this.auditReport != null && !this.auditReport.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.auditSections, "name", "notes", "hours", "kind");

	}

}
