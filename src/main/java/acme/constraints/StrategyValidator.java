
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.StrategyRepository;

@Validator
public class StrategyValidator extends AbstractValidator<ValidStrategy, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private StrategyRepository repository;


	@Override
	public boolean isValid(final Strategy strategy, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (strategy == null)
			result = true;
		else {

			//Validate: Strategies cannot be published unless they have at least one tactic

			{
				boolean hasTactic;
				Integer tacticsPerStrategy;

				tacticsPerStrategy = this.repository.tacticsPerStrategy(strategy.getId());
				hasTactic = tacticsPerStrategy >= 1;

				//super.state(context, hasTactic, "*", "acme.validation.strategy.no-tactics.message");
			}

			//Validate: startMoment and endMoment must be a valid time interval in the future

			{
				boolean validTimeInterval;

				if (strategy.getStartMoment() != null && strategy.getEndMoment() != null)
					validTimeInterval = strategy.getStartMoment().before(strategy.getEndMoment());
				else
					validTimeInterval = true;
				//super.state(context, validTimeInterval, "endMoment", "acme.validation.strategy.invalid-time-in.message");

			}

			result = !super.hasErrors(context);
		}
		return result;
	}

}
