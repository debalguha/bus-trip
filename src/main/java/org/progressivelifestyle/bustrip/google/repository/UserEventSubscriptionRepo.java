package org.progressivelifestyle.bustrip.google.repository;

import java.util.Collection;

import org.progressivelifestyle.bustrip.consumer.User;
import org.progressivelifestyle.bustrip.consumer.UserEventSubscription;
import org.progressivelifestyle.bustrip.consumer.UserEventSubscriptionPK;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserEventSubscriptionRepo extends JpaRepository<UserEventSubscription, UserEventSubscriptionPK>{
	@Query("from UserEventSubscription where pk.user = :user")
	public Collection<UserEventSubscription> findByUser(@Param("user") User user);
	@Query("from UserEventSubscription where pk.event=:event")
	public Collection<UserEventSubscription> getAllSubscription(@Param("event") Event event);
	@Query("select count(subs.pk.user) from UserEventSubscription subs where pk.event=:event")
	public int getSubscriptionCount(@Param("event") Event event);
}
