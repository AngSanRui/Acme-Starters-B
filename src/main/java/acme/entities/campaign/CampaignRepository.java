
package acme.entities.campaign;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;

public interface CampaignRepository extends AbstractRepository {

	@Query("select count(milestone.campaign.id) from Milestone milestone where milestone.campaign.id = :campaignId")
	Integer milestonesPerCampaign(int campaignId);

	@Query("select campaign from Campaign campaign where campaign.ticker = :ticker")
	Campaign findCampaignByTicker(String ticker);

	@Query("select sum(milestone.effort) from Milestone milestone where milestone.campaign.id = :campaignId")
	Optional<Double> getTotalEffort(int campaignId);
}
