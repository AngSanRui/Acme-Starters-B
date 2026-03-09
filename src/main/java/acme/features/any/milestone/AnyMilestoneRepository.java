
package acme.features.any.milestone;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.Milestone;

@Repository
public interface AnyMilestoneRepository extends AbstractRepository {

	@Query("select campaign from Campaign campaign where campaign.id = :id")
	Campaign findCampaignById(int id);

	@Query("select milestone from Milestone milestone where milestone.campaign.id = :campaignId")
	Collection<Milestone> findMilestoneByCampaignId(int campaignId);

	@Query("select milestone from Milestone milestone where milestone.id = :id")
	Milestone findMilestoneById(int id);
}
