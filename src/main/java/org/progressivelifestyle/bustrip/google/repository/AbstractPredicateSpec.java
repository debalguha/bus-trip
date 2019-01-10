package org.progressivelifestyle.bustrip.google.repository;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.Event_;

public class AbstractPredicateSpec {
	protected static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append("%");
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}

	protected static Predicate getHiddenRestrictionForNY(Root<Event> sfRoot, CriteriaBuilder cb) {
		return cb.notEqual(sfRoot.<Boolean> get(Event_.hidden), Boolean.TRUE);
	}
	
	protected static Predicate getDateRestrictionForNY(Root<Event> sfRoot, CriteriaBuilder cb) {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return cb.greaterThanOrEqualTo(sfRoot.<Date> get(Event_.eventDateTime), today.getTime());
	}
	
	protected static Predicate isNotFreezed(Root<Event> sfRoot, CriteriaBuilder cb) {
		return cb.and(cb.or(cb.isNull(sfRoot.<Boolean> get(Event_.freeze)), (cb.equal(sfRoot.<Boolean> get(Event_.freeze), Boolean.FALSE))));
	}
}