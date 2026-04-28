
package acme.features.member.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface MemberSponsorshipRepository extends AbstractRepository {

	@Query("select spon from Sponsorship spon where spon.project.id = :projectId")
	Collection<Sponsorship> findSponsorshipsByProjectId(int projectId);

	@Query("select spon from Sponsorship spon where spon.id = :id")
	Sponsorship findSponsorshipBySponsorshipId(int id);

	@Query("select distinct pr from Project pr, WorksIn w where w.project = pr and w.member.userAccount.id = :userAccountId")
	Collection<Project> findProjectWithUserAccount(Integer userAccountId);

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select count(pr) > 0 from Project pr, WorksIn w, Sponsorship spon where w.project = pr and spon.project = pr and spon.id = :sponsorshipId and w.member.userAccount.id = :userAccountId")
	boolean isSponsorshipInProjectWhereUserIsMember(int sponsorshipId, int userAccountId);
}
