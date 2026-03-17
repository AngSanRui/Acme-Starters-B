
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.sponsorship.Donation;

@Validator
public class DonationValidator extends AbstractValidator<ValidDonation, Donation> {

	// Internal state ---------------------------------------------------------

	// ConstraintValidator interface ------------------------------------------

	@Override
	protected void initialise(final ValidDonation donation) {
		assert donation != null;
	}

	@Override
	public boolean isValid(final Donation donation, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (donation == null)
			result = true;
		else {
			boolean noNullValues;
			boolean supportedCurrency;
			noNullValues = !(donation.getName().isBlank() || donation.getNotes().isBlank() || donation.getMoney() == null || donation.getKind() == null);
			supportedCurrency = donation.getMoney() == null ? false : donation.getMoney().getCurrency().equalsIgnoreCase("EUR");
			result = noNullValues && supportedCurrency;
			super.state(context, supportedCurrency, "money", "acme.validation.donation.currency-not-supported.message");
		}
		return result;
	}
}
