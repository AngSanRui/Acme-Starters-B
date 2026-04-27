
package acme.features.member.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface MemberSponsorshipRepository extends AbstractRepository {

	@Query("select spon from Sponsorship spon where spon.project.id = :projectId")
	Collection<Sponsorship> findSponsorshipsByProjectId(int projectId);

	@Query("select spon from Sponsorship spon where spon.id = :id")
	Sponsorship findSponsorshipBySponsorshipId(int id);
}
