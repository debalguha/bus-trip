package org.progressivelifestyle.bustrip.google.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.progressivelifestyle.bustrip.google.EventType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@Column(nullable = false)
	private Date creationDate;
	@JsonIgnore
	@Column(nullable = false)
	private Date lastUpdateDate;
	
	private Double price;
	private boolean featured;
	@Enumerated(EnumType.STRING)
	private EventType eventType;
	private boolean hidden;
	private String title;
	private String eventLocation;
	private boolean upsellOrUpgradeEvent;
	private Date eventDateTime;
	private boolean freeze;
	
	private String shortDesc;
	@Lob
	private String description;
	@Lob
	private String lineup;
	
	private String imageURL;
	private int minPassenger;
	private int maxPassenger;
	private Date expiration;
	
	private String tiltCampaignId;
	
	@Enumerated(EnumType.STRING)
	private EventState eventState;
	
	@OneToMany(mappedBy="pk.event", fetch=FetchType.LAZY)
	private Set<EventCategory> eventcategory;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="event")
	private Set<PickupLocation> pickupLocations;
	
	public Event(){}
	public Event(Double price, boolean featured, EventType eventType, boolean hidden, String title, String eventLocation, boolean upsellOrUpgradeEvent, Date eventDateTime, String shortDesc, String description, String lineup, String imageURL, int minPassenger, int maxPassenger, Date expiration, EventState eventState, String tiltCampaignId) {
		super();
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
		this.tiltCampaignId = tiltCampaignId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isFeatured() {
		return featured;
	}
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
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
	public Set<EventCategory> getEventcategory() {
		return eventcategory;
	}
	public void setEventcategory(Set<EventCategory> eventcategory) {
		this.eventcategory = eventcategory;
	}
	public EventState getEventState() {
		return eventState;
	}
	public void setEventState(EventState eventState) {
		this.eventState = eventState;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventDateTime == null) ? 0 : eventDateTime.hashCode());
		result = prime * result + ((eventState == null) ? 0 : eventState.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Event other = (Event) obj;
		if (eventDateTime == null) {
			if (other.eventDateTime != null)
				return false;
		} else if (!eventDateTime.equals(other.eventDateTime))
			return false;
		if (eventState != other.eventState)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	public Set<PickupLocation> getPickupLocations() {
		return pickupLocations;
	}
	public void setPickupLocations(Set<PickupLocation> pickupLocations) {
		this.pickupLocations = pickupLocations;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public boolean isUpsellOrUpgradeEvent() {
		return upsellOrUpgradeEvent;
	}
	public void setUpsellOrUpgradeEvent(boolean upsellOrUpgradeEvent) {
		this.upsellOrUpgradeEvent = upsellOrUpgradeEvent;
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
	public boolean isFreeze() {
		return freeze;
	}
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}	
	
	
}
