/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.my_test.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * aaEntity
 * @author aa
 * @version 2016-05-11
 */
public class ProClass extends DataEntity<ProClass> {
	
	private static final long serialVersionUID = 1L;
	private Long sysOrgId;		// 织组
	private String name;		// 名称
	private ProClass parent;		// 父级
	private String parentIds;		// parent_ids
	private Integer treeLevel;		// 当前级别
	private Integer sort;		// 序号
	private String version;		// version
	
	public ProClass() {
		super();
	}

	public ProClass(String id){
		super(id);
	}

	public Long getSysOrgId() {
		return sysOrgId;
	}

	public void setSysOrgId(Long sysOrgId) {
		this.sysOrgId = sysOrgId;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public ProClass getParent() {
		return parent;
	}

	public void setParent(ProClass parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="parent_ids长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=11, message="version长度必须介于 0 和 11 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}