
package acme.features.authenticated.auditSection;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Authenticated;
import acme.client.controllers.AbstractController;
import acme.entities.auditReports.AuditSection;

@Controller
public class AuthenticatedAuditSectionController extends AbstractController<Authenticated, AuditSection> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AuthenticatedAuditSectionListService.class);
		super.addBasicCommand("show", AuthenticatedAuditSectionShowService.class);
	}

}
