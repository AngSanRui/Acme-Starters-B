
package acme.entities.auditReports;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditReportRepository extends AbstractRepository {

	@Query("select auditsec from AuditSection auditsec where auditsec.auditReport.id = :arId")
	Collection<AuditSection> getAuditSections(int arId);

	@Query("select sum(auditsec.hours) from AuditSection auditsec where auditsec.auditReport.id = :arId")
	Integer calculateAuditReportHours(int arId);

	//Preguntar al profesor al debuggear errata la query con el segundo audit report duplicado 
	//me devuelve null cuando debería devolver el otro auditReport
	@Query("select ar from AuditReport ar where ar.ticker = :ticker")
	AuditReport isTickerUnique(String ticker);
}
