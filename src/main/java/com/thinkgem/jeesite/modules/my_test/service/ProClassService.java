/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.my_test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.my_test.entity.ProClass;
import com.thinkgem.jeesite.modules.my_test.dao.ProClassDao;

/**
 * aaService
 * @author aa
 * @version 2016-05-11
 */
@Service
@Transactional(readOnly = true)
public class ProClassService extends CrudService<ProClassDao, ProClass> {

	public ProClass get(String id) {
		return super.get(id);
	}
	
	public List<ProClass> findList(ProClass proClass) {
		return super.findList(proClass);
	}
	
	public Page<ProClass> findPage(Page<ProClass> page, ProClass proClass) {
		return super.findPage(page, proClass);
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