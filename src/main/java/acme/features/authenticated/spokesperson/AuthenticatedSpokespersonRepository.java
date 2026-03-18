
package acme.features.authenticated.spokesperson;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.realms.campaign.Spokesperson;

@Repository
public interface AuthenticatedSpokespersonRepository extends AbstractRepository {

	@Query("select user from UserAccount user where user.id = :id")
	UserAccount findUserAccountById(int id);

	@Query("select spokesperson from Spokesperson spokesperson where spokesperson.id = :id")
	Spokesperson findSpokespersonById(int id);

	@Query("select spokesperson from Spokesperson spokesperson where spokesperson.userAccount.id = :id")
	Spokesperson findSpokespersonByUserAccountId(int id);

}
