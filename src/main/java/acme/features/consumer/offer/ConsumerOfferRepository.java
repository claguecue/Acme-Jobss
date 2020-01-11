
package acme.features.consumer.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ConsumerOfferRepository extends AbstractRepository {

	@Query("select off from Offer off where off.id=?1")
	Offer findOfferById(int id);

	@Query("select off from Offer off")
	Collection<Offer> findMany();

	@Query("select off from Offer off where off.ticker =?1")
	Object findOneOfferByTicker(String ticker);
	// Object findOneOfferByTicker(Offer offer);

}
