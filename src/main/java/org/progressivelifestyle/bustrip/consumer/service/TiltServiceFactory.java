package org.progressivelifestyle.bustrip.consumer.service;

import org.springframework.stereotype.Component;
import org.tilt.api.req.endpoint.EndpointType;
import org.tilt.service.TIltServiceBuilder;
import org.tilt.service.TiltService;

@Component
public class TiltServiceFactory {
	public static TiltService getServiceForSandBox(String apiKey, String apiSecret){
		return new TIltServiceBuilder().withApiKey(apiKey).withApiSecret(apiSecret).withEndpointType(EndpointType.SANDBOX).build();
	}
	public TiltService getServiceForSandBox(){
		return new TIltServiceBuilder().withApiKey("ec541c2363007b8df3e9eb47f7ce99").withApiSecret("afa4488d8a8973a88be49b330804e78dc1982d6f").withEndpointType(EndpointType.SANDBOX).build();
	}
	public static TiltService getServiceForLive(String apiKey, String apiSecret){
		return new TIltServiceBuilder().withApiKey(apiKey).withApiSecret(apiSecret).withEndpointType(EndpointType.LIVE).build();
	}
}
