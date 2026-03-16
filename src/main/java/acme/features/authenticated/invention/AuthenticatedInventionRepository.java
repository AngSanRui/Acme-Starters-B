
package acme.features.authenticated.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;

@Repository
public interface AuthenticatedInventionRepository extends AbstractRepository {

	@Query("select inv from Invention inv where inv.inventor.id = :id")
	Collection<Invention> findInventionsByInventorId(int id);

	@Query("select inv from Invention inv where inv.id =:id")
	Invention findInventionsById(int id);

	@Query("select inv.id from Inventor inv where inv.userAccount.id =:id")
	int findInventorByAccountId(int id);
}
