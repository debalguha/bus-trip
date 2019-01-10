package org.progressivelifestyle.bustrip.google;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.consumer.Employee;
import org.progressivelifestyle.bustrip.google.domain.EventState;
import org.progressivelifestyle.bustrip.web.ConsumerController;
import org.progressivelifestyle.bustrip.web.EmployeeController;
import org.progressivelifestyle.bustrip.web.EventRWController;
import org.progressivelifestyle.bustrip.web.STATUS;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.BusRunningInfoDTO;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.progressivelifestyle.bustrip.web.dto.PickupLocationDTO;
import org.progressivelifestyle.bustrip.web.dto.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Sets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class EmployeeControllerTest {
	@Autowired
	private EmployeeController empController;
	
	@Autowired
	private ConsumerController consumerController;
	
	@Autowired
	private EventRWController eventController;
	
	@Test
	@Ignore
	public void runEmployeeAssignmentTests() throws Exception {
		try {
			APIResponse empResponse = shouldBeAbleToRegisterAnEmployee();
			assertresponse(empResponse);
			APIResponse eventResponse = shouldBeAbleToCreateAnEvent();
			assertresponse(eventResponse);
			APIResponse assignmentResponse = shouldBeAbleToAssignAnEventToUser(((Employee)(empResponse.getData())).getId(), ((EventDTO)(eventResponse.getData())).getId());
			assertresponse(assignmentResponse);
			assignmentResponse = shouldBeAbleToRetrieveAnAssignment(((Employee)(empResponse.getData())).getId(), getWeekStartDate(0), getWeekStartDate(6));
			assertresponse(assignmentResponse);
			assignmentResponse = shouldBeAbleToCancelAnAssignment(((Employee)(empResponse.getData())).getId(), ((EventDTO)(eventResponse.getData())).getId());
			assertresponse(assignmentResponse);
			assignmentResponse = shouldBeAbleToDeleteAnAssignment(((Employee)(empResponse.getData())).getId(), ((EventDTO)(eventResponse.getData())).getId());
			assertresponse(assignmentResponse);
			Assert.assertEquals("SUCCESS",consumerController.deletEmployee(((Employee)(empResponse.getData())).getEmailId()));
			Assert.assertEquals("SUCCESS", eventController.deleteEvent(((EventDTO)(eventResponse.getData())).getId()));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
	}
	
	@Test
	public void runBusRunningInfoTest() throws Exception {
		try {
			APIResponse empResponse = shouldBeAbleToRegisterAnEmployee();
			assertresponse(empResponse);
			APIResponse eventResponse = shouldBeAbleToCreateAnEvent();
			assertresponse(eventResponse);
			APIResponse assignmentResponse = shouldBeAbleToAssignAnEventToUser(((Employee)(empResponse.getData())).getId(), ((EventDTO)(eventResponse.getData())).getId());
			assertresponse(assignmentResponse);
			double latitude = RandomUtils.nextDouble();
			double longitude = RandomUtils.nextDouble();
			APIResponse busRunningInfoResponse = shouldBeAbleToCreateBusRunningInfo(((EventDTO)(eventResponse.getData())).getId(), latitude, longitude, "HR51AQ4382");
			assertresponse(busRunningInfoResponse);
			busRunningInfoResponse = shouldBeAbleToRetrieveBusRunningInfo(((EventDTO)(eventResponse.getData())).getId(), "HR51AQ4382");
			assertEquals(((BusRunningInfoDTO)(busRunningInfoResponse.getData())).getLatitude(), latitude, 0);
			assertEquals(((BusRunningInfoDTO)(busRunningInfoResponse.getData())).getLongitude(), longitude, 0);
			//Update case
			busRunningInfoResponse = shouldBeAbleToCreateBusRunningInfo(((EventDTO)(eventResponse.getData())).getId(), latitude+0.0023d, longitude+0.0023d, "HR51AQ4382");
			assertresponse(busRunningInfoResponse);
			//Check the updated record
			busRunningInfoResponse = shouldBeAbleToRetrieveBusRunningInfo(((EventDTO)(eventResponse.getData())).getId(), "HR51AQ4382");
			assertEquals(((BusRunningInfoDTO)(busRunningInfoResponse.getData())).getLatitude(), latitude+0.0023d, 0);
			assertEquals(((BusRunningInfoDTO)(busRunningInfoResponse.getData())).getLongitude(), longitude+0.0023d, 0);
			busRunningInfoResponse = shouldBeAbleToRemoveBusRunningInfo(((EventDTO)(eventResponse.getData())).getId(), "HR51AQ4382");
			assertresponse(busRunningInfoResponse);			
			assignmentResponse = shouldBeAbleToDeleteAnAssignment(((Employee)(empResponse.getData())).getId(), ((EventDTO)(eventResponse.getData())).getId());
			assertresponse(assignmentResponse);
			assertEquals("SUCCESS",consumerController.deletEmployee(((Employee)(empResponse.getData())).getEmailId()));
			assertEquals("SUCCESS", eventController.deleteEvent(((EventDTO)(eventResponse.getData())).getId()));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
	}	

	private void assertresponse(APIResponse response) {
		Assert.assertNotNull(response);
		Assert.assertEquals(STATUS.SUCCESS, response.getStatus());
	}

	private APIResponse shouldBeAbleToDeleteAnAssignment(Long empId, Long eventId) {
		return empController.deleteAssignment(empId, eventId);
	}

	private APIResponse shouldBeAbleToCancelAnAssignment(Long empId, Long eventId) {
		return empController.cancelAnAssignment(empId, eventId);
	}

	private APIResponse shouldBeAbleToRetrieveAnAssignment(Long empId, Date weekStartDate, Date weekEndDate) {
		return empController.getAllAssignment(empId, weekStartDate, weekStartDate);
	}

	private Date getWeekStartDate(int offset) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()+offset);
		return cal.getTime();
	}

	private APIResponse shouldBeAbleToAssignAnEventToUser(long empId, long eventId) {
		return empController.assignEventToEmployee(empId, eventId);
	}

	private APIResponse shouldBeAbleToCreateAnEvent() throws Exception {
		Set<String> categories = Sets.newHashSet();
		categories.add("Sports");
		categories.add("College");
		PickupLocationDTO pickup = new PickupLocationDTO("categories", "categories", "categories", "categories", "-11.1111", "11.1111", "10:0");
		Set<PickupLocationDTO> pickups = Sets.newHashSet();
		pickups.add(pickup);
		EventDTO eventDTO = new EventDTO(-1, 120.5d, 1, EventType.Bus, 0, "Test Event", "Home", 0, ProjectUtil.dateTimeFormatForTests.parse("2014/11/11 09:00"), "Test", "Test", "Test", "Test", 10, 100, ProjectUtil.dateTimeFormatForTests.parse("2014/12/11 15:00"), EventState.SF, categories,  pickups, "");
		System.out.println(new ObjectMapper().writeValueAsString(eventDTO));
		return eventController.submitEvent(eventDTO);
	}

	private APIResponse shouldBeAbleToRegisterAnEmployee() throws Exception {
		UserRegistrationForm form = new UserRegistrationForm("dguha-emp@dsapient.com", "passw0rd", "9830189052", "1981-03-22", "Debal Guha", "false", "false", "dguha@facebook.com", "", "");
		return consumerController.registerEmployee(form);
	}
	
	private APIResponse shouldBeAbleToCreateBusRunningInfo(long eventId, double latitude, double longitude, String busNum){
		return empController.setBusRunningInfo(eventId, busNum, latitude, longitude);
	}
	
	private APIResponse shouldBeAbleToRemoveBusRunningInfo(long eventId, String busNum) {
		return empController.removeBusRunningInfo(eventId, busNum);
	}
	
	private APIResponse shouldBeAbleToRetrieveBusRunningInfo(long eventId, String busNum) throws Exception {
		return consumerController.getBusRunningInfo(eventId, busNum);
	}
}
