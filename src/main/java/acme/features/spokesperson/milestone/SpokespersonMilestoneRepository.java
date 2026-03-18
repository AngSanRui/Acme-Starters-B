
package acme.features.spokesperson.milestone;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.Milestone;

@Repository
public interface SpokespersonMilestoneRepository extends AbstractRepository {

	@Query("select campaign from Campaign campaign where campaign.id = :id")
	Campaign findCampaignById(int id);

	@Query("select milestone from Milestone milestone where milestone.campaign.id = :campaignId")
	Collection<Milestone> findMilestoneByCampaignId(int campaignId);

	@Query("select milestone from Milestone milestone where milestone.id = :id")
	Milestone findMilestoneById(int id);

	@Query("select spokesperson.id from Spokesperson spokesperson where spokesperson.userAccount.id =:id")
	int findSpokespersonByAccountId(int id);
}
