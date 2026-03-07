
package acme.features.any.inventor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.inventor.Inventor;

@Repository
public interface AnyInventorRepository extends AbstractRepository {

	@Query("select inventor from Inventor inventor where inventor.id = :id")
	Inventor findInventorById(int id);

}
