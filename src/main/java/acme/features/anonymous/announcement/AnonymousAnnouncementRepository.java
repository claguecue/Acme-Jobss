
package acme.features.anonymous.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousAnnouncementRepository extends AbstractRepository {

	@Query("select a from Announcement a where a.id = ?1")
	Announcement findOneById(int id);

	@Query("select a from Announcement a where a.moment between ?1 and ?2")
	Collection<Announcement> findManyBetween(Date date1, Date date2);

}
