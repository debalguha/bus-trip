package org.progressivelifestyle.bustrip.google;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.progressivelifestyle.bustrip.ProjectUtil.filterForNY;
import static org.progressivelifestyle.bustrip.ProjectUtil.filterForSF;

import java.util.Calendar;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ServiceSearchTest {
	@Autowired
	private EventService service;
	
	@Test
	public void shouldBeAbleToGetTwoPagesOfEventDataForSF() throws Exception{
		Collection<Event> objs = filterForSF(service.findAll(0));
		assertNotNull(objs);
		Assert.assertEquals(10, objs.size());
	}
	
	@Test
	public void shouldBeAbleToSearchOnTitleForNY() throws Exception{
		Collection<Event> list = filterForNY(service.findByTitle("Giants", 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToSearchOnTitleForSF() throws Exception{
		Collection<Event> list = filterForSF(service.findByTitle("Beyond", 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToSearchOnLocationForNY() throws Exception{
		Collection<Event> list = filterForNY(service.findByLocation("Rutherford", 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToSearchOnLocationForSF() throws Exception{
		Collection<Event> list = filterForSF(service.findByLocation("shoreline", 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}

	@Test
	public void shouldBeAbleToSearchOnEventDateForSF() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<Event> list = filterForSF(service.findByEventDate(cal.getTime(), 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToSearchOnEventDateForNY() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 28);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<Event> list = filterForNY(service.findByEventDate(cal.getTime(), 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToSearchOnExpirationDateForSF() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<Event> list = filterForSF(service.findByExpirationDate(cal.getTime(), 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToSearchOnExpirationDateForNY() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 21);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Collection<Event> list = filterForNY(service.findByExpirationDate(cal.getTime(), 0));
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}	
}
