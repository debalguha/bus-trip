package org.progressivelifestyle.bustrip.web;

import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.core.MediaType;

import org.progressivelifestyle.bustrip.consumer.SubscriptionAdditionalItems;
import org.progressivelifestyle.bustrip.consumer.service.AdditionalItemSubscriptionService;
import org.progressivelifestyle.bustrip.consumer.service.UserService;
import org.progressivelifestyle.bustrip.google.EventService;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.AdditionalItemDTO;
import org.progressivelifestyle.bustrip.web.dto.SavedEventsDTO;
import org.progressivelifestyle.bustrip.web.dto.SubscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.util.Lists;

@Controller
@RequestMapping("/cart")
public class SubscriptionController extends AbstractEventController{
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AdditionalItemSubscriptionService addlSubService;
	
	@RequestMapping(value = "/event/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public @ResponseBody APIResponse subscribeEvent(@RequestBody SubscriptionDTO subscriptionDTO) throws Exception{
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, userService.createSubscriptionWithAdditionalItems(subscriptionDTO));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/event/get", method = RequestMethod.GET)
	public @ResponseBody APIResponse getAllSavedEvents(@RequestParam(value = "userId") long userId){
		Collection<Event> subscribedEvents = userService.getSubscribedEvents(userId);
		Collection<SavedEventsDTO> savedEventsDTOs = Lists.newArrayList();
		for(Event event : subscribedEvents){
			Collection<SubscriptionAdditionalItems> addlSubsEventItems = addlSubService.findSubscriptionAdditionalItemsForEventAndUserId(userId, event.getId());
			savedEventsDTOs.add(new SavedEventsDTO(dtoUtils.toEventDTO(event), !CollectionUtils.isEmpty(addlSubsEventItems)?dtoUtils.toAdditionalItemDTOs(addlSubsEventItems):Collections.EMPTY_LIST));
		}
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, savedEventsDTOs);
	}
	
	@RequestMapping(value = "/event/subscription/delete", method = RequestMethod.GET)
	public @ResponseBody APIResponse deleteSubscription(@RequestParam(value="userId", required = true) long userId, @RequestParam(value="eventId", required = true) long eventId) {
		userService.deleteASubscription(userId, eventId);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
	}
	
	@RequestMapping(value = "/event/addlitem/create", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody APIResponse createAdditionalItem(@RequestBody AdditionalItemDTO itemDto) {
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, addlSubService.createAdditionalItem(itemDto));
	}	
	
	@RequestMapping(value = "/event/addlitem/get", method = RequestMethod.GET)
	public @ResponseBody APIResponse getAllAdditionalItem() {
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, addlSubService.getAllAdditionalItems());
	}
	
	@RequestMapping(value = "/event/subscription/count", method = RequestMethod.GET)
	public @ResponseBody APIResponse findSubscriptionCount(@RequestParam(value="eventId", required = true) long eventId) {
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, eventService.findSubscriptionsFor(eventId));
	}
}
