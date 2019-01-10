package org.progressivelifestyle.bustrip.web;

import static org.progressivelifestyle.bustrip.ProjectUtil.filterForNY;
import static org.progressivelifestyle.bustrip.ProjectUtil.filterForSF;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.progressivelifestyle.bustrip.google.EventService;
import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventState;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.progressivelifestyle.bustrip.web.dto.PickupLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.util.Lists;

@Controller
public class EventController extends AbstractEventController{
	@Autowired
	private GoogleAuthHelper authHelper;

	@Value("${google.oauth.secret}")
	private String oAuthSecret;

	@Value("${google.oauth.id}")
	private String oAuthClientId;

	@Value("${google.oauth.authURL}")
	private String oAuthAuthURL;

	@Value("${google.oauth.callback}")
	private String oAuthCallbackURL;

	@Value("${google.oauth.spreadsheet.scope}")
	private String oAuthSpreadsheetScope;

	private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	// private static final Log logger =
	// LogFactory.getLog(EventController.class);
	//private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody EventDTO getEventById(@PathVariable(value = "id") long id){
		return dtoUtils.toEventDTO(service.findById(id));
	}
	
	@RequestMapping(value = "/events/dashboard", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody ModelAndView displayDashboard(@RequestParam(value = "isPast", required = false) boolean isPast){
		Collection<Event> all = isPast?service.findPastEventsOnly():service.findFutureEventsOnly();
		List<EventDTO> allDTOs = Lists.newArrayList();
		for(Event event : all)
			allDTOs.add(dtoUtils.toEventDTO(event));
		
		Collections.sort(allDTOs, new Comparator<EventDTO>() {
			@Override
			public int compare(EventDTO o1, EventDTO o2) {
				return o1.getEventDateTime().compareTo(o2.getEventDateTime());
			}
		});
		return new ModelAndView("dashboard", "events", allDTOs);
	}
	
	@RequestMapping(value = "/events/pickups/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Set<PickupLocationDTO> getEventPickupLocationsById(@PathVariable(value = "id") long id){
		return dtoUtils.toPickupLocationDTOs(service.findById(id).getPickupLocations());
	}
	
	@RequestMapping(value = "/NY/events/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getNYEvents(@PathVariable(value = "page") int page) {
		return dtoUtils.toEventDTOs(filterForNY(service.findAll(page - 1)));
	}

	@RequestMapping(value = "/SF/events/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getSFEvents(@PathVariable(value = "page") int page) {
		return dtoUtils.toEventDTOs(filterForSF(service.findAll(page - 1)));
	}

	@RequestMapping(value = "/SF/events/search/location/{location}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getSFEventsByLocation(@PathVariable(value = "page") int page, @PathVariable(value = "location") String location) {
		return dtoUtils.toEventDTOs(filterForSF(service.findByLocation(location, page - 1)));
	}

	@RequestMapping(value = "/NY/events/search/location/{location}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getNYEventsByLocation(@PathVariable(value = "page") int page, @PathVariable(value = "location") String location) {
		return dtoUtils.toEventDTOs(filterForNY(service.findByLocation(location, page - 1)));
	}

	@RequestMapping(value = "/SF/events/search/title/{title}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getSFEventsByTitle(@PathVariable(value = "page") int page, @PathVariable(value = "title") String title) {
		return dtoUtils.toEventDTOs(filterForSF(service.findByTitle(title, page - 1)));
	}

	@RequestMapping(value = "/NY/events/search/title/{title}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getNYEventsByTitle(@PathVariable(value = "page") int page, @PathVariable(value = "title") String title) {
		return dtoUtils.toEventDTOs(filterForNY(service.findByTitle(title, page - 1)));
	}

	@RequestMapping(value = "/SF/events/search/event-date/{eventDT}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getSFEventsByEventDate(@PathVariable(value = "page") int page, @PathVariable(value = "eventDT") String eventDT) throws ParseException {
		Date eventDate = dateFormat.parse(eventDT);
		return dtoUtils.toEventDTOs(filterForSF(service.findByEventDate(eventDate, page - 1)));
	}

	@RequestMapping(value = "/NY/events/search/event-date/{eventDT}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getNYEventsByEventDate(@PathVariable(value = "page") int page, @PathVariable(value = "eventDT") String eventDT) throws ParseException {
		Date eventDate = dateFormat.parse(eventDT);
		return dtoUtils.toEventDTOs(filterForNY(service.findByEventDate(eventDate, page - 1)));
	}

	@RequestMapping(value = "/SF/events/search/expiration-date/{expirationDT}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getSFEventsByExpirationDate(@PathVariable(value = "page") int page, @PathVariable(value = "expirationDT") String expirationDT) throws ParseException {
		Date expirationDate = dateFormat.parse(expirationDT);
		return dtoUtils.toEventDTOs(filterForSF(service.findByExpirationDate(expirationDate, page - 1)));
	}

	@RequestMapping(value = "/NY/events/search/expiration-date/{expirationDT}/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getNYEventsByExpirationDate(@PathVariable(value = "page") int page, @PathVariable(value = "expirationDT") String expirationDT) throws ParseException {
		Date expirationDate = dateFormat.parse(expirationDT);
		return dtoUtils.toEventDTOs(filterForNY(service.findByExpirationDate(expirationDate, page - 1)));
	}

	@RequestMapping(value = "/data/NY/categories", method = RequestMethod.GET)
	public @ResponseBody
	Collection<Category> retrieveAllNYCategories() {
		return ((EventService)service).getAllCategories(EventState.NY);
	}

	@RequestMapping(value = "/NY/events/search/category", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getNYEventsByCategory(@RequestParam(value = "category", required = true) Long category) throws ParseException {
		return dtoUtils.toEventDTOs(filterForNY(service.findByCategory(category)));
	}

	@RequestMapping(value = "/data/SF/categories", method = RequestMethod.GET)
	public @ResponseBody
	Collection<Category> retrieveAllSFCategories() {
		return ((EventService)service).getAllCategories(EventState.SF);
	}

	@RequestMapping(value = "/SF/events/search/category", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getSFEventsByCategory(@RequestParam(value = "category", required = true) Long category) throws ParseException {
		return dtoUtils.toEventDTOs(filterForSF(service.findByCategory(category)));
	}
	
	@RequestMapping(value = "/SF/events/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getAllSFEventsByCategory(@RequestParam(value = "category", required = true) Long category) throws ParseException {
		return dtoUtils.toEventDTOs(filterForSF(service.findByCategory(category)));
	}
	
	@RequestMapping(value = "/NY/events/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getAllNYEventsByCategory(@RequestParam(value = "category", required = true) Long category) throws ParseException {
		return dtoUtils.toEventDTOs(filterForNY(service.findByCategory(category)));
	}	

	@RequestMapping(value = "/NY/events/weekly", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getAllNYEventForWeek(@RequestParam(value = "weekEndDate", required = true) String weekEndDT) throws Exception{
		Date weekEndDate = dateFormat.parse(weekEndDT);
		Date weekStartDate = findWeekStartDate(weekEndDate);
		return dtoUtils.toEventDTOs(service.findAllEventsOnWeek(weekStartDate, weekEndDate, EventState.NY));
	}
	
	@RequestMapping(value = "/SF/events/weekly", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<EventDTO> getAllSFEventForWeek(@RequestParam(value = "weekEndDate", required = true) String weekEndDT) throws Exception{
		Date weekEndDate = dateFormat.parse(weekEndDT);
		Date weekStartDate = findWeekStartDate(weekEndDate);
		return dtoUtils.toEventDTOs(service.findAllEventsOnWeek(weekStartDate, weekEndDate, EventState.SF));
	}	
	
	@RequestMapping(value = "/event/update/campaign", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	APIResponse addTiltCampaignIdToEvent(@RequestParam(value = "eventId", required = true) Long eventId, @RequestParam(value = "campaignId", required = true) String campaignId) throws Exception{
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, dtoUtils.toEventDTO(service.updateEventWithCampaignId(eventId, campaignId)));
	}	
	
	@RequestMapping(value = "/data/import", method = RequestMethod.GET)
	public ModelAndView forwardToGoogleAuthorization(HttpSession session) throws Exception {
		/*
		 * OAuthService service = new
		 * ServiceBuilder().provider(GoogleApi.class).
		 * apiKey("anonymous").apiSecret
		 * ("anonymous").scope(oAuthSpreadsheetScope).build();
		 * logger.info("Fetching the Request Token..."); Token requestToken =
		 * service.getRequestToken(); logger.info("Got the Request Token = " +
		 * requestToken +", redirecting to authorize.");
		 * session.setAttribute("oAuthServiceInstance", service);
		 * session.setAttribute("oAuthRequestToken", requestToken); return
		 * "redirect:"+service.getAuthorizationUrl(requestToken);
		 */
		return new ModelAndView("redirect:".concat(authHelper.buildOAuthUrl()));
	}

	/*
	 * @RequestMapping(value = "/oauth2callback", method = RequestMethod.GET)
	 * public @ResponseBody String
	 * pullDataFromGoogleSpreadsheet(@RequestParam(value = "code", required =
	 * false) String oauthVerifier, HttpSession session) throws Exception {
	 * logger.info("Callback received."); String uniqueId = (String)
	 * session.getAttribute("UUID"); Credential credential = null; if (uniqueId
	 * == null) { uniqueId = UUID.randomUUID().toString();
	 * session.setAttribute("UUID", uniqueId); } else credential =
	 * authHelper.getStoredCredential(uniqueId); if (credential == null)
	 * credential = authHelper.generateCredential(oauthVerifier, uniqueId);
	 * logger.info("Credentials obtained. Initiating service call.");
	 * SpreadsheetService service = new SpreadsheetService("bus-trip");
	 * service.setOAuth2Credentials(credential); Map<String, Category>
	 * categoryIndex = null; try { categoryIndex = categoryService.findAll(); }
	 * catch (Exception e) { categoryIndex = Maps.newHashMap(); }
	 * SpreadsheetWorker worker = new SpreadsheetWorker(service, categoryIndex);
	 * logger.info("Call to worker method in process.");
	 * service.createAll(worker.getSpreadsheetDataForSF("bus feed"));
	 * logger.info("SF called");
	 * service.createAll(worker.getSpreadsheetEventDataForNY("NY bus feed"));
	 * logger.info("NY called");
	 * service.createBulkPickupLocations(worker.getSpreadsheetPickupDataForNY
	 * ("NY bus feed")); logger.info("NY Pickup called."); return "OK"; }
	 */
	
	private Date findWeekStartDate(Date weekEndDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(weekEndDate);
		cal.add(Calendar.DAY_OF_WEEK, -6);
		return cal.getTime();
	}
}
