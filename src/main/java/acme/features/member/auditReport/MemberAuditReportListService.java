
package acme.features.member.auditReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.realms.members.Member;

@Service
public class MemberAuditReportListService extends AbstractService<Member, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private MemberAuditReportRepository	repository;

	private Collection<AuditReport>		auditReports;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().isAuthenticated();
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		this.auditReports = this.repository.findAuditReportByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.auditReports, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}
}
