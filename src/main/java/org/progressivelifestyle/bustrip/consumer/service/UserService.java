package org.progressivelifestyle.bustrip.consumer.service;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.progressivelifestyle.bustrip.consumer.User;
import org.progressivelifestyle.bustrip.consumer.UserEventSubscription;
import org.progressivelifestyle.bustrip.consumer.UserEventSubscriptionPK;
import org.progressivelifestyle.bustrip.google.EventService;
import org.progressivelifestyle.bustrip.google.domain.BusRunningInfo;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventCategory;
import org.progressivelifestyle.bustrip.google.domain.EventCategoryPK;
import org.progressivelifestyle.bustrip.google.domain.PickupLocation;
import org.progressivelifestyle.bustrip.google.repository.BusRunningInfoRepository;
import org.progressivelifestyle.bustrip.google.repository.EventRepository;
import org.progressivelifestyle.bustrip.google.repository.UserEventSubscriptionRepo;
import org.progressivelifestyle.bustrip.google.repository.UserRepository;
import org.progressivelifestyle.bustrip.web.dto.AdditionalItemDTO;
import org.progressivelifestyle.bustrip.web.dto.SubscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tilt.api.model.CampaignModel;
import org.tilt.api.model.PaymentModel;
import org.tilt.service.TiltService;

import com.google.api.client.util.Lists;
import com.google.api.client.util.Sets;
//import org.springframework.util.CollectionUtils;

@Service
public class UserService extends AbstractBaseService<UserEventSubscription> {
	@Autowired
	private UserEventSubscriptionRepo subsRepo;

	/*
	 * @Autowired private EventRepository eventRepo;
	 */

	@Autowired
	private EventService eventService;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private AdditionalItemSubscriptionService addlSubscriptionService;

	@Autowired
	private BusRunningInfoRepository busRunningInfoRepo;

	@Autowired
	private TiltService tiltService;

	@Value("${tilt.admin.id}")
	private String tiltAdminUserId;

	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public synchronized UserEventSubscription subscribeToEvent(Long userId, Long eventId) throws Exception {
		User user = userRepo.findOne(userId);
		Event event = eventService.findById(eventId);
		UserEventSubscription subscription = null;
		if (event.isFreeze())
			throw new RuntimeException("Maximum passengers reached for this event. Can't board any more!!");
		int minpassenger = event.getMinPassenger();
		int maxPassenger = event.getMaxPassenger();
		int totalSubscripton = eventService.findAllSubscriptionFor(event).size();
		if (totalSubscripton == minpassenger) {
			CampaignModel tiltCampaign = createTiltCampaign(event);
			event.setTiltCampaignId(tiltCampaign.getId());
			makeTiltPaymentForAllSubscriber(event);
			eventService.update(event);
		}else if (totalSubscripton > minpassenger)
			makeUserPayment(user, event);
		if (totalSubscripton == maxPassenger) {
			event.setFreeze(true);
			eventService.update(event);
			event = cloneEvent(event);
			event = eventRepo.save(event);
		}
		UserEventSubscriptionPK subsPK = new UserEventSubscriptionPK(event, user);
		subscription = new UserEventSubscription();
		setMandatoryDates(subscription);
		subscription.setPk(subsPK);
		return subsRepo.save(subscription);
	}

	@Override
	public String[] getPropertiesToIgnoreDuringClone() {
		return new String[] {};
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public boolean createSubscriptionWithAdditionalItems(SubscriptionDTO subscriptionDTO) throws Exception {
		UserEventSubscription subscription = subscribeToEvent(subscriptionDTO.getUserId(), subscriptionDTO.getEventId());
		if (!CollectionUtils.isEmpty(subscriptionDTO.getAdditionalItems())) {
			for (AdditionalItemDTO additionalItem : subscriptionDTO.getAdditionalItems())
				addlSubscriptionService.addAdditionalItemsToSubscription(subscriptionDTO.getUserId(), subscription, additionalItem.getId(), additionalItem.getQuantity());
		}
		//subsRepo.save(subscription);
		return true;
	}

	@Transactional(readOnly = true)
	public Collection<Event> getSubscribedEvents(long userId) {
		User user = userRepo.findOne(userId);
		Collection<UserEventSubscription> allSusbs = subsRepo.findByUser(user);
		Collection<Event> subsEvents = Lists.newArrayList();
		for (UserEventSubscription subs : allSusbs)
			subsEvents.add(subs.getPk().getEvent());
		return subsEvents;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
	public void deleteASubscription(long userId, long eventId) {
		User user = userRepo.findOne(userId);
		Event event = eventService.findById(eventId);

		UserEventSubscription subscription = subsRepo.findOne(new UserEventSubscriptionPK(event, user));
		addlSubscriptionService.deleteAdditionalSubscriptionItemsForEventAndUserId(userId, eventId);
		subsRepo.delete(subscription);
	}

	@Transactional(readOnly = true)
	public BusRunningInfo getBusRunningInfo(long eventId, String busNum) {
		Event event = eventService.findById(eventId);
		return busRunningInfoRepo.findByBusNumAndEvent(busNum, event);
	}

	private void makeTiltPaymentForAllSubscriber(Event event) {
		Collection<UserEventSubscription> allSubscription = eventService.findAllSubscriptionFor(event);
		for (UserEventSubscription subscription : allSubscription) {
			User user = subscription.getPk().getUser();
			makeUserPayment(user, event);
		}
	}
	
	private void makeUserPayment(User user, Event event){
		tiltService.getCampaignApi().createCampaignPayment(event.getTiltCampaignId(), new PaymentModel(event.getPrice().intValue(), 0, 0, user.getTiltUserId(), user.getTiltCardId()));
	}

	private CampaignModel createTiltCampaign(Event event) {
		if (StringUtils.isBlank(tiltAdminUserId))
			tiltAdminUserId = System.getProperty("tiltAdminUserId");
		return tiltService.getCampaignApi().createCampaign(new CampaignModel(event.getTitle(), new Double(event.getPrice() * event.getMinPassenger()).intValue(), event.getExpiration(), tiltAdminUserId));
	}
	
	private Event cloneEvent(Event event) throws Exception {
		Event newEvent = new Event();
		org.springframework.beans.BeanUtils.copyProperties(event, newEvent, new String []{"creationDate", "lastUpdateDate", "id", "freeze", "eventcategory", "pickupLocations"});
		Date now = new Date();
		newEvent.setCreationDate(now);
		newEvent.setLastUpdateDate(now);
		Set<EventCategory> eventCategories = Sets.newHashSet();
		for(EventCategory eventCategory : event.getEventcategory()){
			EventCategoryPK pk = new EventCategoryPK(eventCategory.getCategory(), newEvent);
			EventCategory neweventCategory = new EventCategory(pk);
			eventCategories.add(neweventCategory);
		}
		Set<PickupLocation> pickupLocations = Sets.newHashSet();
		for(PickupLocation pickupLocation : event.getPickupLocations())
			pickupLocations.add((PickupLocation)BeanUtils.cloneBean(pickupLocation));
		event = null;
		newEvent.setEventcategory(eventCategories);
		newEvent.setPickupLocations(pickupLocations);
		return newEvent;
	}
}
