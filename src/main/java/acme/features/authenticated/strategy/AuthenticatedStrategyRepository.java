
package acme.features.authenticated.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategies.Strategy;

@Repository
public interface AuthenticatedStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.draftMode = false")
	Collection<Strategy> findAllPublishedStrategies();

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(int id);

	@Query("select coalesce(sum(t.expectedPercentage),0) from Tactic t where t.strategy.id = :strategyId and t.strategy.draftMode = false")
	Double expectedPercentageByStrategyId(int strategyId);

}
