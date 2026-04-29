
package acme.features.manager.auditReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.auditReports.AuditReport;
import acme.entities.projects.Project;

@Repository
public interface ManagerAuditReportRepository extends AbstractRepository {

	@Query("select aud from AuditReport aud where aud.project.id = :projectId")
	Collection<AuditReport> findAuditReportByProjectId(int projectId);

	@Query("select aud from AuditReport aud where aud.id = :id")
	AuditReport findAuditReportByAuditReportId(int id);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select pr from Project pr where pr.manager.id = :managerId")
	Collection<Project> findProjectByManagerId(Integer managerId);

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);
}
