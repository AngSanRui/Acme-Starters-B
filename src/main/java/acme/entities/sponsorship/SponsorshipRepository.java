
package acme.entities.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorshipRepository extends AbstractRepository {

	@Query("select donation from Donation donation where donation.sponsorship.id = :sId")
	Collection<Donation> getDonations(int sId);

	@Query("select sum(donation.money.amount) from Donation donation where donation.sponsorship.id = :sId")
	Double calculateSponsorshipMoney(int sId);

	@Query("select s from Sponsorship s where s.ticker = :ticker")
	Sponsorship isTickerUnique(String ticker);

}
