
package acme.features.any.campaign;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;

@Repository
public interface AnyCampaignRepository extends AbstractRepository {

	@Query("select campaign from Campaign campaign where campaign.draftMode = false")
	Collection<Campaign> findAllPublishedCampaigns();

	@Query("select campaign from Campaign campaign where campaign.id = :id")
	Campaign findCampaignById(int id);

}
