package org.progressivelifestyle.bustrip.google.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bus_running_info", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"bus_number","event_id"})
})
public class BusRunningInfo extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	private Date creationDate;
	@JsonIgnore
	private Date lastUpdateDate;
	
	@Column(name = "running_date")
	@Temporal(TemporalType.DATE)
	private Date runningDate;
	@Column(name = "bus_number", nullable = false)
	private String busNum;
	private Double lattitude;
	private Double logitude;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	private Event event;
	
	public BusRunningInfo(Date runningDate, Double lattitude, Double logitude, Event event, String busNum) {
		super();
		this.runningDate = runningDate;
		this.lattitude = lattitude;
		this.logitude = logitude;
		this.event = event;
		this.busNum = busNum;
	}
	
	public BusRunningInfo(){}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRunningDate() {
		return runningDate;
	}

	public void setRunningDate(Date runningDate) {
		this.runningDate = runningDate;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLogitude() {
		return logitude;
	}

	public void setLogitude(Double logitude) {
		this.logitude = logitude;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getBusNum() {
		return busNum;
	}

	public void setBusNum(String busNum) {
		this.busNum = busNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busNum == null) ? 0 : busNum.hashCode());
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
		BusRunningInfo other = (BusRunningInfo) obj;
		if (busNum == null) {
			if (other.busNum != null)
				return false;
		} else if (!busNum.equals(other.busNum))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}
	
}
