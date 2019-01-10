package org.progressivelifestyle.bustrip.google.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.web.STATUS;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.Lists;

public class LoginControllerTestWithHttp extends AbstractBaseTest{
	@Test
	public void shouldBeAbleToLogin() throws Exception {
		String email = "dguha-011@sapient.com";
		shouldBeAbleToRegisterAnUser(email);
		String url = getBaseURL()+"/bus-trip/rest/user/login";
		System.out.println("URL:: "+url);
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", email);
		form.add("password", "passw0rd");
		APIResponse responseEntity = template.postForObject(url, form, APIResponse.class);
		System.out.println(url);
		assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
		shouldBeAbleToDeleteAnUser(email);
	}
	
	public void shouldBeAbleToRegisterAnUser(String email) throws Exception {
		String url = getBaseURL()+"/bus-trip/rest/profile/user/register";
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/user/register";
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", email);
		form.add("password", "passw0rd");
		form.add("mobileNumber", "9830276056");
		form.add("birthDate", ProjectUtil.dateFormat.format(new Date()));
		form.add("fullName", "Debal Guha");
		form.add("emailVerified", "false");
		form.add("phoneVerified", "false");
		//form.add("idBack", new FileSystemResourceLoader().getResource("C:\\temp\\file1.txt"));
		//form.add("idFront", new FileSystemResourceLoader().getResource("C:\\temp\\file2.txt"));
		//form.add("photo", new FileSystemResourceLoader().getResource("C:\\temp\\file3.txt"));
		APIResponse responseEntity = template.postForObject(url, form, APIResponse.class);
		System.out.println(url);
		assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
	}
	
	public void shouldBeAbleToDeleteAnUser(String emailId) throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/delete/user?emailId=" + URLEncoder.encode("dguha@example.com", "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/delete/user?emailId=" + URLEncoder.encode(emailId, "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		String status = template.getForObject(url, String.class);
		System.out.println(status);
		assertTrue("\"SUCCESS\"".equals(status));
		System.out.println(url);
	}
}
