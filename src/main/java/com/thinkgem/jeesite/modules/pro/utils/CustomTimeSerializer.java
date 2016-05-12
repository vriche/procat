package com.thinkgem.jeesite.modules.pro.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.thinkgem.jeesite.common.utils.StringUtils;

public class CustomTimeSerializer extends JsonSerializer<String> {
	@Override
    public void serialize(String time, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
//		  00:00:00:00
		 List<String> list = StringUtils.split(time,2);
		 String[] array = list.toArray(new String[list.size()]);
		 time = StringUtils.join(array, ":"); 
		 System.out.println("1>>>>>>>>>>>>888888>>>>>"+time);
         gen.writeString(time);
    }
}
