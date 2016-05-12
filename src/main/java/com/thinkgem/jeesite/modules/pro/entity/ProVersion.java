/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo;

import org.hibernate.validator.constraints.Length;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 节目版本Entity
 * @author lin
 * @version 2016-05-24
 */
public class ProVersion extends DataEntity<ProVersion> {
	
	private static final long serialVersionUID = 1L;
	private ProBaseInfo proBaseInfo;		// 节目基本信息
	private String name;		// 版本称名
	private Integer sort;		// 号序
	private Integer version;		// 版本
	private List<ProTapeBro> proTapeBroList = Lists.newArrayList();		// 子表列表
	
	
	
	
	
	public ProVersion() {
		super();
	}
	
	public ProVersion(ProBaseInfo proBaseInfo) {
		this.proBaseInfo = proBaseInfo;
	}

	public ProVersion(String id){
		super(id);
	}

	public ProBaseInfo getProBaseInfo() {
		return proBaseInfo;
	}

	public void setProBaseInfo(ProBaseInfo proBaseInfo) {
		this.proBaseInfo = proBaseInfo;
	}
	
	@NotNull(message="版本名称不能为空")
	@Length(min=0, max=32, message="版本名称长度必须介于 0 和 32 之间")
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
	
	public List<ProTapeBro> getProTapeBroList() {
		return proTapeBroList;
	}

	public void setProTapeBroList(List<ProTapeBro> proTapeBroList) {
		this.proTapeBroList = proTapeBroList;
	}
}