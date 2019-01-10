package org.progressivelifestyle.bustrip.web.dto;

import org.progressivelifestyle.bustrip.consumer.Employee;

public class EmployeeAssignmentDTO {
	private Employee employee;
	private EventDTO event;
	public EmployeeAssignmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeAssignmentDTO(Employee employee, EventDTO event) {
		super();
		this.employee = employee;
		this.event = event;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public EventDTO getEvent() {
		return event;
	}
	public void setEvent(EventDTO event) {
		this.event = event;
	}
	
}
