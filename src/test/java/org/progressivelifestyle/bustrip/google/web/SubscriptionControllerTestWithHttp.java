package org.progressivelifestyle.bustrip.google.web;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.web.STATUS;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.AdditionalItemDTO;
import org.progressivelifestyle.bustrip.web.dto.SubscriptionDTO;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Lists;

public class SubscriptionControllerTestWithHttp extends ConsumerControllerTestWithHttp{
	
	@SuppressWarnings("unchecked")
	@Test
	public void runSubscriptionTests() throws Exception {
		try{
			Map<String, Object> userResponse = (Map<String, Object>)shouldBeAbleToCreateAnUser();
			Long userId = Long.parseLong(userResponse.get("id").toString());
			Long eventId = Long.parseLong(((Map<String, Object>)shouldBeAbleToCreateAnEvent()).get("id").toString());
			Map<String, Object> addlItemMap = (Map<String, Object>)shouldBeAbleToCreateAdditionalItems("T-Shirt", 121.99);
			String item = addlItemMap.get("itemName").toString();
			Double itemPrice = Double.parseDouble(addlItemMap.get("itemPrie").toString());
			Long itemId = Long.parseLong(addlItemMap.get("id").toString());
			AdditionalItemDTO dto = new AdditionalItemDTO(itemId, 4, item, itemPrice);
			Collection<AdditionalItemDTO> items = Lists.newArrayList();
			items.add(dto);
			shouldBeAbleToSubscribeToAnEvent(userId, eventId, items);
			shouldBeAbleToGetSubscriptionForAnUser(userId);
			shouldBeAbleToCountSubscription(eventId, 1);
			//shouldBeAbleToDeleteSubscriptionForAnUser(userId, eventId);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail("Failed miserably!!");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void shouldBeAbleToGetAllAdditionalItems(){
		shouldBeAbleToCreateAdditionalItems("T-Shirt~1", 121.99);
		shouldBeAbleToCreateAdditionalItems("Cap~1", 121.99);
		String url = getBaseURL()+"/bus-trip/rest/cart/event/addlitem/get";
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());
		Assert.assertFalse(((List<Object>)apiResponse.getData()).isEmpty());
	}
	
	private void shouldBeAbleToDeleteSubscriptionForAnUser(Long userId, Long eventId) {
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/cart/event/subscription/delete");
		System.out.println("Cancel: "+url);
		url.append("?userId=").append(userId).append("&eventId=").append(eventId);
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());		
	}

	private void shouldBeAbleToGetSubscriptionForAnUser(Long userId) {
		StringBuilder url = new StringBuilder(getBaseURL()+"/bus-trip/rest/cart/event/get");
		url.append("?userId=").append(userId);
		APIResponse apiResponse = getRestTemplate().getForObject(url.toString(), APIResponse.class);
		Assert.assertNotNull(apiResponse);
		Assert.assertEquals(STATUS.SUCCESS, apiResponse.getStatus());
	}

	public Object shouldBeAbleToSubscribeToAnEvent(Long userId, Long eventId, Collection<AdditionalItemDTO> addlItems) throws Exception {
		String url = new String(getBaseURL()+"/bus-trip/rest/cart/event/add");
		SubscriptionDTO dto = new SubscriptionDTO(userId, eventId, addlItems);
		System.out.println("Create: "+url.toString());
		RestTemplate template = getRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(new ObjectMapper().writeValueAsString(dto), headers);
		return template.postForEntity(url, request, APIResponse.class).getBody().getData();
	}
	
	public Object shouldBeAbleToCreateAdditionalItems(String item, Double price) {
		String itemJson = "{\"id\":-1,\"quantity\":0,\"item\":\"T-Shirt\",\"price\":21.3}";
		RestTemplate template = getRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = getBaseURL()+"/bus-trip/rest/cart/event/addlitem/create";
		System.out.println(url);
		HttpEntity<String> request = new HttpEntity<String>(itemJson, headers);
		return template.postForEntity(url, request, APIResponse.class).getBody().getData();
	}
	
	public Object shouldBeAbleToCreateAnEvent() {
		String url = getBaseURL()+"/bus-trip/rest/RW/event/submit";
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.DAY_OF_WEEK, startCal.getFirstDayOfWeek());
		
		Calendar endCal = Calendar.getInstance();
		startCal.set(Calendar.DAY_OF_WEEK, endCal.getFirstDayOfWeek()+6);
		String eventJSON = "{\"id\":-1,\"price\":120.5,\"featured\":1,\"eventType\":\"Bus\",\"hidden\":0,\"title\":\"Test Event\",\"eventLocation\":\"Home\",\"upsellOrUpgradeEvent\":0,\"eventDateTime\":1415696400000,\"shortDesc\":\"Test\",\"description\":\"Test\",\"lineup\":\"Test\",\"imageURL\":\"Test\",\"minPassenger\":10,\"maxPassenger\":100,\"expiration\":1418310000000,\"eventState\":\"SF\",\"eventcategory\":[\"Sports\",\"College\"],\"pickupLocations\":[{\"neighborhoodName\":\"categories\",\"information\":\"categories\",\"pickupName\":\"categories\",\"pickupAddress\":\"categories\",\"latitude\":\"-11.1111\",\"longitude\":\"11.1111\",\"pickupTime\":\"10:0\"}]}";
		System.out.println(url);
		RestTemplate template = getRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(eventJSON, headers);
		return template.postForEntity(url, request, APIResponse.class).getBody().getData();
	}
	
	public Object shouldBeAbleToCreateAnUser() throws Exception {
		//String url = "http://server651.spikecloud.net.in:9080/bus-trip/rest/profile/employee/register";
		String url = getBaseURL()+"/bus-trip/rest/profile/user/register";
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", "dguha@example.com");
		form.add("password", "passw0rd");
		form.add("mobileNumber", "9830276056");
		form.add("birthDate", ProjectUtil.dateFormat.format(new Date()));
		form.add("fullName", "Debal Guha");
		form.add("emailVerified", "false");
		form.add("phoneVerified", "false");
		form.add("idBack", new FileSystemResourceLoader().getResource("C:\\temp\\file1.txt"));
		form.add("idFront", new FileSystemResourceLoader().getResource("C:\\temp\\file2.txt"));
		form.add("photo", new FileSystemResourceLoader().getResource("C:\\temp\\file3.txt"));
		APIResponse responseEntity = template.postForObject(url, form, APIResponse.class);
		System.out.println(url);
		assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
		return responseEntity.getData();
	}
	
	private void shouldBeAbleToCountSubscription(Long eventId, int expectedCount) {
		String url=getBaseURL()+"/bus-trip/rest/cart/event/subscription/count?eventId="+eventId;
		System.out.println(url);
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		APIResponse responseEntity = template.getForObject(url, APIResponse.class);
		Assert.assertEquals(1, responseEntity.getData());
	}	
}
