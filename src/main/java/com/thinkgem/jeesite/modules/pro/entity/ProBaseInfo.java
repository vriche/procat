/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.pro.entity.ProClass;
import com.thinkgem.jeesite.modules.pro.entity.ProPlotAspect;
import com.thinkgem.jeesite.modules.pro.entity.ProDubbingLanguage;
import com.thinkgem.jeesite.modules.pro.entity.ProCountry;
import com.thinkgem.jeesite.modules.pro.entity.ProGrade;
import com.thinkgem.jeesite.modules.pro.entity.ProIssueYear;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 节目基本信息Entity
 * @author lin
 * @version 2016-05-18
 */
public class ProBaseInfo extends DataEntity<ProBaseInfo> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 织组
	private String name;		// 名称
	private String nameEn;		// 英文片名
	private String nameAlias;		// 别名
	private String code;		// 编码
	private String helpCode;		// 助记码
	private ProClass proClass;		// 节目类型
	private ProPlotAspect proPlotAspect;		// 剧情看点
	private ProDubbingLanguage proDubbingLanguag;		// 配音语言
	private ProCountry proCountry;		// 国家
	private ProGrade proGrade;		// 等级
	private String director;		// 导演
	private ProIssueYear proIssueYear;		// 出品年份
	private String performer;		// 演员
	private Integer sort;		// 号序
	private Integer totalEpisode;		// 总集数
	private String isPreview;		// 预告片
	private String isLimit;		// 禁片
	private Integer version;		// 版本
	private String relation;		// 相关文章
	
	private List<ProContract> proContractList = Lists.newArrayList();		// 子表列表
	private List<ProCopyright> proCopyrightList = Lists.newArrayList();		// 子表列表
	private List<ProSummary> proSummaryList = Lists.newArrayList();		// 子表列表
	private List<ProVersion> proVersionList = Lists.newArrayList();		// 子表列表
	
	
	
	


	public ProBaseInfo() {
		super();
	}

	public ProBaseInfo(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@NotNull(message="节目名称不能为空")
	@Length(min=0, max=256, message="名称长度必须介于 0 和 256 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="英文片名长度必须介于 0 和 256 之间")
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	
	@Length(min=0, max=256, message="别名长度必须介于 0 和 256 之间")
	public String getNameAlias() {
		return nameAlias;
	}

	public void setNameAlias(String nameAlias) {
		this.nameAlias = nameAlias;
	}
	
	@Length(min=0, max=128, message="编码长度必须介于 0 和 128 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=128, message="助记码长度必须介于 0 和 128 之间")
	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}
	
	@NotNull(message="节目类型不能为空")
	public ProClass getProClass() {
		return proClass;
	}

	public void setProClass(ProClass proClass) {
		this.proClass = proClass;
	}
	
//	@NotNull(message="节目看点不能为空")
	public ProPlotAspect getProPlotAspect() {
		return proPlotAspect;
	}

	public void setProPlotAspect(ProPlotAspect proPlotAspect) {
		this.proPlotAspect = proPlotAspect;
	}
	
//	@NotNull(message="配音语言不能为空")
	public ProDubbingLanguage getProDubbingLanguag() {
		return proDubbingLanguag;
	}

	public void setProDubbingLanguag(ProDubbingLanguage proDubbingLanguag) {
		this.proDubbingLanguag = proDubbingLanguag;
	}
	

	@NotNull(message="国家名称不能为空")
	public ProCountry getProCountry() {
		return proCountry;
	}

	public void setProCountry(ProCountry proCountry) {
		this.proCountry = proCountry;
	}
	
	@NotNull(message="节目等级不能为空")
	public ProGrade getProGrade() {
		return proGrade;
	}

	public void setProGrade(ProGrade proGrade) {
		this.proGrade = proGrade;
	}
	
	@Length(min=0, max=256, message="导演长度必须介于 0 和 256 之间")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
//	@NotNull(message="出品年份不能为空")
	public ProIssueYear getProIssueYear() {
		return proIssueYear;
	}

	
	public void setProIssueYear(ProIssueYear proIssueYear) {
		this.proIssueYear = proIssueYear;
	}
	
	@Length(min=0, max=256, message="演员长度必须介于 0 和 256 之间")
	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getTotalEpisode() {
		return totalEpisode;
	}

	public void setTotalEpisode(Integer totalEpisode) {
		this.totalEpisode = totalEpisode;
	}
	
	@Length(min=0, max=2, message="预告片长度必须介于 0 和 2 之间")
	public String getIsPreview() {
		return isPreview;
	}

	public void setIsPreview(String isPreview) {
		this.isPreview = isPreview;
	}
	
	@Length(min=0, max=2, message="预告片长度必须介于 0 和 2 之间")
	public String getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(String isLimit) {
		this.isLimit = isLimit;
	}
	
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	
	
	public List<ProContract> getProContractList() {
		return proContractList;
	}

	public void setProContractList(List<ProContract> proContractList) {
		this.proContractList = proContractList;
	}
	public List<ProCopyright> getProCopyrightList() {
		return proCopyrightList;
	}

	public void setProCopyrightList(List<ProCopyright> proCopyrightList) {
		this.proCopyrightList = proCopyrightList;
	}
	public List<ProSummary> getProSummaryList() {
		return proSummaryList;
	}

	public void setProSummaryList(List<ProSummary> proSummaryList) {
		this.proSummaryList = proSummaryList;
	}
	public List<ProVersion> getProVersionList() {
		return proVersionList;
	}

	public void setProVersionList(List<ProVersion> proVersionList) {
		this.proVersionList = proVersionList;
	}
}