
package acme.features.member.auditReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.realms.members.Member;

@Service
public class MemberAuditReportShowService extends AbstractService<Member, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberAuditReportRepository	repository;

	private Integer						userAccountId;

	private AuditReport					auditReport;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Integer auditReportId;

		auditReportId = super.getRequest().getData("id", int.class);
		this.userAccountId = super.getRequest().getPrincipal().getAccountId();
		status = super.getRequest().getPrincipal().isAuthenticated() && this.repository.isAuditReportInProjectWhereUserIsMember(auditReportId, this.userAccountId);
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
