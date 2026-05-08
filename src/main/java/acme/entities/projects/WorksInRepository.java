
package acme.entities.projects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface WorksInRepository extends AbstractRepository {

	@Query("SELECT wk FROM WorksIn wk WHERE wk.role = :role AND wk.member.id = :memberId AND wk.project.id = :projectId")
	WorksIn findByRoleAndMemberIdAndProjectId(Role role, int memberId, int projectId);

}
