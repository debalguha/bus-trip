package org.progressivelifestyle.bustrip.web.dto;


public class PickupLocationDTO {
	private String neighborhoodName;
	private String information;
	private String pickupName;
	private String pickupAddress;
	private String latitude;
	private String longitude;
	private String pickupTime;
	public PickupLocationDTO() {}
	public PickupLocationDTO(String neighborhoodName, String information, String pickupName, String pickupAddress, String latitude, String longitude, String pickupTime) {
		super();
		this.neighborhoodName = neighborhoodName;
		this.information = information;
		this.pickupName = pickupName;
		this.pickupAddress = pickupAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.pickupTime = pickupTime;
	}
	public PickupLocationDTO(PickupLocationDTO otherPickup) {
		super();
		this.neighborhoodName = otherPickup.neighborhoodName;
		this.information = otherPickup.information;
		this.pickupName = otherPickup.pickupName;
		this.pickupAddress = otherPickup.pickupAddress;
		this.latitude = otherPickup.latitude;
		this.longitude = otherPickup.longitude;
		this.pickupTime = otherPickup.pickupTime;
	}	
	public String getNeighborhoodName() {
		return neighborhoodName;
	}
	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getPickupName() {
		return pickupName;
	}
	public void setPickupName(String pickupName) {
		this.pickupName = pickupName;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	@Override
	public String toString() {
		return "PickupLocationDTO [neighborhoodName=" + neighborhoodName + ", information=" + information + ", pickupName=" + pickupName + ", pickupAddress=" + pickupAddress + ", latitude=" + latitude + ", longitude=" + longitude + ", pickupTime=" + pickupTime + "]";
	}
	
}
