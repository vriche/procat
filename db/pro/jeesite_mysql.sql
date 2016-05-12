SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS pro_audience_carrier;
DROP TABLE IF EXISTS pro_audience_city;
DROP TABLE IF EXISTS pro_audience_ratings;
DROP TABLE IF EXISTS pro_audience_ratings_detail;
DROP TABLE IF EXISTS pro_audience_source;
DROP TABLE IF EXISTS pro_audience_target;
DROP TABLE IF EXISTS pro_base_info;
DROP TABLE IF EXISTS pro_class;
DROP TABLE IF EXISTS pro_contract;
DROP TABLE IF EXISTS pro_copyright;
DROP TABLE IF EXISTS pro_country;
DROP TABLE IF EXISTS pro_dubbing_language;
DROP TABLE IF EXISTS pro_grade;
DROP TABLE IF EXISTS pro_plot_aspect;
DROP TABLE IF EXISTS pro_summary;
DROP TABLE IF EXISTS pro_tape_bro;
DROP TABLE IF EXISTS pro_tape_empty;
DROP TABLE IF EXISTS pro_tape_type;
DROP TABLE IF EXISTS pro_version;




/* Create Tables */

CREATE TABLE pro_audience_carrier
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '组织',
	name varchar(256) COMMENT '名称',
	code varchar(128) COMMENT '编码',
	sort decimal COMMENT '序号',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '收视频道';



CREATE TABLE pro_audience_city
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '组织',
	name varchar(100) COMMENT '名称',
	code varchar(20) COMMENT '码编',
	sort decimal COMMENT '序号',
	parent_id bigint(64) COMMENT '父级编号',
	parent_ids varchar(2000) COMMENT '所有父级编号',
	tree_level int COMMENT '级别',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '省会城市信息表';



CREATE TABLE pro_audience_ratings
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '组织',
	pro_audience_source_id bigint(64) COMMENT '数据来源',
	pro_audience_city_id bigint(64) COMMENT '地区省会',
	pro_audience_carrier_id bigint(64) COMMENT '收视频道',
	pro_base_info_id bigint(64) COMMENT '节目编码',
	crown_name varchar(256) COMMENT '冠名',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '收视率信息表';





CREATE TABLE pro_audience_ratings_detail
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	pro_audience_target_id bigint(64) COMMENT '收视目标',
	bro_date int COMMENT '播出日期',
	bro_time varchar(256) COMMENT '播出时间',
	audience_rate double DEFAULT 0 COMMENT '收视率%',
	market_rate double DEFAULT 0 COMMENT '市场份额%',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '收视率明细信息表';





CREATE TABLE pro_audience_source
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '组织',
	name varchar(256) COMMENT '名称',
	code varchar(128) COMMENT '编码',
	sort decimal COMMENT '序号',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '收视率数据来源';





CREATE TABLE pro_audience_target
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '组织',
	name varchar(256) COMMENT '名称',
	code varchar(128) COMMENT '编码',
	sort decimal COMMENT '号序',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '收视目标';





CREATE TABLE pro_base_info
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '织组',
	name_ch varchar(256) COMMENT '中文片名',
	name_en varchar(256) COMMENT '英文片名',
	name_alias varchar(256) COMMENT '别名',
	code varchar(128) COMMENT '编码',
	help_code varchar(128) COMMENT '助记码',
	pro_class_id bigint(64) COMMENT '节目类型',
	pro_plot_aspect_id bigint(64) COMMENT '剧情看点',
	pro_dubbing_language_id bigint(64) COMMENT '配音语言',
	pro_country_id bigint(64) COMMENT '国家',
	pro_grade_id bigint(64) COMMENT '等级',
	director varchar(256) COMMENT '导演',
	performer varchar(256) COMMENT '演员',
	display_no int COMMENT '号序',
	issue_year int COMMENT '出品年份',
	total_episode int COMMENT '总集数',
	is_preview char(2) COMMENT '预告片',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '节目基本信息表';





CREATE TABLE pro_class
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '织组',
	name varchar(100) COMMENT '名称',
	parent_id bigint(64) COMMENT '父级编号',
	parent_ids varchar(2000) COMMENT '所有父级编号',
	tree_level int COMMENT '当前级别',
	sort decimal COMMENT '序号',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '节目分类信息表';




CREATE TABLE pro_contract
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '织组',
	pro_base_info_id bigint(64) COMMENT '节目',
	content varchar(2000) COMMENT '内容',
	pic mediumblob COMMENT '合同图片',
	sign_date int COMMENT '签定日期',
	sign_person varchar(20) COMMENT '签定人',
	sort decimal COMMENT 'sort',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '节目合同信息表';





