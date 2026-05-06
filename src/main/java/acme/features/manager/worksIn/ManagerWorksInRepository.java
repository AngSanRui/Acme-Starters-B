
package acme.features.manager.worksIn;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.realms.members.Member;

public interface ManagerWorksInRepository extends AbstractRepository {

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select m from Member m")
	Collection<Member> findAllMembers();

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);
}
