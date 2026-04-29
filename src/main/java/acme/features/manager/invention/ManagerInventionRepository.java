
package acme.features.manager.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;

@Repository
public interface ManagerInventionRepository extends AbstractRepository {

	@Query("select inv from Invention inv where inv.project.id = :projectId")
	Collection<Invention> findInventionsByProjectId(int projectId);

	@Query("select inv from Invention inv where inv.id = :id")
	Invention findInventionByInventionId(int id);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select pr from Project pr where pr.manager.id = :managerId")
	Collection<Project> findProjectByManagerId(Integer managerId);

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);

}
