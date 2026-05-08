
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.projects.WorksIn;
import acme.entities.projects.WorksInRepository;
import acme.realms.campaign.Spokesperson;
import acme.realms.inventor.Inventor;
import acme.realms.strategy.Fundraiser;

@Validator
public class WorkInValidator extends AbstractValidator<ValidWorksIn, WorksIn> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private WorksInRepository worksInRepository;

	// ConstraintValidator interface ------------------------------------------


	@Override
	protected void initialise(final ValidWorksIn annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final WorksIn projectMember, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (projectMember == null)
			result = true;
		else {
			{
				boolean uniqueProjectMember;
				WorksIn existingProjectMember = null;

				if (projectMember != null && projectMember.getMember() != null)
					existingProjectMember = this.worksInRepository.findByRoleAndMemberIdAndProjectId(projectMember.getRole(), projectMember.getMember().getId(), projectMember.getProject().getId());
				else
					super.state(context, false, "*", "acme.validation.member-error");
				uniqueProjectMember = existingProjectMember == null || existingProjectMember.equals(projectMember);

				super.state(context, uniqueProjectMember, "*", "acme.validation.duplicated-member");
			}
			if (projectMember != null && projectMember.getMember() != null) {
				boolean hasRole = false;
				switch (projectMember.getRole()) {
				case null: {
					super.state(context, false, "*", "acme.validation.role-error");
				}
				case FUNDRAISER: {
					hasRole = projectMember.getMember().getUserAccount().hasRealmOfType(Fundraiser.class);
					break;
				}
				case INVENTOR: {
					hasRole = projectMember.getMember().getUserAccount().hasRealmOfType(Inventor.class);
					break;
				}
				case SPOKESPERSON: {
					hasRole = projectMember.getMember().getUserAccount().hasRealmOfType(Spokesperson.class);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + projectMember.getRole());
				}
				super.state(context, hasRole, "*", "acme.validation.role-error");

			}
			result = !super.hasErrors(context);
		}
		return result;
	}
}
