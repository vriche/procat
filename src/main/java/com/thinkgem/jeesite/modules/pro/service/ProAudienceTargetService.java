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
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceTargetDao;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;

/**
 * 收视目标Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProAudienceTargetService extends CrudService<ProAudienceTargetDao, ProAudienceTarget> {

	public ProAudienceTarget get(String id) {
		return super.get(id);
	}
	
	public List<ProAudienceTarget> findList(ProAudienceTarget proAudienceTarget) {
		return super.findList(proAudienceTarget);
	}
	
	public Page<ProAudienceTarget> findPage(Page<ProAudienceTarget> page, ProAudienceTarget proAudienceTarget) {
		return super.findPage(page, proAudienceTarget);
	}
	
	@Transactional(readOnly = false)
	public void save(ProAudienceTarget proAudienceTarget) {
		super.save(proAudienceTarget);
		 CacheUtils.remove(DictUtils.CACHE_PRO_AUDIENCETARGET_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProAudienceTarget proAudienceTarget) {
		super.delete(proAudienceTarget);
		 CacheUtils.remove(DictUtils.CACHE_PRO_AUDIENCETARGET_DICT_MAP);
	}
	
}