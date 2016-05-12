/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 版权信息Entity
 * @author lin
 * @version 2016-05-23
 */
public class ProCopyright extends DataEntity<ProCopyright> {
	
	private static final long serialVersionUID = 1L;
	private ProBaseInfo proBaseInfo;		// 节目
	private String issueCompany;		// 发行单位
	
	private Date purchaseTime;		// 购片时间
	private Date beginPurchaseTime;		// 开始 购片时间
	private Date endPurchaseTime;		// 结束 购片时间

	private Date issueDate;		// 发行日期
	private Date beginIssueDate;		// 开始 发行日期
	private Date endIssueDate;		// 结束 发行日期
	
	private Date copyrightStartDate;		// 版权开始
	private Date copyrightEndDate;		// 版权结束
	
	private String broState;		// 播出状态
	private Integer sort;		// 序号
	private Integer version;		// 版本


	
	public ProCopyright() {
		super();
	}
	public ProCopyright(ProBaseInfo proBaseInfo){
		this.proBaseInfo = proBaseInfo;
	}

	public ProCopyright(String id){
		super(id);
	}

	public ProBaseInfo getProBaseInfo() {
		return proBaseInfo;
	}

	public void setProBaseInfo(ProBaseInfo proBaseInfo) {
		this.proBaseInfo = proBaseInfo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	@Length(min=0, max=256, message="发行单位长度必须介于 0 和 256 之间")
	public String getIssueCompany() {
		return issueCompany;
	}

	public void setIssueCompany(String issueCompany) {
		this.issueCompany = issueCompany;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getCopyrightStartDate() {
		return copyrightStartDate;
	}

	public void setCopyrightStartDate(Date copyrightStartDate) {
		this.copyrightStartDate = copyrightStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getCopyrightEndDate() {
		return copyrightEndDate;
	}

	public void setCopyrightEndDate(Date copyrightEndDate) {
		this.copyrightEndDate = copyrightEndDate;
	}
	
	@Length(min=0, max=2, message="播出状态长度必须介于 0 和 2 之间")
	public String getBroState() {
		return broState;
	}

	public void setBroState(String broState) {
		this.broState = broState;
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
	
	public Date getBeginPurchaseTime() {
		return beginPurchaseTime;
	}

	public void setBeginPurchaseTime(Date beginPurchaseTime) {
		this.beginPurchaseTime = beginPurchaseTime;
	}
	
	public Date getEndPurchaseTime() {
		return endPurchaseTime;
	}

	public void setEndPurchaseTime(Date endPurchaseTime) {
		this.endPurchaseTime = endPurchaseTime;
	}
		
	public Date getBeginIssueDate() {
		return beginIssueDate;
	}

	public void setBeginIssueDate(Date beginIssueDate) {
		this.beginIssueDate = beginIssueDate;
	}
	
	public Date getEndIssueDate() {
		return endIssueDate;
	}

	public void setEndIssueDate(Date endIssueDate) {
		this.endIssueDate = endIssueDate;
	}
		
}