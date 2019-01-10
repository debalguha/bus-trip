package org.progressivelifestyle.bustrip.web;

import static org.progressivelifestyle.bustrip.web.ERROR_CODE.NA;

import java.net.URLDecoder;

import javax.ws.rs.core.MediaType;

import org.progressivelifestyle.bustrip.consumer.Consumer;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController extends ConsumerController{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public @ResponseBody APIResponse doLogin(@RequestParam(value = "emailId", required = true) String emailId,
			@RequestParam(value = "password", required = true) String password) throws Exception{
		Consumer user = service.findUser(URLDecoder.decode(emailId, "UTF-8"));
		if(user == null){
			logger.error("Unable to find user with email: "+emailId);
			throw new Exception("Unable to find any user with the email: "+emailId);
		}else
			logger.info("User found:: "+user);
		if(user.getPassword().equals(password))
			return new APIResponse(NA, STATUS.SUCCESS, user);
		else
			throw new Exception("Authentication failed for "+emailId+"!!");
	}
}
