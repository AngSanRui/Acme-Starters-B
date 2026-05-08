
package acme.features.manager.auditReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.realms.managers.Manager;

@Service
public class ManagerAuditReportShowService extends AbstractService<Manager, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerAuditReportRepository	repository;

	private Integer							userAccountId;

	private Integer							managerId;

	private AuditReport						auditReport;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.managerId = this.repository.findManagerIdByAccountId(this.userAccountId);
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.auditReport.getProject().getManager().getId() == this.managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer auditReportId;

		auditReportId = super.getRequest().getData("id", int.class);
		this.auditReport = this.repository.findAuditReportByAuditReportId(auditReportId);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditReport, "auditor", "ticker", "name", "description", "startMoment",//
			"endMoment", "moreInfo", "draftMode", "monthsActive", "hours");
	}

}
