/*
 * AuthenticatedCustomizationRepository.java
 *
 * Copyright (c) 2019 Aureliano Piqueras, based on Rafael Corchuelo's DP Starter project.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.customization;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customizations.Customization;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomizationRepository extends AbstractRepository {

	@Query("select cus from Customization cus where cus.id = ?1")
	Customization findCustomizationById(int id);

	@Query("select cus from Customization cus")
	Collection<Customization> findMany();

	@Query("select cus from Customization cus where cus.activeID=1")
	Customization findMain();

}
