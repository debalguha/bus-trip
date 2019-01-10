package org.progressivelifestyle.bustrip.google.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.web.STATUS;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.Lists;

public class ConsumerControllerTestWithHttp extends AbstractBaseTest{

	@Test
	//@Ignore
	public void runUserTest() throws Exception {
		shouldBeAbleToRegisterAnUser();
		shouldBeAbleToEditUserProfile();
		shouldBeAbleToRetrieveAnUser();
		shouldBeAbleToDownloadIdFrontForAConsumer("dguha@example.com");
//		shouldBeAbleToDeleteAnUser();
	}
	@Test
	public void shouldNotBeAbleToRetrieveAnUser() throws Exception {
		String url = getBaseURL()+"/bus-trip/rest/profile/employee/find?emailId=" + URLEncoder.encode("barakobamamodi@example.com", "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		APIResponse responseEntity = template.getForObject(url, APIResponse.class);
		Assert.assertNotNull(responseEntity);
		Assert.assertTrue(responseEntity.getStatus() == STATUS.FAIL);
	}
	
	@Test
	//@Ignore
	public void runEmployeeTest() throws Exception {
		shouldBeAbleToRegisterAnEmployee();
		shouldBeAbleToEditEmployeeProfile();
		shouldBeAbleToRetrieveAnEmployee();
		shouldBeAbleToDownloadIdBackForAConsumer("dguha-emp@example.com");
		shouldBeAbleToDeleteAnEmployee();
	}

	@Test
	//@Ignore
	public void runAdminTest() throws Exception {
		shouldBeAbleToRegisterAnAdministrator();
		shouldBeAbleToEditAdministratorProfile();
		shouldBeAbleToRetrieveAnAdmin();
		shouldBeAbleToDownloadPhotoForAConsumer("dguha-admin@example.com");
		shouldBeAbleToDeleteAnAdministrator();
	}
	
	public void shouldBeAbleToRegisterAnUser() throws Exception {
		String url = getBaseURL()+"/bus-trip/rest/profile/user/register";
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/user/register";
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
		//form.add("idBack", new FileSystemResourceLoader().getResource("C:\\temp\\file1.txt"));
		form.add("idFront", new FileSystemResourceLoader().getResource("C:\\temp\\file2.txt"));
		form.add("photo", new FileSystemResourceLoader().getResource("C:\\temp\\file3.txt"));
		APIResponse responseEntity = template.postForObject(url, form, APIResponse.class);
		System.out.println(url);
		assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
	}

	public void shouldBeAbleToRegisterAnEmployee() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/employee/register";
		String url = getBaseURL()+"/bus-trip/rest/profile/employee/register";
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", "dguha-emp@example.com");
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
	}

