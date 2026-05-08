
package acme.features.member.campaign;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.projects.Project;

@Repository
public interface MemberCampaignRepository extends AbstractRepository {

	@Query("select camp from Campaign camp where camp.project.id = :projectId")
	Collection<Campaign> findCampaignsByProjectId(int projectId);

	@Query("select camp from Campaign camp where camp.id = :id")
	Campaign findCampaignByCampaignId(int id);

	@Query("select distinct pr from Project pr, WorksIn w where w.project = pr and w.member.userAccount.id = :userAccountId")
	Collection<Project> findProjectWithUserAccount(Integer userAccountId);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select count(pr) > 0 from Project pr, WorksIn w, Campaign camp where w.project = pr and camp.project = pr and camp.id = :campaignId and w.member.userAccount.id = :userAccountId")
	boolean isCampaignIdInProjectWhereUserIsMember(int campaignId, int userAccountId);
}
