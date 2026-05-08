
package acme.features.member.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.strategies.Strategy;

@Repository
public interface MemberStrategyRepository extends AbstractRepository {

	@Query("select str from Strategy str where str.project.id = :projectId")
	Collection<Strategy> findStrategysByProjectId(int projectId);

	@Query("select str from Strategy str where str.id = :id")
	Strategy findStrategyByStrategyId(int id);

	@Query("select distinct pr from Project pr, WorksIn w where w.project = pr and w.member.userAccount.id = :userAccountId")
	Collection<Project> findProjectWithUserAccount(Integer userAccountId);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select count(pr) > 0 from Project pr, WorksIn w, Strategy stra where w.project = pr and stra.project = pr and stra.id = :strategyId and w.member.userAccount.id = :userAccountId")
	boolean isStrategyIdInProjectWhereUserIsMember(int strategyId, int userAccountId);
}
