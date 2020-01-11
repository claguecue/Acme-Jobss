
package acme.features.authenticated.investor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investors.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestorRepository extends AbstractRepository {

	@Query("select i from Investor i where i.id = ?1")
	Investor findOneById(int id);

	@Query("select i from Investor i")
	Collection<Investor> findManyAll();

}
