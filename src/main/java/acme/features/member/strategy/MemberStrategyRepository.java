
package acme.features.member.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategies.Strategy;

@Repository
public interface MemberStrategyRepository extends AbstractRepository {

	@Query("select str from Strategy str where str.project.id = :projectId")
	Collection<Strategy> findStrategysByProjectId(int projectId);

	@Query("select str from Strategy str where str.id = :id")
	Strategy findStrategyByStrategyId(int id);
}
