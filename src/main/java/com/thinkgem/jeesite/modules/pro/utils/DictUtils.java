/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.utils;

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
import com.thinkgem.jeesite.modules.pro.dao.ProBaseInfoDao;
import com.thinkgem.jeesite.modules.pro.dao.ProCountryDao;
import com.thinkgem.jeesite.modules.pro.dao.ProDubbingLanguageDao;
import com.thinkgem.jeesite.modules.pro.dao.ProGradeDao;
import com.thinkgem.jeesite.modules.pro.dao.ProTapeTypeDao;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCarrier;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCity;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceSource;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget;
import com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo;
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
	public static final String CACHE_PRO_GRADE_DICT_MAP = "proGradeDictMap";
    public static final String CACHE_PRO_DUBBINGLANGUAG_DICT_MAP = "proDubbingLanguagDictMap";
    public static final String CACHE_PRO_AUDIENCESOURCE_DICT_MAP = "proAudienceSourceDictMap";
    public static final String CACHE_PRO_AUDIENCECITY_DICT_MAP = "proAudienceCityDictMap";
    public static final String CACHE_PRO_AUDIENCECARRIER_DICT_MAP = "proAudienceCarrierDictMap";
    public static final String CACHE_PRO_AUDIENCETARGET_DICT_MAP = "proAudienceTargetDictMap";
    public static final String CACHE_PRO_TAPETYPE_DICT_MAP = "proTapeTypeDictMap";
    public static final String CACHE_PRO_BASEINFO_DICT_MAP = "proBaseInfoDictMap";
	
	private static ProAudienceCarrierDao proAudienceCarrierDao = SpringContextHolder.getBean(ProAudienceCarrierDao.class);
	private static ProAudienceSourceDao proAudienceSourceDao = SpringContextHolder.getBean(ProAudienceSourceDao.class);
	private static ProAudienceTargetDao proAudienceTargetDao = SpringContextHolder.getBean(ProAudienceTargetDao.class);
	private static ProCountryDao proCountryDao = SpringContextHolder.getBean(ProCountryDao.class);
	private static ProDubbingLanguageDao proDubbingLanguageDao = SpringContextHolder.getBean(ProDubbingLanguageDao.class);
	private static ProGradeDao proGradeDao = SpringContextHolder.getBean(ProGradeDao.class);
	private static ProTapeTypeDao proTapeTypeDao = SpringContextHolder.getBean(ProTapeTypeDao.class);
	private static ProAudienceCityDao proAudienceCityDao = SpringContextHolder.getBean(ProAudienceCityDao.class);
	private static ProBaseInfoDao proBaseInfoDao = SpringContextHolder.getBean(ProBaseInfoDao.class);
	
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
	
	public static Map<String,Object> getDictMap(String type,int model){
		List<Dict> ls = getDictList(type);
		Map<String, Object> dictMap = Maps.newHashMap();
		for(Dict dict:ls){
			if(model == 1){
				dictMap.put(dict.getLabel(), dict.getValue());
			}
			if(model == 2){
				dictMap.put(dict.getValue(), dict.getLabel());
			}
		}
		return dictMap;
	}
	
	public static List<ProAudienceCity> getProAudienceCityList(String type){
		List<Dict> ls = getDictList(type);
		List<ProAudienceCity> ls2 =  Lists.newArrayList();
		for(Dict dict:ls){
			ProAudienceCity proAudienceCity = new ProAudienceCity();
//			System.out.print("importFile 99999999 >>>>>>>>>>>>>"+ dict.getValue());
			
			proAudienceCity.setId(dict.getValue());
			proAudienceCity.setName(dict.getLabel());
			if(!"".equals(dict.getLabel()))ls2.add(proAudienceCity);
		}
		return ls2;
	}
	public static List<ProAudienceTarget> getProAudienceTargetList(String type){
		List<Dict> ls = getDictList(type);
		List<ProAudienceTarget> ls2 =  Lists.newArrayList();
		for(Dict dict:ls){
			ProAudienceTarget proAudienceTarget = new ProAudienceTarget();
			proAudienceTarget.setId(dict.getValue());
			proAudienceTarget.setName(dict.getLabel());
			if(!"".equals(dict.getLabel()))ls2.add(proAudienceTarget);
		}
		return ls2;
	}
	
	public static List<ProAudienceCarrier> getProAudienceCarrierList(String type){
		List<Dict> ls = getDictList(type);
		List<ProAudienceCarrier> ls2 =  Lists.newArrayList();
		for(Dict dict:ls){
			ProAudienceCarrier proAudienceCarrier = new ProAudienceCarrier();
			proAudienceCarrier.setId(dict.getValue());
			proAudienceCarrier.setName(dict.getLabel());
			if(!"".equals(dict.getLabel()))ls2.add(proAudienceCarrier);
		}
		
		return ls2;
	}
	
	public static List<ProAudienceSource> getProAudienceSourceList(String type){
		List<Dict> ls = getDictList(type);
		
//		System.out.print("getProAudienceSourceList vvvvvvvvvvvvvvvvvvvvvvv >>>>>>>>>>>>>>"+ ls.size() +"\n");
		
		
		List<ProAudienceSource> ls2 =  Lists.newArrayList();
		for(Dict dict:ls){
			ProAudienceSource proAudienceSource = new ProAudienceSource();
			proAudienceSource.setId(dict.getValue());
			proAudienceSource.setName(dict.getLabel());
			if(!"".equals(dict.getLabel()))ls2.add(proAudienceSource);
		}
		
//		System.out.print("getProAudienceSourceList vvvvvvvvvvvvvvvvvvvvvvv >>>>>>>>>>>>>>"+ ls2.size() +"\n");
		
		
		return ls2;
	}
	
	public static List<ProBaseInfo> getProBaseInfoList(String type){
		@SuppressWarnings("unchecked")
    	Map<String, List<ProBaseInfo>> dictMap = (Map<String, List<ProBaseInfo>>)CacheUtils.get(CACHE_PRO_BASEINFO_DICT_MAP);
    	
		if (dictMap==null){
    		dictMap = Maps.newHashMap();	
    		dictMap.put(type, proBaseInfoDao.findAllList(new ProBaseInfo()));
			CacheUtils.put(CACHE_PRO_BASEINFO_DICT_MAP, dictMap);
    	}
    	
    	List<ProBaseInfo> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	
	
	public static List<Dict> getDictList(String type){
		
		
	        if("proGrade".equals(type)){
	        	@SuppressWarnings("unchecked")
	        	Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_GRADE_DICT_MAP);
	        	
	        	if (dictMap==null){
	        		dictMap = Maps.newHashMap();	
	        	   	Dict dictEmpty = new Dict();
					dictEmpty.setLabel("");
					dictEmpty.setValue("");
	        		dictMap.put(type, Lists.newArrayList(dictEmpty));
					for (ProGrade obj : proGradeDao.findAllList(new ProGrade())){
						Dict dict = new Dict();
						dict.setLabel(obj.getName());
						dict.setValue(obj.getId());
						List<Dict> dictList = dictMap.get(type);
						dictList.add(dict);		
						dictMap.put(type, dictList);
					}
					
					CacheUtils.put(CACHE_PRO_GRADE_DICT_MAP, dictMap);
	        	}
	        	
	        	List<Dict> dictList = dictMap.get(type);
	    		if (dictList == null){
	    			dictList = Lists.newArrayList();
	    		}
	    		return dictList;
	        }

	        if("proDubbingLanguag".equals(type)){

				@SuppressWarnings("unchecked")
	        	Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_DUBBINGLANGUAG_DICT_MAP);
	        	
	        	if (dictMap==null){
	        		dictMap = Maps.newHashMap();	
	        	   	Dict dictEmpty = new Dict();
					dictEmpty.setLabel("");
					dictEmpty.setValue("");
	        		dictMap.put(type, Lists.newArrayList(dictEmpty));
	        		for (ProDubbingLanguage obj : proDubbingLanguageDao.findAllList(new ProDubbingLanguage())){
						Dict dict = new Dict();
						dict.setLabel(obj.getName());
						dict.setValue(obj.getId());
						List<Dict> dictList = dictMap.get(type);
						dictList.add(dict);
						dictMap.put(type, dictList);
					}
					CacheUtils.put(CACHE_PRO_DUBBINGLANGUAG_DICT_MAP, dictMap);
	        	}
	        	
	        	List<Dict> dictList = dictMap.get(type);
	    		if (dictList == null){
	    			dictList = Lists.newArrayList();
	    		}
	    		return dictList;		

				
	        }
	        

	        
	        if("proAudienceSource".equals(type)){
				
				@SuppressWarnings("unchecked")
	        	Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_AUDIENCESOURCE_DICT_MAP);
	        	
	        	if (dictMap==null){
	        		dictMap = Maps.newHashMap();	
	        	   	Dict dictEmpty = new Dict();
					dictEmpty.setLabel("");
					dictEmpty.setValue("");
	        		dictMap.put(type, Lists.newArrayList(dictEmpty));
					for (ProAudienceSource obj : proAudienceSourceDao.findAllList(new ProAudienceSource())){
						Dict dict = new Dict();
						dict.setLabel(obj.getName());
						dict.setValue(obj.getId());
						List<Dict> dictList = dictMap.get(type);
						dictList.add(dict);	
						dictMap.put(type, dictList);
					}
					CacheUtils.put(CACHE_PRO_AUDIENCESOURCE_DICT_MAP, dictMap);
	        	}
	        	
	        	List<Dict> dictList = dictMap.get(type);
	    		if (dictList == null){
	    			dictList = Lists.newArrayList();
	    		}
	    		return dictList;		
	        }
	        

	        
	        if("proAudienceCity".equals(type)){

				@SuppressWarnings("unchecked")
	        	Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_AUDIENCECITY_DICT_MAP);
	        	
	        	if (dictMap==null){
	        		dictMap = Maps.newHashMap();	
	        	   	Dict dictEmpty = new Dict();
					dictEmpty.setLabel("");
					dictEmpty.setValue("");
	        		dictMap.put(type, Lists.newArrayList(dictEmpty));
					for (ProAudienceCity obj : proAudienceCityDao.findAllList(new ProAudienceCity())){
						Dict dict = new Dict();
						dict.setLabel(obj.getName());
						dict.setValue(obj.getId());
						List<Dict> dictList = dictMap.get(type);
						dictList.add(dict);
						dictMap.put(type, dictList);
					}
					CacheUtils.put(CACHE_PRO_AUDIENCECITY_DICT_MAP, dictMap);
	        	}
	        	
	        	List<Dict> dictList = dictMap.get(type);
	    		if (dictList == null){
	    			dictList = Lists.newArrayList();
	    		}
	    		return dictList;	
	        }
	  
	        
	        
	        if("proAudienceCarrier".equals(type)){

				@SuppressWarnings("unchecked")
	        	Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_AUDIENCECARRIER_DICT_MAP);
	        	
	        	if (dictMap==null){
	        		dictMap = Maps.newHashMap();	
	        	   	Dict dictEmpty = new Dict();
					dictEmpty.setLabel("");
					dictEmpty.setValue("");
	        		dictMap.put(type, Lists.newArrayList(dictEmpty));
					for (ProAudienceCarrier obj : proAudienceCarrierDao.findAllList(new ProAudienceCarrier())){
						Dict dict = new Dict();
						dict.setLabel(obj.getName());
						dict.setValue(obj.getId());
						List<Dict> dictList = dictMap.get(type);
						dictList.add(dict);
						dictMap.put(type, dictList);
					}
					CacheUtils.put(CACHE_PRO_AUDIENCECARRIER_DICT_MAP, dictMap);
	        	}
	        	
	        	List<Dict> dictList = dictMap.get(type);
	    		if (dictList == null){
	    			dictList = Lists.newArrayList();
	    		}
	    		return dictList;	
				
	        }

	        
	        if("proAudienceTarget".equals(type)){
	
				@SuppressWarnings("unchecked")
	        	Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_AUDIENCETARGET_DICT_MAP);
	        	
	        	if (dictMap==null){
	        		dictMap = Maps.newHashMap();	
	        	   	Dict dictEmpty = new Dict();
					dictEmpty.setLabel("");
					dictEmpty.setValue("");
	        		dictMap.put(type, Lists.newArrayList(dictEmpty));
					for (ProAudienceTarget obj : proAudienceTargetDao.findAllList(new ProAudienceTarget())){
						Dict dict = new Dict();
						dict.setLabel(obj.getName());
						dict.setValue(obj.getId());
						List<Dict> dictList = dictMap.get(type);
						dictList.add(dict);	
						dictMap.put(type, dictList);
					}
					CacheUtils.put(CACHE_PRO_AUDIENCETARGET_DICT_MAP, dictMap);
	        	}
	        	
	        	List<Dict> dictList = dictMap.get(type);
	    		if (dictList == null){
	    			dictList = Lists.newArrayList();
	    		}
	    		return dictList;	
				
				
	        }

	        
	        if("proTapeType".equals(type)){

				@SuppressWarnings("unchecked")
	        	Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_PRO_TAPETYPE_DICT_MAP);
	        	
	        	if (dictMap==null){
	        		dictMap = Maps.newHashMap();	
	        	   	Dict dictEmpty = new Dict();
					dictEmpty.setLabel("");
					dictEmpty.setValue("");
	        		dictMap.put(type, Lists.newArrayList(dictEmpty));
	        		for (ProTapeType obj : proTapeTypeDao.findAllList(new ProTapeType())){
						Dict dict = new Dict();
						dict.setLabel(obj.getName());
						dict.setValue(obj.getId());
						List<Dict> dictList = dictMap.get(type);
						dictList.add(dict);	
						dictMap.put(type, dictList);
					}
					CacheUtils.put(CACHE_PRO_TAPETYPE_DICT_MAP, dictMap);
	        	}
	        	
	        	List<Dict> dictList = dictMap.get(type);
	    		if (dictList == null){
	    			dictList = Lists.newArrayList();
	    		}
	    		return dictList;	
	
	        } 
	        
	        
	        return Lists.newArrayList();
			

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
