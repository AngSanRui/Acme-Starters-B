
package acme.features.auditor.auditReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.auditReports.AuditReport;
import acme.entities.auditReports.AuditSection;
import acme.realms.auditors.Auditor;

@Repository
public interface AuditorAuditReportRepository extends AbstractRepository {

	@Query("select ar from AuditReport ar where ar.draftMode = false")
	Collection<AuditReport> findAllPublishedAuditReports();

	@Query("select ar from AuditReport ar where ar.auditor.id = :id")
	Collection<AuditReport> findAllAuditReportsByAuditorId(int id);

	@Query("select ar from AuditReport ar where ar.id = :id")
	AuditReport findAuditReportById(int id);

	@Query("select auditor from Auditor auditor where auditor.id = :id")
	Auditor findAuditorById(int id);

	@Query("select asec from AuditSection asec where asec.auditReport.id = :auditReportId")
	Collection<AuditSection> findAuditSectionsByAuditReportId(int auditReportId);
}
