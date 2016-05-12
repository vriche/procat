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
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceSourceDao;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceSource;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;

/**
 * 收视率数据源Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProAudienceSourceService extends CrudService<ProAudienceSourceDao, ProAudienceSource> {

	public ProAudienceSource get(String id) {
		return super.get(id);
	}
	
	public List<ProAudienceSource> findList(ProAudienceSource proAudienceSource) {
		return super.findList(proAudienceSource);
	}
	
	public Page<ProAudienceSource> findPage(Page<ProAudienceSource> page, ProAudienceSource proAudienceSource) {
		return super.findPage(page, proAudienceSource);
	}
	
	@Transactional(readOnly = false)
	public void save(ProAudienceSource proAudienceSource) {
		super.save(proAudienceSource);
	    CacheUtils.remove(DictUtils.CACHE_PRO_AUDIENCESOURCE_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProAudienceSource proAudienceSource) {
		super.delete(proAudienceSource);
	    CacheUtils.remove(DictUtils.CACHE_PRO_AUDIENCESOURCE_DICT_MAP);
	}
	
}