	public void shouldBeAbleToRegisterAnAdministrator() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/admin/register";
		String url = getBaseURL()+"/bus-trip/rest/profile/admin/register";
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", "dguha-admin@example.com");
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
	}

	public void shouldBeAbleToEditUserProfile() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/user/edit";
		String url = getBaseURL()+"/bus-trip/rest/profile/user/edit";
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", "dguha@example.com");
		form.add("password", "passw1rd");
		APIResponse responseEntity = template.postForObject(url, form, APIResponse.class);
		System.out.println(url);
		assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
	}

	public void shouldBeAbleToEditEmployeeProfile() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/employee/edit";
		String url = getBaseURL()+"/bus-trip/rest/profile/employee/edit";
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", "dguha-emp@example.com");
		form.add("password", "passw1rd");
		APIResponse responseEntity = template.postForObject(url, form, APIResponse.class);
		System.out.println(url);
		assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
	}

	public void shouldBeAbleToEditAdministratorProfile() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/admin/edit";
		String url = getBaseURL()+"/bus-trip/rest/profile/admin/edit";
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("emailId", "dguha-admin@example.com");
		form.add("password", "passw1rd");
		APIResponse responseEntity = template.postForObject(url, form, APIResponse.class);
		System.out.println(url);
		assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
	}

	public void shouldBeAbleToDeleteAnUser() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/delete/user?emailId=" + URLEncoder.encode("dguha@example.com", "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/delete/user?emailId=" + URLEncoder.encode("dguha@example.com", "UTF-8");
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

	public void shouldBeAbleToDeleteAnEmployee() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/delete/employee?emailId=" + URLEncoder.encode("dguha-emp@example.com", "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/delete/employee?emailId=" + URLEncoder.encode("dguha-emp@example.com", "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		String status = template.getForObject(url, String.class);
		assertTrue("\"SUCCESS\"".equals(status));
		System.out.println(url);
	}

	public void shouldBeAbleToDeleteAnAdministrator() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/delete/admin?emailId=" + URLEncoder.encode("dguha-admin@example.com", "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/delete/admin?emailId=" + URLEncoder.encode("dguha-admin@example.com", "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		String status = template.getForObject(url, String.class);
		assertTrue("\"SUCCESS\"".equals(status));
		System.out.println(url);
	}
	
	public Object shouldBeAbleToRetrieveAnUser() throws Exception {
		String url = getBaseURL()+"/bus-trip/rest/profile/user/find?emailId=" + URLEncoder.encode("dguha@example.com", "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		APIResponse responseEntity = template.getForObject(url, APIResponse.class);
		System.out.println(url);
		Assert.assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
		return responseEntity.getData();
	}
	
	public Object shouldBeAbleToRetrieveAnEmployee() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/employee/find?emailId=" + URLEncoder.encode("dguha-admin@example.com", "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/employee/find?emailId=" + URLEncoder.encode("dguha-emp@example.com", "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		APIResponse responseEntity = template.getForObject(url, APIResponse.class);
		System.out.println(url);
		Assert.assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
		return responseEntity.getData();
	}
	
	public Object shouldBeAbleToRetrieveAnAdmin() throws Exception {
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/admin/find?emailId=" + URLEncoder.encode("dguha-admin@example.com", "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/admin/find?emailId=" + URLEncoder.encode("dguha-admin@example.com", "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		APIResponse responseEntity = template.getForObject(url, APIResponse.class);
		System.out.println(url);
		Assert.assertEquals(STATUS.SUCCESS, responseEntity.getStatus());
		return responseEntity.getData();
	}
	
	public void shouldBeAbleToDownloadIdFrontForAConsumer(String emailId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("api.key", "blag-GJV23998002133RTHud#KK$U");
		//HttpEntity<T>
		RestTemplate template = new RestTemplate();
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/download/idFront?emailId=" + URLEncoder.encode(emailId, "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/download/idFront?emailId=" + URLEncoder.encode(emailId, "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new FileHttpMessageConverter());
		template.setMessageConverters(converters);
		ResponseEntity<File> entity = template.getForEntity(url, File.class);
		System.out.println(url);
		Assert.assertNotNull(entity.getBody());
	}
	
	public void shouldBeAbleToDownloadIdBackForAConsumer(String emailId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("api.key", "blag-GJV23998002133RTHud#KK$U");
		//HttpEntity<T>
		RestTemplate template = new RestTemplate();
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/download/idBack?emailId=" + URLEncoder.encode(emailId, "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/download/idBack?emailId=" + URLEncoder.encode(emailId, "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new FileHttpMessageConverter());
		template.setMessageConverters(converters);
		ResponseEntity<File> entity = template.getForEntity(url, File.class);
		System.out.println(url);
		Assert.assertNotNull(entity.getBody());
	}
	
	public void shouldBeAbleToDownloadPhotoForAConsumer(String emailId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("api.key", "blag-GJV23998002133RTHud#KK$U");
		//HttpEntity<T>
		RestTemplate template = new RestTemplate();
		//String url = "http://host403.hostmonster.com:9080/bus-trip/rest/profile/download/photo?emailId=" + URLEncoder.encode(emailId, "UTF-8");
		String url = getBaseURL()+"/bus-trip/rest/profile/download/photo?emailId=" + URLEncoder.encode(emailId, "UTF-8");
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new FileHttpMessageConverter());
		template.setMessageConverters(converters);
		ResponseEntity<File> entity = template.getForEntity(url, File.class);
		System.out.println(url);
		Assert.assertNotNull(entity.getBody());
	}	

	public RestTemplate getRestTemplate(){
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		return template;
	}
}
