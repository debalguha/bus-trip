package org.progressivelifestyle.bustrip.consumer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.progressivelifestyle.bustrip.google.domain.Event;

@SuppressWarnings("serial")
@Embeddable
public class EmployeeAssignmentPK implements Serializable{
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.PERSIST)
	private Event event;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.PERSIST)
	private Employee employee;

	public EmployeeAssignmentPK() {}

	public EmployeeAssignmentPK(Event event, Employee employee) {
		super();
		this.event = event;
		this.employee = employee;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeAssignmentPK other = (EmployeeAssignmentPK) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}

}
