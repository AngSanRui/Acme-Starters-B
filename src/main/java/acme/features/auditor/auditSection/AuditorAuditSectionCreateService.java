
package acme.features.auditor.auditSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.entities.auditReports.AuditSection;
import acme.entities.auditReports.SectionKind;
import acme.realms.auditors.Auditor;

@Service
public class AuditorAuditSectionCreateService extends AbstractService<Auditor, AuditSection> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorAuditSectionRepository	repository;

	private AuditSection					auditSection;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int auditReportId;
		AuditReport auditReport;

		auditReportId = super.getRequest().getData("auditReportId", int.class);
		auditReport = this.repository.findAuditReportById(auditReportId);

		this.auditSection = super.newObject(AuditSection.class);
		this.auditSection.setAuditReport(auditReport);
	}

	@Override
	public void authorise() {
		boolean status;
		int auditReportId;
		AuditReport auditReport;

		auditReportId = super.getRequest().getData("auditReportId", int.class);
		auditReport = this.repository.findAuditReportById(auditReportId);

		status = auditReport != null && // no es null el audit report para el que estamos creando el section
			auditReport.getDraftMode() && // su auditreport no está publicado
			auditReport.getAuditor().isPrincipal(); // el principal es el auditor del auditReport

		super.setAuthorised(status);

	}

	@Override
	public void bind() {
		super.bindObject(this.auditSection, "name", "notes", "hours", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.auditSection);
	}

	@Override
	public void execute() {
		this.repository.save(this.auditSection);
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(SectionKind.class, this.auditSection.getKind());
		tuple = super.unbindObject(this.auditSection, "name", "notes", "hours");

		tuple.put("kind", choices);
		tuple.put("auditReportId", this.auditSection.getAuditReport().getId());
	}
}
