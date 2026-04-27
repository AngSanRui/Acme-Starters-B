
package acme.features.member.auditReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.auditReports.AuditReport;

@Repository
public interface MemberAuditReportRepository extends AbstractRepository {

	@Query("select aud from AuditReport aud where aud.project.id = :projectId")
	Collection<AuditReport> findAuditReportByProjectId(int projectId);

	@Query("select aud from AuditReport aud where aud.id = :id")
	AuditReport findAuditReportByAuditReportId(int id);
}
