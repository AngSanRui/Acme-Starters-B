
package acme.entities.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.constraints.ValidWorksIn;
import acme.realms.members.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidWorksIn
public class WorksIn extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@Valid
	@Column
	private Role				role;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Mandatory
	@Valid
	@ManyToOne
	private Project				project;

	@Mandatory
	@Valid
	@ManyToOne
	private Member				member;
}
