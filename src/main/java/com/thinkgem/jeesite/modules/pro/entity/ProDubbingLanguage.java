/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 配音语言Entity
 * @author lin
 * @version 2016-05-18
 */
public class ProDubbingLanguage extends DataEntity<ProDubbingLanguage> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 织组
	private String name;		// 称名
	private Integer sort;		// 号序
	private Integer version;		// 版本
	
	public ProDubbingLanguage() {
		super();
	}

	public ProDubbingLanguage(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=256, message="称名长度必须介于 0 和 256 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}