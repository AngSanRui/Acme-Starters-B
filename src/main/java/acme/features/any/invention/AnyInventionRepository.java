
package acme.features.any.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;

@Repository
public interface AnyInventionRepository extends AbstractRepository {

	@Query("select inv from Invention inv where inv.draftMode = false")
	Collection<Invention> findAllPublishedInvention();

	@Query("select inv from Invention inv where inv.id = :id")
	Invention findInventionById(int id);

	@Query("select inv from Invention inv where inv.project.id = :projectId")
	Collection<Invention> findInventionByProjectId(int projectId);

	@Query("select pro from Project pro where pro.id = :id")
	Project findProjectByProjectId(int id);
}
