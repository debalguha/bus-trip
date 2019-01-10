package org.progressivelifestyle.bustrip.consumer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class SubscriptionAdditionalItemsPK implements Serializable{
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEventSubscription subscripion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private AdditionalItem item;

	public UserEventSubscription getSubscripion() {
		return subscripion;
	}

	public void setSubscripion(UserEventSubscription subscripion) {
		this.subscripion = subscripion;
	}

	public AdditionalItem getItem() {
		return item;
	}

	public void setItem(AdditionalItem item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((subscripion == null) ? 0 : subscripion.hashCode());
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
		SubscriptionAdditionalItemsPK other = (SubscriptionAdditionalItemsPK) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (subscripion == null) {
			if (other.subscripion != null)
				return false;
		} else if (!subscripion.equals(other.subscripion))
			return false;
		return true;
	}

	public SubscriptionAdditionalItemsPK(UserEventSubscription subscripion, AdditionalItem item) {
		super();
		this.subscripion = subscripion;
		this.item = item;
	}

	public SubscriptionAdditionalItemsPK() {}
}
