package org.progressivelifestyle.bustrip.google.domain;

import java.util.Date;

public abstract class BaseEntity {
	public abstract Date getCreationDate();
	public abstract Date getLastUpdateDate();
	public abstract void setCreationDate(Date creationDate);
	public abstract void setLastUpdateDate(Date lastUpdateDate);
}
