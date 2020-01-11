/*
 * AuthenticatedNonCommercialBannerRepository.java
 *
 * Copyright (c) 2019 Aureliano Piqueras, based on Rafael Corchuelo's DP Starter project.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select req from NonCommercialBanner req where req.id = ?1")
	NonCommercialBanner findNonCommercialBannerById(int id);

	@Query("select req from NonCommercialBanner req")
	Collection<NonCommercialBanner> findMany();

}
