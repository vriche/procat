/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pro.entity.ProTapeEmpty;
import com.thinkgem.jeesite.modules.pro.dao.ProTapeEmptyDao;

/**
 * 空白磁带Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProTapeEmptyService extends CrudService<ProTapeEmptyDao, ProTapeEmpty> {

	public ProTapeEmpty get(String id) {
		return super.get(id);
	}
	
	public List<ProTapeEmpty> findList(ProTapeEmpty proTapeEmpty) {
		return super.findList(proTapeEmpty);
	}
	
	public Page<ProTapeEmpty> findPage(Page<ProTapeEmpty> page, ProTapeEmpty proTapeEmpty) {
		return super.findPage(page, proTapeEmpty);
	}
	
	@Transactional(readOnly = false)
	public void save(ProTapeEmpty proTapeEmpty) {
		super.save(proTapeEmpty);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProTapeEmpty proTapeEmpty) {
		super.delete(proTapeEmpty);
	}
	
}