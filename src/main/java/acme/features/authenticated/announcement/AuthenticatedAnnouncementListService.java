
package acme.features.authenticated.announcement;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAnnouncementListService implements AbstractListService<Authenticated, Announcement> {

	// Internal state

	@Autowired
	AuthenticatedAnnouncementRepository repository;


	// AbstractListService<Authenticated, Announcement> interface

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;

		Collection<Announcement> result;

		Date date1 = Date.from(ZonedDateTime.now().toInstant());
		Date date2 = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());

		result = this.repository.findManyBetween(date2, date1);

		/* result = this.repository.findManyAll(); */

		return result;
	}

}
