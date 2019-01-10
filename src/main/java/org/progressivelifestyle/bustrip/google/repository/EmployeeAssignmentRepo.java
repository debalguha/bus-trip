package org.progressivelifestyle.bustrip.google.repository;

import java.util.Collection;
import java.util.Date;

import org.progressivelifestyle.bustrip.consumer.Employee;
import org.progressivelifestyle.bustrip.consumer.EmployeeAssignment;
import org.progressivelifestyle.bustrip.consumer.EmployeeAssignmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeAssignmentRepo extends JpaRepository<EmployeeAssignment, EmployeeAssignmentPK> {
	@Query("from EmployeeAssignment where active is TRUE and pk.event.eventDateTime < :endDate and expiration > :startDate and pk.employee = :employee")
	public Collection<EmployeeAssignment> findAllAssignmentWithinRange(@Param("startDate") Date startdate, 
			@Param("endDate") Date endDate, @Param("employee") Employee employee);
}
