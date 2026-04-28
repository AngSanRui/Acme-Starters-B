
package acme.features.member.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface MemberProjectRepository extends AbstractRepository {

	@Query("select pr from Project pr")
	Collection<Project> findAllProjects();

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select distinct pr from Project pr, WorksIn w where w.project = pr and w.member.userAccount.id = :userAccountId")
	Collection<Project> findProjectWithUserAccount(Integer userAccountId);

}
