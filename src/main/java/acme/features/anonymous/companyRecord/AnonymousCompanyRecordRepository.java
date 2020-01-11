
package acme.features.anonymous.companyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCompanyRecordRepository extends AbstractRepository {

	//poner la incial de la clase
	@Query("select c from CompanyRecord c")
	Collection<CompanyRecord> findMany();

	@Query("select c from CompanyRecord c where c.id = ?1")
	CompanyRecord findCompanyRecordById(int id);

	@Query("select c from CompanyRecord c where c.starNumber = 5")
	Collection<CompanyRecord> findTop();

}
