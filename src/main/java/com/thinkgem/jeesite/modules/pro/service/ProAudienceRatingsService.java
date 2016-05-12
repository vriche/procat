/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatings;
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceRatingsDao;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatingsDetail;
import com.thinkgem.jeesite.modules.pro.dao.ProAudienceRatingsDetailDao;

/**
 * 收视率信息Service
 * @author lin
 * @version 2016-05-23
 */
@Service
@Transactional(readOnly = true)
public class ProAudienceRatingsService extends CrudService<ProAudienceRatingsDao, ProAudienceRatings> {

	@Autowired
	private ProAudienceRatingsDetailDao proAudienceRatingsDetailDao;
	
	public ProAudienceRatings get(String id) {
		ProAudienceRatings proAudienceRatings = super.get(id);
		proAudienceRatings.setProAudienceRatingsDetailList(proAudienceRatingsDetailDao.findList(new ProAudienceRatingsDetail(proAudienceRatings)));
		return proAudienceRatings;
	}
	
	public List<ProAudienceRatings> findList(ProAudienceRatings proAudienceRatings) {
		return super.findList(proAudienceRatings);
	}
	
	public Page<ProAudienceRatings> findPage(Page<ProAudienceRatings> page, ProAudienceRatings proAudienceRatings) {
		return super.findPage(page, proAudienceRatings);
	}
	
	@Transactional(readOnly = false)
	public void save(ProAudienceRatings proAudienceRatings) {
		
		System.out.print("save ProAudienceRatings 99999999999999999999999  bbbbbbbbbbbbbbb>>>>>>"+ proAudienceRatings.getBroTimeStart() +"\n");
		
		super.save(proAudienceRatings);
		for (ProAudienceRatingsDetail proAudienceRatingsDetail : proAudienceRatings.getProAudienceRatingsDetailList()){
			if (proAudienceRatingsDetail.getId() == null){
				continue;
			}
			if (ProAudienceRatingsDetail.DEL_FLAG_NORMAL.equals(proAudienceRatingsDetail.getDelFlag())){
				if (StringUtils.isBlank(proAudienceRatingsDetail.getId())){
					proAudienceRatingsDetail.setProAudienceRatings(proAudienceRatings);
					proAudienceRatingsDetail.preInsert();
					proAudienceRatingsDetailDao.insert(proAudienceRatingsDetail);
				}else{
					proAudienceRatingsDetail.preUpdate();
					proAudienceRatingsDetailDao.update(proAudienceRatingsDetail);
				}
			}else{
				proAudienceRatingsDetailDao.delete(proAudienceRatingsDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ProAudienceRatings proAudienceRatings) {
		super.delete(proAudienceRatings);
		proAudienceRatingsDetailDao.delete(new ProAudienceRatingsDetail(proAudienceRatings));
	}
	
}