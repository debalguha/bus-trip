package org.progressivelifestyle.bustrip.google.repository;

import org.progressivelifestyle.bustrip.google.domain.EventCategory;
import org.progressivelifestyle.bustrip.google.domain.EventCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepo extends JpaRepository<EventCategory, EventCategoryPK> {

}
