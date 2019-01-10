package org.progressivelifestyle.bustrip.google;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.web.EventController;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class EventControllerIntegrationTest {
	private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

	@Autowired
	private EventController controller;
	@Test
	public void shouldBeAbleToRetrieveFirstPageOfNYEvents() throws Exception {
		Collection<EventDTO> response = controller.getNYEvents(1);//sendWEBRequestForNY("http://localhost:8080/bus-trip/rest/NY/events/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToRetrieveFirstPageOfSFEvents() throws Exception {
		Collection<EventDTO> response = controller.getSFEvents(1);//sendWEBRequestForSF("http://localhost:8080/bus-trip/rest/SF/events/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToRetrieveSecondPageOfSFEvents() throws Exception {
		Collection<EventDTO> response = controller.getSFEvents(2);//sendWEBRequestForSF("http://localhost:8080/bus-trip/rest/SF/events/page/2");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnTitleForNY() throws Exception {
		Collection<EventDTO> response = controller.getNYEventsByTitle(1, "Giants");//sendWEBRequestForNY("http://localhost:8080/bus-trip/rest/NY/events/search/title/Giants/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnTitleForSF() throws Exception {
		Collection<EventDTO> response = controller.getSFEventsByTitle(1, "Beyond");//sendWEBRequestForSF("http://localhost:8080/bus-trip/rest/SF/events/search/title/Beyond/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnLocationForNY() throws Exception {
		Collection<EventDTO> response = controller.getNYEventsByLocation(1, "Rutherford");//sendWEBRequestForNY("http://localhost:8080/bus-trip/rest/NY/events/search/location/Rutherford/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnLocationForSF() throws Exception {
		Collection<EventDTO> response = controller.getSFEventsByLocation(1, "shoreline");//sendWEBRequestForSF("http://localhost:8080/bus-trip/rest/SF/events/search/location/shoreline/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnEventDateForSF() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<EventDTO> response = controller.getSFEventsByEventDate(1, URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8"));//)sendWEBRequestForSF("http://localhost:8080/bus-trip/rest/SF/events/search/event-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnEventDateForNY() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 28);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<EventDTO> response = controller.getNYEventsByEventDate(1, URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8"));//sendWEBRequestForNY("http://localhost:8080/bus-trip/rest/NY/events/search/event-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnExpirationDateForSF() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<EventDTO> response = controller.getSFEventsByExpirationDate(1, URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8"));//sendWEBRequestForSF("http://localhost:8080/bus-trip/rest/SF/events/search/expiration-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnExpirationDateForNY() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 21);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<EventDTO> response = controller.getNYEventsByExpirationDate(1, URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8"));//sendWEBRequestForNY("http://localhost:8080/bus-trip/rest/NY/events/search/expiration-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToFindAllCategoriesForSF() throws Exception {
		Collection<Category> response = controller.retrieveAllSFCategories();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println("SF categories: " + response);
	}

	@Test
	public void shouldBeAbleToFindNYEventsBasedOnCategory() throws Exception {
		Collection<Category> categoryResponse = controller.retrieveAllNYCategories();
		Long categoryId = categoryResponse.iterator().next().getId();
		Collection<EventDTO> response = controller.getAllNYEventsByCategory(categoryId);//sendWEBRequestForNY("http://localhost:8080/bus-trip/rest/NY/events/search/category?category=" + categoryId);
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println(response);
	}

	@Test
	public void shouldBeAbleToFindAllCategoriesForNY() throws Exception {
		Collection<Category> response = controller.retrieveAllNYCategories();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println("NY categories: " + response);
	}

}
