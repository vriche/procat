package com.thinkgem.jeesite.test;

import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;

public class Test {

	public static void main(String[] args) {
//		 String time ="00295916";
//		 List<String> list = StringUtils.split(time,2);
//		 String[] array = list.toArray(new String[list.size()]);
//		 time = StringUtils.join(array, ":"); 
		 
		 String time ="00:29:59:16";
		 String[] array = time.split(":");
		 time = StringUtils.join(array, ""); 
  
         
		
		 System.out.println("1>>>>>>>>>>>>99999999999>>>>>"+time);
	}
}
