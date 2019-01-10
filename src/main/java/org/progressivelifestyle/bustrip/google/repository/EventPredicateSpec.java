package org.progressivelifestyle.bustrip.google.repository;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.progressivelifestyle.bustrip.google.domain.Event;
import org.progressivelifestyle.bustrip.google.domain.Event_;
import org.springframework.data.jpa.domain.Specification;

public class EventPredicateSpec extends AbstractPredicateSpec{
	
	public static Specification<Event> findAllPredicate(){
		return new Specification<Event>() {
			@Override
			public Predicate toPredicate(Root<Event> sfRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(isNotFreezed(sfRoot, cb), getHiddenRestrictionForNY(sfRoot, cb), getDateRestrictionForNY(sfRoot, cb));
			}
		};
	}
	
	public static Specification<Event> titleIsLike(final String searchTerm) {
        
        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> sfRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                String likePattern = getLikePattern(searchTerm);
                return cb.and(isNotFreezed(sfRoot, cb), getHiddenRestrictionForNY(sfRoot, cb), cb.like(cb.lower(sfRoot.<String> get(Event_.title)), likePattern));
            }
        };
    }	
	public static Specification<Event> locationIsLike(final String searchTerm) {
        
        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> sfRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                String likePattern = getLikePattern(searchTerm);
                return cb.and(isNotFreezed(sfRoot, cb), getHiddenRestrictionForNY(sfRoot, cb), cb.like(cb.lower(sfRoot.<String> get(Event_.eventLocation)), likePattern));
            }             
        };
    }	
	
	public static Specification<Event> eventDateTimeIsEqual(final Date eventDate) {

		return new Specification<Event>() {
			@Override
			public Predicate toPredicate(Root<Event> sfRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(sfRoot.<Date> get(Event_.eventDateTime), eventDate);
			}

		};
	}

	public static Specification<Event> expirationIsEqual(final Date expiration) {

		return new Specification<Event>() {
			@Override
			public Predicate toPredicate(Root<Event> sfRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(isNotFreezed(sfRoot, cb), getHiddenRestrictionForNY(sfRoot, cb), getDateRestrictionForNY(sfRoot, cb), cb.greaterThanOrEqualTo(sfRoot.<Date> get(Event_.expiration), expiration));
			}

		};
	}

}