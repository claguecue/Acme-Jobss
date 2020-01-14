
package acme.features.sponsor.banner;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
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

	@Query("select count(b) from NonCommercialBanner b")
	int countNonCommercialBanners();

	@Query("select b from NonCommercialBanner b")
	List<NonCommercialBanner> findManyBanners(PageRequest pageRequest);

	default NonCommercialBanner findRandomBanner() {
		NonCommercialBanner result;
		int bannerCount, bannerIndex;
		ThreadLocalRandom random;
		PageRequest page;
		List<NonCommercialBanner> list;

		bannerCount = this.countNonCommercialBanners();
		random = ThreadLocalRandom.current();
		bannerIndex = random.nextInt(0, bannerCount);

		page = PageRequest.of(bannerIndex, 1);
		list = this.findManyBanners(page);
		result = list.isEmpty() ? null : list.get(0);

		return result;
	}
}
