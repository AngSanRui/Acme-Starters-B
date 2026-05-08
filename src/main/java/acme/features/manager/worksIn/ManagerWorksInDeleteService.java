
package acme.features.manager.worksIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.entities.projects.WorksIn;
import acme.entities.strategies.Strategy;
import acme.realms.campaign.Spokesperson;
import acme.realms.inventor.Inventor;
import acme.realms.managers.Manager;
import acme.realms.strategy.Fundraiser;

public class ManagerWorksInDeleteService extends AbstractService<Manager, WorksIn> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerWorksInRepository	repository;

	private WorksIn						relacion;

	private Project						project;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int userAccountId;
		int managerId;

		userAccountId = super.getRequest().getPrincipal().getAccountId();
		managerId = this.repository.findManagerIdByAccountId(userAccountId);
		//Chequea que el rol sea manager, que el proyecto exista y que el proyecto pertenezca al manager
		status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class) && this.relacion != null && this.project.getDraftMode() && this.project.getManager().getId() == managerId;
		super.setAuthorised(status);
	}

	@Override
	public void load() {
		Integer workInId;
		workInId = super.getRequest().getData("id", int.class);

		this.relacion = this.repository.findWorksInById(workInId);
		this.project = this.relacion.getProject();
	}

	@Override
	public void bind() {
		super.bindObject(this.relacion, "member", "role");
	}

	@Override
	public void validate() {
		;
	}

	@Override
	public void execute() {
		switch (this.relacion.getRole()) {
		case FUNDRAISER: {
			Fundraiser fundraiser = this.repository.findFundraiserByUserAccountId(this.relacion.getMember().getUserAccount().getId());
			Collection<Strategy> strategies = this.repository.findStrategyById(fundraiser.getId(), this.project.getId());
			for (Strategy s : strategies) {
				s.setProject(null);
				this.repository.save(s);
			}
			break;
		}
		case INVENTOR: {
			Inventor inventor = this.repository.findInventorByUserAccountId(this.relacion.getMember().getUserAccount().getId());
			Collection<Invention> inventions = this.repository.findInventionById(inventor.getId(), this.project.getId());
			for (Invention i : inventions) {
				i.setProject(null);
				this.repository.save(i);
			}
			break;
		}
		case SPOKESPERSON: {
			Spokesperson spokesperson = this.repository.findSpokespersonByUserAccountId(this.relacion.getMember().getUserAccount().getId());
			Collection<Campaign> campaigns = this.repository.findCampaignById(spokesperson.getId(), this.project.getId());
			for (Campaign c : campaigns) {
				c.setProject(null);
				this.repository.save(c);
			}
			break;
		}
		default: {
			break;
		}
		}
		this.repository.delete(this.relacion);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.relacion, "member", "role");
	}

}
