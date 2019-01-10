package org.progressivelifestyle.bustrip;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JPAConfigurationTest {
	@Test
	public void shouldBeAbleToPropagateChangesToDB() throws Exception{
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			assertNotNull(ctx);
		} catch (Exception e) {
			e.printStackTrace();
			fail("JPA Config error!!");
		}
	}
}
