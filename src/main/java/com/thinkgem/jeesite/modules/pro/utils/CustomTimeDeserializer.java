package com.thinkgem.jeesite.modules.pro.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.thinkgem.jeesite.common.utils.StringUtils;

public class CustomTimeDeserializer extends JsonDeserializer<String> {
	@Override  
    public String deserialize(JsonParser jp, DeserializationContext ctxt)  
            throws IOException, JsonProcessingException {  
		String time = jp.getText();
		 System.out.println("1>>>>>>>>>>>>99999999999>>>>>"+time);
		String[] array = time.split(":");
		time = StringUtils.join(array, ""); 
		 System.out.println("2>>>>>>>>>>>>99999999999>>>>>"+time);
        return time;  
    }  
}
