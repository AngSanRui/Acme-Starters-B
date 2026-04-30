/*
 * ManagerDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select avg(select count(a) from Application a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.PENDING")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.ACCEPTED")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.REJECTED")
	Double ratioOfRejectedApplications();

	@Query("SELECT (COUNT(p) FROM Project p WHERE p.manager.id = m.id")
	Integer totalNumberOfProjects(Integer managerId);

	@Query("SELECT(COUNT(p) * 1.0) - (SELECT COUNT(p2) * 1.0 / COUNT(DISTINCT m.id) FROM Manager m LEFT JOIN m.projects p2) FROM Project p WHERE p.manager.id = :managerId")
	Double deviationOfTheAverageNumOfProjects(Integer managerId);

	@Query("SELECT MIN(COALESCE((SELECT SUM(m.getMonthsActive)),0)/NULLIF(COUNT(*),0) FROM Member m WHERE m.project_id = p.id) FROM Project p WHERE p.manager_id = :managerId")
	Double minimumDeviationOfEffort(int managerId);

	@Query("SELECT MAX(COALESCE((SELECT SUM(m.getMonthsActive)),0)/NULLIF(COUNT(*),0) FROM Member m WHERE m.project_id = p.id) FROM Project p WHERE p.manager_id = :managerId")
	Double maximumDeviationOfEffort();

	@Query("SELECT AVG(COALESCE((SELECT SUM(m.getMonthsActive)),0)/NULLIF(COUNT(*),0) FROM Member m WHERE m.project_id = p.id) FROM Project p WHERE p.manager_id = :managerId")
	Double averageDeviationOfEffort();

}
