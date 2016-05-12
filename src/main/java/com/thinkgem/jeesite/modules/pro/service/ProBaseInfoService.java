/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.pro.dao.ProBaseInfoDao;
import com.thinkgem.jeesite.modules.pro.dao.ProContractDao;
import com.thinkgem.jeesite.modules.pro.dao.ProCopyrightDao;
import com.thinkgem.jeesite.modules.pro.dao.ProSummaryDao;
import com.thinkgem.jeesite.modules.pro.dao.ProTapeBroDao;
import com.thinkgem.jeesite.modules.pro.dao.ProVersionDao;
import com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo;
import com.thinkgem.jeesite.modules.pro.entity.ProContract;
import com.thinkgem.jeesite.modules.pro.entity.ProCopyright;
import com.thinkgem.jeesite.modules.pro.entity.ProSummary;
import com.thinkgem.jeesite.modules.pro.entity.ProTapeBro;
import com.thinkgem.jeesite.modules.pro.entity.ProVersion;
import com.thinkgem.jeesite.modules.pro.utils.DictUtils;
import com.thinkgem.jeesite.modules.pro.utils.GetFirstLetter;

/**
 * 节目基本信息Service
 * @author lin
 * @version 2016-05-18
 */
@Service
@Transactional(readOnly = true)
public class ProBaseInfoService extends CrudService<ProBaseInfoDao, ProBaseInfo> {

	@Autowired
	private ProContractDao proContractDao;
	@Autowired
	private ProCopyrightDao proCopyrightDao;
	@Autowired
	private ProSummaryDao proSummaryDao;
	@Autowired
	private ProVersionDao proVersionDao;
	@Autowired
	private ProTapeBroDao proTapeBroDao;
	
	public ProBaseInfo get(String id) {
		ProBaseInfo proBaseInfo = super.get(id);
//		System.out.println(">>>>>>>>>>>>>>>>>>>>6666666666666666666 77>>>>> id >>>>>>>"+ id);
		proBaseInfo.setProContractList(proContractDao.findList(new ProContract(proBaseInfo)));
		proBaseInfo.setProCopyrightList(proCopyrightDao.findList(new ProCopyright(proBaseInfo)));
		proBaseInfo.setProSummaryList(proSummaryDao.findList(new ProSummary(proBaseInfo)));
		proBaseInfo.setProVersionList(proVersionDao.findList(new ProVersion(proBaseInfo)));
		for(ProVersion version:proBaseInfo.getProVersionList()){
			version.setProTapeBroList(proTapeBroDao.findList(new ProTapeBro(version)));
			System.out.println(">>>>>>>>>>>>>>>>>>>>6666666666666666666 999999999>>>>>>>>>>>>"+version.getProTapeBroList().size());
		}
		
		return proBaseInfo;
	}
	
	public List<ProBaseInfo> findList(ProBaseInfo proBaseInfo) {
		return super.findList(proBaseInfo);
	}
	
	public Page<ProBaseInfo> findPage(Page<ProBaseInfo> page, ProBaseInfo proBaseInfo) {
		return super.findPage(page, proBaseInfo);
	}
	
	public Page<ProBaseInfo> findPageByIds(Page<ProBaseInfo> page, ProBaseInfo proBaseInfo, String ids) {
		if(ids == null){
			return new Page<ProBaseInfo>();
		}
		List<ProBaseInfo> list = Lists.newArrayList();
		String[] idss = StringUtils.split(ids,",");
		ProBaseInfo e = null;
		for(int i=0;(idss.length-i)>0;i++){
//			System.out.println("idss[i]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+idss[i]);
			e = dao.get(idss[i]);
			list.add(e);
		}
		proBaseInfo.setPage(page);
		page.setList(list);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(ProBaseInfo proBaseInfo) {
		//处理助记码
		String name = proBaseInfo.getName();
		String helpCode = proBaseInfo.getHelpCode();

		if(StringUtils.isEmpty(helpCode)){
			helpCode = GetFirstLetter.getPinYinHeadChar(name);
			proBaseInfo.setHelpCode(helpCode); 
		}

		super.save(proBaseInfo);
		
		
		 
		for (ProContract proContract : proBaseInfo.getProContractList()){
			if (proContract.getId() == null){
				continue;
			}
			if (ProContract.DEL_FLAG_NORMAL.equals(proContract.getDelFlag())){
				if (StringUtils.isBlank(proContract.getId())){
					proContract.setProBaseInfo(proBaseInfo);
					proContract.preInsert();
					proContractDao.insert(proContract);
				}else{
					proContract.preUpdate();
					proContractDao.update(proContract);
				}
			}else{
				proContractDao.delete(proContract);
			}
		}
		for (ProCopyright proCopyright : proBaseInfo.getProCopyrightList()){
			if (proCopyright.getId() == null){
				continue;
			}
			if (ProCopyright.DEL_FLAG_NORMAL.equals(proCopyright.getDelFlag())){
				if (StringUtils.isBlank(proCopyright.getId())){
					proCopyright.setProBaseInfo(proBaseInfo);
					proCopyright.preInsert();
					proCopyrightDao.insert(proCopyright);
				}else{
					proCopyright.preUpdate();
					proCopyrightDao.update(proCopyright);
				}
			}else{
				proCopyrightDao.delete(proCopyright);
			}
		}
		for (ProSummary proSummary : proBaseInfo.getProSummaryList()){
			if (proSummary.getId() == null){
				continue;
			}
			if (ProSummary.DEL_FLAG_NORMAL.equals(proSummary.getDelFlag())){
				if (StringUtils.isBlank(proSummary.getId())){
					proSummary.setProBaseInfo(proBaseInfo);
					proSummary.preInsert();
					proSummaryDao.insert(proSummary);
				}else{
					proSummary.preUpdate();
					proSummaryDao.update(proSummary);
				}
			}else{
				proSummaryDao.delete(proSummary);
			}
		}
		for (ProVersion proVersion : proBaseInfo.getProVersionList()){
			if (proVersion.getId() == null){
				continue;
			}
			if (ProVersion.DEL_FLAG_NORMAL.equals(proVersion.getDelFlag())){
				if (StringUtils.isBlank(proVersion.getId())){
					proVersion.setProBaseInfo(proBaseInfo);
					proVersion.preInsert();
					proVersionDao.insert(proVersion);
				}else{
					proVersion.preUpdate();
					proVersionDao.update(proVersion);
				}
			}else{
				proVersionDao.delete(proVersion);
			}
		}
		
		
		CacheUtils.remove(DictUtils.CACHE_PRO_BASEINFO_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProBaseInfo proBaseInfo) {
		super.delete(proBaseInfo);
		proContractDao.delete(new ProContract(proBaseInfo));
		proCopyrightDao.delete(new ProCopyright(proBaseInfo));
		proSummaryDao.delete(new ProSummary(proBaseInfo));
		proVersionDao.delete(new ProVersion(proBaseInfo));
		
		CacheUtils.remove(DictUtils.CACHE_PRO_BASEINFO_DICT_MAP);
	}
	
}