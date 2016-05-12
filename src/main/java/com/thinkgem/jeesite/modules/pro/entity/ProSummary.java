/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 节目基本信息Entity
 * @author lin
 * @version 2016-05-18
 */
public class ProSummary extends DataEntity<ProSummary> {
	
	private static final long serialVersionUID = 1L;
	private ProBaseInfo proBaseInfo;		// 目节 父类
	private String content;		// 简介内容
	private String poster;		// 海报图片
	private Integer sort;		// 序号
	private Integer version;		// 版本
	
	public ProSummary() {
		super();
	}

	public ProSummary(String id){
		super(id);
	}

	public ProSummary(ProBaseInfo proBaseInfo){
		this.proBaseInfo = proBaseInfo;
	}

	public ProBaseInfo getProBaseInfo() {
		return proBaseInfo;
	}

	public void setProBaseInfo(ProBaseInfo proBaseInfo) {
		this.proBaseInfo = proBaseInfo;
	}
	
	@Length(min=0, max=2000, message="简介内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
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