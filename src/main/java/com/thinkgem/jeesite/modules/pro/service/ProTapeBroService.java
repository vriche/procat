/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pro.entity.ProTapeBro;
import com.thinkgem.jeesite.modules.pro.dao.ProTapeBroDao;

/**
 * 成品带信息Service
 * @author lin
 * @version 2016-05-23
 */
@Service
@Transactional(readOnly = true)
public class ProTapeBroService extends CrudService<ProTapeBroDao, ProTapeBro> {

	public ProTapeBro get(String id) {
		return super.get(id);
	}
	
	public List<ProTapeBro> findList(ProTapeBro proTapeBro) {
		return super.findList(proTapeBro);
	}
	
	public Page<ProTapeBro> findPage(Page<ProTapeBro> page, ProTapeBro proTapeBro) {
		return super.findPage(page, proTapeBro);
	}
	
	@Transactional(readOnly = false)
	public void save(ProTapeBro proTapeBro) {
		super.save(proTapeBro);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProTapeBro proTapeBro) {
		super.delete(proTapeBro);
	}
	
}