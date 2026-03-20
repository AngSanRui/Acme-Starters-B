
package acme.entities.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;

public interface InventionRepository extends AbstractRepository {

	@Query("select part from Part part where part.invention.id = :arId")
	Collection<Part> getParts(int arId);

	@Query("select inv from Invention inv where inv.ticker = :ticker")
	Invention isTickerUnique(String ticker);
}
