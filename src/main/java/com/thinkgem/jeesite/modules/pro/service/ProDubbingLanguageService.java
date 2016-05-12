/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.pro.dao.ProDubbingLanguageDao;
import com.thinkgem.jeesite.modules.pro.entity.ProDubbingLanguage;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;

/**
 * 配音语言Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProDubbingLanguageService extends CrudService<ProDubbingLanguageDao, ProDubbingLanguage> {

	public ProDubbingLanguage get(String id) {
		return super.get(id);
	}
	
	public List<ProDubbingLanguage> findList(ProDubbingLanguage proDubbingLanguage) {
		return super.findList(proDubbingLanguage);
	}
	
	public Page<ProDubbingLanguage> findPage(Page<ProDubbingLanguage> page, ProDubbingLanguage proDubbingLanguage) {
		return super.findPage(page, proDubbingLanguage);
	}
	
	@Transactional(readOnly = false)
	public void save(ProDubbingLanguage proDubbingLanguage) {
		super.save(proDubbingLanguage);
		 CacheUtils.remove(DictUtils.CACHE_PRO_DUBBINGLANGUAG_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProDubbingLanguage proDubbingLanguage) {
		super.delete(proDubbingLanguage);
		 CacheUtils.remove(DictUtils.CACHE_PRO_DUBBINGLANGUAG_DICT_MAP);
	}
	
}