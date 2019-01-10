package org.progressivelifestyle.bustrip.web;

import java.util.logging.Logger;

import org.progressivelifestyle.bustrip.TLSEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SendMailController {
	private static final Logger logger = Logger.getLogger(SendMailController.class.getName());
	@RequestMapping(value = "/mail/test", method = RequestMethod.GET, produces = "application/json")
	public void sendTestMail() throws Exception{
		logger.info("Starting mail send");
		TLSEmail.main(null);
		logger.info("Ended mail send");
	}
}
