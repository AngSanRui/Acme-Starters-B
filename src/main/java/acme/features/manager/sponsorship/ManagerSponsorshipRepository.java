
package acme.features.manager.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface ManagerSponsorshipRepository extends AbstractRepository {

	@Query("select spon from Sponsorship spon where spon.project.id = :projectId")
	Collection<Sponsorship> findSponsorshipsByProjectId(int projectId);

	@Query("select spon from Sponsorship spon where spon.id = :id")
	Sponsorship findSponsorshipBySponsorshipId(int id);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select pr from Project pr where pr.manager.id = :managerId")
	Collection<Project> findProjectByManagerId(Integer managerId);

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);
}
