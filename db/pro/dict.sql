
update pro_tape_type set create_date='2013-10-28 08:00:00',update_date='2013-10-28 08:00:00';

update sys_dict set del_flag=0;

delete from sys_dict where id>1000;

INSERT INTO `sys_dict` VALUES ('1002', 'com.thinkgem.jeesite.modules.pro.entity.ProAudienceCarrier', 'ProAudienceCarrier', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1003', 'com.thinkgem.jeesite.modules.pro.entity.ProAudienceCity', 'ProAudienceCity', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1004', 'com.thinkgem.jeesite.modules.pro.entity.ProAudienceSource', 'ProAudienceSource', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1005', 'com.thinkgem.jeesite.modules.pro.entity.ProAudienceTarget', 'ProAudienceTarget', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1006', 'com.thinkgem.jeesite.modules.pro.entity.ProClass', 'ProClass', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1007', 'com.thinkgem.jeesite.modules.pro.entity.ProCountry', 'ProCountry', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1008', 'com.thinkgem.jeesite.modules.pro.entity.ProDubbingLanguage', 'ProDubbingLanguage', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1009', 'com.thinkgem.jeesite.modules.pro.entity.ProGrade', 'ProGrade', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1010', 'com.thinkgem.jeesite.modules.pro.entity.ProPlotAspect', 'ProPlotAspect', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1011', 'com.thinkgem.jeesite.modules.pro.entity.ProTapeType', 'ProTapeType', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1012', 'com.thinkgem.jeesite.modules.pro.entity.ProBaseInfo', 'ProBaseInfo', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1013', 'com.thinkgem.jeesite.modules.pro.entity.ProAudienceRatings', 'ProAudienceRatings', 'gen_java_type', 'Java类型\0\0', 1000, '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', NULL, '0');


	private List<ProTapeBro> ProTapeBroList = Lists.newArrayList();		// 子表列表

	public List<ProTapeBro> getProTapeBroList() {
		return ProTapeBroList;
	}

	public void setProTapeBroList(List<ProTapeBro> proTapeBroList) {
		ProTapeBroList = proTapeBroList;
	}
	
		public ProTapeBro(ProVersion proVersion) {
		this.proVersion = proVersion;
	}