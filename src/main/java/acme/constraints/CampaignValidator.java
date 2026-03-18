
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.CampaignRepository;

@Validator
public class CampaignValidator extends AbstractValidator<ValidCampaign, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private CampaignRepository repository;


	@Override
	public boolean isValid(final Campaign campaign, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (campaign == null)
			result = true;
		else {

			//Validate: Campaigns cannot be published unless they have at least one milestone
			{
				if (campaign.getDraftMode() != null) {
					boolean publishedWithMilestone;

					publishedWithMilestone = campaign.getDraftMode() || this.repository.milestonesPerCampaign(campaign.getId()) >= 1;

					super.state(context, publishedWithMilestone, "draftMode", "acme.validation.campaign.published-without-milestone.message");
				}
			}
			//Validate: endMoment is after startMoment
			{
				if (campaign.getStartMoment() != null && campaign.getEndMoment() != null) {
					boolean isEndDateBeforeStartDate;

					isEndDateBeforeStartDate = MomentHelper.isBeforeOrEqual(campaign.getStartMoment(), campaign.getEndMoment());

					super.state(context, isEndDateBeforeStartDate, "endMoment", "acme.validation.campaign.start-after-end.message");
				}
			}

			//Validate: unique ticker
			{
				if (campaign.getTicker() != null) {
					boolean isTickerUnique;

					Campaign c = this.repository.findCampaignByTicker(campaign.getTicker());
					if (c == null || c.getId() == campaign.getId())
						isTickerUnique = true;
					else
						isTickerUnique = false;
					super.state(context, isTickerUnique, "ticker", "acme.validation.campaign.ticker-not-unique.message");
				}
			}
			result = !super.hasErrors(context);
		}
		return result;
	}

}
