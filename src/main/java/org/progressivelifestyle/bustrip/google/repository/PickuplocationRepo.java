package org.progressivelifestyle.bustrip.google.repository;

import org.progressivelifestyle.bustrip.google.domain.PickupLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PickuplocationRepo  extends JpaRepository<PickupLocation, Long>, JpaSpecificationExecutor<PickupLocation>{

}
