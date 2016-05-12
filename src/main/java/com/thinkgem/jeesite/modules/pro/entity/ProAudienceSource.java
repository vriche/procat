/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收视率数据源Entity
 * @author lin
 * @version 2016-05-18
 */
public class ProAudienceSource extends DataEntity<ProAudienceSource> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 组织
	private String name;		// 名称
	private String code;		// 编码
	private Integer sort;		// 序号
	private Integer version;		// 版本
	
	public ProAudienceSource() {
		super();
	}

	public ProAudienceSource(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=256, message="名称长度必须介于 0 和 256 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=128, message="编码长度必须介于 0 和 128 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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