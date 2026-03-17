/*
 * AuthenticatedAuditSectionRepository.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.donation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorship.Donation;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface AuthenticatedDonationRepository extends AbstractRepository {

	@Query("select sponsorship from Sponsorship sponsorship where sponsorship.id = :id")
	Sponsorship findSponsorshipById(int id);

	@Query("select sponsorship from Sponsorship sponsorship where sponsorship.sponsor.id = :id")
	Sponsorship findSponsorshipBySponsorId(int id);

	@Query("select donation from Donation donation where donation.sponsorship.id = :sponsorshipId")
	Collection<Donation> findDonationsBySponsorshipId(int sponsorshipId);

	@Query("select donation from Donation donation where donation.id = :id")
	Donation findDonationById(int id);

	@Query("select sponsor.id from Sponsor sponsor where sponsor.userAccount.id =:id")
	int findSponsorByAccountId(int id);

}
