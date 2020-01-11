
package acme.features.sponsor.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select b from CommercialBanner b where b.id = ?1")
	CommercialBanner findOneCommercialBannerById(int id);

	@Query("select b from CommercialBanner b where b.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int sponsorId);

}