CREATE TABLE pro_copyright
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	pro_base_info_id bigint(64) COMMENT '节目',
	purchase_time int COMMENT '购片时间',
	issue_company varchar(256) COMMENT '发行单位',
	issue_date int COMMENT '发行日期',
	copyright_start_date int COMMENT '版权期限（开始日期）',
	copyright_end_date int COMMENT '版权期限（结束日期）',
	bro_state char(2) COMMENT '播出状态（可播、不可播）',
	sort decimal COMMENT '序号',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '版权信息表';





CREATE TABLE pro_country
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '织组',
	name varchar(256) COMMENT '国家名',
	code varchar(20) COMMENT '编码',
	language varchar(128) COMMENT '语言',
	currency varchar(128) COMMENT '货币',
	sort decimal COMMENT '序号',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '国别信息表';





CREATE TABLE pro_dubbing_language
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '织组',
	name varchar(256) COMMENT '称名(华语、外语)',
	sort decimal COMMENT '号序',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '配音语言信息表';





CREATE TABLE pro_grade
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '组织',
	name varchar(256) COMMENT '称名',
	sort decimal COMMENT '号序',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '节目等级标准信息表';





CREATE TABLE pro_plot_aspect
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '织组',
	name varchar(256) COMMENT '名称',
	sort decimal COMMENT '号序',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '剧情看点信息表';





CREATE TABLE pro_summary
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	pro_base_info_id bigint(64) COMMENT '目节',
	content varchar(2000) COMMENT '简介内容',
	poster mediumblob COMMENT '海报图片',
	sort decimal COMMENT '序号',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '节目简介信息表';





CREATE TABLE pro_tape_bro
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	pro_version_id bigint(64) COMMENT '节目版本',
	cur_episode int COMMENT '当前集数',
	bar_code varchar(128) COMMENT '条形码',
	tape_code varchar(128) COMMENT '磁带号',
	sound varchar(128) COMMENT '声道',
	definition varchar(128) COMMENT '清晰度',
	c_total_time varchar(20) COMMENT '总时长',
	c_frist_tc_code varchar(20) COMMENT 'TC首码',
	c_frist_tc_info varchar(20) COMMENT '首画信息',
	c_last_tc_code varchar(20) COMMENT 'TC尾码',
	c_last_tc_info varchar(20) COMMENT '尾画信息',
	s_total_time varchar(20) COMMENT '简版总时长',
	s_frist_tc_code varchar(20) COMMENT '简版首码',
	s_frist_tc_info varchar(20) COMMENT '简版首码画信息',
	s_last_tc_code varchar(20) COMMENT '简版尾码',
	s_last_tc_info varchar(20) COMMENT '简版尾码画信息',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '播出带信息表';





CREATE TABLE pro_tape_empty
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	pro_tape_type_id bigint(64) COMMENT '磁带类型',
	bar_code varchar(128) COMMENT '条形码',
	set_date varchar(20) COMMENT '设置日期',
	start_date varchar(20) COMMENT '起始日期',
	cur_state char(2) COMMENT '当前状态',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '空白磁带表';





CREATE TABLE pro_tape_type
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	sys_org_id bigint(64) COMMENT '织组',
	name varchar(256) COMMENT '名称',
	sort decimal COMMENT '号序',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '磁带类型信息表';





CREATE TABLE pro_version
(
	id bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
	pro_base_info_id bigint(64) COMMENT '节目基本信息',
	name varchar(32) COMMENT '版本称名',
	sort decimal DEFAULT 0 COMMENT '号序',
	version int DEFAULT 0 COMMENT '版本',
	create_by bigint(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by bigint(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(512) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '节目版本信息表';


--alter table pro_audience_carrier comment '节目_收视频道';
--alter table pro_audience_city comment '节目_省会城市信息表';
--alter table pro_audience_ratings comment '节目_收视率信息表';
--alter table pro_audience_ratings_detail comment '节目_收视率明细信息表';
--alter table pro_audience_source comment '节目_收视率数据来源';
--alter table pro_audience_target comment '节目_收视目标';
--alter table pro_base_info comment '节目_节目基本信息表';
--alter table pro_class comment '节目_节目分类信息表';
--alter table pro_contract comment '节目_节目合同信息表';
--alter table pro_copyright comment '节目_版权信息表';
--alter table pro_country comment '节目_国别信息表';
--alter table pro_dubbing_language comment '节目_配音语言信息表';
--alter table pro_grade comment '节目_节目等级标准信息表';
--alter table pro_plot_aspect comment '节目_剧情看点信息表';
--alter table pro_summary comment '节目_节目简介信息表';
--alter table pro_tape_bro comment '节目_播出带信息表';
--alter table pro_tape_empty comment '节目_空白磁带表';
--alter table pro_tape_type comment '节目_磁带类型信息表';
--alter table pro_version comment '节目_节目版本信息表';





