
package acme.features.any.part;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;
import acme.entities.invention.Part;

@Repository
public interface AnyPartRepository extends AbstractRepository {

	@Query("select inv from Invention inv where inv.id = :id")
	Invention findInventionById(int id);

	@Query("select part from Part part where part.invention.id = :inventionId")
	Collection<Part> findPartsByInventiontId(int inventionId);

	@Query("select part from Part part where part.id = :id")
	Part findPartById(int id);
}
