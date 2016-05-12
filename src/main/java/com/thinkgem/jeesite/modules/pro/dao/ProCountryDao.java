/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pro.entity.ProCountry;

/**
 * 国别信息DAO接口
 * @author lin
 * @version 2016-05-29
 */
@MyBatisDao
public interface ProCountryDao extends TreeDao<ProCountry> {
	
}