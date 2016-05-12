/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 剧情看点Entity
 * @author lin
 * @version 2016-05-18
 */
public class ProPlotAspect extends TreeEntity<ProPlotAspect> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 织组
	private ProPlotAspect parent;		// 父级编号
	private String parentIds;		// 所有父级名称
	private String name;		// 名称
	private Integer sort;		// 号序
	private Integer version;		// 版本
	
	public ProPlotAspect() {
		super();
	}

	public ProPlotAspect(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonBackReference
	public ProPlotAspect getParent() {
		return parent;
	}

	public void setParent(ProPlotAspect parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级名称长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=256, message="名称长度必须介于 0 和 256 之间")
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
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}