package org.progressivelifestyle.bustrip.google;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.web.CustomJacksonMapper;
import org.progressivelifestyle.bustrip.web.EventController;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class EventControllerTest {
	@Autowired
	private EventController controller;
	
	@Test
	@Ignore
	public void shouldBeAbleToRetrieveFirstPageOfNYEvents() throws Exception{
		Collection<EventDTO> nyEvents = controller.getNYEvents(1);
		Assert.assertNotNull(nyEvents);
		Assert.assertFalse(nyEvents.isEmpty());
		for(EventDTO event : nyEvents){
			Assert.assertNotNull(event.getPickupLocations());
			Assert.assertFalse(event.getPickupLocations().isEmpty());
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, nyEvents);
		System.out.println("#########################");
	}
	@Test
	@Ignore
	public void shouldBeAbleToRetrieveFirstPageOfSFEvents() throws Exception{
		Collection<EventDTO> sfEvents = controller.getSFEvents(1);
		Assert.assertNotNull(sfEvents);
		Assert.assertFalse(sfEvents.isEmpty());
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, sfEvents);
		System.out.println("#########################");
	}
	@Test
	@Ignore
	public void shouldBeAbleToRetrieveSecondPageOfSFEvents() throws Exception{
		Collection<EventDTO> sfEvents = controller.getSFEvents(2);
		Assert.assertNotNull(sfEvents);
		Assert.assertFalse(sfEvents.isEmpty());
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, sfEvents);
		System.out.println("#########################");
	}
	@Test
	public void shouldBeAbleToRetrieveAllTheCategoriesForSF() throws Exception {
		Collection<Category> allSFCategories = controller.retrieveAllSFCategories();
		assertNotNull(allSFCategories);
		assertFalse(allSFCategories.isEmpty());
	}
	@Test
	public void shouldBeAbleToRetrieveAllTheCategoriesForNY() throws Exception {
		Collection<Category> allNYCategories = controller.retrieveAllNYCategories();
		assertNotNull(allNYCategories);
		assertFalse(allNYCategories.isEmpty());
	}
	@Test
	public void shouldBeAbleToRetrieveNYEventsBasedOnCategory() throws Exception {
		try {
			Collection<Category> allNYCategories = controller.retrieveAllNYCategories();
			Collection<EventDTO> nyEventsByCategory = controller.getNYEventsByCategory(allNYCategories.iterator().next().getId());
			assertNotNull(nyEventsByCategory);
			assertFalse(nyEventsByCategory.isEmpty());
			ObjectMapper mapper = new CustomJacksonMapper();
			mapper.writeValue(System.out, nyEventsByCategory);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
		
	}

}
