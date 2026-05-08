
package acme.features.any.auditReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;

@Service
public class AnyAuditReportProjectListService extends AbstractService<Any, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyAuditReportRepository	repository;

	private Collection<AuditReport>		auditReports;

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
		this.auditReports = this.repository.findAuditReportByProjectId(projectId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.auditReports, "ticker", "name", "startMoment", "endMoment");
	}

}
