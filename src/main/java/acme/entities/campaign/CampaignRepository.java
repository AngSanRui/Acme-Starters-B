
package acme.entities.campaign;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;

public interface CampaignRepository extends AbstractRepository {

	@Query("select count(milestone.campaign.id) from Milestone milestone where milestone.campaign.id = :campaignId")
	Integer milestonesPerCampaign(int campaignId);

	@Query("select campaign from Campaign campaign where s.ticker = :ticker")
	Campaign findCampaignByTicker(String ticker);
}
