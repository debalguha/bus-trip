package org.progressivelifestyle.bustrip.web.dto;

import java.util.Date;

public class BusRunningInfoDTO {
	private long id;
	private double latitude;
	private double longitude;
	private Date runDate;
	private String busNum;
	public BusRunningInfoDTO(long id, double latitude, double longitude, Date runDate, String busNum) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.runDate = runDate;
		this.busNum = busNum;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	public String getBusNum() {
		return busNum;
	}
	public void setBusNum(String busNum) {
		this.busNum = busNum;
	}
}
