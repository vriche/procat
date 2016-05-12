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
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceCarrierDao;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCarrier;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;

/**
 * 成品带信息Service
 * @author lin
 * @version 2016-05-19
 */
@Service
@Transactional(readOnly = true)
public class ProAudienceCarrierService extends CrudService<ProAudienceCarrierDao, ProAudienceCarrier> {

	public ProAudienceCarrier get(String id) {
		return super.get(id);
	}
	
	public List<ProAudienceCarrier> findList(ProAudienceCarrier proAudienceCarrier) {
		return super.findList(proAudienceCarrier);
	}
	
	public Page<ProAudienceCarrier> findPage(Page<ProAudienceCarrier> page, ProAudienceCarrier proAudienceCarrier) {
		return super.findPage(page, proAudienceCarrier);
	}
	
	@Transactional(readOnly = false)
	public void save(ProAudienceCarrier proAudienceCarrier) {
		super.save(proAudienceCarrier);
		 CacheUtils.remove(DictUtils.CACHE_PRO_AUDIENCECARRIER_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProAudienceCarrier proAudienceCarrier) {
		super.delete(proAudienceCarrier);
		 CacheUtils.remove(DictUtils.CACHE_PRO_AUDIENCECARRIER_DICT_MAP);
	}
	
}