
package acme.features.any.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

public interface AnyProjectRepository extends AbstractRepository {

	@Query("select pro from Project pro where pro.id = :id")
	Project findProjectById(int id);

	@Query("select pro from Project pro where pro.draftMode = false")
	Collection<Project> findAllPublishedProjects();

	@Query("select count(i) from Invention i where i.project.id = :projectId")
	Integer getNumOfInventions(int projectId);

	@Query("select pro from Project pro where pro.ticker = :ticker")
	Project isTickerUnique(String ticker);
}
