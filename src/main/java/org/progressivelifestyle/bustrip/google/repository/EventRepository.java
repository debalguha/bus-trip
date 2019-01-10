package org.progressivelifestyle.bustrip.google.repository;

import java.util.Collection;
import java.util.Date;

import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.EventState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
	@Query("from Event where eventDateTime >= :eventDateTime and (freeze is null or freeze <> true)")
	Collection<Event> findByEventDateTime(@Param("eventDateTime") Date eventDate);
	@Query("from Event where eventDateTime <= :expirationDate and (freeze is null or freeze <> true)")
	Collection<Event> findByExpirationtDateTime(@Param("expirationDate") Date expirationDate);
	@Query("from Event where eventState=:eventState and eventDateTime between :weekStartDate and :weekEndDate and (freeze is null or freeze <> true)")
	Collection<Event> findByWeekStartEndDateTime(@Param("weekStartDate") Date weekStartDate, @Param("weekEndDate") Date weekEndDate,  @Param("eventState") EventState state);
	@Query("from Event where eventDateTime <= :eventDateTime and (freeze is null or freeze <> true)")
	Collection<Event> findByEventDateTimePast(@Param("eventDateTime") Date eventDate);
}
