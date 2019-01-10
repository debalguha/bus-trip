package org.progressivelifestyle.bustrip.web;

import static org.progressivelifestyle.bustrip.web.ERROR_CODE.NA;
import static org.progressivelifestyle.bustrip.web.STATUS.SUCCESS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.progressivelifestyle.bustrip.ProjectUtil;
import org.progressivelifestyle.bustrip.consumer.Administrator;
import org.progressivelifestyle.bustrip.consumer.Consumer;
import org.progressivelifestyle.bustrip.consumer.Employee;
import org.progressivelifestyle.bustrip.consumer.Role;
import org.progressivelifestyle.bustrip.consumer.User;
import org.progressivelifestyle.bustrip.consumer.service.ConsumerService;
import org.progressivelifestyle.bustrip.consumer.service.UserService;
import org.progressivelifestyle.bustrip.google.domain.BusRunningInfo;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.BusRunningInfoDTO;
import org.progressivelifestyle.bustrip.web.dto.FileData;
import org.progressivelifestyle.bustrip.web.dto.UserRegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/profile")
public class ConsumerController extends AbstractEventController{
	private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	@Autowired
	protected ConsumerService service;

	@Autowired
	protected UserService userService;

	@Value("${phone.verification.enabled}")
	protected boolean enabledPhoneVerification;

	@Value("${email.verification.enabled}")
	protected boolean enabledEmailVerification;

