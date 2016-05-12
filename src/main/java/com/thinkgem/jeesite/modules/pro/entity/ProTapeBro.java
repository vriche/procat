/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pro.entity;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pro.utils.CustomTimeDeserializer;
import com.thinkgem.jeesite.modules.pro.utils.CustomTimeSerializer;

/**
 * 节目版本Entity
 * @author lin
 * @version 2016-05-24
 */
public class ProTapeBro extends DataEntity<ProTapeBro> {
	
	private static final long serialVersionUID = 1L;
	private ProVersion proVersion;		// 节目版本 父类
	private Integer curEpisode;		// 当前集数
	private String barCode;		// 条形码
	private String tapeCode;		// 磁带号
	private String sound;		// 声道
	private String definition;		// 清晰度
	private String completeTotalTime;		// 总时长
	private String completeFristTcCode;		// TC首码
	private String completeFristTcInfo;		// 首画信息
	private String completeLastTcCode;		// TC尾码
	private String completeLastTcInfo;		// 尾画信息
	private String simpleTotalTime;		// 简版总时长
	private String simpleFristTcCode;		// 简版首码
	private String simpleFristTcInfo;		// 简版首码画信息
	private String simpleLastTcCode;		// 简版尾码
	private String simpleLastTcInfo;		// 简版尾码画信息
	private Integer version;		// 版本
	
	public ProTapeBro() {
		super();
	}

	public ProTapeBro(String id){
		super(id);
	}

	public ProTapeBro(ProVersion proVersion){
		this.proVersion = proVersion;
	}

	public ProVersion getProVersion() {
		return proVersion;
	}

	public void setProVersion(ProVersion proVersion) {
		this.proVersion = proVersion;
	}
	
	public Integer getCurEpisode() {
		return curEpisode;
	}

	public void setCurEpisode(Integer curEpisode) {
		this.curEpisode = curEpisode;
	}
	
	@Length(min=0, max=128, message="条形码长度必须介于 0 和 128 之间")
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	@Length(min=0, max=128, message="磁带号长度必须介于 0 和 128 之间")
	public String getTapeCode() {
		return tapeCode;
	}

	public void setTapeCode(String tapeCode) {
		this.tapeCode = tapeCode;
	}
	
	@Length(min=0, max=128, message="声道长度必须介于 0 和 128 之间")
	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}
	
	@Length(min=0, max=128, message="清晰度长度必须介于 0 和 128 之间")
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	@Length(min=0, max=20, message="总时长长度必须介于 0 和 20 之间")
//	@JsonFormat(pattern = "00:00:00:00")
	@JsonSerialize(using=CustomTimeSerializer.class)
	public String getCompleteTotalTime() {
		return completeTotalTime;
	}
	

//	@JsonDeserialize(using = CustomTimeDeserializer.class)
	public void setCompleteTotalTime(String completeTotalTime) {
		completeTotalTime = settTtimeConvert(completeTotalTime);
		this.completeTotalTime = completeTotalTime;
	}
	
	@Length(min=0, max=20, message="TC首码长度必须介于 0 和 20 之间")
	@JsonSerialize(using=CustomTimeSerializer.class)
	public String getCompleteFristTcCode() {
		return completeFristTcCode;
	}

	public void setCompleteFristTcCode(String completeFristTcCode) {
		completeFristTcCode = settTtimeConvert(completeFristTcCode);
		this.completeFristTcCode = completeFristTcCode;
	}
	
	@Length(min=0, max=20, message="首画信息长度必须介于 0 和 20 之间")
	public String getCompleteFristTcInfo() {
		return completeFristTcInfo;
	}

	public void setCompleteFristTcInfo(String completeFristTcInfo) {
		this.completeFristTcInfo = completeFristTcInfo;
	}
	
	@Length(min=0, max=20, message="TC尾码长度必须介于 0 和 20 之间")
	@JsonSerialize(using=CustomTimeSerializer.class)
	public String getCompleteLastTcCode() {
		return completeLastTcCode;
	}

	public void setCompleteLastTcCode(String completeLastTcCode) {
		completeLastTcCode = settTtimeConvert(completeLastTcCode);
		this.completeLastTcCode = completeLastTcCode;
	}
	
	@Length(min=0, max=20, message="尾画信息长度必须介于 0 和 20 之间")
	public String getCompleteLastTcInfo() {
		return completeLastTcInfo;
	}

	public void setCompleteLastTcInfo(String completeLastTcInfo) {
		this.completeLastTcInfo = completeLastTcInfo;
	}
	
	@Length(min=0, max=20, message="简版总时长长度必须介于 0 和 20 之间")
	@JsonSerialize(using=CustomTimeSerializer.class)
	public String getSimpleTotalTime() {
		return simpleTotalTime;
	}

	public void setSimpleTotalTime(String simpleTotalTime) {
		simpleTotalTime = settTtimeConvert(simpleTotalTime);
		this.simpleTotalTime = simpleTotalTime;
	}
	
	@Length(min=0, max=20, message="简版首码长度必须介于 0 和 20 之间")
	@JsonSerialize(using=CustomTimeSerializer.class)
	public String getSimpleFristTcCode() {
		return simpleFristTcCode;
	}

	public void setSimpleFristTcCode(String simpleFristTcCode) {
		simpleFristTcCode = settTtimeConvert(simpleFristTcCode);
		this.simpleFristTcCode = simpleFristTcCode;
	}
	
	@Length(min=0, max=20, message="简版首码画信息长度必须介于 0 和 20 之间")
	public String getSimpleFristTcInfo() {
		return simpleFristTcInfo;
	}

	public void setSimpleFristTcInfo(String simpleFristTcInfo) {
		this.simpleFristTcInfo = simpleFristTcInfo;
	}
	
	@Length(min=0, max=20, message="简版尾码长度必须介于 0 和 20 之间")
	@JsonSerialize(using=CustomTimeSerializer.class)
	public String getSimpleLastTcCode() {
		return simpleLastTcCode;
	}

	public void setSimpleLastTcCode(String simpleLastTcCode) {
		simpleLastTcCode = settTtimeConvert(simpleLastTcCode);
		this.simpleLastTcCode = simpleLastTcCode;
	}
	
	@Length(min=0, max=20, message="简版尾码画信息长度必须介于 0 和 20 之间")
	public String getSimpleLastTcInfo() {
		return simpleLastTcInfo;
	}

	public void setSimpleLastTcInfo(String simpleLastTcInfo) {
		this.simpleLastTcInfo = simpleLastTcInfo;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	//转换前 "00:00:00:00" 转换后 "00000000"
	private String settTtimeConvert(String s){
		String[] array = s.split(":");
		s = StringUtils.join(array, "");
		return s;
	}
	
}