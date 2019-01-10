package org.progressivelifestyle.bustrip.google;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.web.EventRWController;
import org.progressivelifestyle.bustrip.web.SpreadsheetWorker;
import org.progressivelifestyle.bustrip.web.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.util.AuthenticationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class SpreadsheetWorkerTest {
	@Autowired
	private EventRWController eventController;
	
	@Autowired
	private CategoryService categoryService;
	
	private SpreadsheetWorker worker;
	@Before
	public void setup() throws AuthenticationException{
		SpreadsheetService service = new SpreadsheetService("bus-trip");
		service.setUserCredentials("debalguha.active@gmail.com", "123$Munai");
		worker = new SpreadsheetWorker(service);
	}
	
	@Test
	public void shouldBeAbleToGetAndStoreSpreadsheetDataForSF() throws Exception{
		try {
			Collection<EventDTO> spreadsheetDataForSF = worker.getSpreadsheetDataForSF("bus feed");
			assertNotNull(spreadsheetDataForSF);
			assertFalse(spreadsheetDataForSF.isEmpty());
			for(EventDTO dto : spreadsheetDataForSF)
				eventController.submitEvent(dto);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
	}
	@Test
	public void shouldBeAbleToGetAndStoreSpreadsheetEventDataForNY() throws Exception{
		try {
			Collection<EventDTO> spreadsheetDataForNY = worker.getSpreadsheetEventDataForNY("Debal-bus feed NY", "pickups");
			assertNotNull(spreadsheetDataForNY);
			assertFalse(spreadsheetDataForNY.isEmpty());
			for(EventDTO dto : spreadsheetDataForNY)
				eventController.submitEvent(dto);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed miserably");
		}
	}
}
