
package acme.features.administrator.advertisement;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Administrator;
import acme.client.services.AbstractService;
import acme.entities.advertisements.Advertisement;

@Service
public class AdministratorAdvertisementListService extends AbstractService<Administrator, Advertisement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorAdvertisementRepository	repository;

	private Collection<Advertisement>				advertisements;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		this.advertisements = this.repository.findAllAdvertisements();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {

		super.unbindObjects(this.advertisements, "slogan", "picture", "target");
	}

}
