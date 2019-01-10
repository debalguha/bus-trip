package org.progressivelifestyle.bustrip.consumer.service;

import java.util.Collection;

import org.progressivelifestyle.bustrip.consumer.Administrator;
import org.progressivelifestyle.bustrip.consumer.Consumer;
import org.progressivelifestyle.bustrip.consumer.Employee;
import org.progressivelifestyle.bustrip.consumer.User;
import org.progressivelifestyle.bustrip.google.repository.AdministratorRepository;
import org.progressivelifestyle.bustrip.google.repository.ConsumerRepository;
import org.progressivelifestyle.bustrip.google.repository.EmployeeRepository;
import org.progressivelifestyle.bustrip.google.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerService extends AbstractBaseService<Consumer> {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private AdministratorRepository adminRepo;
	@Autowired
	private ConsumerRepository<Consumer> consumerRepo;

	private static String[] excludeProperties = new String[] { "id", "creationDate", "emailId" };

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public User findUser(String emailId) throws Exception {
		return userRepo.findByEmailId(emailId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Employee findEmployee(String emailId) throws Exception {
		return empRepo.findByEmailId(emailId);
	}
	
	@Transactional(readOnly = true)
	public Collection<Employee> findAllEmployees() throws Exception {
		return empRepo.findAll();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Administrator findAdmin(String emailId) throws Exception {
		return adminRepo.findByEmailId(emailId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Consumer findConsumer(String emailId) throws Exception {
		return consumerRepo.findByEmailId(emailId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public User registerUser(User user) throws Exception {
		setMandatoryDates(user);
		return userRepo.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Administrator registerAdmin(Administrator user) {
		setMandatoryDates(user);
		return adminRepo.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Employee registerEmployee(Employee user) {
		setMandatoryDates(user);
		return empRepo.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public User editUser(User user) throws Exception {
		setMandatoryDates(user);
		return userRepo.save((User)cloneEntities(user, userRepo.findByEmailId(user.getEmailId())));
	}

	private Consumer cloneEntities(Consumer source, Consumer target) {
		if (source.getBirthDate() != null)
			target.setBirthDate(source.getBirthDate());
		target.setEmailVerified(source.isEmailVerified());
		target.setPhoneVerified(source.isPhoneVerified());
		if (source.getFullName() != null)
			target.setFullName(source.getFullName());
		if (source.getEmailId() != null)
			target.setEmailId(source.getEmailId());
		if (source.getIdBack() != null)
			target.setIdBack(source.getIdBack());
		if (source.getIdFront() != null)
			target.setIdFront(source.getIdFront());
		if (source.getMobileNumber() != null)
			target.setMobileNumber(source.getMobileNumber());
		if (source.getPassword() != null)
			target.setPassword(source.getPassword());
		if (source.getPhoto() != null)
			target.setPhoto(source.getPhoto());
		if (source.getRole() != null)
			target.setRole(source.getRole());
		return target;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Administrator editAdmin(Administrator user) throws Exception {
		setMandatoryDates(user);
		return adminRepo.save((Administrator)cloneEntities(user, adminRepo.findByEmailId(user.getEmailId())));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Employee editEmployee(Employee user) throws Exception {
		setMandatoryDates(user);
		return empRepo.save((Employee)cloneEntities(user, empRepo.findByEmailId(user.getEmailId())));
	}

	@Override
	public String[] getPropertiesToIgnoreDuringClone() {
		return excludeProperties;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteEmployee(String emailId) throws Exception {
		empRepo.delete(empRepo.findByEmailId(emailId));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteUser(String emailId) throws Exception {
		userRepo.delete(userRepo.findByEmailId(emailId));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteAdmin(String emailId) throws Exception {
		adminRepo.delete(adminRepo.findByEmailId(emailId));
	}
}
