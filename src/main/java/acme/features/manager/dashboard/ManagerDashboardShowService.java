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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.entities.strategies.Strategy;
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

		Integer userAccountId = super.getRequest().getPrincipal().getAccountId();
		Manager manager = this.repository.findManagerByUserId(userAccountId);
		Integer managerId = manager.getId();

		Integer numberOfMyProjects = this.repository.numberOfProjectsByManager(managerId);
		Double averageNumberOfProjectsExcludingThemselves = this.repository.averageNumberOfProjectsByManagerExcludingThemselves(managerId);
		double desviationNumberOfProjectsByManager = numberOfMyProjects - averageNumberOfProjectsExcludingThemselves;

		Collection<Project> projects = this.repository.findProjectsByManager(managerId);

		Double minEffort = 0.0;
		Double maxEffort = 0.0;
		Double averageOfEffortOfProjectsByManager = 0.0;
		double acum = 0.0;

		List<Double> projectEfforts = new ArrayList<>();

		for (Project p : projects) {
			double totalActiveMonths = 0.0;

			Collection<Strategy> strategies = this.repository.findStrategiesByProjectId(p.getId());
			for (Strategy s : strategies)
				if (s.getMonthsActive() != null)
					totalActiveMonths += s.getMonthsActive();

			Collection<Campaign> campaigns = this.repository.findCampaignsByProjectId(p.getId());
			for (Campaign c : campaigns)
				if (c.getMonthsActive() != null)
					totalActiveMonths += c.getMonthsActive();

			Collection<Invention> inventions = this.repository.findInventionsByProjectId(p.getId());
			for (Invention i : inventions)
				if (i.getMonthsActive() != null)
					totalActiveMonths += i.getMonthsActive();

			Integer membersCount = this.repository.countMembersByProject(p.getId());

			Double effort = 0.0;
			if (membersCount != null && membersCount > 0)
				effort = totalActiveMonths / membersCount;
			projectEfforts.add(effort);

			if (effort < minEffort)
				minEffort = effort;
			if (effort > maxEffort)
				maxEffort = effort;
			acum += effort;
		}

		averageOfEffortOfProjectsByManager = acum / projects.size();

		this.dashboard = super.newObject(Dashboard.class);
		this.dashboard.setTotalNumberOfProjects(numberOfMyProjects);
		this.dashboard.setDeviationOfTheAverageNumOfProjects(desviationNumberOfProjectsByManager);
		this.dashboard.setMinimumDeviationOfEffort(minEffort);
		this.dashboard.setMaximumDeviationOfEffort(maxEffort);
		this.dashboard.setAverageDeviationOfEffort(averageOfEffortOfProjectsByManager);

	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.dashboard, "totalNumberOfProjects", "deviationOfTheAverageNumOfProjects", "minimumDeviationOfEffort", "maximumDeviationOfEffort", "averageDeviationOfEffort");
	}

}
