/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProPlotAspect;
import com.thinkgem.jeesite.modules.pro.dao.ProPlotAspectDao;

/**
 * 剧情看点Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProPlotAspectService extends TreeService<ProPlotAspectDao, ProPlotAspect> {

	public ProPlotAspect get(String id) {
		return super.get(id);
	}
	
	public List<ProPlotAspect> findList(ProPlotAspect proPlotAspect) {
		if (StringUtils.isNotBlank(proPlotAspect.getParentIds())){
			proPlotAspect.setParentIds(","+proPlotAspect.getParentIds()+",");
		}
		return super.findList(proPlotAspect);
	}
	
	@Transactional(readOnly = false)
	public void save(ProPlotAspect proPlotAspect) {
		super.save(proPlotAspect);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProPlotAspect proPlotAspect) {
		super.delete(proPlotAspect);
	}
	
}