	@RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA,MediaType.APPLICATION_FORM_URLENCODED })
	public @ResponseBody APIResponse registerUser(@ModelAttribute UserRegistrationForm form) throws Exception {
		int verificationCode = RandomUtils.nextInt();
		User user = (User) registerCommonUser(form, new User());
		user.setRole(Role.USER);
		user.setVerificationCode(verificationCode);
		user.setTiltCardId(form.getTiltCardId());
		user.setTiltUserId(form.getTiltUserId());
		service.registerUser(user);
		launchVerificationThread(user, verificationCode);
		return new APIResponse(NA, SUCCESS, user);
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED })
	public @ResponseBody APIResponse editUserProfile(@ModelAttribute UserRegistrationForm form) throws Exception {
		User user = (User) registerCommonUser(form, new User());
		user.setRole(Role.USER);
		service.editUser(user);
		return new APIResponse(NA, STATUS.SUCCESS, user);
	}

	@RequestMapping(value = "/user/find", method = RequestMethod.GET)
	public @ResponseBody APIResponse findUserProfile(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		Consumer user = service.findUser(URLDecoder.decode(emailId, "UTF-8"));
		if(user == null){
			logger.error("Unable to find user with email: "+emailId);
			throw new Exception("Unable to find any user with the email: "+emailId);
		}else
			logger.info("User found:: "+user);
		return new APIResponse(NA, STATUS.SUCCESS, user);
	}

	@RequestMapping(value = "/admin/register", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED })
	public @ResponseBody APIResponse registerAdmin(UserRegistrationForm form) throws Exception {
		Administrator user = (Administrator) registerCommonUser(form, new Administrator());
		user.setRole(Role.ADMIN);
		service.registerAdmin(user);
		return new APIResponse(NA, STATUS.SUCCESS, user);
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED })
	public @ResponseBody APIResponse editAdminProfile(@ModelAttribute UserRegistrationForm form) throws Exception {
		Administrator user = (Administrator) registerCommonUser(form, new Administrator());
		user.setRole(Role.ADMIN);
		service.editAdmin(user);
		return new APIResponse(NA, STATUS.SUCCESS, user);
	}

	@RequestMapping(value = "/admin/find", method = RequestMethod.GET)
	public @ResponseBody APIResponse findAdminProfile(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		Consumer admin = service.findAdmin(URLDecoder.decode(emailId, "UTF-8"));
		if(admin == null){
			logger.error("Unable to find user with email: "+emailId);
			throw new Exception("Unable to find any user with the email: "+emailId);
		}else
			logger.info("User found:: "+admin);
		return new APIResponse(NA, STATUS.SUCCESS, admin);
	}

	@RequestMapping(value = "/employee/register", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED })
	public @ResponseBody APIResponse registerEmployee(UserRegistrationForm form) throws Exception {
		int verificationCode = RandomUtils.nextInt();
		Employee user = (Employee) registerCommonUser(form, new Employee());
		user.setRole(Role.EMPLOYEE);
		user.setVerificationCode(verificationCode);
		service.registerEmployee(user);
		launchVerificationThread(user, verificationCode);
		return new APIResponse(NA, STATUS.SUCCESS, user);
	}

	@RequestMapping(value = "/employee/edit", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED })
	public @ResponseBody APIResponse editEmployeeProfile(@ModelAttribute UserRegistrationForm form) throws Exception {
		Employee user = (Employee) registerCommonUser(form, new Employee());
		user.setRole(Role.ADMIN);
		service.editEmployee(user);
		return new APIResponse(NA, STATUS.SUCCESS, user);
	}

	@RequestMapping(value = "/employee/find", method = RequestMethod.GET)
	public @ResponseBody APIResponse findEmployeeProfile(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		Consumer employee = service.findEmployee(URLDecoder.decode(emailId, "UTF-8"));
		if(employee == null){
			logger.error("Unable to find user with email: "+emailId);
			throw new Exception("Unable to find any user with the email: "+emailId);
		}else
			logger.info("User found:: "+employee);
		return new APIResponse(NA, STATUS.SUCCESS, employee);
	}

	@RequestMapping(value = "/download/idFront", method = RequestMethod.GET)
	public void downloadIdFrontFile(@RequestParam(value = "emailId", required = true) String emailId, HttpServletResponse response) throws Exception {
		Consumer user = service.findConsumer(URLDecoder.decode(emailId, "UTF-8"));
		FileData fileData = (FileData) SerializationUtils.deserialize(user.getIdFront());
		writeFileToOutputStream(fileData, response);
	}

	@RequestMapping(value = "/download/idBack", method = RequestMethod.GET)
	public void downloadIdBackFile(@RequestParam(value = "emailId", required = true) String emailId, HttpServletResponse response) throws Exception {
		Consumer user = service.findConsumer(URLDecoder.decode(emailId, "UTF-8"));
		FileData fileData = (FileData) SerializationUtils.deserialize(user.getIdBack());
		writeFileToOutputStream(fileData, response);
	}

	@RequestMapping(value = "/download/photo", method = RequestMethod.GET)
	public void downloadPhotoFile(@RequestParam(value = "emailId", required = true) String emailId, HttpServletResponse response) throws Exception {
		Consumer user = service.findConsumer(URLDecoder.decode(emailId, "UTF-8"));
		FileData fileData = (FileData) SerializationUtils.deserialize(user.getPhoto());
		writeFileToOutputStream(fileData, response);
	}

	private void writeFileToOutputStream(FileData fileData, HttpServletResponse response) throws Exception {
		File downloadFile = File.createTempFile(FilenameUtils.getBaseName(fileData.getFileName()), ".".concat(FilenameUtils.getExtension(fileData.getFileName())));
		IOUtils.write(fileData.getFileData(), new FileOutputStream(downloadFile));
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setHeader("Content-Type", "text/csv");
		response.setHeader("Content-disposition", "attachment;filename=\"" + downloadFile.getName() + "\"");
		logger.info("Going to copy file to output stream.");
		FileCopyUtils.copy(FileUtils.readFileToByteArray(downloadFile), response.getOutputStream());
		logger.info("Copy finished!");
		response.getOutputStream().flush();
	}

	private Consumer registerCommonUser(UserRegistrationForm form, Consumer user) throws IOException, ParseException {
		System.out.println("Reg form: " + form);
		if (!StringUtils.isEmpty(form.getBirthDate()))
			user.setBirthDate(ProjectUtil.dateFormat.parse(form.getBirthDate()));
		if (!StringUtils.isEmpty(form.getEmailId()))
			user.setEmailId(form.getEmailId());
		if (!StringUtils.isEmpty(form.getEmailVerified()))
			user.setEmailVerified(Boolean.valueOf(form.getEmailVerified()));
		if (!StringUtils.isEmpty(form.getFullName()))
			user.setFullName(form.getFullName());

		if (form.getPhoto() != null)
			user.setPhoto(SerializationUtils.serialize(new FileData(form.getPhoto().getOriginalFilename(), form.getPhoto().getBytes())));
		if (form.getIdBack() != null)
			user.setIdBack(SerializationUtils.serialize(new FileData(form.getIdBack().getOriginalFilename(), form.getIdBack().getBytes())));
		if (form.getIdFront() != null)
			user.setIdFront(SerializationUtils.serialize(new FileData(form.getIdFront().getOriginalFilename(), form.getIdFront().getBytes())));

		if (!StringUtils.isEmpty(form.getMobileNumber()))
			user.setMobileNumber(form.getMobileNumber());
		if (!StringUtils.isEmpty(form.getPassword()))
			user.setPassword(form.getPassword());
		if (!StringUtils.isEmpty(form.getPhoneVerified()))
			user.setPhoneVerified(Boolean.valueOf(form.getPhoneVerified()));

		return user;
	}

	@RequestMapping(value = "/delete/user", method = RequestMethod.GET)
	public @ResponseBody String deleteUser(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		logger.info("Email to delete " + URLDecoder.decode(emailId, "UTF-8"));
		service.deleteUser(URLDecoder.decode(emailId, "UTF-8"));
		return STATUS.SUCCESS.toString();
	}

	@RequestMapping(value = "/delete/employee", method = RequestMethod.GET)
	public @ResponseBody String deletEmployee(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		logger.info("Email to delete " + URLDecoder.decode(emailId, "UTF-8"));
		service.deleteEmployee(URLDecoder.decode(emailId, "UTF-8"));
		return STATUS.SUCCESS.toString();
	}

	@RequestMapping(value = "/delete/admin", method = RequestMethod.GET)
	public @ResponseBody String deleteAdmin(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		logger.info("Email to delete " + URLDecoder.decode(emailId, "UTF-8"));
		service.deleteAdmin(URLDecoder.decode(emailId, "UTF-8"));
		return STATUS.SUCCESS.toString();
	}

	@RequestMapping(value = "/event/bus/location", method = RequestMethod.GET)
	public @ResponseBody APIResponse getBusRunningInfo(@RequestParam(value = "eventId", required = true) long eventId, @RequestParam(value = "busNum", required = true) String busNum) throws Exception {
		BusRunningInfo busRunningInfo = userService.getBusRunningInfo(eventId, busNum);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, new BusRunningInfoDTO(busRunningInfo.getId(), busRunningInfo.getLattitude(), busRunningInfo.getLogitude(), busRunningInfo.getRunningDate(), busRunningInfo.getBusNum()));
	}

	@RequestMapping(value = "/user/phone/verify", method = RequestMethod.GET)
	public @ResponseBody APIResponse verifyUserPhone(@RequestParam(value = "code", required = true) int code, @RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		User user = service.findUser(emailId);
		if (enabledPhoneVerification) {
			if (user.getVerificationCode() == code) {
				user.setPhoneVerified(true);
				service.editUser(user);
				return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
			}
		}else{
			user.setPhoneVerified(true);
			service.editUser(user);
			return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
		}
		return new APIResponse(ERROR_CODE.REG_PHONE_VERIFICATION_FAILED, STATUS.FAIL, null);
	}

	@RequestMapping(value = "/employee/phone/verify", method = RequestMethod.GET)
	public @ResponseBody APIResponse verifyEmployeePhone(@RequestParam(value = "code", required = true) int code, @RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		Employee employee = service.findEmployee(emailId);
		if (enabledPhoneVerification) {
			if (employee.getVerificationCode() == code) {
				employee.setPhoneVerified(true);
				service.editEmployee(employee);
				return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
			}
		}else{
			employee.setPhoneVerified(true);
			service.editEmployee(employee);
			return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
		}
		return new APIResponse(ERROR_CODE.REG_PHONE_VERIFICATION_FAILED, STATUS.FAIL, null);
	}

	@RequestMapping(value = "/user/phone/verification/resend", method = RequestMethod.GET)
	public @ResponseBody APIResponse resendVerificationCodeForUser(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		int verificationCode = RandomUtils.nextInt();
		User user = service.findUser(emailId);
		user.setPhoneVerified(false);
		user.setVerificationCode(verificationCode);
		service.editUser(user);
		launchVerificationThread(user, verificationCode);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
	}

	@RequestMapping(value = "/employee/phone/verification/resend", method = RequestMethod.GET)
	public @ResponseBody APIResponse resendVerificationCodeForEmployee(@RequestParam(value = "emailId", required = true) String emailId) throws Exception {
		int verificationCode = RandomUtils.nextInt();
		Employee employee = service.findEmployee(emailId);
		employee.setPhoneVerified(false);
		employee.setVerificationCode(verificationCode);
		service.editEmployee(employee);
		launchVerificationThread(employee, verificationCode);
		return new APIResponse(ERROR_CODE.NA, STATUS.SUCCESS, null);
	}

	public void launchVerificationThread(Consumer consumer, int verificationCode) {
		if (enabledPhoneVerification) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// code to send sms
				}
			}).start();
		}
	}
}
