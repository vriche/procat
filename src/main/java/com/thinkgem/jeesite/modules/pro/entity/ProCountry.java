/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.modules.sys.entity.Office;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 国别信息Entity
 * @author lin
 * @version 2016-05-29
 */
public class ProCountry extends TreeEntity<ProCountry> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 织组
	private String name;		// 国家名
	private ProCountry parent;		// 父级编号
	private String code;		// 编码
	private String parentIds;		// 所有父级编号
	private String language;		// 语言
	private String currency;		// 货币
	private Integer sort;		// 序号
	private Integer version;		// 版本
	
	public ProCountry() {
		super();
	}

	public ProCountry(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@NotNull(message="国家名不能为空")
	@Length(min=0, max=256, message="国家名长度必须介于 0 和 256 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public ProCountry getParent() {
		return parent;
	}

	public void setParent(ProCountry parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=20, message="编码长度必须介于 0 和 20 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=128, message="语言长度必须介于 0 和 128 之间")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Length(min=0, max=128, message="货币长度必须介于 0 和 128 之间")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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