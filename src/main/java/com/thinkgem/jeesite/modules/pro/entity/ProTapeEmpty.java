/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.pro.entity.ProTapeType;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 空白磁带Entity
 * @author lin
 * @version 2016-05-18
 */
public class ProTapeEmpty extends DataEntity<ProTapeEmpty> {
	
	private static final long serialVersionUID = 1L;
	private ProTapeType proTapeType;		// 磁带类型
	private String barCode;		// 条形码
	private String setDate;		// 设置日期
	private String startDate;		// 起始日期
	private String curState;		// 当前状态
	private Integer version;		// 版本
	
	public ProTapeEmpty() {
		super();
	}

	public ProTapeEmpty(String id){
		super(id);
	}

	public ProTapeType getProTapeType() {
		return proTapeType;
	}

	public void setProTapeType(ProTapeType proTapeType) {
		this.proTapeType = proTapeType;
	}
	
	@Length(min=0, max=128, message="条形码长度必须介于 0 和 128 之间")
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	@Length(min=0, max=20, message="设置日期长度必须介于 0 和 20 之间")
	public String getSetDate() {
		return setDate;
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}
	
	@Length(min=0, max=20, message="起始日期长度必须介于 0 和 20 之间")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@Length(min=0, max=2, message="当前状态长度必须介于 0 和 2 之间")
	public String getCurState() {
		return curState;
	}

	public void setCurState(String curState) {
		this.curState = curState;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}