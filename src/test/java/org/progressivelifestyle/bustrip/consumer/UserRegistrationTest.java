package org.progressivelifestyle.bustrip.consumer;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.progressivelifestyle.bustrip.consumer.service.ConsumerService;
import org.progressivelifestyle.bustrip.web.ConsumerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class UserRegistrationTest {
	@Autowired
	private ConsumerService service;
	@Autowired
	private ConsumerController controller;
	@Test
	@Ignore
	public void shouldBeAbleToRegisterUser() throws Exception {
		User user = new User("dguha@sapient.com", "passw0rd", "919830276056", new Date(), "Debal Guha", false, false
				, IOUtils.toByteArray(new FileInputStream(new File("C:\\Users\\dguha\\Downloads\\Requested Information\\Passport Front New - Debal Guha.pdf")))
					, IOUtils.toByteArray(new FileInputStream(new File("C:\\Users\\dguha\\Downloads\\Requested Information\\Passport Back New - Debal Guha.pdf")))
					, IOUtils.toByteArray(new FileInputStream(new File("C:/Users/dguha/Pictures/scanned-Debal.pdf"))), null, "dguha@facebook.com", "", "");
		Consumer registerUser = service.registerUser(user);
		Assert.assertNotNull(registerUser);	
	}
	
	@Test
	public void shouldNotBeAbleToFindAnUser() throws Exception {
		try {
			controller.findUserProfile("abscdealkajsf@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
