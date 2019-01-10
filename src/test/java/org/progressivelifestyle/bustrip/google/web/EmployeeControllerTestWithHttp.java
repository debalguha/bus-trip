package org.progressivelifestyle.bustrip.google.web;

import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.web.STATUS;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class EmployeeControllerTestWithHttp extends ConsumerControllerTestWithHttp {
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void runEmployeeAssignmentTests() throws Exception {
		try {
			shouldBeAbleToRegisterAnEmployee();
			Map<String, Object> employee = (Map<String, Object>)shouldBeAbleToRetrieveAnEmployee();
			Map<String, Object> event = (Map<String, Object>)shouldBeAbleToCreateAnEvent();
			shouldBeAbleToAssignAnEventToUser(Long.parseLong(employee.get("id").toString()), new Long(event.get("id").toString()));
			shouldBeAbleToRetrieveAnAssignment(Long.parseLong(employee.get("id").toString()), new Long(event.get("id").toString()));
			shouldBeAbleToCancelAnAssignment(Long.parseLong(employee.get("id").toString()), new Long(event.get("id").toString()));
			shouldBeAbleToDeleteAnAssignment(Long.parseLong(employee.get("id").toString()), new Long(event.get("id").toString()));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void runBusRunningInfoTests() throws Exception {
		try {
			shouldBeAbleToRegisterAnEmployee();
			Map<String, Object> employee = (Map<String, Object>)shouldBeAbleToRetrieveAnEmployee();
			Map<String, Object> event = (Map<String, Object>)shouldBeAbleToCreateAnEvent();
			shouldBeAbleToAssignAnEventToUser(Long.parseLong(employee.get("id").toString()), new Long(event.get("id").toString()));
			double latitude = RandomUtils.nextDouble();
			double longitude = RandomUtils.nextDouble();
			shouldBeAbleToCreateBusRunningInfo(new Long(event.get("id").toString()), latitude, longitude);
			Map<String, Object> busRunningInfoDTOResponse = (Map<String, Object>)shouldBeAbleToRetrieveBusRunningInfo(new Long(event.get("id").toString()));
			Assert.assertEquals(latitude, Double.parseDouble(busRunningInfoDTOResponse.get("latitude").toString()), 0);
			Assert.assertEquals(longitude, Double.parseDouble(busRunningInfoDTOResponse.get("longitude").toString()), 0);
			shouldBeAbleToDeleteAnAssignment(Long.parseLong(employee.get("id").toString()), new Long(event.get("id").toString()));
			shouldBeAbleToDeleteAnEmployee();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
	}

	private void shouldBeAbleToDeleteAnAssignment(long employeeId, long eventId) {
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/emp/event/assignment/delete");
		System.out.println("Cancel: "+url);
		url.append("?empId=").append(employeeId).append("&eventId=").append(eventId);
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());		
	}

	private void shouldBeAbleToCancelAnAssignment(Long employeeId, Long eventId) {
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/emp/event/assignment/cancel");
		System.out.println("Cancel: "+url);
		url.append("?empId=").append(employeeId).append("&eventId=").append(eventId);
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());
	}

	private void shouldBeAbleToRetrieveAnAssignment(Long employeeId, Long eventId) {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.DAY_OF_WEEK, startCal.getFirstDayOfWeek());
		
		Calendar endCal = Calendar.getInstance();
		startCal.set(Calendar.DAY_OF_WEEK, endCal.getFirstDayOfWeek()+6);
		
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/emp/event/assignment/get");
		url.append("?empId=").append(employeeId).append("&startDate=").append(ProjectUtil.dateTimeFormatForTests.format(startCal.getTime()));
		url.append("&endDate=").append(ProjectUtil.dateTimeFormatForTests.format(endCal.getTime()));
		System.out.println("Range Get: "+url.toString());
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());		
	}

	private void shouldBeAbleToAssignAnEventToUser(Long employeeId, Long eventId) {
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/emp/event/assignment/create");
		url.append("?empId=").append(employeeId).append("&eventId=").append(eventId);
		System.out.println("Create: "+url.toString());
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());
	}

	private Object shouldBeAbleToCreateAnEvent() {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.DAY_OF_WEEK, startCal.getFirstDayOfWeek());
		
		Calendar endCal = Calendar.getInstance();
		startCal.set(Calendar.DAY_OF_WEEK, endCal.getFirstDayOfWeek()+6);
		String eventJSON = "{\"id\":-1,\"price\":120.5,\"featured\":1,\"eventType\":\"Bus\",\"hidden\":0,\"title\":\"Test Event\",\"eventLocation\":\"Home\",\"upsellOrUpgradeEvent\":0,\"eventDateTime\":1415696400000,\"shortDesc\":\"Test\",\"description\":\"Test\",\"lineup\":\"Test\",\"imageURL\":\"Test\",\"minPassenger\":10,\"maxPassenger\":100,\"expiration\":1418310000000,\"eventState\":\"SF\",\"eventcategory\":[\"Sports\",\"College\"],\"pickupLocations\":[{\"neighborhoodName\":\"categories\",\"information\":\"categories\",\"pickupName\":\"categories\",\"pickupAddress\":\"categories\",\"latitude\":\"-11.1111\",\"longitude\":\"11.1111\",\"pickupTime\":\"10:0\"}]}";
		System.out.println(eventJSON);
		RestTemplate template = getRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(eventJSON, headers);
		return template.postForEntity(getBaseURL()+"/bus-trip/rest/RW/event/submit", request, APIResponse.class).getBody().getData();
	}
	
	private void shouldBeAbleToCreateBusRunningInfo(Long eventId, double latitude, double longitude) {
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/emp/event/bus/location/update");
		url.append("?eventId=").append(eventId).append("&lat=").append(latitude).append("&long=").append(longitude);
		System.out.println("Create: "+url.toString());
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());
	}
	
	private Object shouldBeAbleToRetrieveBusRunningInfo(Long eventId) {
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/profile/event/bus/location");
		url.append("?eventId=").append(eventId);
		System.out.println("Create: "+url.toString());
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());
		return apiResponse.getData();
	}
}
