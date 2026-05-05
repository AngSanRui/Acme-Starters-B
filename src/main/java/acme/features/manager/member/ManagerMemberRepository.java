
package acme.features.manager.member;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.WorksIn;
import acme.realms.members.Member;

@Repository
public interface ManagerMemberRepository extends AbstractRepository {

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select pr from Project pr where pr.manager.id = :managerId")
	Collection<Project> findProjectByManagerId(Integer managerId);

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);

	@Query("select wk.member.userAccount from WorksIn wk WHERE wk.project.id = :projectId")
	Collection<UserAccount> findAllUserAccountsByProjectId(int projectId);

	@Query("select wk.member from WorksIn wk WHERE wk.project.id = :projectId")
	Collection<Member> findAllMembersByProjectId(int projectId);

	@Query("SELECT wk FROM WorksIn wk WHERE wk.project.id = :projectId")
	Collection<WorksIn> findWorkInByProjectId(int projectId);

	//	@Query("select usac.username from UserAccount where usac.id = :userAccountId")
	//	String findUsernameByUserAccountId(int userAccountId);

}
