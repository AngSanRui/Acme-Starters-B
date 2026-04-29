
package acme.features.manager.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.strategies.Strategy;

@Repository
public interface ManagerStrategyRepository extends AbstractRepository {

	@Query("select str from Strategy str where str.project.id = :projectId")
	Collection<Strategy> findStrategysByProjectId(int projectId);

	@Query("select str from Strategy str where str.id = :id")
	Strategy findStrategyByStrategyId(int id);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select pr from Project pr where pr.manager.id = :managerId")
	Collection<Project> findProjectByManagerId(Integer managerId);

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);
}
