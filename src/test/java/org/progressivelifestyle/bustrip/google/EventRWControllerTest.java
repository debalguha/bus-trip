package org.progressivelifestyle.bustrip.google;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.web.CustomJacksonMapper;
import org.progressivelifestyle.bustrip.web.EventRWController;
import org.progressivelifestyle.bustrip.web.dto.DTOUtils;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })	
public class EventRWControllerTest {
	@Autowired
	private EventRWController controller;
	@Autowired
	private DTOUtils dtoUtils;

	@Test
	//@Ignore
	public void shouldBeAbleToCreateAnEvent() {
		try {
			String jsonStr = "{\"synced\":true,\"id\":0,\"price\":\"112\",\"title\":\"forms.jsp\",\"eventType\":\"Pub\",\"hidden\":\"1\",\"eventLocation\":\"forms.jsp\",\"upsellOrUpgradeEvent\":\"1\",\"eventDateTime\":\"2014/11/10 07:30\",\"description\":\"forms.jsp\",\"lineup\":\"forms.jsp\",\"imageURL\":\"forms.jsp\",\"minPassenger\":\"1\",\"maxPassenger\":\"11\",\"expiration\":\"2014/11/17 15:00\",\"eventState\":\"SF\",\"eventcategory\":[\"Football\",\"Pro\"],\"pickupLocations\":[{\"neighborhoodName\":\"forms.jsp\",\"information\":\"forms.jsp\",\"pickupName\":\"forms.jsp\",\"longitude\":\"11\",\"latitude\":\"11\",\"pickupAddress\":\"forms.jsp\",\"pickupTime\":\"11:00\",\"uid\":0}],\"featured\":\"1\"}";
			EventDTO eventDTO = new CustomJacksonMapper().readValue(jsonStr, EventDTO.class);
			EventDTO submitEvent = (EventDTO)controller.submitEvent(eventDTO).getData();
			assertNotNull(submitEvent);
			assertTrue(submitEvent.getId() > 0);
			controller.deleteEvent(submitEvent.getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed miserably");
		}
	}

	@Test
	@Ignore
	public void shouldBeAbleToConvertDTOIntoEvent() throws Exception {
		try {
			String jsonStr = "{\"synced\":true,\"id\":1,\"price\":\"112\",\"title\":\"forms.jsp\",\"eventType\":\"Pub\",\"hidden\":\"1\",\"eventLocation\":\"forms.jsp\",\"upsellOrUpgradeEvent\":\"1\",\"eventDateTime\":\"2014/11/16 09:00\",\"description\":\"forms.jsp\",\"lineup\":\"forms.jsp\",\"imageURL\":\"forms.jsp\",\"minPassenger\":\"1\",\"maxPassenger\":\"11\",\"expiration\":\"2014/11/23 21:00\",\"eventState\":\"SF\",\"eventcategory\":[\"Sports\",\"College\"],\"pickupLocations\":[{\"neighborhoodName\":\"forms.jsp\",\"information\":\"forms.jsp\",\"pickupName\":\"forms.jsp\",\"longitude\":\"11\",\"latitude\":\"11\",\"pickupAddress\":\"forms.jsp\",\"pickupTime\":\"11:00\",\"uid\":0},{\"uid\":1,\"id\":1,\"neighborhoodName\":\"forms.jsp_1\",\"pickupTime\":\"11:30\",\"information\":\"forms.jsp_1\",\"pickupName\":\"forms.jsp_1\",\"pickupAddress\":\"forms.jsp_1\",\"latitude\":\"11\",\"longitude\":\"11\"}],\"featured\":\"1\"}";
			EventDTO eventDTO = new CustomJacksonMapper().readValue(jsonStr, EventDTO.class);
			Event event = dtoUtils.createEventFromDTO(eventDTO);
			assertNotNull(event);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed miserably");
		}
	}
}
