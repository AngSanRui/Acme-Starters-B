
package acme.features.member.auditReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.auditReports.AuditReport;
import acme.entities.projects.Project;

@Repository
public interface MemberAuditReportRepository extends AbstractRepository {

	@Query("select aud from AuditReport aud where aud.project.id = :projectId")
	Collection<AuditReport> findAuditReportByProjectId(int projectId);

	@Query("select aud from AuditReport aud where aud.id = :id")
	AuditReport findAuditReportByAuditReportId(int id);

	@Query("select distinct pr from Project pr, WorksIn w where w.project = pr and w.member.userAccount.id = :userAccountId")
	Collection<Project> findProjectWithUserAccount(Integer userAccountId);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select count(pr) > 0 from Project pr, WorksIn w, AuditReport aud where w.project = pr and aud.project = pr and aud.id = :auditReportId and w.member.userAccount.id = :userAccountId")
	boolean isAuditReportInProjectWhereUserIsMember(int auditReportId, int userAccountId);
}
