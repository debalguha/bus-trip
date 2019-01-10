package org.progressivelifestyle.bustrip.web;

import java.util.Date;

import org.progressivelifestyle.bustrip.consumer.service.ConsumerService;
import org.progressivelifestyle.bustrip.consumer.service.EmployeeService;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/emp")
public class EmployeeController extends AbstractEventController{
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ConsumerService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody APIResponse listEmployeed() throws Exception {
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, service.findAllEmployees());
	}
	
	@RequestMapping(value = "/event/assignment/create", method = RequestMethod.GET)
	public @ResponseBody APIResponse assignEventToEmployee(@RequestParam(value="empId", required = true) long empId, @RequestParam(value="eventId", required = true) long eventId) {
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, empService.assignEmployeeToEvent(eventId, empId));
	}
	
	@RequestMapping(value = "/event/assignment/cancel", method = RequestMethod.GET)
	public @ResponseBody APIResponse cancelAnAssignment(@RequestParam(value="empId", required = true) long empId, @RequestParam(value="eventId", required = true) long eventId) {
		empService.cancelAssignment(empId, eventId);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
	}
	
	@RequestMapping(value = "/event/assignment/get", method = RequestMethod.GET)
	public @ResponseBody APIResponse getAllAssignment(@RequestParam(value="empId", required = true) long empId, @RequestParam(value="startDate", required = true) Date startDate, @RequestParam(value="endDate", required = true) Date endDate) {
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, empService.findAllEventsForDateRange(startDate, endDate, empId));
	}	
	
	@RequestMapping(value = "/event/assignment/delete", method = RequestMethod.GET)
	public @ResponseBody APIResponse deleteAssignment(@RequestParam(value="empId", required = true) long empId, @RequestParam(value="eventId", required = true) long eventId) {
		empService.deleteAssignment(empId, eventId);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
	}
	
	@RequestMapping(value="/event/bus/location/update", method=RequestMethod.GET)
	public @ResponseBody APIResponse setBusRunningInfo(@RequestParam(value="eventId", required=true) long eventId, @RequestParam(value="busNum", required=true) String busNum, @RequestParam(value="lat", required=true) Double latitude, @RequestParam(value="long", required = true) Double longitude) {
		empService.createOrUpdateBusRunningInfo(new Date(), latitude, longitude, eventId, busNum);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
	}
	
	@RequestMapping(value="/event/bus/location/delete", method=RequestMethod.GET)
	public @ResponseBody APIResponse removeBusRunningInfo(@RequestParam(value="eventId", required=true) long eventId, @RequestParam(value="busNum", required=true) String busNum) {
		empService.deletBusRunningInfo(eventId, busNum);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
	}
}
