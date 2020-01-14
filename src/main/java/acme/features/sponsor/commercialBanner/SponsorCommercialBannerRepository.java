
package acme.features.sponsor.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.entities.customizations.Customization;
import acme.entities.roles.Sponsor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select b from CommercialBanner b where b.id = ?1")
	CommercialBanner findOneCommercialBannerById(int id);

	@Query("select b from CommercialBanner b where b.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findTheSponsor(int idUserAccount);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findTheUserAccount(int idPrincipal);

	@Query("select s.creditCard from Sponsor s where s.id = ?1")
	String findCreditCard(int idSponsor);

	@Query("select c from Customization c")
	Customization findCustomization();
}
