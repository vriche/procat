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
public class ProContract extends DataEntity<ProContract> {
	
	private static final long serialVersionUID = 1L;
	private ProBaseInfo proBaseInfo;		// 节目 父类
	private String content;		// 内容
	private String pic;		// 合同图片
	private String signDate;		// 签定日期
	private String signPerson;		// 签定人
	private Integer sort;		// 序号
	private Integer version;		// 版本
	
	public ProContract() {
		super();
	}

	public ProContract(String id){
		super(id);
	}

	public ProContract(ProBaseInfo proBaseInfo){
		this.proBaseInfo = proBaseInfo;
	}

	public ProBaseInfo getProBaseInfo() {
		return proBaseInfo;
	}

	public void setProBaseInfo(ProBaseInfo proBaseInfo) {
		this.proBaseInfo = proBaseInfo;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Length(min=0, max=11, message="签定日期长度必须介于 0 和 11 之间")
	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	
	@Length(min=0, max=20, message="签定人长度必须介于 0 和 20 之间")
	public String getSignPerson() {
		return signPerson;
	}

	public void setSignPerson(String signPerson) {
		this.signPerson = signPerson;
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