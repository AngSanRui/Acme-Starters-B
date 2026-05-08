
package acme.features.member.member;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.WorksIn;
import acme.realms.members.Member;

@Repository
public interface MemberMemberRepository extends AbstractRepository {

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select pr from Project pr where pr.manager.id = :managerId")
	Collection<Project> findProjectByManagerId(Integer managerId);

	@Query("select mem from Member mem where mem.userAccount.id =:id")
	Member findMemberByAccountId(int id);

	@Query("select wk.member.userAccount from WorksIn wk WHERE wk.project.id = :projectId")
	Collection<UserAccount> findAllUserAccountsByProjectId(int projectId);

	@Query("select m from Member m")
	Collection<Member> findAllMembers();

	@Query("select wk.member from WorksIn wk WHERE wk.project.id = :projectId")
	Collection<Member> findAllMembersByProjectId(int projectId);

	@Query("SELECT wk FROM WorksIn wk WHERE wk.project.id = :projectId")
	Collection<WorksIn> findWorkInByProjectId(int projectId);

	@Query("select mem from Member mem where mem.id = :memberId")
	Member findMemberById(int memberId);

	@Query("select wk.project from WorksIn wk where wk.member.id = :memberId")
	Collection<Project> findAllProjectsByMemberId(int memberId);

	//	@Query("select usac.username from UserAccount where usac.id = :userAccountId")
	//	String findUsernameByUserAccountId(int userAccountId);

}
