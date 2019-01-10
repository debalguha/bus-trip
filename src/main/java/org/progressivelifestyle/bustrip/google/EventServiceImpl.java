package org.progressivelifestyle.bustrip.google;

import static org.progressivelifestyle.bustrip.google.repository.EventPredicateSpec.locationIsLike;
import static org.progressivelifestyle.bustrip.google.repository.EventPredicateSpec.titleIsLike;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.progressivelifestyle.bustrip.consumer.UserEventSubscription;
import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventCategory;
import org.progressivelifestyle.bustrip.google.domain.EventState;
import org.progressivelifestyle.bustrip.google.domain.PickupLocation;
import org.progressivelifestyle.bustrip.google.repository.CategoryRepo;
import org.progressivelifestyle.bustrip.google.repository.EventCategoryRepo;
import org.progressivelifestyle.bustrip.google.repository.EventRepository;
import org.progressivelifestyle.bustrip.google.repository.PickuplocationRepo;
import org.progressivelifestyle.bustrip.google.repository.UserEventSubscriptionRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Sets;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private EventCategoryRepo eventCategoryRepo;
	@Autowired
	private CategoryRepo catRepo;
	@Autowired
	private PickuplocationRepo pickuplocationRepo;
	@Autowired
	private UserEventSubscriptionRepo subsRepo;
	
	private static final Logger logger = Logger.getLogger(EventServiceImpl.class.getName());

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#deleteEvent(org.progressivelifestyle.bustrip.google.domain.Event)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteEvent(Event obj) {
		eventRepo.delete(obj);
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#create(org.progressivelifestyle.bustrip.google.domain.Event)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Event create(Event obj) {
		try {
			if (obj.getId()!=null && eventRepo.exists(obj.getId()))
				return update(obj);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("Creating a new SF object");
		Date now = new Date();
		if (obj.getCreationDate() == null) {
			obj.setCreationDate(now);
			obj.setLastUpdateDate(now);
		}
		obj = eventRepo.save(obj);
		if (!CollectionUtils.isEmpty(obj.getEventcategory())) {
			for (EventCategory nyEventCategory : obj.getEventcategory()) {
				nyEventCategory.setCreationDate(now);
				nyEventCategory.setLastUpdateDate(now);
				Category category = nyEventCategory.getCategory();
				if (category.getId() == null) {
					category.setCreationDate(now);
					category.setLastUpdateDate(now);
					catRepo.save(category);
				}
				eventCategoryRepo.save(nyEventCategory);
			}
		}
		if (!CollectionUtils.isEmpty(obj.getPickupLocations())) {
			for (PickupLocation pickupLocation : obj.getPickupLocations()){
				if(pickupLocation.getId() == null){
					pickupLocation.setCreationDate(now);
					pickupLocation.setLastUpdateDate(now);
				}
				pickupLocation.setEvent(obj);
				pickuplocationRepo.save(pickupLocation);
			}
		}
		// nyEventCategoryRepo.save(obj.getEventcategory());
		return obj;
	}


	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#update(org.progressivelifestyle.bustrip.google.domain.Event)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Event update(Event updated) {
		logger.info("Creating an SF object");
		Event nyObj = eventRepo.findOne(updated.getId());
		if (nyObj == null) {
			logger.severe("No SF object found with id: " + updated.getId());
			throw new RuntimeException("No SF object found with id: " + updated.getId());
		}
		Date now = new Date();
		Set<EventCategory> eventcategoryOld = nyObj.getEventcategory();
		Set<EventCategory> eventcategoryNew = updated.getEventcategory();
		
		Set<PickupLocation> pickupsOld = nyObj.getPickupLocations();
		Set<PickupLocation> pickupsNew = updated.getPickupLocations();
		if (eventcategoryOld == null)
			eventcategoryOld = Sets.newHashSet();
		if (eventcategoryNew == null)
			eventcategoryNew = Sets.newHashSet();
		
		if(pickupsOld == null)
			pickupsOld = Sets.newHashSet();
		if(pickupsNew == null)
			pickupsNew = Sets.newHashSet();
		
		Set<EventCategory> difference = Sets.difference(eventcategoryOld, eventcategoryNew);
		Set<PickupLocation> diffPickups = Sets.difference(pickupsOld, pickupsNew);
		eventCategoryRepo.delete(difference);
		pickuplocationRepo.delete(diffPickups);
		
		BeanUtils.copyProperties(updated, nyObj, "rowId", "creationDate");
		
		nyObj.setLastUpdateDate(now);
		for (EventCategory eventCategory : nyObj.getEventcategory()) {
			if(eventCategory.getCreationDate() == null)
				eventCategory.setCreationDate(now);
			eventCategory.setLastUpdateDate(now);
			Category category = eventCategory.getCategory();
			if (category.getId() == null) {
				category.setCreationDate(now);
				category.setLastUpdateDate(now);
				catRepo.save(category);
			}
			eventCategoryRepo.save(eventCategory);
		}
		for(PickupLocation pickup : nyObj.getPickupLocations()){
			if(pickup.getCreationDate() == null)
				pickup.setCreationDate(now);
			pickup.setLastUpdateDate(now);
			pickuplocationRepo.save(pickup);
		}
		// nyEventCategoryRepo.save(nyObj.getEventcategory());
		return eventRepo.save(nyObj);
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findById(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findById(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Event findById(Long id) {
		return eventRepo.findOne(id);
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findAll()
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findAll() {
		return eventRepo.findAll();
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findAll(int)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findAll(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findAll(int pageIndex) {
		return eventRepo.findAll(constructPageSpecification(pageIndex)).getContent();
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByTitle(java.lang.String, int)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByTitle(java.lang.String, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findByTitle(String searchTerm, int pageIndex) {
		logger.info("Searching SF objects with search term: " + searchTerm);
		Page<Event> requestedPage = eventRepo.findAll(titleIsLike(searchTerm), constructPageSpecification(pageIndex));
		return requestedPage.getContent();
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByLocation(java.lang.String, int)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByLocation(java.lang.String, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findByLocation(String searchTerm, int pageIndex) {
		logger.info("Searching SF objects with search term: " + searchTerm);
		Page<Event> requestedPage = eventRepo.findAll(locationIsLike(searchTerm), constructPageSpecification(pageIndex));
		return requestedPage.getContent();
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByEventDate(java.util.Date, int)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByEventDate(java.util.Date, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findByEventDate(Date eventDate, int pageIndex) {
		logger.info("Searching SF objects with search term: " + eventDate);
		return eventRepo.findByEventDateTime(eventDate);
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByExpirationDate(java.util.Date, int)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByExpirationDate(java.util.Date, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findByExpirationDate(Date expirationDate, int pageIndex) {
		logger.info("Searching SF objects with search term: " + expirationDate);
		return eventRepo.findByExpirationtDateTime(expirationDate);
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#getAllCategories(org.progressivelifestyle.bustrip.google.domain.EventState)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Category> getAllCategories(EventState eventState) {
		logger.info("Searching for all categories from NY");
		Set<Category> allCategories = Sets.newHashSet();
		List<EventCategory> allEventCategories = eventCategoryRepo.findAll();
		if (CollectionUtils.isEmpty(allEventCategories))
			return allCategories;
		for (EventCategory eventCategory : allEventCategories) {
			if (eventCategory.getEvent().getEventState().equals(eventState))
				allCategories.add(eventCategory.getCategory());
		}
		return allCategories;
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByCategory(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findByCategory(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findByCategory(Long category) {
		Set<Event> ret = Sets.newHashSet();
		if (!catRepo.exists(category))
			return ret;
		try {
			logger.info("Searching for Events based on category");
			Set<EventCategory> nyEventCategories = catRepo.findOne(category).getEventCategories();
			for (EventCategory eventCat : nyEventCategories) {
				/*
				 * Event eventNY = eventCat.getEvent(); Set<PickupLocation>
				 * locations = Sets.newHashSet();
				 * locations.addAll(nyPickupRepo.findAll());
				 * eventNY.setPickupLocations(locations);
				 */
				ret.add(eventCat.getEvent());
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unable to find event for category: " + category, e);
		}
		return ret;
	}

	/**
	 * Returns a new object which specifies the the wanted result page.
	 * 
	 * @param pageIndex
	 *            The index of the wanted result page
	 * @return
	 */
	private Pageable constructPageSpecification(int pageIndex) {
		Pageable pageSpecification = new PageRequest(pageIndex, 10);
		return pageSpecification;
	}

	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findEventcategories(org.progressivelifestyle.bustrip.google.domain.Event)
	 */
	/* (non-Javadoc)
	 * @see org.progressivelifestyle.bustrip.google.EventService#findEventcategories(org.progressivelifestyle.bustrip.google.domain.Event)
	 */
	@Override
	@Transactional(readOnly = true)
	public Set<String> findEventcategories(Event event) {
		Event eventFromDB;
		try {
			eventFromDB = eventRepo.findOne(event.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Set<String> categories = Sets.newHashSet();
		for (EventCategory eventCategory : eventFromDB.getEventcategory())
			categories.add(eventCategory.getCategory().getCategory());
		return categories;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findAllEventsOnWeek(Date weekStartDate, Date weekEndDate, EventState state) {
		return eventRepo.findByWeekStartEndDateTime(weekStartDate, weekEndDate, state);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Event updateEventWithCampaignId(Long eventId, String campaignId) {
		Event event = eventRepo.findOne(eventId);
		event.setTiltCampaignId(campaignId);
		return eventRepo.save(event);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<UserEventSubscription> findAllSubscriptionFor(Event event) {
		return subsRepo.getAllSubscription(event);
	}
	
	@Override
	@Transactional(readOnly = true)
	public int findSubscriptionsFor(long eventId) {
		return subsRepo.getSubscriptionCount(eventRepo.findOne(eventId));
	}

	@Override
	public Collection<Event> findFutureEventsOnly() {
		return eventRepo.findByEventDateTime(new Date());
	}
	
	@Override
	public Collection<Event> findPastEventsOnly() {
		return eventRepo.findByEventDateTimePast(new Date());
	}
}
