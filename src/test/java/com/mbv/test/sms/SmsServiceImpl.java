package com.mbv.test.sms;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;

public class SmsServiceImpl {

	Client client = Client.create();

    Gson gson = new Gson();
    
	String mcaSmsUrl = "http://localhost:8787/mca-service/api/sms/request";
	
	public String sendMessage(String mobile, String content) {
		Map<String, String> data = new HashMap<String, String>();
    	
    	data.put("mobile", mobile);
    	data.put("sms", content);
    	
    	String response = client.resource(mcaSmsUrl)
        	.accept(MediaType.APPLICATION_JSON_TYPE)
        	.entity(gson.toJson(data), MediaType.APPLICATION_JSON_TYPE)
        	.post(String.class);
    	
    	return response;
	}
	
	public void setMcaSmsUrl(String mcaSmsUrl) {
		this.mcaSmsUrl = mcaSmsUrl;
	}
}
