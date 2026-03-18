
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.invention.Part;

@Validator
public class PartValidator extends AbstractValidator<ValidPart, Part> {

	// Internal state ---------------------------------------------------------

	// ConstraintValidator interface ------------------------------------------

	@Override
	protected void initialise(final ValidPart part) {
		assert part != null;
	}

	@Override
	public boolean isValid(final Part part, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (part == null)
			result = true;
		else {
			boolean noNullValues;
			boolean supportedCurrency;
			noNullValues = !(part.getName().isBlank() || part.getDescription().isBlank() || part.getCost() == null || part.getKind() == null);
			supportedCurrency = part.getCost() == null ? false : part.getCost().getCurrency().equalsIgnoreCase("EUR");
			result = noNullValues && supportedCurrency;
			super.state(context, supportedCurrency, "cost", "acme.validation.part.currency-not-supported.message");
		}
		return result;
	}
}
