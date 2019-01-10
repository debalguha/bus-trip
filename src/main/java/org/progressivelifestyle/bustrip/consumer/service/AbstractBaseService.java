package org.progressivelifestyle.bustrip.consumer.service;

import java.util.Date;

import org.progressivelifestyle.bustrip.google.domain.BaseEntity;

public abstract class AbstractBaseService<T extends BaseEntity> {
	public void setMandatoryDates(T entity){
		Date nowDate = new Date();
		if(entity.getCreationDate() == null)
			entity.setCreationDate(nowDate);
		if(entity.getLastUpdateDate() == null)
			entity.setLastUpdateDate(nowDate);
	}
	public abstract String[] getPropertiesToIgnoreDuringClone();
}
