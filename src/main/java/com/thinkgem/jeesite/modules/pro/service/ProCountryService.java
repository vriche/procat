/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProCountry;
import com.thinkgem.jeesite.modules.pro.dao.ProCountryDao;

/**
 * 国别信息Service
 * @author lin
 * @version 2016-05-29
 */
@Service
@Transactional(readOnly = true)
public class ProCountryService extends TreeService<ProCountryDao, ProCountry> {

	public ProCountry get(String id) {
		return super.get(id);
	}
	
	public List<ProCountry> findList(ProCountry proCountry) {
		if (StringUtils.isNotBlank(proCountry.getParentIds())){
			proCountry.setParentIds(","+proCountry.getParentIds()+",");
		}
		return super.findList(proCountry);
	}
	
	@Transactional(readOnly = false)
	public void save(ProCountry proCountry) {
		super.save(proCountry);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProCountry proCountry) {
		super.delete(proCountry);
	}
	
}