/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 收视率信息Entity
 * @author lin
 * @version 2016-05-23
 */
public class ProAudienceRatingsDetail extends DataEntity<ProAudienceRatingsDetail> {
	
	private static final long serialVersionUID = 1L;
	private ProAudienceRatings proAudienceRatings;		// 收视主表 父类
	private ProAudienceTarget proAudienceTarget;	// 收视目标
	private String audienceRate;			// 收视率%
	private String beginAudienceRate;		// 开始 收视率
	private String endBAudienceRate;		// 结束 收视率
	private String marketRate;		// 市场份额%
	private Integer version;		// 版本
	

	public ProAudienceRatingsDetail() {
		super();
	}

	public ProAudienceRatingsDetail(String id){
		super(id);
	}
	
	

	public ProAudienceRatingsDetail(ProAudienceRatings proAudienceRatings){
		this.proAudienceRatings = proAudienceRatings;
	}
	
	public ProAudienceRatings getProAudienceRatings() {
		return proAudienceRatings;
	}

	public void setProAudienceRatings(ProAudienceRatings proAudienceRatings) {
		this.proAudienceRatings = proAudienceRatings;
	}
	
	public String getBeginAudienceRate() {
		return beginAudienceRate;
	}

	public void setBeginAudienceRate(String beginAudienceRate) {
		this.beginAudienceRate = beginAudienceRate;
	}

	public String getEndBAudienceRate() {
		return endBAudienceRate;
	}

	public void setEndBAudienceRate(String endBAudienceRate) {
		this.endBAudienceRate = endBAudienceRate;
	}
	

	
	public ProAudienceTarget getProAudienceTarget() {
		return proAudienceTarget;
	}

	public void setProAudienceTarget(ProAudienceTarget proAudienceTarget) {
		this.proAudienceTarget = proAudienceTarget;
	}
	

	
	public String getAudienceRate() {
		return audienceRate;
	}

	public void setAudienceRate(String audienceRate) {
		this.audienceRate = audienceRate;
	}
	
	public String getMarketRate() {
		return marketRate;
	}

	public void setMarketRate(String marketRate) {
		this.marketRate = marketRate;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
//	@JsonIgnore
//	public List<ProAudienceTarget> getProAudienceTargets() {
//		return proAudienceTargets;
//	}
//
//	public void setProAudienceTargets(List<ProAudienceTarget> proAudienceTargets) {
//		this.proAudienceTargets = proAudienceTargets;
//	}
		
}