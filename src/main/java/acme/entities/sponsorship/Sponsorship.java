
package acme.entities.sponsorship;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.client.components.validation.ValidMoney;
import acme.client.components.validation.ValidUrl;
import acme.client.helpers.MessageHelper;
import acme.client.helpers.MomentHelper;
import acme.constraints.ValidHeader;
import acme.constraints.ValidSponsorship;
import acme.constraints.ValidText;
import acme.constraints.ValidTicker;
import acme.entities.projects.Project;
import acme.realms.sponsorship.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidSponsorship
public class Sponsorship extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@ValidTicker
	@Column(unique = true)
	private String					ticker;

	@Mandatory
	@ValidHeader
	@Column
	private String					name;

	@Mandatory
	@ValidText
	@Column
	private String					description;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date					startMoment;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date					endMoment;

	@Optional
	@ValidUrl
	@Column
	private String					moreInfo;

	@Mandatory
	@Valid
	@Column
	private Boolean					draftMode;

	// Derived attributes -----------------------------------------------------

	@Transient
	@Autowired
	private SponsorshipRepository	repository;


	@Transient
	public String getDraftModeLabel() {
		if (this.draftMode == null)
			return "";
		return Boolean.TRUE.equals(this.draftMode) ? MessageHelper.getMessage("acme.validation.true") : MessageHelper.getMessage("acme.validation.false");
	}

	@Mandatory
	@Valid
	@Transient
	public Double getMonthsActive() {
		if (this.startMoment == null || this.endMoment == null)
			return null;

		double months = MomentHelper.computeDifference(this.startMoment, this.endMoment, ChronoUnit.MONTHS);

		months = Math.round(months * 100.0) / 100.0;
		return months;
	}

	@Mandatory
	@ValidMoney(min = 0)
	@Transient
	public Money getTotalMoney() {
		Double amount = this.repository.calculateSponsorshipMoney(this.getId());
		Money money = new Money();
		money.setAmount(amount == null ? 0.0 : amount);
		money.setCurrency("EUR");
		return money;
	}

	// Relationships ----------------------------------------------------------


	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Sponsor	sponsor;

	//@Mandatory
	@Valid
	@ManyToOne(optional = true)
	private Project	project;
}
