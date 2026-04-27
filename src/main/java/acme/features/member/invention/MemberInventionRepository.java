
package acme.features.member.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;

@Repository
public interface MemberInventionRepository extends AbstractRepository {

	@Query("select inv from Invention inv where inv.project.id = :projectId")
	Collection<Invention> findInventionsByProjectId(int projectId);

	@Query("select inv from Invention inv where inv.id = :id")
	Invention findInventionByInventionId(int id);
}
