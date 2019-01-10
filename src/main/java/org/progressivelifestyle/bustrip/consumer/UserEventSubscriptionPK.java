package org.progressivelifestyle.bustrip.consumer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.progressivelifestyle.bustrip.google.domain.Event;

@SuppressWarnings("serial")
@Embeddable
public class UserEventSubscriptionPK implements Serializable{
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.PERSIST)
	private Event event;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.PERSIST)
	private User user;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserEventSubscriptionPK() {}

	public UserEventSubscriptionPK(Event event, User user) {
		super();
		this.event = event;
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserEventSubscriptionPK other = (UserEventSubscriptionPK) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
