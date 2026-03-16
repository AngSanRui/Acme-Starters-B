
package acme.features.spokesperson.campaign;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;

@Repository
public interface SpokespersonCampaignRepository extends AbstractRepository {

	@Query("select campaign from Campaign campaign where campaign.spokesperson.id = :id")
	Collection<Campaign> findCampaignsBySpokespersonId(int id);

	@Query("select campaign from Campaign campaign where campaign.id = :id")
	Campaign findCampaignById(int id);

	@Query("select spokesperson.id from Spokesperson spokesperson where spokesperson.userAccount.id =:id")
	int findSpokespersonByAccountId(int id);
}
