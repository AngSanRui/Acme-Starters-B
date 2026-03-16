
package acme.features.authenticated.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;

@Repository
public interface AuthenticatedInventionRepository extends AbstractRepository {

	@Query("select inv from Invention inv where inv.draftMode = false")
	Collection<Invention> findAllPublishedInvention();

	@Query("select inv from Invention inv where inv.id = :id")
	Invention findInventionById(int id);
}
