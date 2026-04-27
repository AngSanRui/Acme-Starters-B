
package acme.entities.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.realms.members.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorksIn extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	//Habría que ver si es necesario
	@Mandatory
	@Column
	private String				roles;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Mandatory
	@Valid
	@ManyToOne
	private Project				projects;

	@Mandatory
	@Valid
	@ManyToOne
	private Member				member;
}
