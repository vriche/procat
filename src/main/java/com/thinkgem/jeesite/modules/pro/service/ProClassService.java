/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProClass;
import com.thinkgem.jeesite.modules.pro.dao.ProClassDao;

/**
 * 节目分类Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProClassService extends TreeService<ProClassDao, ProClass> {

	public ProClass get(String id) {
		return super.get(id);
	}
	
	public List<ProClass> findList(ProClass proClass) {
		if (StringUtils.isNotBlank(proClass.getParentIds())){
			proClass.setParentIds(","+proClass.getParentIds()+",");
		}
		return super.findList(proClass);
	}
	
	@Transactional(readOnly = false)
	public void save(ProClass proClass) {
		super.save(proClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProClass proClass) {
		super.delete(proClass);
	}
	
}