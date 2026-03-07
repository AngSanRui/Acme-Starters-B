
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.invention.Invention;
import acme.entities.invention.InventionRepository;

@Validator
public class InventionValidator extends AbstractValidator<ValidInvention, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InventionRepository repository;

	// ConstraintValidator interface ------------------------------------------


	@Override
	protected void initialise(final ValidInvention annotation) {
		assert annotation != null;
	}

	//TODO: hacer validador (cuadro rojo del uml)

	@Override
	public boolean isValid(final Invention invention, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (invention == null)
			result = true;
		else {
			{	//check if its published that it has audit section
				if (invention.getDraftMode() != null) {
					boolean publishedWithAuditSection;

					publishedWithAuditSection = invention.getDraftMode() || this.repository.getParts(invention.getId()).size() >= 1;

					super.state(context, publishedWithAuditSection, "draftMode", "acme.validation.invention.published-without-audit-section.message");
				}
			}
			{	//check that the startMoment is before the endMoment (only triggers if not null both)
				if (invention.getStartMoment() != null && invention.getEndMoment() != null) {
					boolean startBeforeEnd;

					startBeforeEnd = MomentHelper.isBeforeOrEqual(invention.getStartMoment(), invention.getEndMoment());

					super.state(context, startBeforeEnd, "endMoment", "acme.validation.invention.start-after-end.message");
				}
			}
			//			{	//check that ticker is unique
			//				if (invention.getTicker() != null) {
			//					boolean tickerIsUnique;
			//
			//					Invention arMismoTicker = this.repository.isTickerUnique(invention.getTicker());
			//					if (arMismoTicker == null || arMismoTicker.getId() == invention.getId())
			//						tickerIsUnique = true;
			//					else
			//						tickerIsUnique = false;
			//
			//					super.state(context, tickerIsUnique, "ticker", "acme.validation.invention.ticker-not-unique.message");
			//				}
			//			}
			result = !super.hasErrors(context);
		}

		return result;
	}
}
