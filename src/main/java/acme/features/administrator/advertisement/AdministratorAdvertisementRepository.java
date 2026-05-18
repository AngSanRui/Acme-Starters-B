
package acme.features.administrator.advertisement;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.advertisements.Advertisement;

@Repository
public interface AdministratorAdvertisementRepository extends AbstractRepository {

	@Query("SELECT adv FROM Advertisement adv WHERE adv.id = :advertisementId")
	Advertisement findAdvertisementById(int advertisementId);

	@Query("SELECT adv FROM Advertisement adv")
	Collection<Advertisement> findAllAdvertisements();

}
