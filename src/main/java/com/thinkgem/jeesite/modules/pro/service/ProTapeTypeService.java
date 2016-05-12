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
import com.thinkgem.jeesite.modules.pro.dao.ProTapeTypeDao;
import com.thinkgem.jeesite.modules.pro.entity.ProTapeType;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;

/**
 * 磁带类型Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProTapeTypeService extends CrudService<ProTapeTypeDao, ProTapeType> {

	public ProTapeType get(String id) {
		return super.get(id);
	}
	
	public List<ProTapeType> findList(ProTapeType proTapeType) {
		return super.findList(proTapeType);
	}
	
	public Page<ProTapeType> findPage(Page<ProTapeType> page, ProTapeType proTapeType) {
		return super.findPage(page, proTapeType);
	}
	
	@Transactional(readOnly = false)
	public void save(ProTapeType proTapeType) {
		super.save(proTapeType);
		CacheUtils.remove(DictUtils.CACHE_PRO_TAPETYPE_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProTapeType proTapeType) {
		super.delete(proTapeType);
		CacheUtils.remove(DictUtils.CACHE_PRO_TAPETYPE_DICT_MAP);
	}
	
}