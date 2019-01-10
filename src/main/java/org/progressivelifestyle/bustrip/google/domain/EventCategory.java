package org.progressivelifestyle.bustrip.google.domain;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "event_category")
@AssociationOverrides({
		@AssociationOverride(name = "pk.event", 
			joinColumns = @JoinColumn(name = "EVENT_ID")),
		@AssociationOverride(name = "pk.category", 
			joinColumns = @JoinColumn(name = "CATEGORY_ID")) })
public class EventCategory extends BaseEntity{
	@EmbeddedId
	private EventCategoryPK pk;
	
	private Date creationDate;
	private Date lastUpdateDate;
	public EventCategory(){}
	public EventCategory(EventCategoryPK pk) {
		super();
		this.pk = pk;
	}
	
	
	public EventCategoryPK getPk() {
		return pk;
	}
	public void setPk(EventCategoryPK pk) {
		this.pk = pk;
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
	
	public Category getCategory() {
		return pk.getCategory();
	}
	public Event getEvent() {
		return pk.getEvent();
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
		EventCategory other = (EventCategory) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
}
