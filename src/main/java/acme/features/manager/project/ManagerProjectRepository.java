
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.auditReports.AuditReport;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.strategies.Strategy;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select pr from Project pr where pr.manager.id = :managerId")
	Collection<Project> findProjectByManagerId(Integer managerId);

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);

	//Delete

	@Query("select inv from Invention inv where inv.project.id = :projectId")
	Collection<Invention> findInventionsByProjectId(int projectId);

	@Query("select cam from Campaign cam where cam.project.id = :projectId")
	Collection<Campaign> findCampaignsByProjectId(int projectId);

	@Query("select spon from Sponsorship spon where spon.project.id = :projectId")
	Collection<Sponsorship> findSponsorshipsByProjectId(int projectId);

	@Query("select aud from AuditReport aud where aud.project.id = :projectId")
	Collection<AuditReport> findAuditReportsByProjectId(int projectId);

	@Query("select sta from Strategy sta where sta.project.id = :projectId")
	Collection<Strategy> findStrategysByProjectId(int projectId);

}
