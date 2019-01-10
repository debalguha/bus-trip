package org.progressivelifestyle.bustrip.google.web;

import java.util.List;

import org.progressivelifestyle.bustrip.web.CustomJacksonMapper;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.Lists;

public abstract class AbstractBaseTest {
	public String getBaseURL(){
		return "http://rich-minds.com:9080";
		//return "http://localhost:8080";
	}
	public RestTemplate getRestTemplate(){
		List<HttpMessageConverter<?>> converters = Lists.newArrayList();
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new CustomJacksonMapper("yyyy/MM/dd HH:mm"));
		converters.add(converter);
		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);
		
		return template;
	}
}
