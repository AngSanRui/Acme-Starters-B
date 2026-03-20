
package acme.entities.strategies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface StrategyRepository extends AbstractRepository {

	@Query("select coalesce(sum(t.expectedPercentage),0) from Tactic t where t.strategy.id = :strategyId")
	Double getExpectedPercentage(int strategyId);

	@Query("select s from Strategy s where s.ticker = :ticker")
	Strategy strategyByTicker(String ticker);

}
