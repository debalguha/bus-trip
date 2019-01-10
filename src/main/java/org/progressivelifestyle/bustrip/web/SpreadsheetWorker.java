package org.progressivelifestyle.bustrip.web;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.progressivelifestyle.bustrip.google.EventType;
import org.progressivelifestyle.bustrip.google.domain.EventState;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.progressivelifestyle.bustrip.web.dto.PickupLocationDTO;

import com.google.api.client.util.Sets;
import com.google.common.collect.Lists;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

public class SpreadsheetWorker {
	private static final Logger logger = Logger.getLogger(SpreadsheetWorker.class.getName());
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private SpreadsheetService service;

	public SpreadsheetWorker(SpreadsheetService service) {
		this.service = service;
	}

	public ListFeed getSpreadsheetListFeed(String spreadSheetName, String sheetName) throws Exception {

		logger.info("Entry into getSpreadsheetListFeed");
		SpreadsheetFeed feed = (SpreadsheetFeed) this.service.getFeed(new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full"), SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();
		if (spreadsheets.size() == 0) {
			logger.info("Spreadsheet received 0.");
			return null;
		}
		logger.info("Spreadsheet received " + spreadsheets.size());
		Iterator<SpreadsheetEntry> iterator = spreadsheets.iterator();
		SpreadsheetEntry targetSpreadsheet = null;
		while (iterator.hasNext()) {
			SpreadsheetEntry spreadsheet = iterator.next();
			logger.info("Spreadsheet title: " + spreadsheet.getTitle().getPlainText());
			if (spreadsheet.getTitle().getPlainText().equals(spreadSheetName)) {
				targetSpreadsheet = spreadsheet;
				break;
			}
		}
		if (targetSpreadsheet == null)
			return null;
		logger.info(targetSpreadsheet.getTitle().getPlainText());
		WorksheetFeed worksheetFeed = (WorksheetFeed) this.service.getFeed(targetSpreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
		List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
		WorksheetEntry worksheet = null;
		for (WorksheetEntry theWorksheet : worksheets) {
			if (theWorksheet.getTitle().getPlainText().equals(sheetName)) {
				worksheet = theWorksheet;
				break;
			}
		}
		if (worksheet == null) {
			return null;
		}
		URL listFeedUrl = worksheet.getListFeedUrl();
		return (ListFeed) this.service.getFeed(listFeedUrl, ListFeed.class);
	}

	public List<EventDTO> getSpreadsheetDataForSF(String spreadSheetName) throws Exception {
		List<EventDTO> retList = Lists.newArrayList();
		ListFeed listFeed = getSpreadsheetListFeed(spreadSheetName, "Sheet1");
		for (ListEntry row : listFeed.getEntries()) {
			retList.add(createSpreadObjectSFromRow(row));
		}
		return retList;
	}

	public List<EventDTO> getSpreadsheetEventDataForNY(String spreadSheetName, String pickupSpreadsheetName) throws Exception {
		List<EventDTO> retList = Lists.newArrayList();
		List<PickupLocationDTO> pickups = getSpreadsheetPickupDataForNY(spreadSheetName, pickupSpreadsheetName);
		ListFeed listFeed = getSpreadsheetListFeed(spreadSheetName, "events");
		for (ListEntry row : listFeed.getEntries()) {
			EventDTO eventDTO = createSpreadEventObjectNYromRow(row);
			String departureTime1 = row.getCustomElements().getValue("departuretime1");
			String departureTime2 = row.getCustomElements().getValue("departuretime2");
			Set<PickupLocationDTO> pickupsWithTime = Sets.newHashSet();
			for (PickupLocationDTO pickupDTO : pickups) {
				PickupLocationDTO copyDTO = new PickupLocationDTO(pickupDTO);
				pickupDTO.setPickupTime(departureTime1);
				copyDTO.setPickupTime(departureTime2);
				pickupsWithTime.add(pickupDTO);
				pickupsWithTime.add(copyDTO);
			}
			eventDTO.setPickupLocations(pickupsWithTime);
			retList.add(eventDTO);
		}
		return retList;
	}

	public List<PickupLocationDTO> getSpreadsheetPickupDataForNY(String spreadSheetName, String sheetName) throws Exception {
		List<PickupLocationDTO> retList = Lists.newArrayList();
		ListFeed listFeed = getSpreadsheetListFeed(spreadSheetName, sheetName);
		for (ListEntry row : listFeed.getEntries()) {
			retList.add(createSpreadPickupObjectNYromRow(row));
		}
		return retList;
	}

	private PickupLocationDTO createSpreadPickupObjectNYromRow(ListEntry row) {
		String neighborhoodName = !StringUtils.isEmpty(row.getCustomElements().getValue("neighborhoodname")) ? row.getCustomElements().getValue("neighborhoodname") : null;
		String information = !StringUtils.isEmpty(row.getCustomElements().getValue("information")) ? row.getCustomElements().getValue("information") : null;
		String pickupName = !StringUtils.isEmpty(row.getCustomElements().getValue("pickup#1name")) ? row.getCustomElements().getValue("pickup#1name") : null;
		String pickupAddress = !StringUtils.isEmpty(row.getCustomElements().getValue("pickup#1address")) ? row.getCustomElements().getValue("pickup#1address") : null;
		String latitude = !StringUtils.isEmpty(row.getCustomElements().getValue("lat")) ? row.getCustomElements().getValue("lat") : null;
		String longitude = !StringUtils.isEmpty(row.getCustomElements().getValue("long")) ? row.getCustomElements().getValue("long") : null;
		return new PickupLocationDTO(neighborhoodName, information, pickupName, pickupAddress, latitude, longitude, null);
	}

	private EventDTO createSpreadObjectSFromRow(ListEntry row) throws ParseException {
		int featured = Integer.parseInt(!StringUtils.isEmpty(row.getCustomElements().getValue("featured")) ? row.getCustomElements().getValue("featured") : "0");
		EventType eventType = !StringUtils.isEmpty(row.getCustomElements().getValue("eventtype")) ? EventType.valueOf(row.getCustomElements().getValue("eventtype")) : null;
		int hidden = (!StringUtils.isEmpty(row.getCustomElements().getValue("hidden")) ? row.getCustomElements().getValue("hidden") : "n").equals("n") ? 0 : 1;
		String title = row.getCustomElements().getValue("title");
		String eventLocation = row.getCustomElements().getValue("eventLocation");
		int upsellOrUpgradeEvent = 0;
		Date eventDateTime = !StringUtils.isEmpty(row.getCustomElements().getValue("date")) ? dateFormat.parse(row.getCustomElements().getValue("date")) : null;
		String departureTime1 = row.getCustomElements().getValue("departuretime1");
		String departureTime2 = row.getCustomElements().getValue("departuretime2");
		Double price = Double.parseDouble(row.getCustomElements().getValue("price").substring(1));
		String pickupLocationLatLong = row.getCustomElements().getValue("pickuplocation");
		String pickupAddress = row.getCustomElements().getValue("pickupaddress");
		String pickupName = row.getCustomElements().getValue("pickupname");
		String shortDesc = row.getCustomElements().getValue("shortdescription");
		String description = row.getCustomElements().getValue("description");
		String lineup = row.getCustomElements().getValue("lineup");
		String imageURL = row.getCustomElements().getValue("image");
		int minPassenger = !StringUtils.isEmpty(row.getCustomElements().getValue("minpassenger")) ? Integer.parseInt(row.getCustomElements().getValue("minpassenger")) : 0;
		int maxPassenger = !StringUtils.isEmpty(row.getCustomElements().getValue("maxpassenger")) ? Integer.parseInt(row.getCustomElements().getValue("maxpassenger")) : 0;
		Date expiration = !StringUtils.isEmpty(row.getCustomElements().getValue("expiration")) ? dateFormat.parse(row.getCustomElements().getValue("expiration")) : null;
		String category = row.getCustomElements().getValue("category");
		PickupLocationDTO pickupLocation_1 = new PickupLocationDTO(pickupName, null, pickupName, pickupAddress, pickupLocationLatLong.split(",")[0], pickupLocationLatLong.split(",")[1], departureTime1);
		PickupLocationDTO pickupLocation_2 = new PickupLocationDTO(pickupName, null, pickupName, pickupAddress, pickupLocationLatLong.split(",")[0], pickupLocationLatLong.split(",")[1], departureTime2);
		Set<PickupLocationDTO> pickupLocations = Sets.newHashSet();
		pickupLocations.add(pickupLocation_1);
		pickupLocations.add(pickupLocation_2);
		Set<String> eventcategory = Sets.newHashSet();
		if (!StringUtils.isEmpty(category))
			eventcategory.addAll(Arrays.asList(category.split(",")));
		return new EventDTO(-1, price, featured, eventType, hidden, title, eventLocation, upsellOrUpgradeEvent, eventDateTime, shortDesc, description, lineup, imageURL, minPassenger, maxPassenger, expiration, EventState.SF, eventcategory, pickupLocations, "");
	}

	private EventDTO createSpreadEventObjectNYromRow(ListEntry row) throws ParseException {
		int featured = Integer.parseInt(!StringUtils.isEmpty(row.getCustomElements().getValue("featured")) ? row.getCustomElements().getValue("featured") : "0");
		EventType eventType = !StringUtils.isEmpty(row.getCustomElements().getValue("eventtype")) ? EventType.valueOf(row.getCustomElements().getValue("eventtype")) : EventType.Bus;
		int hidden = 0;
		String title = row.getCustomElements().getValue("title");
		String eventLocation = row.getCustomElements().getValue("location");
		int upsellOrUpgradeEvent = 0;

		Calendar eventDateTime = Calendar.getInstance();
		eventDateTime.setTime(dateFormat.parse(row.getCustomElements().getValue("date")));
		Calendar onlyEventTime = Calendar.getInstance();
		onlyEventTime.setTime(timeFormat.parse(row.getCustomElements().getValue("eventTime")));

		eventDateTime.set(Calendar.HOUR_OF_DAY, onlyEventTime.get(Calendar.HOUR_OF_DAY));
		eventDateTime.set(Calendar.MINUTE, onlyEventTime.get(Calendar.MINUTE));

		Double price = Double.parseDouble(row.getCustomElements().getValue("price").substring(1));
		String shortDesc = row.getCustomElements().getValue("shortdescription");
		String description = row.getCustomElements().getValue("description");
		String lineup = row.getCustomElements().getValue("lineup");
		String imageURL = row.getCustomElements().getValue("image");
		int minPassenger = !StringUtils.isEmpty(row.getCustomElements().getValue("minpassenger")) ? Integer.parseInt(row.getCustomElements().getValue("minpassenger")) : 0;
		int maxPassenger = !StringUtils.isEmpty(row.getCustomElements().getValue("maxpassenger")) ? Integer.parseInt(row.getCustomElements().getValue("maxpassenger")) : 0;
		Date expiration = !StringUtils.isEmpty(row.getCustomElements().getValue("expiration")) ? dateFormat.parse(row.getCustomElements().getValue("expiration")) : null;
		String category = row.getCustomElements().getValue("category");
		Set<String> eventcategory = Sets.newHashSet();
		if (!StringUtils.isEmpty(category))
			eventcategory.addAll(Arrays.asList(category.split(",")));
		return new EventDTO(-1, price, featured, eventType, hidden, title, eventLocation, upsellOrUpgradeEvent, eventDateTime.getTime(), shortDesc, description, lineup, imageURL, minPassenger, maxPassenger, expiration, EventState.NY, eventcategory, null, "");
	}
}
