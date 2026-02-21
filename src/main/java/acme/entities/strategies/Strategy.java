
package acme.entities.strategies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.data.annotation.Transient;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.client.components.validation.ValidUrl;
import acme.realms.strategies.Fundraiser;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Strategy extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	//ValidTicker
	@Column(unique = true)
	private String				ticker;

	@Mandatory
	//ValidHeader
	@Column
	private String				name;

	@Mandatory
	//ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startMoment;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;

	@Mandatory
	@Valid
	@Column
	private Boolean				draftMode;

	// Derived attributes -----------------------------------------------------


	//@Mandatory
	//@Valid
	@Transient
	private Double getMothsActive() {

		Double result = null;

		if (this.startMoment != null && this.endMoment != null) {
			long startMomentMillisecs = this.startMoment.getTime();
			long endMomentMillisecs = this.endMoment.getTime();

			long diffInMilliSecs = endMomentMillisecs - startMomentMillisecs;
			long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMilliSecs);
			result = Math.round(diffInDays / 30.0 * 10.0) / 10.0;
		}
		return result;
	}

	//@Mandatory
	//@ValidScore
	@Transient
	private double getExpectedPercentage() {

		double result = 0;

		List<Tactic> tactics = new ArrayList<Tactic>();

		if (tactics != null && !tactics.isEmpty())
			for (Tactic tactic : tactics)
				result += tactic.getExpectedPercentage();
		return result;
	}

	// Relationships ----------------------------------------------------------


	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Fundraiser fundraiser;

}
