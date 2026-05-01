/*
 * ManagerDashboardShowService.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.forms.Dashboard;
import acme.realms.managers.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerDashboardRepository	repository;

	private Dashboard					dashboard;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {

		int managerId = super.getRequest().getData("id", int.class);

		Integer totalNumberOfProjects;
		Double deviationOfTheAverageNumOfProjects;
		Double minimumDeviationOfEffort;
		Double maximumDeviationOfEffort;
		Double averageDeviationOfEffort;

		totalNumberOfProjects = this.repository.totalNumberOfProjects(managerId);
		deviationOfTheAverageNumOfProjects = this.repository.deviationOfTheAverageNumOfProjects(managerId);
		minimumDeviationOfEffort = this.repository.minimumDeviationOfEffort(managerId);
		maximumDeviationOfEffort = this.repository.maximumDeviationOfEffort(managerId);
		averageDeviationOfEffort = this.repository.averageDeviationOfEffort(managerId);

		this.dashboard = super.newObject(Dashboard.class);
		this.dashboard.setTotalNumberOfProjects(totalNumberOfProjects);
		this.dashboard.setDeviationOfTheAverageNumOfProjects(deviationOfTheAverageNumOfProjects);
		this.dashboard.setMinimumDeviationOfEffort(minimumDeviationOfEffort);
		this.dashboard.setMaximumDeviationOfEffort(maximumDeviationOfEffort);
		this.dashboard.setAverageDeviationOfEffort(averageDeviationOfEffort);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.dashboard, //
			"totalNumberOfProjects", "deviationOfTheAverageNumOfProjects", // 
			"minimumDeviationOfEffort", "maximumDeviationOfEffort", //
			"averageDeviationOfEffort");
	}

}
