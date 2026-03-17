
package acme.features.authenticated.auditReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;

@Service
public class AuthenticatedAuditReportShowService extends AbstractService<Authenticated, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuditReportRepository	repository;

	private AuditReport							auditReport;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.auditReport = this.repository.findAuditReportById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		//que esté publicado
		status = this.auditReport != null && !this.auditReport.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditReport, "auditor", "ticker", "name", "description", "startMoment",//
			"endMoment", "moreInfo", "draftMode", "monthsActive", "hours");
	}
}
