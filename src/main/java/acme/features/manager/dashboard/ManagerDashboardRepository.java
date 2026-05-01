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

	@Query("SELECT COUNT(p) FROM Project p WHERE p.manager.id = :managerId")
	Integer totalNumberOfProjects(Integer managerId);

	@Query("SELECT(COUNT(p) * 1.0) - (SELECT COUNT(p2) * 1.0 / COUNT(DISTINCT m.id) FROM Project p2, Manager m WHERE p2.manager.id = m.id) FROM Project p WHERE p.manager.id = :managerId")
	Double deviationOfTheAverageNumOfProjects(Integer managerId);

	@Query(
		value = "SELECT MIN(esfuerzo) FROM (  SELECT (CAST(SUM(m.months_active) AS DOUBLE) / NULLIF(COUNT(m.id), 0)) as esfuerzo  FROM member m WHERE m.project_id IN (SELECT id FROM project WHERE manager_id = :managerId) GROUP BY m.project_id) as subquery",
		nativeQuery = true)
	Double minimumDeviationOfEffort(Integer managerId);

	@Query(
		value = "SELECT MAX(esfuerzo) FROM (  SELECT (CAST(SUM(m.months_active) AS DOUBLE) / NULLIF(COUNT(m.id), 0)) as esfuerzo  FROM member m WHERE m.project_id IN (SELECT id FROM project WHERE manager_id = :managerId) GROUP BY m.project_id) as subquery",
		nativeQuery = true)
	Double maximumDeviationOfEffort(Integer managerId);

	@Query(
		value = "SELECT AVG(esfuerzo) FROM (  SELECT (CAST(SUM(m.months_active) AS DOUBLE) / NULLIF(COUNT(m.id), 0)) as esfuerzo  FROM member m WHERE m.project_id IN (SELECT id FROM project WHERE manager_id = :managerId) GROUP BY m.project_id) as subquery",
		nativeQuery = true)
	Double averageDeviationOfEffort(Integer managerId);

}
