
package acme.features.sponsor.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customizations.Customization;
import acme.entities.roles.Sponsor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select b from NonCommercialBanner b where b.id = ?1")
	NonCommercialBanner findOneBannerById(int id);

	@Query("select b from NonCommercialBanner b where b.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findTheSponsor(int idUserAccount);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findTheUserAccount(int idPrincipal);

	@Query("select c from Customization c")
	Customization findCustomization();
}
