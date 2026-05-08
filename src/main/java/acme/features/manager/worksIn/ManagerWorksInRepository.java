
package acme.features.manager.worksIn;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.entities.projects.Role;
import acme.entities.projects.WorksIn;
import acme.entities.strategies.Strategy;
import acme.realms.campaign.Spokesperson;
import acme.realms.inventor.Inventor;
import acme.realms.members.Member;
import acme.realms.strategy.Fundraiser;

public interface ManagerWorksInRepository extends AbstractRepository {

	@Query("select pr from Project pr where pr.id = :id")
	Project findProjectById(int id);

	@Query("select m from Member m")
	Collection<Member> findAllMembers();

	@Query("select man.id from Manager man where man.userAccount.id =:id")
	int findManagerIdByAccountId(int id);

	@Query("select wk.member from WorksIn wk WHERE wk.project.id = :id")
	Collection<Member> findMemberProjectById(int id);

	@Query("select wk from WorksIn wk WHERE wk.project.id = :id")
	Collection<WorksIn> findWorksInProjectById(int id);

	@Query("select wk from WorksIn wk WHERE wk.id = :id")
	WorksIn findWorksInById(int id);

	@Query("select wi from WorksIn wi where wi.project.id = :projectId and wi.member.id = :memberId and wi.role = :role")
	WorksIn findWorksInByProjectByMemberByRole(int projectId, int memberId, Role role);

	@Query("select inv from Inventor inv where inv.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);

	@Query("select inv from Invention inv where inv.inventor.id = :id and inv.project.id = :projectId")
	Collection<Invention> findInventionById(int id, int projectId);

	@Query("select f from Fundraiser f where f.userAccount.id = :id")
	Fundraiser findFundraiserByUserAccountId(int id);

	@Query("select str from Strategy str where str.fundraiser.id = :id and str.project.id = :projectId")
	Collection<Strategy> findStrategyById(int id, int projectId);

	@Query("select spok from Spokesperson spok where spok.userAccount.id = :id")
	Spokesperson findSpokespersonByUserAccountId(int id);

	@Query("select cam from Campaign cam where cam.spokesperson.id = :id and cam.project.id = :projectId")
	Collection<Campaign> findCampaignById(int id, int projectId);
}
