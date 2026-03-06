
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.auditReports.AuditReport;
import acme.entities.auditReports.AuditReportRepository;

@Validator
public class AuditReportValidator extends AbstractValidator<ValidAuditReport, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditReportRepository repository;

	// ConstraintValidator interface ------------------------------------------


	@Override
	protected void initialise(final ValidAuditReport annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final AuditReport auditReport, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (auditReport == null)
			result = true;
		else {
			{	//check if its published that it has audit section
				if (auditReport.getDraftMode() != null) {
					boolean publishedWithAuditSection;

					publishedWithAuditSection = auditReport.getDraftMode() || this.repository.getAuditSections(auditReport.getId()).size() >= 1;

					super.state(context, publishedWithAuditSection, "draftMode", "acme.validation.audit-report.published-without-audit-section.message");
				}
			}
			{	//check that the startMoment is before the endMoment (only triggers if not null both)
				if (auditReport.getStartMoment() != null && auditReport.getEndMoment() != null) {
					boolean startBeforeEnd;

					startBeforeEnd = MomentHelper.isBeforeOrEqual(auditReport.getStartMoment(), auditReport.getEndMoment());

					super.state(context, startBeforeEnd, "endMoment", "acme.validation.audit-report.start-after-end.message");
				}
			}
			{	//check that ticker is unique
				if (auditReport.getTicker() != null) {
					boolean tickerIsUnique;

					AuditReport arMismoTicker = this.repository.isTickerUnique(auditReport.getTicker());
					if (arMismoTicker == null || arMismoTicker.getId() == auditReport.getId())
						tickerIsUnique = true;
					else
						tickerIsUnique = false;

					super.state(context, tickerIsUnique, "ticker", "acme.validation.audit-report.ticker-not-unique.message");
				}
			}
			result = !super.hasErrors(context);
		}

		return result;
	}

}
