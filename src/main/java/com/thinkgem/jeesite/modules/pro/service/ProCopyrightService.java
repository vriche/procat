/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pro.entity.ProCopyright;
import com.thinkgem.jeesite.modules.pro.dao.ProCopyrightDao;

/**
 * 版权信息Service
 * @author lin
 * @version 2016-05-23
 */
@Service
@Transactional(readOnly = true)
public class ProCopyrightService extends CrudService<ProCopyrightDao, ProCopyright> {

	public ProCopyright get(String id) {
		return super.get(id);
	}
	
	public List<ProCopyright> findList(ProCopyright proCopyright) {
		return super.findList(proCopyright);
	}
	
	public Page<ProCopyright> findPage(Page<ProCopyright> page, ProCopyright proCopyright) {
		return super.findPage(page, proCopyright);
	}
	
	@Transactional(readOnly = false)
	public void save(ProCopyright proCopyright) {
		super.save(proCopyright);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProCopyright proCopyright) {
		super.delete(proCopyright);
	}
	
}