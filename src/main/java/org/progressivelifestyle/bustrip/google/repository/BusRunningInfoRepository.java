package org.progressivelifestyle.bustrip.google.repository;

import org.progressivelifestyle.bustrip.google.domain.BusRunningInfo;
import org.progressivelifestyle.bustrip.google.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusRunningInfoRepository  extends JpaRepository<BusRunningInfo, Long>{
	@Query("from BusRunningInfo b where b.busNum = :busNum and b.event = :event")
	public BusRunningInfo findByBusNumAndEvent(@Param("busNum") String busNum, @Param("event") Event event);
}
