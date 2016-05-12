/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.my_test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.my_test.entity.TestTree;

/**
 * aaDAO接口
 * @author aa
 * @version 2016-05-11
 */
@MyBatisDao
public interface TestTreeDao extends CrudDao<TestTree> {
	
}