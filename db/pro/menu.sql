-- prompt 46 records loaded
-- insert menu

--update pro_audience_carrier set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_audience_city set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_audience_source set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_audience_target set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_base_info set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_class set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_copyright set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_country set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_dubbing_language set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_grade set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_plot_aspect set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_summary set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_tape_bro set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_tape_empty set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';
--update pro_version set create_date='2013-05-27 08:00:00',update_date='2013-05-27 08:00:00';

--alter table pro_audience_carrier comment '节目_收视频道';
--alter table pro_audience_city comment '节目_收视区域信息表';
--alter table pro_audience_ratings comment '节目_收视率信息表';
--alter table pro_audience_ratings_detail comment '节目_收视率明细信息表';
--alter table pro_audience_source comment '节目_收视率数据来源';
--alter table pro_audience_target comment '节目_收视目标';
--alter table pro_base_info comment '节目_节目基本信息表';
--alter table pro_class comment '节目_节目分类信息表';
--alter table pro_contract comment '节目_节目合同信息表';
--alter table pro_copyright comment '节目_版权信息表';
-- alter table pro_country comment '节目_国别信息表';
-- alter table pro_dubbing_language comment '节目_配音语言信息表';
-- alter table pro_grade comment '节目_节目等级标准信息表';
-- alter table pro_plot_aspect comment '节目_剧情看点信息表';
-- alter table pro_summary comment '节目_节目简介信息表';
-- alter table pro_tape_bro comment '节目_播出带信息表';
-- alter table pro_tape_empty comment '节目_空白磁带表';
-- alter table pro_tape_type comment '节目_磁带类型信息表';
-- alter table pro_version comment '节目_节目版本信息表';


DELETE FROM `sys_menu` WHERE id>=500 and id<9999;


