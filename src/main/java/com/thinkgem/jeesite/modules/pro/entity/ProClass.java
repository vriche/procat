/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 节目分类Entity
 * @author lin
 * @version 2016-05-18
 */
public class ProClass extends TreeEntity<ProClass> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 织组
	private ProClass parent;		// 父级编号
	private String parentIds;		// 所有父级名称
	private String name;		// 名称
	private Integer treeLevel;		// 当前级别
	private Integer sort;		// 序号
	private Integer version;		// 版本
	
	public ProClass() {
		super();
	}

	public ProClass(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonBackReference
	public ProClass getParent() {
		return parent;
	}

	public void setParent(ProClass parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级名称长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}