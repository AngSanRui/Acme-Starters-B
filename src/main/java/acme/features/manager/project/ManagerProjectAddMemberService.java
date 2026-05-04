
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.basis.AbstractObject;
import acme.client.components.basis.AbstractRealm;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;

@Service
public class ManagerProjectAddMemberService extends AbstractService<AbstractRealm, AbstractObject> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository	repository;

	private Project						project;

	// AbstractService interface -------------------------------------------

}