INSERT INTO `sys_menu` VALUES ('500', '1', '0,1,', '影片信息', '1000', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('550', '500', '0,1,500,', '节目管理', '970', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('601', '550', '0,1,500,550,', '节目入库', '970', '/pro/proBaseInfo', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('603', '601', '0,1,500,550,601,', '查看', '30', null, null, null, '0', 'pro:proBaseInfo:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('604', '601', '0,1,500,550,601,', '修改', '40', null, null, null, '0', 'pro:proBaseInfo:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('621', '550', '0,1,500,550,', '节目版权', '970', '/pro/proCopyright', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('623', '621', '0,1,500,550,621,', '查看', '30', null, null, null, '0', 'pro:proCopyright:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('624', '621', '0,1,500,550,621,', '修改', '40', null, null, null, '0', 'pro:proCopyright:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('631', '550', '0,1,500,550,', '节目合同', '970', '/pro/proContract', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('633', '631', '0,1,500,550,631,', '查看', '30', null, null, null, '0', 'pro:proContract:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('634', '631', '0,1,500,550,631,', '修改', '40', null, null, null, '0', 'pro:proContract:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('701', '550', '0,1,500,550,', '节目收视', '970', '/pro/proAudienceRatings', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('702', '701', '0,1,500,550,701,', '查看', '30', null, null, null, '0', 'pro:proAudienceRatings:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('703', '701', '0,1,500,550,701,', '修改', '40', null, null, null, '0', 'pro:proAudienceRatings:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('801', '550', '0,1,500,550,', '收视率查询', '970', '/pro/proAudienceRatingsDetail', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('802', '801', '0,1,2,500,550,801,', '查看', '30', null, null, null, '0', 'pro:proAudienceRatingsDetail:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('803', '801', '0,1,2,500,550,801,', '修改', '40', null, null, null, '0', 'pro:proAudienceRatingsDetail:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('901', '550', '0,1,500,550,', '节目版本', '970', '/pro/proVersion', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('902', '901', '0,1,2,500,550,901,', '查看', '30', null, null, null, '0', 'pro:proVersion:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('903', '901', '0,1,2,500,550,901,', '修改', '40', null, null, null, '0', 'pro:proVersion:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');



INSERT INTO `sys_menu` VALUES ('1001', '500', '0,1,500,', '磁带管理', '970', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('1002', '1001', '0,1,500,1001,', '成品带', '970', '/pro/proTapeBro', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('1003', '1002', '0,1,500,1001,1002,', '查看', '30', null, null, null, '0', 'pro:proTapeBro:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('1004', '1003', '0,1,500,1001,1002,', '修改', '40', null, null, null, '0', 'pro:proTapeBro:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('1005', '1001', '0,1,500,1001,', '空白带', '970', '/pro/proTapeEmpty', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('1006', '1005', '0,1,500,1001,1005,', '查看', '30', null, null, null, '0', 'pro:proTapeEmpty:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('1007', '1005', '0,1,500,1001,1005,', '修改', '40', null, null, null, '0', 'pro:proTapeEmpty:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('2000', '500', '0,1,500,', '基础数据', '970', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('5001', '2000', '0,1,500,2000,', '收视数据源', '103', '/pro/proAudienceSource', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('5002', '5001', '0,1,2,500,2000,5001,', '查看', '30', null, null, null, '0', 'pro:proAudienceSource:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('5003', '5001', '0,1,2,500,2000,5001,', '修改', '40', null, null, null, '0', 'pro:proAudienceSource:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('3001', '2000', '0,1,500,2000,', '收视地区', '104', '/pro/proAudienceCity', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('3002', '3001', '0,1,2,500,2000,3001,', '查看', '30', null, null, null, '0', 'pro:proAudienceCity:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('3003', '2002', '0,1,2,500,2000,3001,', '修改', '40', null, null, null, '0', 'pro:proAudienceCity:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('2001', '2000', '0,1,500,2000,', '收视频道', '105', '/pro/proAudienceCarrier', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('2002', '2001', '0,1,2,500,2000,2001,', '查看', '30', null, null, null, '0', 'pro:proAudienceCarrier:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('2003', '2001', '0,1,2,500,2000,2001,', '修改', '40', null, null, null, '0', 'pro:proAudienceCarrier:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('6001', '2000', '0,1,500,2000,', '收视目标', '106', '/pro/proAudienceTarget', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('6002', '6001', '0,1,2,500,2000,6001,', '查看', '30', null, null, null, '0', 'pro:proAudienceTarget:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('6003', '6001', '0,1,2,500,2000,6001,', '修改', '40', null, null, null, '0', 'pro:proAudienceTarget:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('4001', '2000', '0,1,500,2000,', '收视率信息', '107', '/pro/proAudienceRatings', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('4002', '4001', '0,1,2,500,2000,4001,', '查看', '30', null, null, null, '0', 'pro:proAudienceRatings:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('4003', '4001', '0,1,2,500,2000,4001,', '修改', '40', null, null, null, '0', 'pro:proAudienceRatings:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');




INSERT INTO `sys_menu` VALUES ('7001', '2000', '0,1,500,2000,', '节目分类', '11', '/pro/proClass', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('7002', '7001', '0,1,2,500,2000,7001,', '查看', '30', null, null, null, '0', 'pro:proClass:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('7003', '7001', '0,1,2,500,2000,7001,', '修改', '40', null, null, null, '0', 'pro:proClass:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('8001', '2000', '0,1,500,2000,', '节目国别', '22', '/pro/proCountry', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('8002', '8001', '0,1,2,500,2000,8001,', '查看', '30', null, null, null, '0', 'pro:proCountry:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('8003', '8001', '0,1,2,500,2000,8001,', '修改', '40', null, null, null, '0', 'pro:proCountry:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('9001', '2000', '0,1,500,2000,', '节目配音语言', '33', '/pro/proDubbingLanguage', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9002', '9001', '0,1,2,500,2000,9001,', '查看', '30', null, null, null, '0', 'pro:proDubbingLanguage:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9003', '9001', '0,1,2,500,2000,9001,', '修改', '40', null, null, null, '0', 'pro:proDubbingLanguage:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('9101', '2000', '0,1,500,2000,', '节目等级标准', '44', '/pro/proGrade', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9102', '9101', '0,1,2,500,2000,9101,', '查看', '30', null, null, null, '0', 'pro:proGrade:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9103', '9101', '0,1,2,500,2000,9101,', '修改', '40', null, null, null, '0', 'pro:proGrade:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('9201', '2000', '0,1,500,2000,', '节目剧情看点', '55', '/pro/proPlotAspect', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9202', '9201', '0,1,2,500,2000,9201,', '查看', '30', null, null, null, '0', 'pro:proPlotAspect:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9203', '9201', '0,1,2,500,2000,9201,', '修改', '40', null, null, null, '0', 'pro:proPlotAspect:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

INSERT INTO `sys_menu` VALUES ('9301', '2000', '0,1,500,2000,', '节目出品年份', '66', '/pro/proIssueYear', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9302', '9301', '0,1,2,500,2000,9301,', '查看', '30', null, null, null, '0', 'pro:proIssueYear:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9303', '9301', '0,1,2,500,2000,9301,', '修改', '40', null, null, null, '0', 'pro:proIssueYear:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


INSERT INTO `sys_menu` VALUES ('9401', '2000', '0,1,500,2000,', '磁带类型', '66', '/pro/proTapeType', null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9402', '9401', '0,1,2,500,2000,9401,', '查看', '30', null, null, null, '0', 'pro:proTapeType:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('9403', '9401', '0,1,2,500,2000,9401,', '修改', '40', null, null, null, '0', 'pro:proTapeType:edit', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');


commit;
