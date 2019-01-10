package org.progressivelifestyle.bustrip.web.dto;

import java.util.Date;
import java.util.Set;

import org.progressivelifestyle.bustrip.google.EventType;
import org.progressivelifestyle.bustrip.google.domain.EventState;

public class EventDTO {
	private long id;
	private Double price;
	private int featured;
	private EventType eventType;
	private int hidden;
	private String title;
	private String eventLocation;
	private int upsellOrUpgradeEvent;
	private Date eventDateTime;
	private String shortDesc;
	private String description;
	private String lineup;
	private String imageURL;
	private int minPassenger;
	private int maxPassenger;
	private Date expiration;
	private EventState eventState;
	private String tiltCampaignId;
	private Set<String> eventcategory;
	private Set<PickupLocationDTO> pickupLocations;
	public EventDTO() {}
	public EventDTO(long id, Double price, int featured, EventType eventType, int hidden, String title, String eventLocation, int upsellOrUpgradeEvent, Date eventDateTime, String shortDesc, String description, String lineup, String imageURL, int minPassenger, int maxPassenger, Date expiration, EventState eventState, Set<String> eventcategory, Set<PickupLocationDTO> pickupLocations, String tiltCampaignId) {
		super();
		this.id = id;
		this.price = price;
		this.featured = featured;
		this.eventType = eventType;
		this.hidden = hidden;
		this.title = title;
		this.eventLocation = eventLocation;
		this.upsellOrUpgradeEvent = upsellOrUpgradeEvent;
		this.eventDateTime = eventDateTime;
		this.shortDesc = shortDesc;
		this.description = description;
		this.lineup = lineup;
		this.imageURL = imageURL;
		this.minPassenger = minPassenger;
		this.maxPassenger = maxPassenger;
		this.expiration = expiration;
		this.eventState = eventState;
		this.eventcategory = eventcategory;
		this.pickupLocations = pickupLocations;
		this.tiltCampaignId = tiltCampaignId;
	}
	public int getFeatured() {
		return featured;
	}
	public void setFeatured(int featured) {
		this.featured = featured;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public int getUpsellOrUpgradeEvent() {
		return upsellOrUpgradeEvent;
	}
	public void setUpsellOrUpgradeEvent(int upsellOrUpgradeEvent) {
		this.upsellOrUpgradeEvent = upsellOrUpgradeEvent;
	}
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLineup() {
		return lineup;
	}
	public void setLineup(String lineup) {
		this.lineup = lineup;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getMinPassenger() {
		return minPassenger;
	}
	public void setMinPassenger(int minPassenger) {
		this.minPassenger = minPassenger;
	}
	public int getMaxPassenger() {
		return maxPassenger;
	}
	public void setMaxPassenger(int maxPassenger) {
		this.maxPassenger = maxPassenger;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public EventState getEventState() {
		return eventState;
	}
	public void setEventState(EventState eventState) {
		this.eventState = eventState;
	}
	public Set<String> getEventcategory() {
		return eventcategory;
	}
	public void setEventcategory(Set<String> eventcategory) {
		this.eventcategory = eventcategory;
	}
	public Set<PickupLocationDTO> getPickupLocations() {
		return pickupLocations;
	}
	public void setPickupLocations(Set<PickupLocationDTO> pickupLocations) {
		this.pickupLocations = pickupLocations;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", featured=" + featured + ", eventType=" + eventType + ", hidden=" + hidden + ", title=" + title + ", eventLocation=" + eventLocation + ", upsellOrUpgradeEvent=" + upsellOrUpgradeEvent + ", eventDateTime=" + eventDateTime + ", shortDesc=" + shortDesc + ", description=" + description + ", lineup=" + lineup + ", imageURL=" + imageURL + ", minPassenger="
				+ minPassenger + ", maxPassenger=" + maxPassenger + ", expiration=" + expiration + ", eventState=" + eventState + ", eventcategory=" + eventcategory + ", pickupLocations=" + pickupLocations + "]";
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTiltCampaignId() {
		return tiltCampaignId;
	}
	public void setTiltCampaignId(String tiltCampaignId) {
		this.tiltCampaignId = tiltCampaignId;
	}
	
	
}
