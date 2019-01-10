package org.progressivelifestyle.bustrip.web;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.progressivelifestyle.bustrip.google.CategoryService;
import org.progressivelifestyle.bustrip.google.EventService;
import org.progressivelifestyle.bustrip.web.dto.APIResponse;
import org.progressivelifestyle.bustrip.web.dto.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.util.Maps;

public abstract class AbstractEventController {
	@Autowired
	protected EventService service;
	@Autowired
	protected CategoryService categoryService;
	@Autowired 
	protected DTOUtils dtoUtils;
	
	private AtomicInteger exceptionCount = new AtomicInteger(0);
	
	private static final Logger logger = Logger.getLogger(AbstractEventController.class);
	
	@ExceptionHandler(Throwable.class)
	public @ResponseBody APIResponse handleException(Exception exception){
		int excptCnt = exceptionCount.incrementAndGet();
		logger.error("Controller error occurred: "+excptCnt, exception);
		Map<String, Object> exceptionMap = Maps.newHashMap();
		exceptionMap.put("ERROR_COUNT", excptCnt);
		exceptionMap.put("EXCEPTION", exception);
		return new APIResponse(ERROR_CODE.UNKNOWN, STATUS.FAIL, exceptionMap);
	}
}
