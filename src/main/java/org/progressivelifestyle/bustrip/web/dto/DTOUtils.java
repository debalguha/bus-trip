package org.progressivelifestyle.bustrip.web.dto;

import java.sql.Time;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.consumer.EmployeeAssignment;
import org.progressivelifestyle.bustrip.consumer.SubscriptionAdditionalItems;
import org.progressivelifestyle.bustrip.google.CategoryService;
import org.progressivelifestyle.bustrip.google.EventService;
import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventCategory;
import org.progressivelifestyle.bustrip.google.domain.EventCategoryPK;
import org.progressivelifestyle.bustrip.google.domain.PickupLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.client.util.Lists;
import com.google.api.client.util.Sets;

@Component
public class DTOUtils {
	@Autowired
	private EventService service;
	@Autowired
	private CategoryService categoryService;
	public Map<String, Category> categoryIndex;
	private static final Logger logger = Logger.getLogger(DTOUtils.class.getName());
	
	@PostConstruct
	public void init() throws Exception{
		categoryIndex = categoryService.findAll();
	}
	
	public Event createEventFromDTO(EventDTO eventDTO) throws Exception {
		init();
		logger.info("Event: "+eventDTO);
		Event event = new Event(eventDTO.getPrice(), eventDTO.getFeatured()==1?true:false, eventDTO.getEventType(), eventDTO.getHidden()==1?true:false, 
				eventDTO.getTitle(), eventDTO.getEventLocation(), eventDTO.getUpsellOrUpgradeEvent()==1?true:false, eventDTO.getEventDateTime(), eventDTO.getShortDesc(), 
						eventDTO.getDescription(), eventDTO.getLineup(), eventDTO.getImageURL(), eventDTO.getMinPassenger(), eventDTO.getMaxPassenger(),
						eventDTO.getExpiration(), eventDTO.getEventState(), eventDTO.getTiltCampaignId());
		if(eventDTO.getId()>0)
			event.setId(eventDTO.getId());
		Set<String> categories = eventDTO.getEventcategory();
		Set<EventCategory> eventCategories = Sets.newHashSet();
		for(String category : categories){
			Category categoryObj = categoryIndex.get(category.trim());
			if(categoryObj == null)
				categoryObj = new Category(category.trim());
			EventCategoryPK pk = new EventCategoryPK(categoryObj, event);
			EventCategory eventCategory = new EventCategory(pk);
			eventCategories.add(eventCategory);
		}
		Set<PickupLocation> pickupLocations = createPickupLocationsFromDTO(eventDTO.getPickupLocations());
		event.setPickupLocations(pickupLocations);
		event.setEventcategory(eventCategories);
		return event;
	}
	public Set<PickupLocation> createPickupLocationsFromDTO(Set<PickupLocationDTO> pickupLocationDTOs) throws ParseException {
		Set<PickupLocation> pickupLocations = Sets.newHashSet();
		for(PickupLocationDTO pickupDTO : pickupLocationDTOs){
			pickupLocations.add(createPickupLocationFromDTO(pickupDTO));
		}
		return pickupLocations;
	}

	public PickupLocation createPickupLocationFromDTO(PickupLocationDTO pickupDTO) throws ParseException {
		return new PickupLocation(pickupDTO.getNeighborhoodName(), pickupDTO.getInformation(), pickupDTO.getPickupName(), pickupDTO.getPickupAddress(), pickupDTO.getLatitude(), pickupDTO.getLongitude(), !StringUtils.isBlank(pickupDTO.getPickupTime())?new Time(ProjectUtil.timeFormat.parse(pickupDTO.getPickupTime()).getTime()):null);
	}
	
	public Collection<EventDTO> toEventDTOs(Collection<Event> events){
		List<EventDTO> dtos = Lists.newArrayList();
		for(Event event : events)
			dtos.add(toEventDTO(event));
		return dtos;
	}
	
	public EventDTO toEventDTO(Event event) {
		Set<String> eventCategories = service.findEventcategories(event);
		return new EventDTO(event.getId(), event.getPrice(), event.isFeatured()?1:0, event.getEventType(), event.isHidden()?1:0, event.getTitle(), event.getEventLocation(), event.isUpsellOrUpgradeEvent()?1:0,
				event.getEventDateTime(), event.getShortDesc(), event.getDescription(), event.getLineup(), event.getImageURL(), event.getMinPassenger(), event.getMaxPassenger(),
				event.getExpiration(), event.getEventState(), eventCategories, toPickupLocationDTOs(event.getPickupLocations()), event.getTiltCampaignId());
		
	}

	public Set<PickupLocationDTO> toPickupLocationDTOs(Set<PickupLocation> pickupLocations) {
		Set<PickupLocationDTO> pickupDTOs = Sets.newHashSet();
		for(PickupLocation pickupLocation : pickupLocations)
			pickupDTOs.add(toPickupDTO(pickupLocation));
		return pickupDTOs;
	}

	public PickupLocationDTO toPickupDTO(PickupLocation pickupLocation) {
		return new PickupLocationDTO(pickupLocation.getNeighborhoodName(), pickupLocation.getInformation(), pickupLocation.getPickupName(), pickupLocation.getPickupAddress(),
				pickupLocation.getLatitude(), pickupLocation.getLongitude(), pickupLocation.getPickupTime()!=null?ProjectUtil.timeFormat.format(pickupLocation.getPickupTime()):"");
	}
	
	public Collection<AdditionalItemDTO> toAdditionalItemDTOs(Collection<SubscriptionAdditionalItems> addlSubsEventItems){
		Collection<AdditionalItemDTO> addlItmDTOs = Lists.newArrayList();
		for(SubscriptionAdditionalItems subscriptionAdditionalItems : addlSubsEventItems)
			addlItmDTOs.add(toAdditionalItemDTO(subscriptionAdditionalItems));
		return addlItmDTOs;
	}
	
	public AdditionalItemDTO toAdditionalItemDTO(SubscriptionAdditionalItems additionalItem) {
		return new AdditionalItemDTO(additionalItem.getPk().getItem().getId(), additionalItem.getQuantity(), additionalItem.getPk().getItem().getItemName(), additionalItem.getPk().getItem().getItemPrie());
	}
	
	public EmployeeAssignmentDTO toEmployeeAssignmentDTO(EmployeeAssignment assignEmployeeToEvent) {
		return new EmployeeAssignmentDTO(assignEmployeeToEvent.getEmployee(), toEventDTO(assignEmployeeToEvent.getEvent()));
	}
	
	public Collection<EmployeeAssignmentDTO> toEmployeeAssignmentDTOs(Collection<EmployeeAssignment> assignEmployeeToEvents) {
		List<EmployeeAssignmentDTO> ret = Lists.newArrayList();
		for(EmployeeAssignment assignment : assignEmployeeToEvents)
			ret.add(toEmployeeAssignmentDTO(assignment));
		return ret;
	}
}
