package org.progressivelifestyle.bustrip;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventState;

import com.google.api.client.util.Lists;

public class ProjectUtil {
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dateTimeFormatForTests = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	public static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	public static Collection<Event> filterForNY(Collection<Event> events){
		return filterForState(events, EventState.NY);
	}
	public static Collection<Event> filterForSF(Collection<Event> events){
		return filterForState(events, EventState.SF);
	}
	
	public static Collection<Event> filterForState(Collection<Event> events, EventState eventState){
		Collection<Event> filtered = Lists.newArrayList();
		for(Event event : events){
			if(event.getEventState().equals(eventState) && !event.isFreeze())
				filtered.add(event);
		}
		return filtered;
	}
}
