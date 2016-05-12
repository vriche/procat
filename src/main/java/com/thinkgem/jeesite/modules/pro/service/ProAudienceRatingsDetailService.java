/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatingsDetail;
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceRatingsDetailDao;

/**
 * 收视明细Service
 * @author lin
 * @version 2016-05-23
 */
@Service
@Transactional(readOnly = true)
public class ProAudienceRatingsDetailService extends CrudService<ProAudienceRatingsDetailDao, ProAudienceRatingsDetail> {

	public ProAudienceRatingsDetail get(String id) {
		return super.get(id);
	}
	
	public List<ProAudienceRatingsDetail> findList(ProAudienceRatingsDetail proAudienceRatingsDetail) {
		return super.findList(proAudienceRatingsDetail);
	}
	
	public Page<ProAudienceRatingsDetail> findPage(Page<ProAudienceRatingsDetail> page, ProAudienceRatingsDetail proAudienceRatingsDetail) {
		return super.findPage(page, proAudienceRatingsDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(ProAudienceRatingsDetail proAudienceRatingsDetail) {
		super.save(proAudienceRatingsDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProAudienceRatingsDetail proAudienceRatingsDetail) {
		super.delete(proAudienceRatingsDetail);
	}
	
}