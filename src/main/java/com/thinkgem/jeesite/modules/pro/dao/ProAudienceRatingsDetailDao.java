/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatingsDetail;

/**
 * 收视率信息DAO接口
 * @author lin
 * @version 2016-05-23
 */
@MyBatisDao
public interface ProAudienceRatingsDetailDao extends CrudDao<ProAudienceRatingsDetail> {
	
}