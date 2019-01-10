package org.progressivelifestyle.bustrip.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextUtil implements ApplicationContextAware{
	private static ApplicationContext appCtx;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtil.appCtx = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		if(appCtx == null)
			throw new IllegalStateException("Application has not been initialized yet!!");
		return appCtx;
	}
}
