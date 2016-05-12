/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceSource;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCity;
import com.thinkgem.jeesite.modules.pro.entity.ProAudienceCarrier;
import com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 收视率信息Entity
 * @author lin
 * @version 2016-05-23
 */
public class ProAudienceRatings extends DataEntity<ProAudienceRatings> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 组织
	private ProAudienceSource proAudienceSource;	// 数据来源
	private ProAudienceCity proAudienceCity;		// 地区省会
	private ProAudienceCarrier proAudienceCarrier;	// 收视频道
	private ProBaseInfo proBaseInfo;				// 节目编号
	
	private Date broDate;		// 播出日期
	private Date beginBroDate;		// 开始 播出日期
	private Date endBroDate;		// 结束 播出日期
	private Date broTimeStart;		// 播出时间
	private Date broTimeEnd;		// 播出时间
	private String crownName;		// 冠名
	private Integer version;		// 版本
	
	private List<ProAudienceRatingsDetail> proAudienceRatingsDetailList = Lists.newArrayList();		// 子表列表
	
	public ProAudienceRatings() {
		super();
	}

	public ProAudienceRatings(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonIgnore
	@ExcelField(title="数据来源", align=2, sort=20, type=2, value="proAudienceSource.id")
	public ProAudienceSource getProAudienceSource() {
		return proAudienceSource;
	}

	public void setProAudienceSource(ProAudienceSource proAudienceSource) {
		this.proAudienceSource = proAudienceSource;
	}
	@JsonIgnore
	@ExcelField(title="地区", align=2, sort=30, type=2,value="proAudienceCity.id")
	public ProAudienceCity getProAudienceCity() {
		return proAudienceCity;
	}

	public void setProAudienceCity(ProAudienceCity proAudienceCity) {
		this.proAudienceCity = proAudienceCity;
	}
	
	
	@JsonIgnore
	@ExcelField(title="节目名称", align=2, sort=40,  type=2,value="proBaseInfo.name")
	public ProBaseInfo getProBaseInfo() {
		return proBaseInfo;
	}
	public void setProBaseInfo(ProBaseInfo proBaseInfo) {
		this.proBaseInfo = proBaseInfo;
	}

	
	@JsonIgnore
	@ExcelField(title="频道名称", align=2, sort=50,  type=2,value="proAudienceCarrier.name")
	public ProAudienceCarrier getProAudienceCarrier() {
		return proAudienceCarrier;
	}

	public void setProAudienceCarrier(ProAudienceCarrier proAudienceCarrier) {
		this.proAudienceCarrier = proAudienceCarrier;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonIgnore
	@ExcelField(title="播出日期", align=2, sort=60,  type=2)
	public Date getBroDate() {
		return broDate;
	}

	public void setBroDate(Date broDate) {
		this.broDate = broDate;
	}
	
	@Length(min=0, max=256, message="冠名长度必须介于 0 和 256 之间")
	@JsonIgnore
	@ExcelField(title="冠名", align=2, sort=70,  type=2)
	public String getCrownName() {
		return crownName;
	}

	public void setCrownName(String crownName) {
		this.crownName = crownName;
	}
	
	@JsonFormat(pattern = "HH:mm:ss")
	@JsonIgnore
	@ExcelField(title="开始时间", align=2, sort=80,  type=2)
	public Date getBroTimeStart() {
		return broTimeStart;
	}


	public void setBroTimeStart(Date broTimeStart) {
		this.broTimeStart = broTimeStart;
	}
	
	@JsonFormat(pattern = "HH:mm:ss")
	@JsonIgnore
	@ExcelField(title="结束时间", align=2, sort=90,  type=2)
	public Date getBroTimeEnd() {
		return broTimeEnd;
	}

	public void setBroTimeEnd(Date broTimeEnd) {
		this.broTimeEnd = broTimeEnd;
	}
	

	
	public Date getBeginBroDate() {
		return beginBroDate;
	}

	public void setBeginBroDate(Date beginBroDate) {
		this.beginBroDate = beginBroDate;
	}
	
	public Date getEndBroDate() {
		return endBroDate;
	}

	public void setEndBroDate(Date endBroDate) {
		this.endBroDate = endBroDate;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public List<ProAudienceRatingsDetail> getProAudienceRatingsDetailList() {
		return proAudienceRatingsDetailList;
	}

	public void setProAudienceRatingsDetailList(List<ProAudienceRatingsDetail> proAudienceRatingsDetailList) {
		this.proAudienceRatingsDetailList = proAudienceRatingsDetailList;
	}
}