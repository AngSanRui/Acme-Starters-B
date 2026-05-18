
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.helpers.MomentHelper;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;

public class ProjectValidator extends AbstractValidator<ValidProject, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyProjectRepository repository;

	// ConstraintValidator interface ------------------------------------------


	@Override
	protected void initialise(final ValidProject annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Project project, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (project == null)
			result = true;
		else {
			boolean isDraft = project.getDraftMode() != null && project.getDraftMode().booleanValue();
			{
				boolean correctNumberOfInventions = true;
				if (!project.getDraftMode()) {
					Integer existingInventions;
					existingInventions = this.repository.getNumOfInventions(project.getId());
					if (existingInventions == null)
						existingInventions = 0;

					correctNumberOfInventions = existingInventions >= 1;
					super.state(context, correctNumberOfInventions, "*", "acme.validation.numberOfInventions.message");
				}
			}
			{
				boolean correctDates = true;

				if (!isDraft && project.getStartMoment() != null && project.getEndMoment() != null)
					correctDates = MomentHelper.isBefore(project.getStartMoment(), project.getEndMoment());
				super.state(context, correctDates, "start-moment", "acme.validation.correctDates.message");
				super.state(context, correctDates, "end-moment", "acme.validation.correctDates.message");
			}
			if (project.getStartMoment() != null && project.getEndMoment() != null) {
				boolean startBeforeEnd;

				startBeforeEnd = MomentHelper.isBeforeOrEqual(project.getStartMoment(), project.getEndMoment());

				super.state(context, startBeforeEnd, "endMoment", "acme.validation.sponsorship.start-after-end.message");
			}
			{	//check that ticker is unique
				if (project.getTicker() != null) {
					boolean tickerIsUnique;

					Project s = this.repository.isTickerUnique(project.getTicker());
					if (s == null || s.getId() == project.getId())
						tickerIsUnique = true;
					else
						tickerIsUnique = false;

					super.state(context, tickerIsUnique, "ticker", "acme.validation.sponsorship.ticker-not-unique.message");
				}
			}
			result = !super.hasErrors(context);
		}
		return result;
	}
}
