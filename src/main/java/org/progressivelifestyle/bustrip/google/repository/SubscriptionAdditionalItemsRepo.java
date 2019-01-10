package org.progressivelifestyle.bustrip.google.repository;

import java.util.Collection;

import org.progressivelifestyle.bustrip.consumer.SubscriptionAdditionalItems;
import org.progressivelifestyle.bustrip.consumer.SubscriptionAdditionalItemsPK;
import org.progressivelifestyle.bustrip.consumer.UserEventSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionAdditionalItemsRepo extends JpaRepository<SubscriptionAdditionalItems, SubscriptionAdditionalItemsPK> {
	@Query("from SubscriptionAdditionalItems where pk.subscripion = :userEventSubs")
	public Collection<SubscriptionAdditionalItems> findAllSubscriptionAdditionalItems(@Param("userEventSubs") UserEventSubscription userEventSubs);
}
