
package acme.features.member.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;

@Repository
public interface MemberInventionRepository extends AbstractRepository {

	@Query("select inv from Invention inv where inv.project.id = :projectId")
	Collection<Invention> findInventionsByProjectId(int projectId);

	@Query("select inv from Invention inv where inv.id = :id")
	Invention findInventionByInventionId(int id);

	@Query("select distinct pr from Project pr, WorksIn w where w.project = pr and w.member.userAccount.id = :userAccountId")
	Collection<Project> findProjectWithUserAccount(Integer userAccountId);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select count(pr) > 0 from Project pr, WorksIn w, Invention inv where w.project = pr and inv.project = pr and inv.id = :inventionId and w.member.userAccount.id = :userAccountId")
	boolean isInventionInProjectWhereUserIsMember(int inventionId, int userAccountId);
}
