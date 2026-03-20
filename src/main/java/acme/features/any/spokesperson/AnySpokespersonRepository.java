
package acme.features.any.spokesperson;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.campaign.Spokesperson;

@Repository
public interface AnySpokespersonRepository extends AbstractRepository {

	@Query("select spokesperson from Spokesperson spokesperson where spokesperson.id = :id")
	Spokesperson findSpokespersonById(int id);
}
