
package acme.features.manager.auditReport;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.auditReports.AuditReport;
import acme.realms.managers.Manager;

@Controller
public class ManagerAuditReportController extends AbstractController<Manager, AuditReport> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerAuditReportListService.class);
		super.addBasicCommand("show", ManagerAuditReportShowService.class);
	}
}
