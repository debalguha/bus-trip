package org.progressivelifestyle.bustrip.consumer;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.progressivelifestyle.bustrip.google.domain.BaseEntity;

@Entity
//@EntityListeners(value = SubscriptionValidator.class)
@Table(name = "event_subscription")
@AssociationOverrides({
		@AssociationOverride(name = "pk.event", 
			joinColumns = @JoinColumn(name = "EVENT_ID")),
		@AssociationOverride(name = "pk.user", 
			joinColumns = @JoinColumn(name = "USER_ID")) })
public class UserEventSubscription extends BaseEntity{
	@EmbeddedId
	private UserEventSubscriptionPK pk;
	
	private Date creationDate;
	private Date lastUpdateDate;
	public UserEventSubscriptionPK getPk() {
		return pk;
	}
	public void setPk(UserEventSubscriptionPK pk) {
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
		UserEventSubscription other = (UserEventSubscription) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	
	
}
