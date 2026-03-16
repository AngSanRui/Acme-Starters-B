
package acme.features.fundraiser.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.Tactic;
import acme.realms.strategy.Fundraiser;

@Repository
public interface FundraiserStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.draftMode = false")
	Collection<Strategy> findAllPublishedStrategies();

	@Query("select s from Strategy s where s.fundraiser.id = :id")
	Collection<Strategy> findAllStrategiesByFundraiserId(int id);

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(int id);

	@Query("select coalesce(sum(t.expectedPercentage),0) from Tactic t where t.strategy.id = :strategyId and t.strategy.draftMode = false")
	Double expectedPercentageByStrategyId(int strategyId);

	@Query("select f from Fundraiser f where f.userAccount.id = :userAccountId")
	Fundraiser findFundraiserByUserAccountId(int userAccountId);

	@Query("select u from UserAccount u where u.id = :id")
	UserAccount findUserAccountById(int id);

	@Query("select count(t) from Tactic t where t.strategy.id = :strategyId")
	int countTacticsByStrategyId(int strategyId);

	@Query("select t from Tactic t where t.strategy.id = :strategyId")
	Collection<Tactic> findAllTacticsByStrategyId(int strategyId);

}
