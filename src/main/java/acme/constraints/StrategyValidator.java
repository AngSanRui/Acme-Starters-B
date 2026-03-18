
package acme.constraints;

import java.util.Date;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.StrategyRepository;

@Validator
public class StrategyValidator extends AbstractValidator<ValidStrategy, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private StrategyRepository repository;


	@Override
	protected void initialise(final ValidStrategy annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Strategy strategy, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (strategy == null)
			result = true;
		else {

			//Validate: Strategy must exist

			{
				boolean exists;
				Strategy existingStrategy;

				existingStrategy = this.repository.strategyByTicker(strategy.getTicker());
				exists = existingStrategy == null || existingStrategy.equals(strategy);

				super.state(context, exists, "ticker", "acme.validation.strategy.duplicated-strategies.message");
			}

			//Validate: startMoment and endMoment must be a valid time interval in the future

			{
				boolean validTimeInterval;
				Date startMoment = strategy.getStartMoment();
				Date endMoment = strategy.getEndMoment();

				validTimeInterval = startMoment == null || endMoment == null || MomentHelper.isAfter(endMoment, startMoment);

				super.state(context, validTimeInterval, "endMoment", "acme.validation.strategy.invalid-time-in.message");

			}

			result = !super.hasErrors(context);
		}
		return result;
	}

}
