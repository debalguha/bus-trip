package org.progressivelifestyle.bustrip.google;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.consumer.AdditionalItem;
import org.progressivelifestyle.bustrip.consumer.User;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventState;
import org.progressivelifestyle.bustrip.web.ConsumerController;
import org.progressivelifestyle.bustrip.web.EventRWController;
import org.progressivelifestyle.bustrip.web.STATUS;
import org.progressivelifestyle.bustrip.web.SubscriptionController;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.AdditionalItemDTO;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.progressivelifestyle.bustrip.web.dto.PickupLocationDTO;
import org.progressivelifestyle.bustrip.web.dto.SubscriptionDTO;
import org.progressivelifestyle.bustrip.web.dto.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tilt.api.model.BankModel;
import org.tilt.api.model.CampaignModel;
import org.tilt.api.model.CardModel;
import org.tilt.api.model.ResponseMessage;
import org.tilt.api.model.UserModel;
import org.tilt.api.model.VerificationModel;
import org.tilt.service.TiltService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Sets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class SubscriptionControllerTest {
	@Autowired
	protected ConsumerController consumerController;

	@Autowired
	protected SubscriptionController subscriptionController;

	@Autowired
	protected EventRWController eventController;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private TiltService tiltService;

	@Test
	public void shouldBeAbleToCountSubscriptionForEvent() throws Exception {
		try {
			APIResponse userResponse = shouldBeABleToCreateAnUser();
			assertresponse(userResponse);
			APIResponse eventResponse = shouldBeAbleToCreateAnEvent();
			assertresponse(eventResponse);
			APIResponse itemResponseTshirt = shouldBeAbleToCreateAdditionalItems("T-Shirt", 21.30);
			assertresponse(itemResponseTshirt);
			APIResponse itemResponseCap = shouldBeAbleToCreateAdditionalItems("Cap", 51.30);
			assertresponse(itemResponseCap);
			shouldBeAbleToSubscribeToAnEvent(((User) userResponse.getData()).getId(), ((EventDTO) eventResponse.getData()).getId(), ((AdditionalItem) itemResponseTshirt.getData()), ((AdditionalItem) itemResponseCap.getData()));
			Assert.assertEquals(1, subscriptionController.findSubscriptionCount(((EventDTO)eventResponse.getData()).getId()).getData());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
	}
	
	@Test
	@Ignore
	public void runSubscriptionTests() throws Exception {
		try {
			APIResponse userResponse = shouldBeABleToCreateAnUser();
			assertresponse(userResponse);
			APIResponse eventResponse = shouldBeAbleToCreateAnEvent();
			assertresponse(eventResponse);
			APIResponse itemResponseTshirt = shouldBeAbleToCreateAdditionalItems("T-Shirt", 21.30);
			assertresponse(itemResponseTshirt);
			APIResponse itemResponseCap = shouldBeAbleToCreateAdditionalItems("Cap", 51.30);
			assertresponse(itemResponseCap);
			shouldBeAbleToSubscribeToAnEvent(((User) userResponse.getData()).getId(), ((EventDTO) eventResponse.getData()).getId(), ((AdditionalItem) itemResponseTshirt.getData()), ((AdditionalItem) itemResponseCap.getData()));
			APIResponse savedEventsResponse = shouldBeAbleToRetrieveAllSavedEvents(((User) userResponse.getData()).getId());
			assertresponse(savedEventsResponse);
			APIResponse deleteResponse = shouldBeAbleToDeleteASubscription(((User) userResponse.getData()).getId(), ((EventDTO) eventResponse.getData()).getId());
			assertresponse(deleteResponse);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably!!");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void shouldBeAbleToRetrieveAllAdditionalItems(){
		try {
			APIResponse itemResponseTshirt = shouldBeAbleToCreateAdditionalItems("T-Shirt~1", 21.30);
			assertresponse(itemResponseTshirt);
			APIResponse itemResponseCap = shouldBeAbleToCreateAdditionalItems("Cap~1", 51.30);
			assertresponse(itemResponseCap);
			APIResponse addlItemsResponse = subscriptionController.getAllAdditionalItem();
			assertresponse(addlItemsResponse);
			Assert.assertFalse(((List<Object>)addlItemsResponse.getData()).isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably!!");
		}
	}
	
	@Test
	@Ignore
	public void shouldBeAbleToCreateTiltCampaignWhenMinPassengerIsReached() throws Exception{
		shouldBeAbleToCreateTiltAdminUser();
		APIResponse eventResponse = shouldBeAbleToCreateAnEvent();
		assertresponse(eventResponse);
		APIResponse itemResponseTshirt = shouldBeAbleToCreateAdditionalItems("T-Shirt", 21.30);
		assertresponse(itemResponseTshirt);
		APIResponse itemResponseCap = shouldBeAbleToCreateAdditionalItems("Cap", 51.30);
		assertresponse(itemResponseCap);
		for(int i=0;i<3;i++){
			APIResponse userResponse = shouldBeABleToCreateAnUser(i);
			shouldBeAbleToSubscribeToAnEvent(((User) userResponse.getData()).getId(), ((EventDTO) eventResponse.getData()).getId(), ((AdditionalItem) itemResponseTshirt.getData()), ((AdditionalItem) itemResponseCap.getData()));
		}
		CampaignModel campaignModel = tiltService.getCampaignApi().getCampaign(eventService.findById(((EventDTO) eventResponse.getData()).getId()).getTiltCampaignId());
		assertNotNull(campaignModel);
		assertTrue(campaignModel.getIsTilted() == 1);
	}
	
	@Test
	@Ignore
	public void shouldBeAbleToFreezeAndLaunchNewCampaignWhenMaxPassengerIsReached() throws Exception {
		shouldBeAbleToCreateTiltAdminUser();
		APIResponse eventResponse = shouldBeAbleToCreateAnEvent();
		assertresponse(eventResponse);
		String eventTitle = ((EventDTO)eventResponse.getData()).getTitle();
		APIResponse itemResponseTshirt = shouldBeAbleToCreateAdditionalItems("T-Shirt", 21.30);
		assertresponse(itemResponseTshirt);
		APIResponse itemResponseCap = shouldBeAbleToCreateAdditionalItems("Cap", 51.30);
		assertresponse(itemResponseCap);
		for(int i=0;i<11;i++){
			APIResponse userResponse = shouldBeABleToCreateAnUser(i);
			Event eventToBoard = eventService.findByTitle(eventTitle, 0).iterator().next();
			shouldBeAbleToSubscribeToAnEvent(((User) userResponse.getData()).getId(), eventToBoard.getId(), ((AdditionalItem) itemResponseTshirt.getData()), ((AdditionalItem) itemResponseCap.getData()));
		}
		Collection<Event> eventsNew = eventService.findByTitle(((EventDTO)eventResponse.getData()).getTitle(), 0);
		assertNotNull(eventsNew);
		assertFalse(eventsNew.isEmpty());
		assertTrue(eventsNew.size()==1);
		assertFalse(eventsNew.iterator().next().getId() == ((EventDTO)eventResponse.getData()).getId());
	}
	private void shouldBeAbleToCreateTiltAdminUser() {
		UserModel user = tiltService.getUserApi().createUser(new UserModel("dguha-admin@dsapient.com", "Debal-Admin", "Guha"));
		verifyAnUser(user.getId());
		BankModel bankModel = tiltService.getUserApi().createUserBank(new BankModel("CITI Bank", "123-222-5543", "026009593"), user.getId());
		tiltService.getUserApi().makeUserbankDefault(user.getId(), bankModel);
		System.setProperty("tiltAdminUserId", user.getId());
	}
	protected APIResponse shouldBeABleToCreateAnUser(int i) throws Exception {
		UserModel user = tiltService.getUserApi().createUser(new UserModel("dguha-"+i+"@dsapient.com", "Debal-"+i, "Guha"));
		verifyAnUser(user.getId());
		CardModel usercard = tiltService.getUserApi().createUserCard(new CardModel("12", 2019, "483", "4111111111111111"), user.getId());
		UserRegistrationForm form = new UserRegistrationForm("dguha-"+i+"@dsapient.com", "passw0rd", "9830189052", "1981-03-22", "Debal Guha", "false", "false", "dguha-"+i+"@facebook.com", user.getId(), usercard.getId());
		return consumerController.registerUser(form);
	}
	
	protected ResponseMessage verifyAnUser(String userId){
		return tiltService.getUserApi().verifyUser(new VerificationModel("Test User", new Date(), "2673-9113", "10 Elis Way", "E20 1AL"), userId);
	}
	
	protected void assertresponse(APIResponse response) {
		Assert.assertNotNull(response);
		Assert.assertEquals(STATUS.SUCCESS, response.getStatus());
	}

	protected APIResponse shouldBeAbleToDeleteASubscription(Long userId, Long eventId) {
		return subscriptionController.deleteSubscription(userId, eventId);
	}

	protected APIResponse shouldBeAbleToRetrieveAllSavedEvents(Long id) {
		return subscriptionController.getAllSavedEvents(id);
	}

	protected APIResponse shouldBeAbleToSubscribeToAnEvent(Long userId, Long eventId, AdditionalItem additionalItem_1, AdditionalItem additionalItem_2) throws Exception {
		Collection<AdditionalItemDTO> items = Lists.newArrayList();
		items.add(new AdditionalItemDTO(additionalItem_1.getId(), 1));
		items.add(new AdditionalItemDTO(additionalItem_2.getId(), 2));

		SubscriptionDTO subsDTO = new SubscriptionDTO(userId, eventId, items);
		return subscriptionController.subscribeEvent(subsDTO);
	}

	protected APIResponse shouldBeAbleToCreateAdditionalItems(String itemName, Double price) throws JsonProcessingException {
		AdditionalItemDTO itemDto = new AdditionalItemDTO(-1, 0, itemName, price);
		System.out.println(new ObjectMapper().writeValueAsString(itemDto));
		return subscriptionController.createAdditionalItem(itemDto);
	}

	protected APIResponse shouldBeAbleToCreateAnEvent() throws Exception {
		return shouldBeAbleToCreateAnEvent(String.valueOf(RandomUtils.nextDouble()));
	}
	
	protected APIResponse shouldBeAbleToCreateAnEvent(String eventName) throws Exception {
		Set<String> categories = Sets.newHashSet();
		categories.add("Sports");
		categories.add("College");
		PickupLocationDTO pickup = new PickupLocationDTO("categories", "categories", "categories", "categories", "-11.1111", "11.1111", "10:0");
		Set<PickupLocationDTO> pickups = Sets.newHashSet();
		pickups.add(pickup);
		EventDTO eventDTO = new EventDTO(-1, 120d, 1, EventType.Bus, 0, "Test Event-"+eventName, "Home", 0, ProjectUtil.dateTimeFormatForTests.parse("2015/03/17 09:00"), "Test", "Test", "Test", "Test", 2, 3, ProjectUtil.dateTimeFormatForTests.parse("2018/03/17 15:00"), EventState.SF, categories, pickups, "");
		return eventController.submitEvent(eventDTO);
	}

	protected APIResponse shouldBeABleToCreateAnUser() throws Exception {
		UserRegistrationForm form = new UserRegistrationForm("dguha@dsapient.com", "passw0rd", "9830189052", "1981-03-22", "Debal Guha", "false", "false", "dguha@facebook.com", "", "");
		return consumerController.registerUser(form);
	}
}
