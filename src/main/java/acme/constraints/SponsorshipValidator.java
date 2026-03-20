
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.sponsorship.SponsorshipRepository;

@Validator
public class SponsorshipValidator extends AbstractValidator<ValidSponsorship, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorshipRepository repository;

	// ConstraintValidator interface ------------------------------------------


	@Override
	protected void initialise(final ValidSponsorship annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Sponsorship sponsorship, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (sponsorship == null)
			result = true;
		else {
			{	//check if its published that it has donations				
				if (sponsorship.getDraftMode() != null) {
					boolean publishedWithDonation;

					publishedWithDonation = sponsorship.getDraftMode() || this.repository.getDonations(sponsorship.getId()).size() >= 1;

					super.state(context, publishedWithDonation, "draftMode", "acme.validation.sponsorship.published-without-donation.message");
				}
			}
			{	//check that the startMoment is before the endMoment (only triggers if not null both)
				if (sponsorship.getStartMoment() != null && sponsorship.getEndMoment() != null) {
					boolean startBeforeEnd;

					startBeforeEnd = MomentHelper.isBeforeOrEqual(sponsorship.getStartMoment(), sponsorship.getEndMoment());

					super.state(context, startBeforeEnd, "endMoment", "acme.validation.sponsorship.start-after-end.message");
				}
			}
			{	//check that ticker is unique
				if (sponsorship.getTicker() != null) {
					boolean tickerIsUnique;

					Sponsorship s = this.repository.isTickerUnique(sponsorship.getTicker());
					if (s == null || s.getId() == sponsorship.getId())
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
