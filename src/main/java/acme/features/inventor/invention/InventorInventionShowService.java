
package acme.features.inventor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.inventor.Inventor;

@Service
public class InventorInventionShowService extends AbstractService<Inventor, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InventorInventionRepository	repository;

	private Invention					invention;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int inventionId;

		inventionId = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionsById(inventionId);
	}

	@Override
	public void authorise() {
		boolean status;

		int inventorId = this.repository.findInventorByAccountId(super.getRequest().getPrincipal().getAccountId());
		status = super.getRequest().getPrincipal().hasRealmOfType(Inventor.class) && this.invention != null && this.invention.getInventor().getId() == inventorId;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "inventor", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "cost");
	}
}
