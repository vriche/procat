/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;

/**
 * 字段类型转换
 * @author ThinkGem
 * @version 2013-03-10
 */
public class ProAudienceTargetType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		if(StringUtils.endsWith(val, ".0")){
			val = StringUtils.substringBefore(val, ".0");
		}else{
			val = String.valueOf(val.toString());
		}
		for (ProAudienceTarget e : DictUtils.getProAudienceTargetList("proAudienceTarget")){
			if (StringUtils.trimToEmpty(val).equals(e.getName())||StringUtils.trimToEmpty(val).equals(e.getId())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((ProAudienceTarget)val).getName() != null){
			return ((ProAudienceTarget)val).getName();
		}
		return "";
	}
}
