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
import com.thinkgem.jeesite.modules.pro.entity.ProVersion;
import com.thinkgem.jeesite.modules.pro.dao.ProVersionDao;
import com.thinkgem.jeesite.modules.pro.entity.ProTapeBro;
import com.thinkgem.jeesite.modules.pro.dao.ProTapeBroDao;

/**
 * 节目版本Service
 * @author lin
 * @version 2016-05-24
 */
@Service
@Transactional(readOnly = true)
public class ProVersionService extends CrudService<ProVersionDao, ProVersion> {

	@Autowired
	private ProTapeBroDao proTapeBroDao;
	
	public ProVersion get(String id) {
		ProVersion proVersion = super.get(id);
		proVersion.setProTapeBroList(proTapeBroDao.findList(new ProTapeBro(proVersion)));
		return proVersion;
	}
	
	public List<ProVersion> findList(ProVersion proVersion) {
		return super.findList(proVersion);
	}
	
	public Page<ProVersion> findPage(Page<ProVersion> page, ProVersion proVersion) {
		return super.findPage(page, proVersion);
	}
	
	@Transactional(readOnly = false)
	public void save(ProVersion proVersion) {
		super.save(proVersion);
		for (ProTapeBro proTapeBro : proVersion.getProTapeBroList()){
			if (proTapeBro.getId() == null){
				continue;
			}
			if (ProTapeBro.DEL_FLAG_NORMAL.equals(proTapeBro.getDelFlag())){
				if (StringUtils.isBlank(proTapeBro.getId())){
					proTapeBro.setProVersion(proVersion);
					proTapeBro.preInsert();
					proTapeBroDao.insert(proTapeBro);
				}else{
					proTapeBro.preUpdate();
					proTapeBroDao.update(proTapeBro);
				}
			}else{
				proTapeBroDao.delete(proTapeBro);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ProVersion proVersion) {
		super.delete(proVersion);
		proTapeBroDao.delete(new ProTapeBro(proVersion));
	}
	
}