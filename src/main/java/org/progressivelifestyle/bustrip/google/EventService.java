package org.progressivelifestyle.bustrip.google;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.progressivelifestyle.bustrip.consumer.UserEventSubscription;
import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventState;

public interface EventService {

	public abstract void deleteEvent(Event obj);

	public abstract Event create(Event obj);

	public abstract Event update(Event updated);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findById(java.lang.Long)
	 */
	public abstract Event findById(Long id);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findAll()
	 */
	public abstract Collection<Event> findAll();

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findAll(int)
	 */
	public abstract Collection<Event> findAll(int pageIndex);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findFutureEventsOnly(int)
	 */
	
	public abstract Collection<Event> findFutureEventsOnly();
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByTitle(java.lang.String, int)
	 */
	public abstract Collection<Event> findByTitle(String searchTerm, int pageIndex);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByLocation(java.lang.String, int)
	 */
	public abstract Collection<Event> findByLocation(String searchTerm, int pageIndex);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByEventDate(java.util.Date, int)
	 */
	public abstract Collection<Event> findByEventDate(Date eventDate, int pageIndex);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByExpirationDate(java.util.Date, int)
	 */
	public abstract Collection<Event> findByExpirationDate(Date expirationDate, int pageIndex);

	public abstract Collection<Category> getAllCategories(EventState eventState);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByCategory(java.lang.Long)
	 */
	public abstract Collection<Event> findByCategory(Long category);

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findEventcategories(org.progressivelifestyle.bustrip.google.domain.Event)
	 */
	public abstract Set<String> findEventcategories(Event event);
	
	public abstract Collection<Event> findAllEventsOnWeek(Date weekStartDate, Date weekEndDate, EventState state);

	public abstract Event updateEventWithCampaignId(Long eventId, String campaignId);

	public abstract Collection<UserEventSubscription> findAllSubscriptionFor(Event event);

	public int findSubscriptionsFor(long eventId);

	public Collection<Event> findPastEventsOnly();
}