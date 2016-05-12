/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pro.entity.ProContract;
import com.thinkgem.jeesite.modules.pro.dao.ProContractDao;

/**
 * 节目合同Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProContractService extends CrudService<ProContractDao, ProContract> {

	public ProContract get(String id) {
		return super.get(id);
	}
	
	public List<ProContract> findList(ProContract proContract) {
		return super.findList(proContract);
	}
	
	public Page<ProContract> findPage(Page<ProContract> page, ProContract proContract) {
		return super.findPage(page, proContract);
	}
	
	@Transactional(readOnly = false)
	public void save(ProContract proContract) {
		super.save(proContract);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProContract proContract) {
		super.delete(proContract);
	}
	
}