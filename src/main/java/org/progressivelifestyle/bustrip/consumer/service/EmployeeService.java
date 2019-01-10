package org.progressivelifestyle.bustrip.consumer.service;

import java.util.Collection;
import java.util.Date;

import org.progressivelifestyle.bustrip.consumer.Employee;
import org.progressivelifestyle.bustrip.consumer.EmployeeAssignment;
import org.progressivelifestyle.bustrip.consumer.EmployeeAssignmentPK;
import org.progressivelifestyle.bustrip.google.domain.BusRunningInfo;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.repository.BusRunningInfoRepository;
import org.progressivelifestyle.bustrip.google.repository.EmployeeAssignmentRepo;
import org.progressivelifestyle.bustrip.google.repository.EmployeeRepository;
import org.progressivelifestyle.bustrip.google.repository.EventRepository;
import org.progressivelifestyle.bustrip.web.dto.DTOUtils;
import org.progressivelifestyle.bustrip.web.dto.EmployeeAssignmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService extends AbstractBaseService<EmployeeAssignment>{
	@Autowired
	private EmployeeAssignmentRepo empAssignRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private BusRunningInfoRepository busRunningInfoRepo;
	
	@Autowired
	private DTOUtils dtoUtils;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public EmployeeAssignmentDTO assignEmployeeToEvent(Long eventId, Long empId){
		Employee emp = empRepo.findOne(empId);
		Event event = eventRepo.findOne(eventId);
		EmployeeAssignmentPK pk = new EmployeeAssignmentPK(event, emp);
		EmployeeAssignment assignment = new EmployeeAssignment();
		assignment.setPk(pk);
		setMandatoryDates(assignment);
		return dtoUtils.toEmployeeAssignmentDTO(empAssignRepo.save(assignment));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public EmployeeAssignmentDTO cancelAssignment(Long empId, Long oldEventId){
		Employee employee = empRepo.getOne(empId);
		Event oldEvent = eventRepo.getOne(oldEventId);
		
		EmployeeAssignment assignment = empAssignRepo.findOne(new EmployeeAssignmentPK(oldEvent, employee));
		assignment.setEmployee(employee);
		assignment.setActive(true);
		setMandatoryDates(assignment);
		return dtoUtils.toEmployeeAssignmentDTO(empAssignRepo.save(assignment));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public EmployeeAssignmentDTO updateAssignment(Long empId, Long eventId, Long oldEventId){
		Employee employee = empRepo.getOne(empId);
		Event oldEvent = eventRepo.getOne(oldEventId);
		Event newEvent = eventRepo.getOne(eventId);
		
		EmployeeAssignment assignment = empAssignRepo.findOne(new EmployeeAssignmentPK(oldEvent, employee));
		assignment.setEmployee(employee);
		assignment.setEvent(newEvent);
		assignment.setActive(false);
		setMandatoryDates(assignment);
		return dtoUtils.toEmployeeAssignmentDTO(empAssignRepo.save(assignment));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteAssignment(Long empId, Long eventId){
		Employee employee = empRepo.getOne(empId);
		Event event = eventRepo.getOne(eventId);
		EmployeeAssignment assignment = empAssignRepo.findOne(new EmployeeAssignmentPK(event, employee));
		
		empAssignRepo.delete(assignment);
	}
	
	@Transactional(readOnly = true)
	public Collection<EmployeeAssignmentDTO> findAllEventsForDateRange(Date startDate, Date endDate, Long empId){
		return dtoUtils.toEmployeeAssignmentDTOs(empAssignRepo.findAllAssignmentWithinRange(startDate, endDate, empRepo.findOne(empId)));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void createOrUpdateBusRunningInfo(Date runningDate, Double lattitude, Double longitude, Long eventId, String busNum){
		Event event = eventRepo.findOne(eventId);
		BusRunningInfo busRunningInfo = null;
		try {busRunningInfo = busRunningInfoRepo.findByBusNumAndEvent(busNum, event);} catch (Exception e) {}
		if(busRunningInfo == null)
			busRunningInfo = new BusRunningInfo(runningDate, lattitude, longitude, event, busNum);
		else{
			busRunningInfo.setLattitude(lattitude);
			busRunningInfo.setLogitude(longitude);
		}
		Date now = new Date();
		if(busRunningInfo.getCreationDate() == null)
			busRunningInfo.setCreationDate(now);
		busRunningInfo.setLastUpdateDate(now);
		busRunningInfoRepo.save(busRunningInfo);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deletBusRunningInfo(Long eventId, String busNum){
		Event event = eventRepo.findOne(eventId);
		BusRunningInfo busRunningInfo = busRunningInfoRepo.findByBusNumAndEvent(busNum, event);
		busRunningInfoRepo.delete(busRunningInfo);
	}

	@Override
	public String[] getPropertiesToIgnoreDuringClone() {
		return new String[]{};
	}
}
