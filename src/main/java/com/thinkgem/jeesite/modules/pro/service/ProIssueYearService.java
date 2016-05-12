/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProIssueYear;
import com.thinkgem.jeesite.modules.pro.dao.ProIssueYearDao;

/**
 * 出品年份Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProIssueYearService extends TreeService<ProIssueYearDao, ProIssueYear> {

	public ProIssueYear get(String id) {
		return super.get(id);
	}
	
	public List<ProIssueYear> findList(ProIssueYear proIssueYear) {
		if (StringUtils.isNotBlank(proIssueYear.getParentIds())){
			proIssueYear.setParentIds(","+proIssueYear.getParentIds()+",");
		}
		return super.findList(proIssueYear);
	}
	
	@Transactional(readOnly = false)
	public void save(ProIssueYear proIssueYear) {
		super.save(proIssueYear);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProIssueYear proIssueYear) {
		super.delete(proIssueYear);
	}
	
}