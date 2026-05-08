
package acme.features.any.campaign;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.projects.Project;

@Repository
public interface AnyCampaignRepository extends AbstractRepository {

	@Query("select campaign from Campaign campaign where campaign.draftMode = false")
	Collection<Campaign> findAllPublishedCampaigns();

	@Query("select campaign from Campaign campaign where campaign.id = :id")
	Campaign findCampaignById(int id);

	@Query("select cam from Campaign cam where cam.project.id = :projectId")
	Collection<Campaign> findCampaignByProjectId(int projectId);

	@Query("select pro from Project pro where pro.id = :id")
	Project findProjectByProjectId(int id);

}
