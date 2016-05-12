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
import com.thinkgem.jeesite.modules.pro.entity.ProGrade;
import com.thinkgem.jeesite.modules.pro.dao.ProGradeDao;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;

/**
 * 节目等级Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProGradeService extends CrudService<ProGradeDao, ProGrade> {

	public ProGrade get(String id) {
		return super.get(id);
	}
	
	public List<ProGrade> findList(ProGrade proGrade) {
		return super.findList(proGrade);
	}
	
	public Page<ProGrade> findPage(Page<ProGrade> page, ProGrade proGrade) {
		return super.findPage(page, proGrade);
	}
	
	@Transactional(readOnly = false)
	public void save(ProGrade proGrade) {
		super.save(proGrade);
		CacheUtils.remove(DictUtils.CACHE_PRO_GRADE_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProGrade proGrade) {
		super.delete(proGrade);
		CacheUtils.remove(DictUtils.CACHE_PRO_GRADE_DICT_MAP);
	}
	
}