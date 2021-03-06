/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pro.entity.ProTapeType;

/**
 * 磁带类型DAO接口
 * @author lin
 * @version 2016-05-18
 */
@MyBatisDao
public interface ProTapeTypeDao extends CrudDao<ProTapeType> {
	
}