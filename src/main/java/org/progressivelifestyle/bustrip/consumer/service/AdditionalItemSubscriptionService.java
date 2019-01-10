package org.progressivelifestyle.bustrip.consumer.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.progressivelifestyle.bustrip.consumer.AdditionalItem;
import org.progressivelifestyle.bustrip.consumer.SubscriptionAdditionalItems;
import org.progressivelifestyle.bustrip.consumer.SubscriptionAdditionalItemsPK;
import org.progressivelifestyle.bustrip.consumer.User;
import org.progressivelifestyle.bustrip.consumer.UserEventSubscription;
import org.progressivelifestyle.bustrip.consumer.UserEventSubscriptionPK;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.repository.AdditionalItempRepository;
import org.progressivelifestyle.bustrip.google.repository.EventRepository;
import org.progressivelifestyle.bustrip.google.repository.SubscriptionAdditionalItemsRepo;
import org.progressivelifestyle.bustrip.google.repository.UserEventSubscriptionRepo;
import org.progressivelifestyle.bustrip.google.repository.UserRepository;
import org.progressivelifestyle.bustrip.web.dto.AdditionalItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;

@Service
public class AdditionalItemSubscriptionService extends AbstractBaseService<SubscriptionAdditionalItems>{
	private static final Logger logger = Logger.getLogger(AdditionalItemSubscriptionService.class.getName());//logger
	@Autowired
	private UserEventSubscriptionRepo subsRepo;
	
	@Autowired
	private SubscriptionAdditionalItemsRepo addlItemsRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private AdditionalItempRepository itemRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public SubscriptionAdditionalItems addAdditionalItemsToSubscription(Long userId, Long eventId, Long itemId, int quantity){
		User user = userRepo.findOne(userId);
		Event event = eventRepo.findOne(eventId);
		UserEventSubscription subscription = subsRepo.findOne(new UserEventSubscriptionPK(event, user));
		AdditionalItem item = itemRepo.findOne(itemId);
		SubscriptionAdditionalItemsPK additionalItemsPK = new SubscriptionAdditionalItemsPK(subscription, item);
		SubscriptionAdditionalItems subsAdditionalItem = new SubscriptionAdditionalItems();
		setMandatoryDates(subsAdditionalItem);
		subsAdditionalItem.setPk(additionalItemsPK);
		subsAdditionalItem.setQuantity(quantity);
		subsAdditionalItem.setAmount(quantity * item.getItemPrie());
		return addlItemsRepo.save(subsAdditionalItem);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public SubscriptionAdditionalItems addAdditionalItemsToSubscription(Long userId, UserEventSubscription subscription, Long itemId, int quantity){
		AdditionalItem item = itemRepo.findOne(itemId);
		SubscriptionAdditionalItemsPK additionalItemsPK = new SubscriptionAdditionalItemsPK(subscription, item);
		SubscriptionAdditionalItems subsAdditionalItem = new SubscriptionAdditionalItems();
		setMandatoryDates(subsAdditionalItem);
		subsAdditionalItem.setPk(additionalItemsPK);
		subsAdditionalItem.setQuantity(quantity);
		subsAdditionalItem.setAmount(quantity * item.getItemPrie());
		return addlItemsRepo.save(subsAdditionalItem);
	}
	
	@Transactional(readOnly = true)
	public Collection<SubscriptionAdditionalItems> findSubscriptionAdditionalItemsForEventAndUserId(long userId, long eventId){
		User user = userRepo.findOne(userId);
		Event event = eventRepo.findOne(eventId);
		UserEventSubscription eventSubs = subsRepo.findOne(new UserEventSubscriptionPK(event, user));
		Collection<SubscriptionAdditionalItems> subscriptionAdditionalItems = null;
		try {
			subscriptionAdditionalItems = addlItemsRepo.findAllSubscriptionAdditionalItems(eventSubs);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while finding additional items for "+userId+" and "+eventId, e);
			subscriptionAdditionalItems = Lists.newArrayList();
		}
		return subscriptionAdditionalItems;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteAdditionalSubscriptionItemsForEventAndUserId(Long userId, Long eventId) {
		User user = userRepo.findOne(userId);
		Event event = eventRepo.findOne(eventId);
		UserEventSubscription eventSubs = subsRepo.findOne(new UserEventSubscriptionPK(event, user));
		Collection<SubscriptionAdditionalItems> subscriptionAdditionalItems = null;
		try {
			subscriptionAdditionalItems = addlItemsRepo.findAllSubscriptionAdditionalItems(eventSubs);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while finding additional items for "+userId+" and "+eventId, e);
			subscriptionAdditionalItems = Lists.newArrayList();
		}
		for(SubscriptionAdditionalItems addlItemSubscription : subscriptionAdditionalItems)
			addlItemsRepo.delete(addlItemSubscription);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public AdditionalItem createAdditionalItem(AdditionalItemDTO dto){
		AdditionalItem item = new AdditionalItem(null, dto.getItem(), dto.getPrice());
		Date now = new Date();
		item.setCreationDate(now);
		item.setLastUpdateDate(now);
		return itemRepo.save(item);
	}
	
	@Override
	public String[] getPropertiesToIgnoreDuringClone() {
		return new String[]{};
	}

	@Transactional(readOnly = true)
	public Collection<AdditionalItem> getAllAdditionalItems() {
		if(itemRepo.count() > 0)
			return itemRepo.findAll();
		else
			return Collections.emptyList();
	}

}
