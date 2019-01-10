package org.progressivelifestyle.bustrip.google;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;

public class EventFilter {
	   @AfterReturning(
			      pointcut = "execution(* org.progressivelifestyle.bustrip.google.EventService.find*(..))",
			      returning= "result")
	   public void applyFilters(JoinPoint joinPoint, Object result){
		   System.out.println("Hi, filter called");
	   }
}
