
package acme.features.inventor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.inventor.Inventor;

@Service
public class InventorInventionPublishService extends AbstractService<Inventor, Invention> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InventorInventionRepository	repository;

	private Invention					invention;

	// AbstractService interface ----------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionsById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.invention != null && this.invention.getDraftMode() && this.invention.getInventor().isPrincipal();
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(Invention.class, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.invention);
		{
			boolean hasParts;

			hasParts = !this.repository.findPartsByInventionId(this.invention.getId()).isEmpty();
			super.state(hasParts, "*", "acme.validation.invention.hasParts.message");
		}
	}

	@Override
	public void execute() {
		this.invention.setDraftMode(false);
		this.repository.save(this.invention);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "inventor", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
	}

}
