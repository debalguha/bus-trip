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
@Table(name = "user_subscription_additional_item")
@AssociationOverrides({
		@AssociationOverride(name = "pk.subscription", 
			joinColumns = @JoinColumn(name = "SUBSCRIPTION_ID")),
		@AssociationOverride(name = "pk.item", 
			joinColumns = @JoinColumn(name = "ITEM_ID"))})
public class SubscriptionAdditionalItems extends BaseEntity{
	@EmbeddedId
	private SubscriptionAdditionalItemsPK pk;
	
	private Integer quantity;
	private Double amount; 
	
	private Date creationDate;
	private Date lastUpdateDate;
	public SubscriptionAdditionalItemsPK getPk() {
		return pk;
	}
	public void setPk(SubscriptionAdditionalItemsPK pk) {
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
		SubscriptionAdditionalItems other = (SubscriptionAdditionalItems) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
