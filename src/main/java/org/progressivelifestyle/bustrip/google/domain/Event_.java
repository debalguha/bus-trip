package org.progressivelifestyle.bustrip.google.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Event.class)
public class Event_ {
	public static volatile SingularAttribute<Event, String> title;
	public static volatile SingularAttribute<Event, String> eventLocation;
	public static volatile SingularAttribute<Event, Boolean> hidden;
	public static volatile SingularAttribute<Event, Date> eventDateTime;
	public static volatile SingularAttribute<Event, Date> expiration;
	public static volatile SingularAttribute<Event, Boolean> freeze;
}
