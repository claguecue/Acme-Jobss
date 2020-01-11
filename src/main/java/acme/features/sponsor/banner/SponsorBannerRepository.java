
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerRepository extends AbstractRepository {

	@Query("select b from NonCommercialBanner b where b.id = ?1")
	NonCommercialBanner findOneBannerById(int id);

	@Query("select b from NonCommercialBanner b where b.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int sponsorId);

}
