
package acme.features.member.campaign;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;

@Repository
public interface MemberCampaignRepository extends AbstractRepository {

	@Query("select camp from Campaign camp where camp.project.id = :projectId")
	Collection<Campaign> findCampaignsByProjectId(int projectId);

	@Query("select camp from Campaign camp where camp.id = :id")
	Campaign findCampaignByCampaignId(int id);

}
