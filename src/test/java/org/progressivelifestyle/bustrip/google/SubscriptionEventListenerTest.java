package org.progressivelifestyle.bustrip.google;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.consumer.AdditionalItem;
import org.progressivelifestyle.bustrip.consumer.User;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class SubscriptionEventListenerTest extends SubscriptionControllerTest{
	@Autowired
	private EventService eventService;
	
	@Autowired
	private TiltService tiltService;
	
	@Test
	public void shouldBeAbleToCreateTiltCampaignWhenMinPassengerIsReached() throws Exception{
		shouldBeAbleToCreateTiltAdminUser();
		APIResponse eventResponse = shouldBeAbleToCreateAnEvent();
		assertresponse(eventResponse);
		APIResponse itemResponseTshirt = shouldBeAbleToCreateAdditionalItems("T-Shirt", 21.30);
		assertresponse(itemResponseTshirt);
		APIResponse itemResponseCap = shouldBeAbleToCreateAdditionalItems("Cap", 51.30);
		assertresponse(itemResponseCap);
		for(int i=0;i<11;i++){
			APIResponse userResponse = shouldBeABleToCreateAnUser(i);
			shouldBeAbleToSubscribeToAnEvent(((User) userResponse.getData()).getId(), ((EventDTO) eventResponse.getData()).getId(), ((AdditionalItem) itemResponseTshirt.getData()), ((AdditionalItem) itemResponseCap.getData()));
		}
		CampaignModel campaignModel = tiltService.getCampaignApi().getCampaign(eventService.findById(((EventDTO) eventResponse.getData()).getId()).getTiltCampaignId());
		Assert.assertNotNull(campaignModel);
		Assert.assertTrue(campaignModel.getIsTilted() == 1);
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
}
