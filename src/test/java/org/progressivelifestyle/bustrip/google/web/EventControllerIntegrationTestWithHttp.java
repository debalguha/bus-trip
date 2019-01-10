package org.progressivelifestyle.bustrip.google.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class EventControllerIntegrationTestWithHttp extends AbstractBaseTest{
	private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

	@Test
	public void shouldBeAbleToRetrieveFirstPageOfNYEvents() throws Exception {
		List<EventDTO> response = sendWEBRequestForNY(getBaseURL()+"bus-trip/rest/NY/events/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToRetrieveFirstPageOfSFEvents() throws Exception {
		List<EventDTO> response = sendWEBRequestForSF(getBaseURL()+"bus-trip/rest/SF/events/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToRetrieveSecondPageOfSFEvents() throws Exception {
		List<EventDTO> response = sendWEBRequestForSF(getBaseURL()+"bus-trip/rest/SF/events/page/2");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnTitleForNY() throws Exception {
		List<EventDTO> response = sendWEBRequestForNY(getBaseURL()+"bus-trip/rest/NY/events/search/title/Giants/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnTitleForSF() throws Exception {
		List<EventDTO> response = sendWEBRequestForSF(getBaseURL()+"bus-trip/rest/SF/events/search/title/Beyond/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnLocationForNY() throws Exception {
		List<EventDTO> response = sendWEBRequestForNY(getBaseURL()+"bus-trip/rest/NY/events/search/location/Rutherford/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnLocationForSF() throws Exception {
		List<EventDTO> response = sendWEBRequestForSF(getBaseURL()+"bus-trip/rest/SF/events/search/location/shoreline/page/1");
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
		List<EventDTO> response = sendWEBRequestForSF(getBaseURL()+"bus-trip/rest/SF/events/search/event-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
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
		List<EventDTO> response = sendWEBRequestForNY(getBaseURL()+"bus-trip/rest/NY/events/search/event-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
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
		List<EventDTO> response = sendWEBRequestForSF(getBaseURL()+"bus-trip/rest/SF/events/search/expiration-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
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
		List<EventDTO> response = sendWEBRequestForNY(getBaseURL()+"bus-trip/rest/NY/events/search/expiration-date/" + URLEncoder.encode(dateFormat.format(cal.getTime()), "UTF-8") + "/page/1");
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void shouldBeAbleToFindAllCategoriesForSF() throws Exception {
		RestTemplate template = getRestTemplate();
		ParameterizedTypeReference<List<Category>> typeRef = new ParameterizedTypeReference<List<Category>>() {
		};
		// String url =
		// getBaseURL()+"bus-trip/rest/data/SF/categories";
		String url = getBaseURL()+"bus-trip/rest/data/SF/categories";
		List<Category> response = template.exchange(url, HttpMethod.GET, null, typeRef).getBody();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println("SF categories: " + response);
	}

	@Test
	public void shouldBeAbleToFindNYCategoriesBasedOnEvents() throws Exception {
		RestTemplate template = getRestTemplate();
		ParameterizedTypeReference<List<Category>> typeRef = new ParameterizedTypeReference<List<Category>>() {
		};
		// String url =
		// getBaseURL()+"bus-trip/rest/data/NY/categories";
		String url = getBaseURL()+"bus-trip/rest/data/NY/categories";
		List<Category> categoryResponse = template.exchange(url, HttpMethod.GET, null, typeRef).getBody();
		Long categoryId = categoryResponse.iterator().next().getId();
		// List<EventDTO> response =
		// sendWEBRequestForNY(getBaseURL()+"bus-trip/rest/NY/events/search/category?category="+categoryId);
		List<EventDTO> response = sendWEBRequestForNY(getBaseURL()+"bus-trip/rest/NY/events/search/category?category=" + categoryId);
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println(response);
	}

	@Test
	public void shouldBeAbleToFindAllCategoriesForNY() throws Exception {
		RestTemplate template = getRestTemplate();
		ParameterizedTypeReference<List<Category>> typeRef = new ParameterizedTypeReference<List<Category>>() {
		};
		// String url =
		// getBaseURL()+"bus-trip/rest/data/NY/categories";
		String url = getBaseURL()+"bus-trip/rest/data/NY/categories";
		List<Category> response = template.exchange(url, HttpMethod.GET, null, typeRef).getBody();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println("NY categories: " + response);
	}

	private List<EventDTO> sendWEBRequestForNY(String url) throws Exception {
		System.out.println(url);
		RestTemplate template = getRestTemplate();
		/*template.setErrorHandler(new ResponseErrorHandler() {

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return response.getStatusCode().equals(HttpStatus.OK);
			}

			@SuppressWarnings("unchecked")
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				List<String> errors = IOUtils.readLines(response.getBody());
				logger.log(Level.SEVERE, StringUtils.collectionToCommaDelimitedString(errors));
			}
		});*/
		ParameterizedTypeReference<List<EventDTO>> typeRef = new ParameterizedTypeReference<List<EventDTO>>() {
		};
		return template.exchange(url, HttpMethod.GET, null, typeRef).getBody();
	}

	private List<EventDTO> sendWEBRequestForSF(String url) throws Exception {
		System.out.println(url);
		RestTemplate template = getRestTemplate();
		/*template.setErrorHandler(new ResponseErrorHandler() {

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return response.getStatusCode().equals(HttpStatus.OK);
			}

			@SuppressWarnings("unchecked")
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				List<String> errors = IOUtils.readLines(response.getBody());
				logger.log(Level.SEVERE, StringUtils.collectionToCommaDelimitedString(errors));
			}
		});*/
		ParameterizedTypeReference<List<EventDTO>> typeRef = new ParameterizedTypeReference<List<EventDTO>>() {
		};
		return template.exchange(url, HttpMethod.GET, null, typeRef).getBody();
	}
	@Test
	public void shouldBeAbleToListSFEventsWeekly() throws Exception {
		RestTemplate template = getRestTemplate();
		ParameterizedTypeReference<List<EventDTO>> typeRef = new ParameterizedTypeReference<List<EventDTO>>() {
		};
		// String url =
		// getBaseURL()+"bus-trip/rest/data/NY/categories";
		String url = getBaseURL()+"bus-trip/rest/SF/events/weekly?weekEndDate=09-20-2014";
		List<EventDTO> response = template.exchange(url, HttpMethod.GET, null, typeRef).getBody();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println("SF events: " + response);
	}
	
	@Test
	public void shouldBeAbleToListNYEventsWeekly() throws Exception {
		RestTemplate template = getRestTemplate();
		ParameterizedTypeReference<List<EventDTO>> typeRef = new ParameterizedTypeReference<List<EventDTO>>() {
		};
		// String url =
		// getBaseURL()+"bus-trip/rest/data/NY/categories";
		String url = getBaseURL()+"bus-trip/rest/NY/events/weekly?weekEndDate=09-25-2014";
		List<EventDTO> response = template.exchange(url, HttpMethod.GET, null, typeRef).getBody();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		System.out.println("NY events: " + response);
	}	
}
