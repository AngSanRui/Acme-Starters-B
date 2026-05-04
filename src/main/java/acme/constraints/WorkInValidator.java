
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.projects.WorksIn;
import acme.entities.projects.WorksInRepository;

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

				if (projectMember != null)
					existingProjectMember = this.worksInRepository.findByRoleAndMemberIdAndProjectId(projectMember.getRole(), projectMember.getMember().getId(), projectMember.getProject().getId());
				uniqueProjectMember = existingProjectMember == null || existingProjectMember.equals(projectMember);

				super.state(context, uniqueProjectMember, "*", "acme.validation.duplicated-member");
			}
			result = !super.hasErrors(context);
		}
		return result;
	}
}
