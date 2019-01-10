package org.progressivelifestyle.bustrip.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/RW")
public class EventRWController extends AbstractEventController{
	private static final Logger logger = Logger.getLogger(EventRWController.class.getName());
	
	@RequestMapping(value = "/event/submit", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody APIResponse submitEvent(@RequestBody EventDTO eventDTO) throws Exception{
		try {
			return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, dtoUtils.toEventDTO(service.create(dtoUtils.createEventFromDTO(eventDTO))));//toEventDTO(service.create(createEventFromDTO(eventDTO)));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception: ", e);
			e.printStackTrace();
			return new APIResponse(ERROR_CODE.EVENT_SUBMIT_ERROR, STATUS.FAIL, eventDTO);
		}
	}
	
	@RequestMapping(value = "/event/delete/{id}", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String deleteEvent(@PathVariable(value = "id") long id) throws Exception{
		try {
			Event event = service.findById(id);
			service.deleteEvent(event);
			return STATUS.SUCCESS.name();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception: ", e);
			e.printStackTrace();
			throw e;
		}
	}
	
	@RequestMapping("/event/edit")
	public ModelAndView editEvent(HttpServletRequest req){
		String mode = req.getParameter("mode");
		long eventId = Long.parseLong(req.getParameter("eventId"));
		req.setAttribute("mode", mode);
		req.setAttribute("eventId", eventId);
		return new ModelAndView("forms");
	}
	
	@ExceptionHandler(Throwable.class)
	public @ResponseBody String handleException(Throwable t){
		logger.log(Level.SEVERE, "Exception: ", t);
		t.printStackTrace();
		return "ERROR";
	}
}
