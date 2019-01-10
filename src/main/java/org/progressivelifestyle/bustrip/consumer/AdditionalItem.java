package org.progressivelifestyle.bustrip.consumer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.progressivelifestyle.bustrip.google.domain.BaseEntity;
@Entity
public class AdditionalItem extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date creationDate;
	private Date lastUpdateDate;
	
	private String itemName;
	private Double itemPrie;
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getItemPrie() {
		return itemPrie;
	}
	public void setItemPrie(Double itemPrie) {
		this.itemPrie = itemPrie;
	}
	public AdditionalItem(Long id, String itemName, Double itemPrie) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemPrie = itemPrie;
	}
	public AdditionalItem() {}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemPrie == null) ? 0 : itemPrie.hashCode());
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
		AdditionalItem other = (AdditionalItem) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrie == null) {
			if (other.itemPrie != null)
				return false;
		} else if (!itemPrie.equals(other.itemPrie))
			return false;
		return true;
	}
}
