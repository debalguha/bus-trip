package org.progressivelifestyle.bustrip.web.dto;

import java.util.Collection;

public class SavedEventsDTO {
	private EventDTO event;
	private Collection<AdditionalItemDTO> item;
	public SavedEventsDTO() {}
	public SavedEventsDTO(EventDTO eventDTO, Collection<AdditionalItemDTO> itemDTOs) {
		super();
		this.event = eventDTO;
		this.item = itemDTOs;
	}
	public EventDTO getEvent() {
		return event;
	}
	public void setEvent(EventDTO event) {
		this.event = event;
	}
	public Collection<AdditionalItemDTO> getItem() {
		return item;
	}
	public void setItem(Collection<AdditionalItemDTO> item) {
		this.item = item;
	}
	
}
