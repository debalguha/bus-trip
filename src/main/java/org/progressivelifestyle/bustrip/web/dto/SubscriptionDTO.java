package org.progressivelifestyle.bustrip.web.dto;

import java.util.Collection;

public class SubscriptionDTO {
	private long userId;
	private long eventId;
	
	private Collection<AdditionalItemDTO> additionalItems;

	public SubscriptionDTO() {}

	public SubscriptionDTO(long userId, long eventId, Collection<AdditionalItemDTO> additionalItems) {
		super();
		this.userId = userId;
		this.eventId = eventId;
		this.additionalItems = additionalItems;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public Collection<AdditionalItemDTO> getAdditionalItems() {
		return additionalItems;
	}

	public void setAdditionalItems(Collection<AdditionalItemDTO> additionalItems) {
		this.additionalItems = additionalItems;
	}
	
}
