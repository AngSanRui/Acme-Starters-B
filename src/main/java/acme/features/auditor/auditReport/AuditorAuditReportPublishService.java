/*
 * AuditorAuditReportPublishService.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.auditor.auditReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.auditReports.AuditReport;
import acme.realms.auditors.Auditor;

@Service
public class AuditorAuditReportPublishService extends AbstractService<Auditor, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorAuditReportRepository	repository;

	private AuditReport						auditReport;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.auditReport = this.repository.findAuditReportById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.auditReport != null && // no es null
			this.auditReport.getDraftMode() && // el auditreport no está publicado
			this.auditReport.getAuditor().isPrincipal(); // el principal es el auditor

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.auditReport, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.auditReport);
		//cannot be published unless they have at least one audit section
		{
			boolean hasAnyAuditSections;
			hasAnyAuditSections = this.repository.findAuditSectionsByAuditReportId(this.auditReport.getId()).size() > 0;
			super.state(hasAnyAuditSections, "*", "acme.validation.notAnyAuditSections.message");
		}
		// (esto realmente no hace falta porque de por sí las propiedades tienen ValidMoment(future))
		// startMoment y endMoment tienen que ser futuro respecto al momento de publicación
		{
			boolean startMomentIsFuture;
			startMomentIsFuture = MomentHelper.isAfter(this.auditReport.getStartMoment(), MomentHelper.getCurrentMoment());
			super.state(startMomentIsFuture, "startMoment", "acme.validation.startMomentIsNotFuture.message");
		}
		{
			boolean endMomentIsFuture;
			endMomentIsFuture = MomentHelper.isAfter(this.auditReport.getEndMoment(), MomentHelper.getCurrentMoment());
			super.state(endMomentIsFuture, "endMoment", "acme.validation.endMomentIsNotFuture.message");
		}
	}

	@Override
	public void execute() {
		this.auditReport.setDraftMode(false);
		this.repository.save(this.auditReport);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditReport, "auditor", "ticker", "name", "description", "startMoment",//
			"endMoment", "moreInfo", "draftMode", "monthsActive", "hours");
	}

}
