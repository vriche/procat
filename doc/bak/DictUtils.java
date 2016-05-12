/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceCarrierDao;
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceCityDao;
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceSourceDao;
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceTargetDao;
import com.thinkgem.jeesite.modules.pro.dao.ProCountryDao;
import com.thinkgem.jeesite.modules.pro.dao.ProDubbingLanguageDao;
import com.thinkgem.jeesite.modules.pro.dao.ProGradeDao;
import com.thinkgem.jeesite.modules.pro.dao.ProTapeTypeDao;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCarrier;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCity;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceSource;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget;
import com.thinkgem.jeesite.modules.pro.entity.ProCountry;
import com.thinkgem.jeesite.modules.pro.entity.ProDubbingLanguage;
import com.thinkgem.jeesite.modules.pro.entity.ProGrade;
import com.thinkgem.jeesite.modules.pro.entity.ProTapeType;
import com.thinkgem.jeesite.modules.sys.entity.Dict;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	public static final String CACHE_PRO_DICT_MAP = "proDictMap";
	private static ProAudienceCarrierDao proAudienceCarrierDao = SpringContextHolder.getBean(ProAudienceCarrierDao.class);
	private static ProAudienceSourceDao proAudienceSourceDao = SpringContextHolder.getBean(ProAudienceSourceDao.class);
	private static ProAudienceTargetDao proAudienceTargetDao = SpringContextHolder.getBean(ProAudienceTargetDao.class);
	private static ProCountryDao proCountryDao = SpringContextHolder.getBean(ProCountryDao.class);
	private static ProDubbingLanguageDao proDubbingLanguageDao = SpringContextHolder.getBean(ProDubbingLanguageDao.class);
	private static ProGradeDao proGradeDao = SpringContextHolder.getBean(ProGradeDao.class);
	private static ProTapeTypeDao proTapeTypeDao = SpringContextHolder.getBean(ProTapeTypeDao.class);
	private static ProAudienceCityDao proAudienceCityDao = SpringContextHolder.getBean(ProAudienceCityDao.class);

	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					System.out.println(">>>>>>>>>>>>"+dict.getLabel());
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}	
	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_DICT_MAP);
//		List<Dict> dictList = new ArrayList<Dict>();
		
		if (dictMap==null){
			dictMap = Maps.newHashMap();	
	        if("proCountry".equals(type)){
	        	System.out.println(">>>>>>>>>>>>proCountry");
	        	Dict dictEmpty = new Dict();
				dictEmpty.setLabel("");
				dictEmpty.setValue("");
			
	        	
				for (ProCountry obj : proCountryDao.findAllList(new ProCountry())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					List<Dict> dictList = dictMap.get(type);
					if (dictList != null){
						dictList.add(dict);
					}else{

						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);
	        }
	        
	        if("proGrade".equals(type)){
				
	        	Dict dictEmpty = new Dict();
				dictEmpty.setLabel("");
				dictEmpty.setValue("");
			
				for (ProGrade obj : proGradeDao.findAllList(new ProGrade())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					List<Dict> dictList = dictMap.get(type);
					if (dictList != null){
						dictList.add(dict);
					}else{
						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);
	        }
	        
	        if("proDubbingLanguag".equals(type)){
				
	        	Dict dictEmpty = new Dict();
				dictEmpty.setLabel("");
				dictEmpty.setValue("");
				
	        	
				for (ProDubbingLanguage obj : proDubbingLanguageDao.findAllList(new ProDubbingLanguage())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					List<Dict> dictList = dictMap.get(type);
					if (dictList != null){
						dictList.add(dict);
					}else{
						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);			
				
	        }
	        
	        if("proAudienceSource".equals(type)){
			
	        	Dict dictEmpty = new Dict();
				dictEmpty.setLabel("");
				dictEmpty.setValue("");

	        	
				for (ProAudienceSource obj : proAudienceSourceDao.findAllList(new ProAudienceSource())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					List<Dict> dictList = dictMap.get(type);
					if (dictList != null){
						dictList.add(dict);
					}else{
						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);					
	        }
	        
	        
	        if("proAudienceCity".equals(type)){
				
	        	Dict dictEmpty = new Dict();
				dictEmpty.setLabel("");
				dictEmpty.setValue("");
		
	        	
				for (ProAudienceCity obj : proAudienceCityDao.findAllList(new ProAudienceCity())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					List<Dict> dictList = dictMap.get(type);
					if (dictList != null){
						dictList.add(dict);
					}else{
						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);					
				
				
	        }
	        if("proAudienceCarrier".equals(type)){
	         	Dict dictEmpty = new Dict();
	         	dictEmpty.setLabel("");
	         	dictEmpty.setValue("");

				for (ProAudienceCarrier obj : proAudienceCarrierDao.findAllList(new ProAudienceCarrier())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					List<Dict> dictList = dictMap.get(type);
					if (dictList != null){
						dictList.add(dict);
					}else{
						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);					
				
	        }
	        if("proAudienceTarget".equals(type)){
				List<Dict> dictList = dictMap.get(type);
	        	Dict dictEmpty = new Dict();
				dictEmpty.setLabel("");
				dictEmpty.setValue("");
				dictList.add(dictEmpty);
	        	
				for (ProAudienceTarget obj : proAudienceTargetDao.findAllList(new ProAudienceTarget())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					if (dictList != null){
						dictList.add(dict);
					}else{
						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);				
				
				
	        }
	        if("proTapeType".equals(type)){
				List<Dict> dictList = dictMap.get(type);
	        	Dict dictEmpty = new Dict();
				dictEmpty.setLabel("");
				dictEmpty.setValue("");
				dictList.add(dictEmpty);
	        	
				for (ProTapeType obj : proTapeTypeDao.findAllList(new ProTapeType())){
					Dict dict = new Dict();
					dict.setLabel(obj.getName());
					dict.setValue(obj.getId());
					if (dictList != null){
						dictList.add(dict);
					}else{
						dictMap.put(type, Lists.newArrayList(dictEmpty));
						dictMap.put(type, Lists.newArrayList(dict));
					}			
				}
				CacheUtils.put(CACHE_PRO_DICT_MAP, dictMap);				
	
	        } 
			
			
		}


		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
}
