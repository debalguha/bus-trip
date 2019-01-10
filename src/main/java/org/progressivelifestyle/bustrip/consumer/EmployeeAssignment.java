package org.progressivelifestyle.bustrip.consumer;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.progressivelifestyle.bustrip.google.domain.BaseEntity;
import org.progressivelifestyle.bustrip.google.domain.Event;

@Entity
@Table(name = "event_assignment")
@AssociationOverrides({
		@AssociationOverride(name = "pk.event", 
			joinColumns = @JoinColumn(name = "EVENT_ID")),
		@AssociationOverride(name = "pk.employee", 
			joinColumns = @JoinColumn(name = "EMP_ID")) })
public class EmployeeAssignment extends BaseEntity{
	@EmbeddedId
	private EmployeeAssignmentPK pk;
	
	private boolean active;

	private Date creationDate;
	private Date lastUpdateDate;
	
	public EmployeeAssignment() {
		active = true;
	}

	public EmployeeAssignmentPK getPk() {
		return pk;
	}

	public void setPk(EmployeeAssignmentPK pk) {
		this.pk = pk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		EmployeeAssignment other = (EmployeeAssignment) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public Event getEvent() {
		return pk.getEvent();
	}

	public void setEvent(Event event) {
		pk.setEvent(event);
	}

	public Employee getEmployee() {
		return pk.getEmployee();
	}

	public void setEmployee(Employee employee) {
		pk.setEmployee(employee);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
