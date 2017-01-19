/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-11-04 14:24:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '银行系统编码',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `bank_code` varchar(255) DEFAULT NULL COMMENT '银行编码',
  `bank_type` int(11) DEFAULT NULL COMMENT '银行类型\n1 - 总行\n2 - 分行\n3 - 支行',
  `bank_address` varchar(1000) DEFAULT NULL COMMENT '银行地址',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `status` int(11) DEFAULT NULL COMMENT '状态\n0 - 正常\n1 - 作废',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `notes` varchar(1000) DEFAULT NULL COMMENT '备注',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank
-- ----------------------------
INSERT INTO `bank` VALUES ('1', '中国银行北京分行', 'BOCBJ', '2', '北京北京', '王总', '010-41209333', null, '0', null, '34', '2016-09-20 14:35:25', '34', '2016-09-20 14:35:25');
INSERT INTO `bank` VALUES ('2', '招商银行上海分行', 'CMBSH', '2', '上海上海', '小王', '021-63726612', null, '0', null, '34', '2016-09-20 14:35:08', '34', '2016-09-20 14:35:08');

-- ----------------------------
-- Table structure for bank_account
-- ----------------------------
DROP TABLE IF EXISTS `bank_account`;
CREATE TABLE `bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `bank_id` bigint(20) DEFAULT NULL COMMENT '银行系统编码',
  `account_name` varchar(255) DEFAULT NULL COMMENT '开户人',
  `phone` varchar(255) DEFAULT NULL COMMENT '开户人电话',
  `account_type` varchar(50) DEFAULT NULL COMMENT '账号类型 (数据字典)\n1 - 基本账号\n2 - 一般账号\n3 - 临时账号\n4 - 专用账号',
  `account` varchar(255) DEFAULT NULL COMMENT '银行账号',
  `account_alias` varchar(255) DEFAULT NULL COMMENT '账号别名',
  `notes` varchar(1000) DEFAULT NULL COMMENT '备注',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `status` int(11) DEFAULT NULL COMMENT '状态\n0 - 正常\n1 - 作废',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `company_id` bigint(20) DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_account
-- ----------------------------
INSERT INTO `bank_account` VALUES ('1', '1', '老董', '13981832883', '1', '4392881828838484', null, null, '0', null, '34', '2016-09-20 14:36:35', null, null, '1');
INSERT INTO `bank_account` VALUES ('2', '2', '熊先生', '13877474711', '1', '62250193848378444', null, null, '0', null, '34', '2016-09-20 14:36:59', null, null, '1');

-- ----------------------------
-- Table structure for code_rule
-- ----------------------------
DROP TABLE IF EXISTS `code_rule`;
CREATE TABLE `code_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `type` varchar(50) DEFAULT NULL COMMENT '类型 (数据字典)\n1 - 融资租赁合同\n2 - 产品买卖合同',
  `expression` varchar(1000) DEFAULT NULL COMMENT '表达式',
  `serial` bigint(20) DEFAULT NULL COMMENT '流水号当前值',
  `serial_length` int(11) DEFAULT NULL COMMENT '流水号长度',
  `reset_type` int(11) DEFAULT NULL COMMENT '1 - 每年重置\n2 - 每年不重置',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态\n0 - 使用中\n1 - 已作废',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_rule
-- ----------------------------
INSERT INTO `code_rule` VALUES ('1', '1', 'CZR{年份}{月份}{客户类型}{流水号}', '92910', '5', '1', '0', null, null, null, null, null);
INSERT INTO `code_rule` VALUES ('2', '2', 'RZZL{业务类型}{客户类型}{年份}{月份}{流水号}', '77', '5', '2', '0', null, null, null, null, null);
INSERT INTO `code_rule` VALUES ('3', '3', '{融资租赁编号}-M', '1', '1', '1', '0', null, null, null, null, null);
INSERT INTO `code_rule` VALUES ('4', '4', '{承租人编码}-ZX', '1', '1', '1', '0', null, null, null, null, null);
INSERT INTO `code_rule` VALUES ('5', '5', 'ZJ{年份}{月份}{流水号}', '45', '5', '1', '0', null, null, null, null, null);
INSERT INTO `code_rule` VALUES ('6', '6', 'ZJFJ{融资租赁编号}{项目类型}{省份简称}{项目编号}{经销商代码}{客户类型}{业务类型}', '888', '5', '1', '0', null, null, null, null, null);
INSERT INTO `code_rule` VALUES ('7', '7', 'HXZF{年份}{月份}{流水号}', '26', '3', '2', '0', '21', '2016-09-06 18:43:10', '21', '2016-09-06 18:43:10', null);
INSERT INTO `code_rule` VALUES ('8', '8', 'CHDH{年份}{月份}{流水号}', '22', '3', '2', '0', null, null, null, null, null);
INSERT INTO `code_rule` VALUES ('9', '9', 'YQBM{年份}{月份}{流水号}', '1', '1', '1', '0', '21', '2016-09-19 15:28:12', '21', '2016-09-19 15:28:12', null);
INSERT INTO `code_rule` VALUES ('10', '6', 'FJD{年份}{月份}{流水号}', '1', '2', '1', '0', '21', '2016-09-20 10:10:56', '21', '2016-09-20 10:10:56', null);
INSERT INTO `code_rule` VALUES ('11', '2', '{年份}', '1', '2', '1', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司编码主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父公司编码',
  `name` varchar(255) DEFAULT NULL COMMENT '融资租赁公司中文名称',
  `email` varchar(255) DEFAULT NULL COMMENT '企业邮箱',
  `english_name` varchar(255) DEFAULT NULL COMMENT '融资租赁公司英文名称',
  `short_name` varchar(50) DEFAULT NULL COMMENT '融资租赁公司简称',
  `worker_num` int(11) DEFAULT NULL COMMENT '融资租赁公司职工数',
  `license` varchar(255) DEFAULT NULL COMMENT '融资租赁公司营业执照',
  `expire_date` datetime DEFAULT NULL COMMENT '融资租赁公司营业期限',
  `tax_num` varchar(255) DEFAULT NULL COMMENT '融资租赁公司税务登记号',
  `org_code` varchar(255) DEFAULT NULL COMMENT '组织机构代码证',
  `found_date` datetime DEFAULT NULL COMMENT '融资租赁公司成立日期',
  `reg_capital` int(11) DEFAULT NULL COMMENT '融资租赁公司注册资本(万元)',
  `scope` varchar(255) DEFAULT NULL COMMENT '融资租赁公司经营范围',
  `phone` varchar(255) DEFAULT NULL COMMENT '融资租赁公司电话',
  `address` varchar(500) DEFAULT NULL COMMENT '融资租赁公司地址',
  `fax` varchar(50) DEFAULT NULL COMMENT '融资租赁公司传真',
  `postcode` varchar(50) DEFAULT NULL COMMENT '融资租赁公司邮编',
  `reg_address` varchar(500) DEFAULT NULL COMMENT '融资租赁公司注册地址',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `legal_name` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `legal_id` varchar(255) DEFAULT NULL COMMENT '法人身份证号',
  `legal_phone` varchar(50) DEFAULT NULL COMMENT '法人办公电话',
  `legal_address` varchar(500) DEFAULT NULL COMMENT '法人住址',
  `legal_postcode` varchar(50) DEFAULT NULL COMMENT '法人邮编',
  `legal_mobile` varchar(50) DEFAULT NULL COMMENT '法人手机',
  `legal_email` varchar(255) DEFAULT NULL COMMENT '法人邮箱',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '融资租赁公司联系人',
  `contact_id` varchar(255) DEFAULT NULL COMMENT '联系人身份证',
  `contact_phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `contact_address` varchar(500) DEFAULT NULL COMMENT '联系人住址',
  `contact_postcode` varchar(50) DEFAULT NULL COMMENT '联系人邮编',
  `contact_mobile` varchar(50) DEFAULT NULL COMMENT '联系人手机',
  `contact_email` varchar(255) DEFAULT NULL COMMENT '联系人邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `level` int(11) DEFAULT NULL COMMENT '公司级别\n0 - 分公司\n1 - 总公司',
  `status` int(11) DEFAULT NULL COMMENT '状态\n0 - 正常\n1 - 作废',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `credit_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
  `business_code_type` int(11) DEFAULT NULL COMMENT '社会代码类型\n0 - 普通\n1 - 三证合一',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', null, '仁聚汇通融资租赁公司', null, 'RJHT', '仁聚汇通', '200', null, '2018-09-08 00:00:00', null, null, '2013-09-09 00:00:00', '5000', '各类融资租赁', '010-69228342', '北京酒仙桥', null, null, null, null, '老董', '340817197102023451', null, '北京北京', null, '13988331133', null, '鹏哥', '340817197102023451', null, null, null, '13747366311', null, '2016-09-20 14:31:20', '1', null, '0', '1', null, null, 'ABC1234134u44y2211', '1');

-- ----------------------------
-- Table structure for interest
-- ----------------------------
DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '利率系统编码',
  `interest_date` datetime DEFAULT NULL COMMENT '时间',
  `half_year` decimal(14,3) DEFAULT NULL COMMENT '半年利率',
  `one_year` decimal(14,3) DEFAULT NULL COMMENT '一年利率',
  `three_year` decimal(14,3) DEFAULT NULL COMMENT '三年利率',
  `five_year` decimal(14,3) DEFAULT NULL COMMENT '五年利率',
  `five_year_above` decimal(14,3) DEFAULT NULL COMMENT '五年以上利率',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `notes` varchar(100) DEFAULT NULL COMMENT '备注',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interest
-- ----------------------------
INSERT INTO `interest` VALUES ('28', '2015-08-08 00:00:00', '5.000', '5.500', '6.000', '7.000', '8.000', '0', '基准贷款利率--测试用', '34', '2016-09-20 14:41:30', null, null);

-- ----------------------------
-- Table structure for leasing_overdue
-- ----------------------------
DROP TABLE IF EXISTS `leasing_overdue`;
CREATE TABLE `leasing_overdue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `leasing_contract_id` bigint(20) DEFAULT NULL COMMENT '融资合同系统编码',
  `leasing_request_id` bigint(20) DEFAULT NULL COMMENT '融资申请编码',
  `lessee_id` bigint(20) DEFAULT NULL COMMENT '承租人编码',
  `overdue_rent` decimal(14,3) DEFAULT NULL COMMENT '逾期租金=所有租金表逾期金额之和',
  `default_interest` decimal(14,3) DEFAULT NULL COMMENT '罚息等于所有租金表下罚息金额之和',
  `overdue_days` decimal(14,3) DEFAULT NULL COMMENT '逾期天数=最长租金表逾期天数',
  `create_time` datetime DEFAULT NULL COMMENT '生成时间',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leasing_overdue
-- ----------------------------

-- ----------------------------
-- Table structure for lessee
-- ----------------------------
DROP TABLE IF EXISTS `lessee`;
CREATE TABLE `lessee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '承租人系统编码',
  `code` varchar(255) DEFAULT NULL COMMENT '承租人编码',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(50) DEFAULT NULL COMMENT '数据字典\n1 - 男\n2 - 女',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `identity_type` varchar(50) DEFAULT NULL COMMENT '数据字典\n1 - 身份证\n2 - 护照',
  `identity_number` varchar(255) DEFAULT NULL COMMENT '证件号码',
  `education` varchar(50) DEFAULT NULL COMMENT '文化程度 （数据字典）\n1 - 小学\n2 - 初中\n3 - 高中\n4 - 大学\n5 - 研究生\n6 - 博士\n7 - 博士后',
  `hukou` varchar(50) DEFAULT NULL COMMENT '户口',
  `marriage` varchar(50) DEFAULT NULL COMMENT '婚姻状况 （数据字典）\n1 - 未婚\n2 - 已婚\n3 - 离异\n4 - 丧偶',
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机号码',
  `home_phone` varchar(45) DEFAULT NULL COMMENT '家庭电话',
  `home_address` varchar(1000) DEFAULT NULL COMMENT '家庭地址',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `office_address` varchar(1000) DEFAULT NULL COMMENT '办公室地址',
  `mail_address_type` int(11) DEFAULT NULL COMMENT '1 - 同家庭地址\n2 - 同单位地址\n3 - 新地址',
  `mail_address` varchar(1000) DEFAULT NULL COMMENT '邮寄地址',
  `type` varchar(50) DEFAULT NULL COMMENT '承租人类别 （数据字典）\n1 - 自然人\n2 - 法人',
  `residential_type` varchar(50) DEFAULT NULL COMMENT '居住类型 （数据字典）\n1 - 租房\n2 - 自有房',
  `residential_year` int(11) DEFAULT NULL COMMENT '居住年限',
  `mortgage_monthly` decimal(14,3) DEFAULT NULL COMMENT '月供',
  `office` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `work_year` int(11) DEFAULT NULL COMMENT '工作年限',
  `work_industry` varchar(50) DEFAULT NULL COMMENT '所属行业',
  `work_position` varchar(255) DEFAULT NULL COMMENT '单位职务',
  `work_nature` int(11) DEFAULT NULL COMMENT '企业性质 （数据字典）\n1 - 私企\n2 - 国企\n3 - 外企\n4 - 外商合资',
  `salary_monthly` decimal(14,3) DEFAULT NULL COMMENT '月收入',
  `work_type` varchar(50) DEFAULT NULL COMMENT '职业类型',
  `notes` varchar(3000) DEFAULT NULL COMMENT '备注',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `corp_name` varchar(255) DEFAULT NULL COMMENT '企业名称',
  `corp_nature` varchar(50) DEFAULT NULL COMMENT '企业性质\n1 - 私企\n2 - 国企\n3 - 外企\n4 - 外商合资',
  `business_code_type` int(11) DEFAULT NULL COMMENT '是否三证合一\n0 - 普通\n1 - 三证合一',
  `org_code` varchar(255) DEFAULT NULL COMMENT '组织机构代码号',
  `tax_num` varchar(255) DEFAULT NULL COMMENT '税务登记号',
  `corp_found_date` datetime DEFAULT NULL COMMENT '成立日期',
  `corp_expire_date` datetime DEFAULT NULL COMMENT '营业执照有效期',
  `corp_reg_capital` int(11) DEFAULT NULL COMMENT '注册资本',
  `corp_license` varchar(255) DEFAULT NULL COMMENT '营业执照注册号',
  `corp_scope` varchar(500) DEFAULT NULL COMMENT '经营范围',
  `corp_reg_address` varchar(500) DEFAULT NULL COMMENT '公司注册地址',
  `corp_office_address` varchar(500) DEFAULT NULL COMMENT '公司办公地址',
  `corp_mail_address` varchar(500) DEFAULT NULL COMMENT '通讯住址',
  `corp_staff_num` int(11) DEFAULT NULL COMMENT '公司人数',
  `corp_contact` varchar(255) DEFAULT NULL COMMENT '单位联系人',
  `corp_contract_phone` varchar(45) DEFAULT NULL COMMENT '联系人电话',
  `corp_postcode` varchar(45) DEFAULT NULL COMMENT '公司邮政编码',
  `credit_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
  `status` int(11) DEFAULT NULL COMMENT '承租人状态\n0 - 意向客户\n1 - 正式客户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lessee
-- ----------------------------
INSERT INTO `lessee` VALUES ('1', 'CZR201609Z92909', '张三', null, null, '1', '310010198812302331', null, null, null, '13987163276', null, '上海上海浦东', null, null, '1', '上海上海浦东', '1', null, null, null, null, null, null, null, null, null, null, null, '0', '35', '2016-09-20 17:15:00', '36', '2016-09-22 10:15:51', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `lessee` VALUES ('2', 'CZR201609F92910', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2', null, null, null, null, null, null, null, null, null, null, null, '0', '35', '2016-09-20 17:16:08', null, null, '开心麻花工作室', '1', '1', null, null, null, '2018-12-31 00:00:00', null, null, '各类平面设计', null, null, null, null, '开心', '021-3871773433', null, 'ABC123456765433212', '0');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `payer` varchar(255) DEFAULT NULL COMMENT '来款人',
  `payer_account` varchar(255) DEFAULT NULL COMMENT '来款账号',
  `payee_account` varchar(255) DEFAULT NULL COMMENT '收款账号',
  `payee_account_id` bigint(20) DEFAULT NULL COMMENT '收款账号',
  `amount` decimal(14,3) DEFAULT NULL COMMENT '来款金额',
  `amount_bd` decimal(14,3) DEFAULT NULL COMMENT '已分解金额',
  `amount_remained` decimal(14,3) DEFAULT NULL COMMENT '分解后剩余金额',
  `certificate_type` int(11) DEFAULT NULL COMMENT '凭证类型\n0 - 汇款单\n1 - 现金收据\n2 - 银行回执单',
  `certificate_code` varchar(255) DEFAULT NULL COMMENT '凭证号码',
  `notes` varchar(1000) DEFAULT NULL COMMENT '来款备注',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `payment_time` datetime DEFAULT NULL COMMENT '来款时间',
  `status` int(11) DEFAULT NULL COMMENT '状态\n0 - 未认款\n1 - 已认款/未分解\n2 - 分解已提交\n3 - 分解已通过\n4 - 分解已驳回\n5 - 核销作废已提交\n6 - 核销作废已通过\n7 - 核销作废已驳回\n8 - 冲红已提交\n9 - 冲红已通过\n10 - 冲红已驳回',
  `payment_type` int(11) DEFAULT NULL COMMENT '来款方式\n0 - 人工录入\n1 - 代扣\n2 - 凭证上传\n3 - 其他',
  `payment_code` varchar(255) DEFAULT NULL COMMENT '资金编号',
  `assigner` bigint(20) DEFAULT NULL COMMENT '认款操作员用户编码',
  `lessee_id` bigint(20) DEFAULT NULL COMMENT '承租人编码',
  `bd_operator` bigint(20) DEFAULT NULL COMMENT '资金分解人',
  `assign_time` datetime DEFAULT NULL COMMENT '认款时间',
  `bd_time` datetime DEFAULT NULL COMMENT '分解时间',
  `bd_reviewer` bigint(20) DEFAULT NULL COMMENT '资金分解的审核人',
  `bd_cancel_operator` bigint(20) DEFAULT NULL COMMENT '核销作废申请人',
  `bd_cancel_reviewer` bigint(20) DEFAULT NULL COMMENT '核销作废审核人员',
  `bd_cancel_notes` varchar(1000) DEFAULT NULL COMMENT '核销作废原因',
  `bd_cancel_code` varchar(255) DEFAULT NULL COMMENT '核销作废单号',
  `bd_code` varchar(255) DEFAULT NULL COMMENT '分解单号',
  `bd_cancel_time` datetime DEFAULT NULL COMMENT '核销作废申请时间',
  `bd_review_notes` varchar(1000) DEFAULT NULL COMMENT '分解审核备注',
  `bd_cancel_review_notes` varchar(1000) DEFAULT NULL COMMENT '核销作废审核备注',
  `rollback_operator` bigint(20) DEFAULT NULL COMMENT '冲红申请人',
  `rollback_reviewer` bigint(20) DEFAULT NULL COMMENT '冲红审批人',
  `rollback_notes` varchar(1000) DEFAULT NULL COMMENT '冲红备注',
  `rollback_review_notes` varchar(1000) DEFAULT NULL COMMENT '冲红审核备注',
  `rollback_code` varchar(255) DEFAULT NULL COMMENT '冲红单号',
  `rollback_time` datetime DEFAULT NULL COMMENT '冲红申请时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `import_file_name` bigint(20) DEFAULT NULL COMMENT '导入文件的名称',
  `bd_review_time` datetime DEFAULT NULL COMMENT '分解审核时间',
  `bd_cancel_review_time` datetime DEFAULT NULL COMMENT '核销作废审核时间',
  `rollback_review_time` datetime DEFAULT NULL COMMENT '冲红审核时间',
  `payment_import_file_id` bigint(20) DEFAULT NULL COMMENT '导入文件系统编码',
  `term_type` int(11) DEFAULT NULL COMMENT '0 - 当期\n1 - 往期',
  `bd_notes` varchar(1000) DEFAULT NULL COMMENT '分解备注',
  `ori_payment_id` bigint(20) DEFAULT NULL COMMENT '原来款编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------

-- ----------------------------
-- Table structure for payment_period_breakdown
-- ----------------------------
DROP TABLE IF EXISTS `payment_period_breakdown`;
CREATE TABLE `payment_period_breakdown` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_id` bigint(20) DEFAULT NULL COMMENT '来款编码',
  `rent_id` bigint(20) DEFAULT NULL COMMENT '来款分解对应的租金表编码',
  `payment_sn` int(11) DEFAULT NULL COMMENT '租金表期次',
  `principal_received` decimal(14,3) DEFAULT NULL COMMENT '本金实收款',
  `interest_received` decimal(14,3) DEFAULT NULL COMMENT '利息实收款',
  `status` int(11) DEFAULT NULL COMMENT '分解状态\n0 - 分解已提交\n1 - 分解已通过\n2 - 分解已驳回\n3 - 核销作废已提交\n4 - 核销作废已通过\n5 - 核销作废已驳回\n6 - 冲红已提交\n7 - 冲红已通过\n8 - 冲红已驳回',
  `notes` varchar(1000) DEFAULT NULL COMMENT '备注',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `payment_date` date DEFAULT NULL COMMENT '支付日期',
  `rent_period_id` bigint(20) DEFAULT NULL COMMENT '租金表分期的系统编码',
  `default_interest_received` decimal(14,3) DEFAULT NULL COMMENT '罚息实收款',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment_period_breakdown
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '查看演示菜单', null, '菜单控制', 'MENU_DEMO_VIEW');
INSERT INTO `permission` VALUES ('2', '设置中心菜单', null, '菜单控制', 'MENU_MASTER_SETTING');
INSERT INTO `permission` VALUES ('3', '权限管理菜单', null, '菜单控制', 'MENU_AUTH_MANAGER');
INSERT INTO `permission` VALUES ('4', '新建用户', null, '权限设置', 'USER_CREATE');
INSERT INTO `permission` VALUES ('5', '编辑用户', null, '权限设置', 'USER_EDIT');
INSERT INTO `permission` VALUES ('6', '删除用户', null, '权限设置', 'USER_DELETE');
INSERT INTO `permission` VALUES ('7', '查看用户', null, '权限设置', 'USER_VIEW');
INSERT INTO `permission` VALUES ('8', '新建角色', null, '权限设置', 'ROLE_CREATE');
INSERT INTO `permission` VALUES ('9', '编辑角色', null, '权限设置', 'ROLE_EDIT');
INSERT INTO `permission` VALUES ('10', '删除角色', null, '权限设置', 'ROLE_DELETE');
INSERT INTO `permission` VALUES ('11', '查看角色', null, '权限设置', 'ROLE_VIEW');
INSERT INTO `permission` VALUES ('12', '新建公司', null, '设置中心', 'COMPANY_CREATE');
INSERT INTO `permission` VALUES ('13', '编辑公司', null, '设置中心', 'COMPANY_EDIT');
INSERT INTO `permission` VALUES ('14', '删除公司', null, '设置中心', 'COMPANY_DELETE');
INSERT INTO `permission` VALUES ('15', '查看公司', null, '设置中心', 'COMPANY_VIEW');
INSERT INTO `permission` VALUES ('16', '新建银行', null, '设置中心', 'BANK_CREATE');
INSERT INTO `permission` VALUES ('17', '编辑银行', null, '设置中心', 'BANK_EDIT');
INSERT INTO `permission` VALUES ('18', '删除银行', null, '设置中心', 'BANK_DELETE');
INSERT INTO `permission` VALUES ('19', '查看银行', null, '设置中心', 'BANK_VIEW');
INSERT INTO `permission` VALUES ('20', '新建银行账户', null, '设置中心', 'BANK_ACCOUNT_CREATE');
INSERT INTO `permission` VALUES ('21', '编辑银行账户', null, '设置中心', 'BANK_ACCOUNT_EDIT');
INSERT INTO `permission` VALUES ('22', '删除银行账户', null, '设置中心', 'BANK_ACCOUNT_DELETE');
INSERT INTO `permission` VALUES ('23', '查看银行账户', null, '设置中心', 'BANK_ACCOUNT_VIEW');
INSERT INTO `permission` VALUES ('24', '新建出卖方', null, '设置中心', 'SELLER_CREATE');
INSERT INTO `permission` VALUES ('25', '编辑出卖方', null, '设置中心', 'SELLER_EDIT');
INSERT INTO `permission` VALUES ('26', '删除出卖方', null, '设置中心', 'SELLER_DELETE');
INSERT INTO `permission` VALUES ('27', '查看出卖方', null, '设置中心', 'SELLER_VIEW');
INSERT INTO `permission` VALUES ('28', '新建数据字典', null, '设置中心', 'DICTIONARY_CREATE');
INSERT INTO `permission` VALUES ('29', '编辑数据字典', null, '设置中心', 'DICTIONARY_EDIT');
INSERT INTO `permission` VALUES ('30', '删除数据字典', null, '设置中心', 'DICTIONARY_DELETE');
INSERT INTO `permission` VALUES ('31', '查看数据字典', null, '设置中心', 'DICTIONARY_VIEW');
INSERT INTO `permission` VALUES ('32', '新建利率', null, '设置中心', 'INTEREST_CREATE');
INSERT INTO `permission` VALUES ('33', '编辑利率', null, '设置中心', 'INTEREST_EDIT');
INSERT INTO `permission` VALUES ('34', '删除利率', null, '设置中心', 'INTEREST_DELETE');
INSERT INTO `permission` VALUES ('35', '查看利率', null, '设置中心', 'INTEREST_VIEW');
INSERT INTO `permission` VALUES ('36', '新建编码', null, '设置中心', 'CODE_RULE_CREATE');
INSERT INTO `permission` VALUES ('37', '编辑编码', null, '设置中心', 'CODE_RULE_EDIT');
INSERT INTO `permission` VALUES ('38', '删除编码', null, '设置中心', 'CODE_RULE_DELETE');
INSERT INTO `permission` VALUES ('39', '查看编码', null, '设置中心', 'CODE_RULE_VIEW');
INSERT INTO `permission` VALUES ('40', '新建融租方案', null, '设置中心', 'LEASING_SCHEMA_CREATE');
INSERT INTO `permission` VALUES ('41', '编辑融租方案', null, '设置中心', 'LEASING_SCHEMA_EDIT');
INSERT INTO `permission` VALUES ('42', '删除融租方案', null, '设置中心', 'LEASING_SCHEMA_DELETE');
INSERT INTO `permission` VALUES ('43', '查看融租方案', null, '设置中心', 'LEASING_SCHEMA_VIEW');
INSERT INTO `permission` VALUES ('44', '工作台菜单', null, '菜单控制', 'MENU_DESKTOP');
INSERT INTO `permission` VALUES ('45', '融资申请菜单', null, '菜单控制', 'MENU_LEASING_REQUEST');
INSERT INTO `permission` VALUES ('46', '资信管理菜单', null, '菜单控制', 'MENU_CREDIT');
INSERT INTO `permission` VALUES ('47', '合同管理菜单', null, '菜单控制', 'MENU_CONTRACT');
INSERT INTO `permission` VALUES ('48', '承租人管理菜单', null, '菜单控制', 'MENU_LESSEE');
INSERT INTO `permission` VALUES ('49', '财务管理菜单', null, '菜单控制', 'MENU_FINANCE');
INSERT INTO `permission` VALUES ('50', '催收管理菜单', null, '菜单控制', 'MENU_DEBT');
INSERT INTO `permission` VALUES ('51', '流程中心菜单', null, '菜单控制', 'MENU_PROCESS');
INSERT INTO `permission` VALUES ('52', '组件参考菜单', null, '菜单控制', 'MENU_COMPONENT');
INSERT INTO `permission` VALUES ('53', '新建融资试算', null, '融资申请', 'LEASING_REQUEST_CREATE');
INSERT INTO `permission` VALUES ('54', '编辑融资试算', null, '融资申请', 'LEASING_REQUEST_EDIT');
INSERT INTO `permission` VALUES ('55', '删除融资试算', null, '融资申请', 'LEASING_REQUEST_DELETE');
INSERT INTO `permission` VALUES ('56', '查看融资试算', null, '融资申请', 'LEASING_REQUEST_VIEW');
INSERT INTO `permission` VALUES ('57', '新建资信信息', null, '资信管理', 'CREDIT_INFO_CREATE');
INSERT INTO `permission` VALUES ('58', '编辑资信信息', null, '资信管理', 'CREDIT_INFO_EDIT');
INSERT INTO `permission` VALUES ('59', '删除资信信息', null, '资信管理', 'CREDIT_INFO_DELETE');
INSERT INTO `permission` VALUES ('60', '查看资信信息', null, '资信管理', 'CREDIT_INFO_VIEW');
INSERT INTO `permission` VALUES ('61', '审核资信信息', null, '资信管理', 'CREDIT_INFO_VERIFY');
INSERT INTO `permission` VALUES ('62', '新建融租合同', null, '合同管理', 'LEASING_CONTRACT_CREATE');
INSERT INTO `permission` VALUES ('63', '编辑融租合同', null, '合同管理', 'LEASING_CONTRACT_EDIT');
INSERT INTO `permission` VALUES ('64', '删除融租合同', null, '合同管理', 'LEASING_CONTRACT_DELETE');
INSERT INTO `permission` VALUES ('65', '查看融租合同', null, '合同管理', 'LEASING_CONTRACT_VIEW');
INSERT INTO `permission` VALUES ('66', '审核融租合同', null, '合同管理', 'LEASING_CONTRACT_VERIFY');
INSERT INTO `permission` VALUES ('67', '终止融租合同', null, '合同管理', 'LEASING_CONTRACT_FINISH');
INSERT INTO `permission` VALUES ('68', '新建买卖合同', null, '合同管理', 'PURCHASE_CONTRACT_CREATE');
INSERT INTO `permission` VALUES ('69', '编辑买卖合同', null, '合同管理', 'PURCHASE_CONTRACT_EDIT');
INSERT INTO `permission` VALUES ('70', '删除买卖合同', null, '合同管理', 'PURCHASE_CONTRACT_DELETE');
INSERT INTO `permission` VALUES ('71', '查看买卖合同', null, '合同管理', 'PURCHASE_CONTRACT_VIEW');
INSERT INTO `permission` VALUES ('72', '审核买卖合同', null, '合同管理', 'PURCHASE_CONTRACT_VERIFY');
INSERT INTO `permission` VALUES ('73', '终止买卖合同', null, '合同管理', 'PURCHASE_CONTRACT_FINISH');
INSERT INTO `permission` VALUES ('74', '新建支付表', null, '合同管理', 'PURCHASE_PAYMENT_PLAN_CREATE');
INSERT INTO `permission` VALUES ('75', '编辑支付表', null, '合同管理', 'PURCHASE_PAYMENT_PLAN_EDIT');
INSERT INTO `permission` VALUES ('76', '删除支付表', null, '合同管理', 'PURCHASE_PAYMENT_PLAN_DELETE');
INSERT INTO `permission` VALUES ('77', '查看支付表', null, '合同管理', 'PURCHASE_PAYMENT_PLAN_VIEW');
INSERT INTO `permission` VALUES ('78', '审核支付表', null, '合同管理', 'PURCHASE_PAYMENT_PLAN_VERIFY');
INSERT INTO `permission` VALUES ('79', '终止支付表', null, '合同管理', 'PURCHASE_PAYMENT_PLAN_FINISH');
INSERT INTO `permission` VALUES ('80', '新建承租人', null, '承租人管理', 'LESSEE_CREATE');
INSERT INTO `permission` VALUES ('81', '编辑承租人', null, '承租人管理', 'LESSEE_EDIT');
INSERT INTO `permission` VALUES ('82', '删除承租人', null, '承租人管理', 'LESSEE_DELETE');
INSERT INTO `permission` VALUES ('83', '查看承租人', null, '承租人管理', 'LESSEE_VIEW');
INSERT INTO `permission` VALUES ('84', '新建买卖付款', null, '合同管理', 'PURCHASE_PAYMENT_CREATE');
INSERT INTO `permission` VALUES ('85', '编辑买卖付款', null, '合同管理', 'PURCHASE_PAYMENT_EDIT');
INSERT INTO `permission` VALUES ('86', '删除买卖付款', null, '合同管理', 'PURCHASE_PAYMENT_DELETE');
INSERT INTO `permission` VALUES ('87', '查看买卖付款', null, '合同管理', 'PURCHASE_PAYMENT_VIEW');
INSERT INTO `permission` VALUES ('88', '审核买卖付款', null, '合同管理', 'PURCHASE_PAYMENT_VERIFY');
INSERT INTO `permission` VALUES ('89', '终止买卖付款', null, '合同管理', 'PURCHASE_PAYMENT_FINISH');
INSERT INTO `permission` VALUES ('90', '查看权限', null, '权限设置', 'PERMISSION_VIEW');
INSERT INTO `permission` VALUES ('91', '终止出卖方', null, '设置中心', 'SELLER_FINISH');
INSERT INTO `permission` VALUES ('92', '提交资信', null, '资信管理', 'CREDIT_INFO_SUBMIT');
INSERT INTO `permission` VALUES ('93', '查看租金表', null, '流程中心', 'RENT_VIEW');
INSERT INTO `permission` VALUES ('94', '设定起租日', null, '流程中心', 'RENT_SET_START_TIME');
INSERT INTO `permission` VALUES ('95', '来款录入', null, '财务管理', 'PAYMENT_INPUT');
INSERT INTO `permission` VALUES ('96', '上传资金', null, '财务管理', 'PAYMENT_UPLOAD');
INSERT INTO `permission` VALUES ('97', '认款', null, '财务管理', 'PAYMENT_CLAIM');
INSERT INTO `permission` VALUES ('98', '查看资金详情', null, '财务管理', 'PAYMENT_DETAIL_VIEW');
INSERT INTO `permission` VALUES ('99', '付款查看', null, '财务管理', 'PURCHASE_CONTRACT_PAYMENT_VIEW');
INSERT INTO `permission` VALUES ('100', '付款申请', null, '财务管理', 'PURCHASE_CONTRACT_PAYMENT_REQUEST');
INSERT INTO `permission` VALUES ('101', '付款执行', null, '财务管理', 'PURCHASE_CONTRACT_PAYMENT_PAY');
INSERT INTO `permission` VALUES ('102', '使用权转移', null, '财务管理', 'PURCHASE_CONTRACT_PAYMENT_USUFRUCT_TRANSFER');
INSERT INTO `permission` VALUES ('103', '付款审核', null, '财务管理', 'PURCHASE_CONTRACT_PAYMENT_VERIFY');

-- ----------------------------
-- Table structure for rent
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `rent_code` varchar(255) DEFAULT NULL COMMENT '租金表号',
  `rent_master_id` bigint(20) DEFAULT NULL COMMENT '租金表主表编码',
  `rent_start_date` datetime DEFAULT NULL COMMENT '起租日',
  `rent_end_date` datetime DEFAULT NULL COMMENT '租赁到期日',
  `status` int(11) DEFAULT NULL COMMENT '租金表状态\n0 - 正常\n1 - 已展期\n2 - 提前结清\n3 - 到期结清\n4 - 回购',
  `notes` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `pre_rent_id` bigint(20) DEFAULT NULL COMMENT '之前的租金表编码\n当调息、展期操作发生时，生成新的租金表',
  `discount_amount` decimal(14,3) DEFAULT NULL COMMENT '融资额',
  `version` varchar(50) DEFAULT NULL COMMENT '租金表版本号',
  `dp_receivable` decimal(14,3) DEFAULT NULL COMMENT '首期应付款',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `locked` int(11) DEFAULT NULL COMMENT '租金表锁标志\n0 - 正常\n1 - lock',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rent
-- ----------------------------
INSERT INTO `rent` VALUES ('1', 'RZZLHF20160900077-1', '1', '2016-09-24 00:00:00', '2017-09-24 00:00:00', '0', null, '2016-09-22 10:49:53', null, '240000.000', '1.0', '86000.000', '0', '35', '35', '2016-09-22 11:18:20', '1');

-- ----------------------------
-- Table structure for rent_master
-- ----------------------------
DROP TABLE IF EXISTS `rent_master`;
CREATE TABLE `rent_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `rent_master_code` varchar(255) DEFAULT NULL COMMENT '租金表号',
  `leasing_request_id` bigint(20) DEFAULT NULL COMMENT '融资请求系统编码',
  `notes` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `total_price` decimal(14,3) DEFAULT NULL COMMENT '租赁物总价 = unit_price * item_number',
  `discount_amount` decimal(14,3) DEFAULT NULL COMMENT '融资额',
  `dp_receivable` decimal(14,3) DEFAULT NULL COMMENT '首期应付款',
  `dp_received` decimal(14,3) DEFAULT NULL COMMENT '首期款已收',
  `dp_remained` decimal(14,3) DEFAULT NULL COMMENT '首期款剩余 = dp_receivable - dp_received',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rent_master
-- ----------------------------
INSERT INTO `rent_master` VALUES ('1', 'RZZLHF20160900077-0', '2', null, '2016-09-22 10:15:51', '300000.000', '240000.000', '86000.000', '0.000', '86000.000', '0', '36', null, null);

-- ----------------------------
-- Table structure for rent_overdue
-- ----------------------------
DROP TABLE IF EXISTS `rent_overdue`;
CREATE TABLE `rent_overdue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `leasing_contract_id` bigint(20) DEFAULT NULL COMMENT '融资合同系统编码',
  `rent_id` bigint(20) DEFAULT NULL COMMENT '租金表编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `default_interest` decimal(14,3) DEFAULT NULL COMMENT '罚息',
  `default_interest_received` decimal(14,3) DEFAULT NULL COMMENT '罚息收款',
  `default_interest_remained` decimal(14,3) DEFAULT NULL COMMENT '罚息剩余',
  `overdue_rent` decimal(14,3) DEFAULT NULL COMMENT '逾期租金',
  `overdue_total` decimal(14,3) DEFAULT NULL COMMENT '逾期总金额',
  `overdue_days` decimal(14,3) DEFAULT NULL COMMENT '逾期天数',
  `lessee_id` bigint(20) DEFAULT NULL COMMENT '承租人编码',
  `lessee_name` varchar(255) DEFAULT NULL,
  `lessee_contact` varchar(50) DEFAULT NULL,
  `rent_code` varchar(255) DEFAULT NULL COMMENT '租金表号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rent_overdue
-- ----------------------------

-- ----------------------------
-- Table structure for rent_period
-- ----------------------------
DROP TABLE IF EXISTS `rent_period`;
CREATE TABLE `rent_period` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rent_id` bigint(20) NOT NULL COMMENT '租金拆分表的编码',
  `payment_sn` int(11) DEFAULT NULL COMMENT '期次',
  `payment_date` date DEFAULT NULL COMMENT '支付日期',
  `rent` decimal(14,3) DEFAULT NULL COMMENT '每期租金',
  `principal` decimal(14,3) DEFAULT NULL COMMENT '本金',
  `interest` decimal(14,3) DEFAULT NULL COMMENT '利息',
  `principal_remained` decimal(14,3) DEFAULT NULL COMMENT '剩余本金 = principal - principal_received',
  `principal_received` decimal(14,3) DEFAULT NULL COMMENT '本金已收款',
  `interest_received` decimal(14,3) DEFAULT NULL COMMENT '利息已收款',
  `interest_remained` decimal(14,3) DEFAULT NULL COMMENT '利息剩余款 = interest - interest_received',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `total_principal_remained` decimal(14,3) DEFAULT NULL COMMENT '总剩余本金',
  `default_interest` decimal(14,3) DEFAULT NULL COMMENT '罚息',
  `default_interest_received` decimal(14,3) DEFAULT NULL COMMENT '罚息收款',
  `default_interest_remained` decimal(14,3) DEFAULT NULL COMMENT '罚息剩余',
  `exempt_amount` decimal(14,3) DEFAULT NULL COMMENT '减免金额',
  `overdue_rent` decimal(14,3) DEFAULT NULL COMMENT '逾期租金',
  `overdue_days` decimal(14,3) DEFAULT NULL COMMENT '逾期天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rent_period
-- ----------------------------
INSERT INTO `rent_period` VALUES ('1', '1', '1', '2016-10-24', '21800.000', '20000.000', '1800.000', '20000.000', '0.000', '0.000', '1800.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '220000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('2', '1', '2', '2016-11-24', '21650.000', '20000.000', '1650.000', '20000.000', '0.000', '0.000', '1650.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '200000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('3', '1', '3', '2016-12-24', '21500.000', '20000.000', '1500.000', '20000.000', '0.000', '0.000', '1500.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '180000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('4', '1', '4', '2017-01-24', '21350.000', '20000.000', '1350.000', '20000.000', '0.000', '0.000', '1350.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '160000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('5', '1', '5', '2017-02-24', '21200.000', '20000.000', '1200.000', '20000.000', '0.000', '0.000', '1200.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '140000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('6', '1', '6', '2017-03-24', '21050.000', '20000.000', '1050.000', '20000.000', '0.000', '0.000', '1050.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '120000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('7', '1', '7', '2017-04-24', '20900.000', '20000.000', '900.000', '20000.000', '0.000', '0.000', '900.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '100000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('8', '1', '8', '2017-05-24', '20750.000', '20000.000', '750.000', '20000.000', '0.000', '0.000', '750.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '80000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('9', '1', '9', '2017-06-24', '20600.000', '20000.000', '600.000', '20000.000', '0.000', '0.000', '600.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '60000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('10', '1', '10', '2017-07-24', '20450.000', '20000.000', '450.000', '20000.000', '0.000', '0.000', '450.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '40000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('11', '1', '11', '2017-08-24', '20300.000', '20000.000', '300.000', '20000.000', '0.000', '0.000', '300.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '20000.000', '0.000', '0.000', '0.000', null, null, null);
INSERT INTO `rent_period` VALUES ('12', '1', '12', '2017-09-24', '20150.000', '20000.000', '150.000', '20000.000', '0.000', '0.000', '150.000', '0', '35', '2016-09-22 10:49:53', '35', '2016-09-22 11:18:20', '0.000', '0.000', '0.000', '0.000', null, null, null);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4379 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('3959', '35', '2');
INSERT INTO `role_permission` VALUES ('3960', '35', '35');
INSERT INTO `role_permission` VALUES ('3961', '35', '43');
INSERT INTO `role_permission` VALUES ('3962', '35', '44');
INSERT INTO `role_permission` VALUES ('3963', '35', '45');
INSERT INTO `role_permission` VALUES ('3964', '35', '46');
INSERT INTO `role_permission` VALUES ('3965', '35', '47');
INSERT INTO `role_permission` VALUES ('3966', '35', '48');
INSERT INTO `role_permission` VALUES ('3967', '35', '51');
INSERT INTO `role_permission` VALUES ('3968', '35', '53');
INSERT INTO `role_permission` VALUES ('3969', '35', '54');
INSERT INTO `role_permission` VALUES ('3970', '35', '56');
INSERT INTO `role_permission` VALUES ('3971', '35', '57');
INSERT INTO `role_permission` VALUES ('3972', '35', '58');
INSERT INTO `role_permission` VALUES ('3973', '35', '60');
INSERT INTO `role_permission` VALUES ('3974', '35', '62');
INSERT INTO `role_permission` VALUES ('3975', '35', '63');
INSERT INTO `role_permission` VALUES ('3976', '35', '65');
INSERT INTO `role_permission` VALUES ('3977', '35', '68');
INSERT INTO `role_permission` VALUES ('3978', '35', '69');
INSERT INTO `role_permission` VALUES ('3979', '35', '71');
INSERT INTO `role_permission` VALUES ('3980', '35', '74');
INSERT INTO `role_permission` VALUES ('3981', '35', '75');
INSERT INTO `role_permission` VALUES ('3982', '35', '77');
INSERT INTO `role_permission` VALUES ('3983', '35', '80');
INSERT INTO `role_permission` VALUES ('3984', '35', '81');
INSERT INTO `role_permission` VALUES ('3985', '35', '83');
INSERT INTO `role_permission` VALUES ('3986', '35', '92');
INSERT INTO `role_permission` VALUES ('3987', '35', '93');
INSERT INTO `role_permission` VALUES ('3988', '35', '94');
INSERT INTO `role_permission` VALUES ('3989', '36', '62');
INSERT INTO `role_permission` VALUES ('3990', '36', '2');
INSERT INTO `role_permission` VALUES ('3991', '36', '35');
INSERT INTO `role_permission` VALUES ('3992', '36', '43');
INSERT INTO `role_permission` VALUES ('3993', '36', '44');
INSERT INTO `role_permission` VALUES ('3994', '36', '45');
INSERT INTO `role_permission` VALUES ('3995', '36', '46');
INSERT INTO `role_permission` VALUES ('3996', '36', '47');
INSERT INTO `role_permission` VALUES ('3997', '36', '48');
INSERT INTO `role_permission` VALUES ('3998', '36', '51');
INSERT INTO `role_permission` VALUES ('3999', '36', '53');
INSERT INTO `role_permission` VALUES ('4000', '36', '54');
INSERT INTO `role_permission` VALUES ('4001', '36', '55');
INSERT INTO `role_permission` VALUES ('4002', '36', '56');
INSERT INTO `role_permission` VALUES ('4003', '36', '57');
INSERT INTO `role_permission` VALUES ('4004', '36', '58');
INSERT INTO `role_permission` VALUES ('4005', '36', '59');
INSERT INTO `role_permission` VALUES ('4006', '36', '60');
INSERT INTO `role_permission` VALUES ('4007', '36', '61');
INSERT INTO `role_permission` VALUES ('4008', '36', '63');
INSERT INTO `role_permission` VALUES ('4009', '36', '64');
INSERT INTO `role_permission` VALUES ('4010', '36', '65');
INSERT INTO `role_permission` VALUES ('4011', '36', '66');
INSERT INTO `role_permission` VALUES ('4012', '36', '67');
INSERT INTO `role_permission` VALUES ('4013', '36', '68');
INSERT INTO `role_permission` VALUES ('4014', '36', '69');
INSERT INTO `role_permission` VALUES ('4015', '36', '70');
INSERT INTO `role_permission` VALUES ('4016', '36', '71');
INSERT INTO `role_permission` VALUES ('4017', '36', '72');
INSERT INTO `role_permission` VALUES ('4018', '36', '73');
INSERT INTO `role_permission` VALUES ('4019', '36', '74');
INSERT INTO `role_permission` VALUES ('4020', '36', '75');
INSERT INTO `role_permission` VALUES ('4021', '36', '76');
INSERT INTO `role_permission` VALUES ('4022', '36', '77');
INSERT INTO `role_permission` VALUES ('4023', '36', '78');
INSERT INTO `role_permission` VALUES ('4024', '36', '79');
INSERT INTO `role_permission` VALUES ('4025', '36', '80');
INSERT INTO `role_permission` VALUES ('4026', '36', '81');
INSERT INTO `role_permission` VALUES ('4027', '36', '82');
INSERT INTO `role_permission` VALUES ('4028', '36', '83');
INSERT INTO `role_permission` VALUES ('4029', '36', '92');
INSERT INTO `role_permission` VALUES ('4030', '36', '93');
INSERT INTO `role_permission` VALUES ('4031', '36', '94');
INSERT INTO `role_permission` VALUES ('4050', '1', '2');
INSERT INTO `role_permission` VALUES ('4051', '1', '3');
INSERT INTO `role_permission` VALUES ('4052', '1', '1');
INSERT INTO `role_permission` VALUES ('4053', '1', '44');
INSERT INTO `role_permission` VALUES ('4054', '1', '45');
INSERT INTO `role_permission` VALUES ('4055', '1', '46');
INSERT INTO `role_permission` VALUES ('4056', '1', '47');
INSERT INTO `role_permission` VALUES ('4057', '1', '48');
INSERT INTO `role_permission` VALUES ('4058', '1', '49');
INSERT INTO `role_permission` VALUES ('4059', '1', '50');
INSERT INTO `role_permission` VALUES ('4060', '1', '51');
INSERT INTO `role_permission` VALUES ('4061', '1', '52');
INSERT INTO `role_permission` VALUES ('4062', '1', '4');
INSERT INTO `role_permission` VALUES ('4063', '1', '8');
INSERT INTO `role_permission` VALUES ('4064', '1', '90');
INSERT INTO `role_permission` VALUES ('4065', '1', '9');
INSERT INTO `role_permission` VALUES ('4066', '1', '5');
INSERT INTO `role_permission` VALUES ('4067', '1', '80');
INSERT INTO `role_permission` VALUES ('4068', '1', '81');
INSERT INTO `role_permission` VALUES ('4069', '1', '82');
INSERT INTO `role_permission` VALUES ('4070', '1', '83');
INSERT INTO `role_permission` VALUES ('4071', '1', '12');
INSERT INTO `role_permission` VALUES ('4072', '1', '13');
INSERT INTO `role_permission` VALUES ('4073', '1', '14');
INSERT INTO `role_permission` VALUES ('4074', '1', '15');
INSERT INTO `role_permission` VALUES ('4075', '1', '16');
INSERT INTO `role_permission` VALUES ('4076', '1', '17');
INSERT INTO `role_permission` VALUES ('4077', '1', '18');
INSERT INTO `role_permission` VALUES ('4078', '1', '19');
INSERT INTO `role_permission` VALUES ('4079', '1', '20');
INSERT INTO `role_permission` VALUES ('4080', '1', '21');
INSERT INTO `role_permission` VALUES ('4081', '1', '22');
INSERT INTO `role_permission` VALUES ('4082', '1', '23');
INSERT INTO `role_permission` VALUES ('4083', '1', '28');
INSERT INTO `role_permission` VALUES ('4084', '1', '29');
INSERT INTO `role_permission` VALUES ('4085', '1', '30');
INSERT INTO `role_permission` VALUES ('4086', '1', '31');
INSERT INTO `role_permission` VALUES ('4087', '1', '32');
INSERT INTO `role_permission` VALUES ('4088', '1', '33');
INSERT INTO `role_permission` VALUES ('4089', '1', '34');
INSERT INTO `role_permission` VALUES ('4090', '1', '35');
INSERT INTO `role_permission` VALUES ('4091', '1', '36');
INSERT INTO `role_permission` VALUES ('4092', '1', '37');
INSERT INTO `role_permission` VALUES ('4093', '1', '38');
INSERT INTO `role_permission` VALUES ('4094', '1', '39');
INSERT INTO `role_permission` VALUES ('4095', '1', '40');
INSERT INTO `role_permission` VALUES ('4096', '1', '41');
INSERT INTO `role_permission` VALUES ('4097', '1', '42');
INSERT INTO `role_permission` VALUES ('4098', '1', '43');
INSERT INTO `role_permission` VALUES ('4099', '1', '24');
INSERT INTO `role_permission` VALUES ('4100', '1', '25');
INSERT INTO `role_permission` VALUES ('4101', '1', '26');
INSERT INTO `role_permission` VALUES ('4102', '1', '27');
INSERT INTO `role_permission` VALUES ('4103', '1', '91');
INSERT INTO `role_permission` VALUES ('4104', '1', '57');
INSERT INTO `role_permission` VALUES ('4105', '1', '59');
INSERT INTO `role_permission` VALUES ('4106', '1', '65');
INSERT INTO `role_permission` VALUES ('4107', '1', '60');
INSERT INTO `role_permission` VALUES ('4108', '1', '61');
INSERT INTO `role_permission` VALUES ('4109', '1', '6');
INSERT INTO `role_permission` VALUES ('4110', '1', '7');
INSERT INTO `role_permission` VALUES ('4111', '1', '10');
INSERT INTO `role_permission` VALUES ('4112', '1', '11');
INSERT INTO `role_permission` VALUES ('4113', '1', '53');
INSERT INTO `role_permission` VALUES ('4114', '1', '54');
INSERT INTO `role_permission` VALUES ('4115', '1', '55');
INSERT INTO `role_permission` VALUES ('4116', '1', '56');
INSERT INTO `role_permission` VALUES ('4117', '1', '58');
INSERT INTO `role_permission` VALUES ('4118', '1', '62');
INSERT INTO `role_permission` VALUES ('4119', '1', '63');
INSERT INTO `role_permission` VALUES ('4120', '1', '64');
INSERT INTO `role_permission` VALUES ('4121', '1', '66');
INSERT INTO `role_permission` VALUES ('4122', '1', '67');
INSERT INTO `role_permission` VALUES ('4123', '1', '68');
INSERT INTO `role_permission` VALUES ('4124', '1', '69');
INSERT INTO `role_permission` VALUES ('4125', '1', '70');
INSERT INTO `role_permission` VALUES ('4126', '1', '71');
INSERT INTO `role_permission` VALUES ('4127', '1', '72');
INSERT INTO `role_permission` VALUES ('4128', '1', '73');
INSERT INTO `role_permission` VALUES ('4129', '1', '74');
INSERT INTO `role_permission` VALUES ('4130', '1', '75');
INSERT INTO `role_permission` VALUES ('4131', '1', '76');
INSERT INTO `role_permission` VALUES ('4132', '1', '77');
INSERT INTO `role_permission` VALUES ('4133', '1', '78');
INSERT INTO `role_permission` VALUES ('4134', '1', '79');
INSERT INTO `role_permission` VALUES ('4135', '1', '84');
INSERT INTO `role_permission` VALUES ('4136', '1', '85');
INSERT INTO `role_permission` VALUES ('4137', '1', '86');
INSERT INTO `role_permission` VALUES ('4138', '1', '87');
INSERT INTO `role_permission` VALUES ('4139', '1', '88');
INSERT INTO `role_permission` VALUES ('4140', '1', '89');
INSERT INTO `role_permission` VALUES ('4141', '1', '92');
INSERT INTO `role_permission` VALUES ('4142', '1', '93');
INSERT INTO `role_permission` VALUES ('4143', '1', '94');
INSERT INTO `role_permission` VALUES ('4144', '1', '95');
INSERT INTO `role_permission` VALUES ('4145', '1', '96');
INSERT INTO `role_permission` VALUES ('4146', '1', '97');
INSERT INTO `role_permission` VALUES ('4147', '1', '98');
INSERT INTO `role_permission` VALUES ('4148', '1', '99');
INSERT INTO `role_permission` VALUES ('4149', '1', '100');
INSERT INTO `role_permission` VALUES ('4150', '1', '101');
INSERT INTO `role_permission` VALUES ('4151', '1', '102');
INSERT INTO `role_permission` VALUES ('4152', '1', '103');
INSERT INTO `role_permission` VALUES ('4183', '41', '84');
INSERT INTO `role_permission` VALUES ('4184', '41', '85');
INSERT INTO `role_permission` VALUES ('4185', '41', '87');
INSERT INTO `role_permission` VALUES ('4186', '41', '49');
INSERT INTO `role_permission` VALUES ('4187', '41', '100');
INSERT INTO `role_permission` VALUES ('4188', '41', '99');
INSERT INTO `role_permission` VALUES ('4189', '42', '86');
INSERT INTO `role_permission` VALUES ('4190', '42', '87');
INSERT INTO `role_permission` VALUES ('4191', '42', '88');
INSERT INTO `role_permission` VALUES ('4192', '42', '89');
INSERT INTO `role_permission` VALUES ('4193', '42', '49');
INSERT INTO `role_permission` VALUES ('4194', '42', '102');
INSERT INTO `role_permission` VALUES ('4195', '42', '101');
INSERT INTO `role_permission` VALUES ('4196', '42', '103');
INSERT INTO `role_permission` VALUES ('4197', '42', '99');
INSERT INTO `role_permission` VALUES ('4288', '34', '62');
INSERT INTO `role_permission` VALUES ('4289', '34', '2');
INSERT INTO `role_permission` VALUES ('4290', '34', '3');
INSERT INTO `role_permission` VALUES ('4291', '34', '4');
INSERT INTO `role_permission` VALUES ('4292', '34', '5');
INSERT INTO `role_permission` VALUES ('4293', '34', '6');
INSERT INTO `role_permission` VALUES ('4294', '34', '7');
INSERT INTO `role_permission` VALUES ('4295', '34', '8');
INSERT INTO `role_permission` VALUES ('4296', '34', '9');
INSERT INTO `role_permission` VALUES ('4297', '34', '10');
INSERT INTO `role_permission` VALUES ('4298', '34', '11');
INSERT INTO `role_permission` VALUES ('4299', '34', '12');
INSERT INTO `role_permission` VALUES ('4300', '34', '13');
INSERT INTO `role_permission` VALUES ('4301', '34', '14');
INSERT INTO `role_permission` VALUES ('4302', '34', '15');
INSERT INTO `role_permission` VALUES ('4303', '34', '16');
INSERT INTO `role_permission` VALUES ('4304', '34', '17');
INSERT INTO `role_permission` VALUES ('4305', '34', '18');
INSERT INTO `role_permission` VALUES ('4306', '34', '19');
INSERT INTO `role_permission` VALUES ('4307', '34', '20');
INSERT INTO `role_permission` VALUES ('4308', '34', '21');
INSERT INTO `role_permission` VALUES ('4309', '34', '22');
INSERT INTO `role_permission` VALUES ('4310', '34', '23');
INSERT INTO `role_permission` VALUES ('4311', '34', '24');
INSERT INTO `role_permission` VALUES ('4312', '34', '25');
INSERT INTO `role_permission` VALUES ('4313', '34', '26');
INSERT INTO `role_permission` VALUES ('4314', '34', '27');
INSERT INTO `role_permission` VALUES ('4315', '34', '31');
INSERT INTO `role_permission` VALUES ('4316', '34', '32');
INSERT INTO `role_permission` VALUES ('4317', '34', '33');
INSERT INTO `role_permission` VALUES ('4318', '34', '34');
INSERT INTO `role_permission` VALUES ('4319', '34', '35');
INSERT INTO `role_permission` VALUES ('4320', '34', '39');
INSERT INTO `role_permission` VALUES ('4321', '34', '40');
INSERT INTO `role_permission` VALUES ('4322', '34', '41');
INSERT INTO `role_permission` VALUES ('4323', '34', '42');
INSERT INTO `role_permission` VALUES ('4324', '34', '43');
INSERT INTO `role_permission` VALUES ('4325', '34', '44');
INSERT INTO `role_permission` VALUES ('4326', '34', '45');
INSERT INTO `role_permission` VALUES ('4327', '34', '46');
INSERT INTO `role_permission` VALUES ('4328', '34', '47');
INSERT INTO `role_permission` VALUES ('4329', '34', '48');
INSERT INTO `role_permission` VALUES ('4330', '34', '49');
INSERT INTO `role_permission` VALUES ('4331', '34', '51');
INSERT INTO `role_permission` VALUES ('4332', '34', '53');
INSERT INTO `role_permission` VALUES ('4333', '34', '54');
INSERT INTO `role_permission` VALUES ('4334', '34', '55');
INSERT INTO `role_permission` VALUES ('4335', '34', '56');
INSERT INTO `role_permission` VALUES ('4336', '34', '57');
INSERT INTO `role_permission` VALUES ('4337', '34', '58');
INSERT INTO `role_permission` VALUES ('4338', '34', '59');
INSERT INTO `role_permission` VALUES ('4339', '34', '60');
INSERT INTO `role_permission` VALUES ('4340', '34', '61');
INSERT INTO `role_permission` VALUES ('4341', '34', '63');
INSERT INTO `role_permission` VALUES ('4342', '34', '64');
INSERT INTO `role_permission` VALUES ('4343', '34', '65');
INSERT INTO `role_permission` VALUES ('4344', '34', '66');
INSERT INTO `role_permission` VALUES ('4345', '34', '67');
INSERT INTO `role_permission` VALUES ('4346', '34', '68');
INSERT INTO `role_permission` VALUES ('4347', '34', '69');
INSERT INTO `role_permission` VALUES ('4348', '34', '70');
INSERT INTO `role_permission` VALUES ('4349', '34', '71');
INSERT INTO `role_permission` VALUES ('4350', '34', '72');
INSERT INTO `role_permission` VALUES ('4351', '34', '73');
INSERT INTO `role_permission` VALUES ('4352', '34', '74');
INSERT INTO `role_permission` VALUES ('4353', '34', '75');
INSERT INTO `role_permission` VALUES ('4354', '34', '76');
INSERT INTO `role_permission` VALUES ('4355', '34', '77');
INSERT INTO `role_permission` VALUES ('4356', '34', '78');
INSERT INTO `role_permission` VALUES ('4357', '34', '79');
INSERT INTO `role_permission` VALUES ('4358', '34', '80');
INSERT INTO `role_permission` VALUES ('4359', '34', '81');
INSERT INTO `role_permission` VALUES ('4360', '34', '82');
INSERT INTO `role_permission` VALUES ('4361', '34', '83');
INSERT INTO `role_permission` VALUES ('4362', '34', '84');
INSERT INTO `role_permission` VALUES ('4363', '34', '85');
INSERT INTO `role_permission` VALUES ('4364', '34', '86');
INSERT INTO `role_permission` VALUES ('4365', '34', '87');
INSERT INTO `role_permission` VALUES ('4366', '34', '88');
INSERT INTO `role_permission` VALUES ('4367', '34', '89');
INSERT INTO `role_permission` VALUES ('4368', '34', '90');
INSERT INTO `role_permission` VALUES ('4369', '34', '91');
INSERT INTO `role_permission` VALUES ('4370', '34', '92');
INSERT INTO `role_permission` VALUES ('4371', '34', '93');
INSERT INTO `role_permission` VALUES ('4372', '34', '94');
INSERT INTO `role_permission` VALUES ('4373', '34', '98');
INSERT INTO `role_permission` VALUES ('4374', '34', '99');
INSERT INTO `role_permission` VALUES ('4375', '34', '100');
INSERT INTO `role_permission` VALUES ('4376', '34', '101');
INSERT INTO `role_permission` VALUES ('4377', '34', '103');
INSERT INTO `role_permission` VALUES ('4378', '34', '102');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `seller_brand` varchar(255) DEFAULT NULL COMMENT '出卖方所属品牌/厂商',
  `seller_name` varchar(255) DEFAULT NULL COMMENT '出卖方名称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '简称',
  `seller_type` int(11) DEFAULT NULL COMMENT '企业类型\n1 - 有限责任公司\n2 - 股份制公司\n3 - 集团公司\n4 - 一人制公司',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `found_date` datetime DEFAULT NULL COMMENT '成立日期',
  `reg_capital` int(11) DEFAULT NULL COMMENT '注册资本',
  `seller_postcode` varchar(45) DEFAULT NULL COMMENT '出卖方邮编',
  `buyback_type` int(11) DEFAULT NULL COMMENT '回购类型\n1 - 回购\n2 - 不回购',
  `org_code` varchar(255) DEFAULT NULL COMMENT '组织机构代码号',
  `seller_address` varchar(1000) DEFAULT NULL COMMENT '出卖方地址',
  `seller_industry` varchar(50) DEFAULT NULL COMMENT '所属行业',
  `partnership_expire` datetime DEFAULT NULL COMMENT '合作有效期 (无逻辑控制)',
  `seller_nature` varchar(50) DEFAULT NULL COMMENT '企业性质\n1 - 私企\n2 - 国企\n3 - 外企\n4 - 外商合资',
  `seller_fax` varchar(45) DEFAULT NULL COMMENT '出卖方传真',
  `license` varchar(255) DEFAULT NULL COMMENT '营业执照注册号',
  `expire_date` datetime DEFAULT NULL COMMENT '营业执照有效期',
  `tax_num` varchar(255) DEFAULT NULL COMMENT '税务登记号',
  `scope` varchar(500) DEFAULT NULL COMMENT '经营范围',
  `reg_address` varchar(500) DEFAULT NULL COMMENT '注册地址',
  `area` varchar(50) DEFAULT NULL COMMENT '区域',
  `seller_website` varchar(500) DEFAULT NULL COMMENT '出卖方网址',
  `real_capital` int(11) DEFAULT NULL COMMENT '实收资本',
  `legal_name` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `legal_id` varchar(255) DEFAULT NULL COMMENT '法人身份证号',
  `legal_phone` varchar(255) DEFAULT NULL COMMENT '法人办公电话',
  `legal_address` varchar(500) DEFAULT NULL COMMENT '法人住址',
  `legal_mobile1` varchar(255) DEFAULT NULL COMMENT '法人手机1',
  `legal_email` varchar(255) DEFAULT NULL COMMENT '法人邮箱',
  `legal_mobile2` varchar(255) DEFAULT NULL COMMENT '法人手机2',
  `poa_name` varchar(255) DEFAULT NULL COMMENT '委托代理人',
  `poa_id` varchar(255) DEFAULT NULL COMMENT '委托代理人身份证号',
  `poa_phone` varchar(255) DEFAULT NULL COMMENT '委托代理人办公电话',
  `poa_address` varchar(1000) DEFAULT NULL COMMENT '委托代理人住址',
  `poa_mobile1` varchar(255) DEFAULT NULL COMMENT '委托代理人手机1',
  `poa_email` varchar(255) DEFAULT NULL COMMENT '委托代理人邮箱',
  `poa_mobile2` varchar(255) DEFAULT NULL COMMENT '委托代理人手机2',
  `u_contact_name` varchar(255) DEFAULT NULL COMMENT '紧急联系人',
  `u_contact_id` varchar(255) DEFAULT NULL COMMENT '紧急联系人身份证号',
  `u_contact_phone` varchar(255) DEFAULT NULL COMMENT '紧急联系人办公电话',
  `u_contact_address` varchar(1000) DEFAULT NULL COMMENT '紧急联系人地址',
  `u_contact_mobile1` varchar(255) DEFAULT NULL COMMENT '紧急联系人手机1',
  `u_contact_email` varchar(255) DEFAULT NULL COMMENT '紧急联系人邮箱',
  `u_contact_mobile2` varchar(255) DEFAULT NULL COMMENT '紧急联系人手机2',
  `u_contact_position` varchar(255) DEFAULT NULL COMMENT '紧急联系人职务',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '合作状态\n0 - 合作中\n1 - 已终止',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `credit_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
  `business_code_type` int(11) DEFAULT NULL COMMENT '社会代码类型\n0 - 普通\n1 - 三证合一',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `license_copy_url` varchar(255) DEFAULT NULL COMMENT '营业执照复印件',
  `legal_id_front_url` varchar(255) DEFAULT NULL COMMENT '法人代表身份证正面复印件',
  `legal_id_back_url` varchar(255) DEFAULT NULL COMMENT '法人代表身份证正面复印件',
  `agreement_url` varchar(255) DEFAULT NULL COMMENT '合作协议复印件',
  `other_attachment_url` varchar(255) DEFAULT NULL COMMENT '其他附件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('1', '上海大众', '大众静安寺经销商', '大众静安寺', '1', null, '2016-01-01 00:00:00', '1000', null, null, null, '上海静安寺', null, null, '1', null, null, '2020-12-31 00:00:00', null, '各种大众汽车', null, null, null, null, '林先生', '310320197902183456', null, null, null, null, null, '林先生', '310320197902183456', null, null, '13874663663', null, null, '王先生', '310320197902183456', null, null, '13572663611', null, null, null, null, '0', '0', null, 'BDC13984y7y7112334', '1', '34', '2016-09-20 14:40:37', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for seller_bank
-- ----------------------------
DROP TABLE IF EXISTS `seller_bank`;
CREATE TABLE `seller_bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `seller_id` bigint(20) NOT NULL COMMENT '出卖方系统编码',
  `bank_name` varchar(500) DEFAULT NULL COMMENT '出卖方开户银行',
  `bank_account` varchar(500) DEFAULT NULL COMMENT '出卖方银行账户',
  `bank_address` varchar(500) DEFAULT NULL COMMENT '出卖方银行地址',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\n0 - 正常\n1 - 已逻辑删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller_bank
-- ----------------------------
INSERT INTO `seller_bank` VALUES ('1', '1', '招商银行', '44001238387474444', '上海上海', '0', '34', '2016-09-20 14:40:37', null, null);

-- ----------------------------
-- Table structure for sys_element
-- ----------------------------
DROP TABLE IF EXISTS `sys_element`;
CREATE TABLE `sys_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统编码',
  `ELE_GROUP_ID` bigint(20) DEFAULT NULL COMMENT '字典组ID',
  `ELE_CODE` varchar(40) DEFAULT NULL COMMENT '元素代码',
  `ELE_NAME` varchar(100) DEFAULT NULL COMMENT '元素名称',
  `ELE_KEY` varchar(100) DEFAULT NULL COMMENT '元素值',
  `ORDER_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `STATUS_FLAG` varchar(10) DEFAULT '' COMMENT '状态',
  `CRT_USER` bigint(20) DEFAULT NULL COMMENT '创建人用户编码',
  `CRT_DATETIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPD_USER` bigint(20) DEFAULT NULL COMMENT '修改用户编码',
  `delete_flag` int(20) unsigned zerofill DEFAULT NULL COMMENT '删除',
  `UPD_DATETIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_element
-- ----------------------------
INSERT INTO `sys_element` VALUES ('1', '1', '1', '男性', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('2', '1', '2', '女性', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('3', '2', '0', '分公司', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('4', '2', '1', '总公司', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('5', '3', '0', '否', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('6', '3', '1', '是', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('7', '4', '1', '总行', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('8', '4', '2', '分行', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('9', '4', '3', '支行', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('10', '5', '1', '有限责任公司', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('11', '5', '2', '股份制公司', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('12', '5', '3', '集团公司', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('13', '5', '4', '一人制公司', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('14', '6', '1', '私企', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('15', '6', '2', '国企', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('16', '6', '3', '外企', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('17', '6', '4', '外商合资', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('18', '7', '1', '是', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('19', '7', '2', '否', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('20', '8', '0', '合作中', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('21', '8', '1', '已终止', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('22', '9', '1', '承租人编号', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('23', '9', '2', '融资租赁合同编号', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('24', '10', '1', '是', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('25', '10', '2', '否', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('26', '11', '1', '华东区', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('27', '11', '2', '华南区', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('28', '11', '3', '华北区', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('29', '12', '1', '直租', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('30', '12', '2', '售后回租', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('31', '13', '1', '车辆', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('32', '13', '2', '票据', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('33', '14', '1', '月', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('34', '15', '1', '期初还款', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('35', '15', '2', '期末还款', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('36', '16', '1', '按比例收取', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('37', '16', '2', '按金额收取', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('38', '17', '1', '按比例收取', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('39', '17', '2', '按金额收取', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('40', '18', '1', '按比例收取', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('41', '18', '2', '按金额收取', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('42', '19', '1', '固定利率', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('43', '19', '2', '浮动利率', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('44', '20', '1', '期末退回', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('45', '20', '2', '期末冲抵', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('46', '21', '1', '是', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('47', '21', '0', '否', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('48', '22', '1', '等额本金', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('49', '22', '2', '等额本息', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('50', '23', '1', '先抵押后放款', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('51', '23', '2', '先放款后抵押', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('52', '24', '1', '留购', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('53', '24', '2', '续租', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('54', '24', '3', '退回', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('55', '25', '1', '基本账号', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('56', '25', '2', '一般账号', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('57', '26', '1', '印花费', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('58', '26', '2', '鉴定费', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('59', '26', '3', '数据字典', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('60', '27', '0', '资信未提交', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('61', '27', '1', '资信审核中', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('62', '27', '2', '资信审核通过', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('63', '27', '3', '资信审核不通过', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('64', '28', '1', '自然人', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('65', '28', '2', '法人', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('66', '29', '1', '身份证', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('67', '29', '2', '护照', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('68', '30', '1', '未婚', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('69', '30', '2', '已婚', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('70', '30', '3', '离异', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('71', '30', '4', '丧偶', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('72', '31', '1', '小学', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('73', '31', '2', '初中', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('74', '31', '3', '高中', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('75', '31', '4', '大学', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('76', '31', '5', '研究生', '', '5', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('77', '31', '6', '博士', '', '6', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('78', '31', '7', '博士后', '', '7', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('79', '32', '1', '设备抵押', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('80', '32', '2', '应收账款质押', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('81', '32', '3', '股东无限连带责任担保', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('82', '32', '4', '承租企业连带担保', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('83', '32', '5', '第三方连带责任担保', '', '5', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('84', '33', '1', '担保中', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('85', '33', '2', '已解除', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('86', '34', '0', '未签订', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('87', '34', '1', '已签订', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('88', '34', '2', '已完成', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('89', '34', '3', '已终止', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('90', '35', '0', '意向客户', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('91', '35', '1', '正式客户', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('92', '36', '1', '租房', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('93', '36', '2', '自有房', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('94', '37', '1', '农村', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('95', '37', '2', '城镇', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('96', '38', '1', '工程师', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('97', '38', '2', '高层', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('98', '38', '3', '管理者', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('99', '39', '1', '同家庭地址', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('100', '39', '2', '同单位地址', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('101', '39', '3', '新地址', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('102', '40', '0', '正常', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('103', '40', '1', '已展期', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('104', '40', '2', '提前结清', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('105', '40', '3', '到期结清', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('106', '40', '4', '回购', '', '5', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('107', '41', '0', '人工录入', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('108', '41', '1', '代扣', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('109', '41', '2', '批量录入', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('110', '41', '3', '其他', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('111', '42', '0', '汇款单', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('112', '42', '1', '现金收据', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('113', '42', '2', '银行回执单', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('114', '43', '0', '未认款', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('115', '43', '1', '已认款/未分解', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('116', '43', '2', '分解已提交', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('117', '43', '3', '分解已通过', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('118', '43', '4', '分解已驳回', '', '5', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('119', '43', '5', '核销作废已提交', '', '6', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('120', '43', '6', '核销作废已通过', '', '7', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('121', '43', '7', '核销作废已驳回', '', '8', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('122', '43', '8', '冲红已提交', '', '9', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('123', '43', '9', '冲红已通过', '', '10', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('124', '43', '10', '冲红已驳回', '', '11', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('125', '44', '0', '未认款', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('126', '44', '1', '已认款/未分解', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('127', '44', '2', '分解已提交', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('128', '44', '3', '分解已通过', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('129', '44', '4', '分解已驳回', '', '5', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('130', '44', '5', '核销作废已提交', '', '6', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('131', '44', '6', '核销作废已通过', '', '7', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('132', '44', '7', '核销作废已驳回', '', '8', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('133', '44', '8', '冲红已提交', '', '9', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('134', '44', '9', '冲红已通过', '', '10', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('135', '44', '10', '冲红已驳回', '', '11', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('136', '45', '1', '审核通过', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('137', '45', '2', '审核不通过', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('138', '46', '-1', '未申请', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('139', '46', '0', '付款审核中', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('140', '46', '1', '审核通过', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('141', '46', '2', '审核不通过', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('142', '46', '3', '已付款', '', '5', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('143', '47', '0', '公务卡', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('144', '47', '1', '电汇', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('145', '47', '2', '现金', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('146', '47', '3', '支票', '', '4', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('147', '48', '0', '是', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('148', '48', '1', '否', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('149', '49', '0', '未转移', '', '1', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('150', '49', '1', '已转移', '', '2', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('165', '25', '3', '临时账号', '', '3', '0', null, null, null, null, null);
INSERT INTO `sys_element` VALUES ('166', '25', '4', '专用账号', '', '4', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_element_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_element_group`;
CREATE TABLE `sys_element_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ELE_GROUP_CODE` varchar(50) DEFAULT NULL COMMENT '字典组代码',
  `ELE_GROUP_NAME` varchar(100) DEFAULT NULL COMMENT '字典组名称',
  `STATUS_FLAG` varchar(10) DEFAULT NULL COMMENT '状态',
  `CRT_USER` bigint(20) DEFAULT NULL COMMENT '创建人用户编码',
  `CRT_DATETIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPD_USER` bigint(20) DEFAULT NULL COMMENT '修改用户编码',
  `UPD_DATETIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_element_group
-- ----------------------------
INSERT INTO `sys_element_group` VALUES ('1', null, '性别', '0', '21', '2016-09-07 00:43:07', '21', '2016-09-07 00:43:07');
INSERT INTO `sys_element_group` VALUES ('2', null, '公司级别', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('3', null, '三证合一', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('4', null, '银行类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('5', null, '出卖方类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('6', null, '企业性质', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('7', null, '是否回购', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('8', null, '合作状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('9', '21', '编码管理类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('10', null, '每年重置', '0', '21', '2016-09-06 22:45:23', '21', '2016-09-06 22:45:23');
INSERT INTO `sys_element_group` VALUES ('11', null, '大区', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('12', null, '业务类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('13', null, '租赁物类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('14', null, '租赁周期', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('15', null, '支付方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('16', null, '保证金计算方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('17', null, '首期租金计算方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('18', null, '手续费计算方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('19', null, '利率方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('20', null, '保证金处理方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('21', null, '担保人必须', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('22', null, '试算方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('23', null, '放款规则', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('24', null, '期满处理方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('25', null, '账号类型', '0', '1', '2016-09-07 10:43:05', '1', '2016-09-07 10:43:05');
INSERT INTO `sys_element_group` VALUES ('26', null, '费用类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('27', null, '资信状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('28', null, '承租人类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('29', null, '证件类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('30', null, '婚姻状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('31', null, '文化程度', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('32', null, '担保类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('33', null, '担保状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('34', null, '合同状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('35', null, '承租人状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('36', null, '居住类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('37', null, '户口类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('38', null, '职业类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('39', null, '通讯地址', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('40', null, '租金表状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('41', null, '来款方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('42', null, '凭证类型', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('43', null, '认款状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('44', null, '资金状态', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('45', null, '审核信息', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('46', null, '付款状态', '0', '1', '2016-09-07 11:10:53', '1', '2016-09-07 11:10:53');
INSERT INTO `sys_element_group` VALUES ('47', null, '付款方式', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('48', null, '是否加急', '0', null, null, null, null);
INSERT INTO `sys_element_group` VALUES ('49', null, '使用权转移状态', '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编码',
  `rolename` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `updater` bigint(20) DEFAULT NULL,
  `rolecode` varchar(30) DEFAULT NULL COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统', null, '2016-09-06 17:59:49', null, '1', 'ADMIN');
INSERT INTO `sys_role` VALUES ('34', '融资租赁公司管理员', '2016-09-20 14:13:24', '2016-09-20 14:13:24', '1', '1', 'RZZL-ADMIN');
INSERT INTO `sys_role` VALUES ('35', '融资融资公司客户代表', '2016-09-20 14:50:29', '2016-09-20 16:53:41', '34', '34', 'RLZL-REP');
INSERT INTO `sys_role` VALUES ('36', '客户经理', '2016-09-20 16:43:01', '2016-09-20 16:53:51', '34', '34', 'RZZL-REP-M');
INSERT INTO `sys_role` VALUES ('37', '财务会计-资金录入', '2016-09-20 16:46:54', '2016-09-20 17:33:04', '34', '34', 'RZZL-FIN-1');
INSERT INTO `sys_role` VALUES ('38', '财务-认款', '2016-09-20 17:33:21', '2016-09-20 17:33:21', '34', '34', 'RZZL-FIN-2');
INSERT INTO `sys_role` VALUES ('39', '财务-核销申请', '2016-09-20 17:33:46', '2016-09-20 17:34:33', '34', '34', 'RZZL-FIN-3');
INSERT INTO `sys_role` VALUES ('40', '财务-核销审核', '2016-09-20 17:34:21', '2016-09-20 17:34:21', '34', '34', 'RZZL-FIN-4');
INSERT INTO `sys_role` VALUES ('41', '财务-付款申请', '2016-09-20 17:35:45', '2016-09-20 17:35:45', '34', '34', 'RZZL-FIN-5');
INSERT INTO `sys_role` VALUES ('42', '财务-付款审核', '2016-09-20 17:36:14', '2016-09-20 17:36:22', '34', '34', 'RZZL-FIN-6');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编码',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `gender` int(11) DEFAULT NULL COMMENT '用户性别\n1 - 男\n2 - 女',
  `email` varchar(100) DEFAULT NULL COMMENT '邮件地址',
  `realname` varchar(30) DEFAULT NULL COMMENT '用户真实姓名',
  `activestatus` tinyint(4) DEFAULT NULL COMMENT '激活状态\n0：停用  \n1：启用',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `company_id` bigint(20) DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '19ee1f75b9174f927d79c73f810e173f5305d33b', '13999999989', '1', '55@66.com', '超级管理员', '1', null, '2016-10-21 17:34:15', null, '1', '1');
INSERT INTO `sys_user` VALUES ('34', 'rjhtadmin', 'c0c5f97727ead6576fc55636950bfb69c8fee791', '13711133341', '1', null, '仁聚汇通管理员', '1', '2016-09-20 14:32:01', '2016-09-20 14:32:24', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('35', 'clientrep', '530c7e04707c91d9a2e721a21d1878295ee86636', '13847747479', '1', null, '客户代表', '1', '2016-09-20 16:52:28', '2016-09-20 16:52:28', '34', '34', '1');
INSERT INTO `sys_user` VALUES ('36', 'clientmgr', '62141f9722963579ee270d7ab4533fc1653f8aef', '13837711223', '1', null, '客户经理', '1', '2016-09-20 17:47:29', '2016-09-20 17:47:29', '34', '34', '1');
INSERT INTO `sys_user` VALUES ('37', 'fin-5', '8280a2df7d114775655ee3e2f2e0c816b204a412', '13874747122', '1', null, '付款申请人', '1', '2016-09-20 17:48:05', '2016-09-20 17:48:05', '34', '34', '1');
INSERT INTO `sys_user` VALUES ('38', 'fin-6', '2757174b3c1a4a6affe8cbe0a9b4498c7309bc66', '13848747111', '2', null, '付款审核人', '1', '2016-09-20 17:48:25', '2016-09-20 17:48:25', '34', '34', '1');

-- ----------------------------
-- Table structure for sys_userrole
-- ----------------------------
DROP TABLE IF EXISTS `sys_userrole`;
CREATE TABLE `sys_userrole` (
  `user_id` bigint(20) NOT NULL COMMENT '主键  用户编号',
  `role_id` bigint(20) NOT NULL COMMENT '主键  角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_userrole
-- ----------------------------
INSERT INTO `sys_userrole` VALUES ('1', '1');
INSERT INTO `sys_userrole` VALUES ('34', '34');
INSERT INTO `sys_userrole` VALUES ('35', '35');
INSERT INTO `sys_userrole` VALUES ('36', '36');
INSERT INTO `sys_userrole` VALUES ('37', '41');
INSERT INTO `sys_userrole` VALUES ('38', '42');

-- ----------------------------
-- Procedure structure for leasing_overdue_update_proc
-- ----------------------------
DROP PROCEDURE IF EXISTS `leasing_overdue_update_proc`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `leasing_overdue_update_proc`(IN `t_date` timestamp,OUT `success` int)
BEGIN
-- 时间输入格式：'yyy-MM-dd',需带引号

	DECLARE t_error INTEGER DEFAULT 0;   -- 事务回滚标识位
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;     

	START TRANSACTION; 

	SELECT COUNT(*) INTO @leasingNum FROM leasing_overdue;

	IF @leasingNum != 0 THEN
		UPDATE leasing_overdue SET delete_flag = '1';
	END IF;

	INSERT INTO leasing_overdue(
		create_time,modify_time,overdue_rent,default_interest,overdue_days,
		leasing_contract_id,leasing_request_id,lessee_id,creator,modifier,delete_flag,lessee_name,lessee_contact
	)
	SELECT 
		t_date,t_date,
	-- ro.overdue_rent,ro.default_interest,ro.overdue_days,
		SUM(ro.overdue_rent),SUM(ro.default_interest),MAX(ro.overdue_days),
		ro.leasing_contract_id,
		lr.id,
		ro.lessee_id,
		ro.creator,
		ro.modifier,
		0,
		ro.lessee_name,
		ro.lessee_contact
	FROM rent_overdue ro 
	LEFT JOIN leasing_contract lc ON lc.id = ro.leasing_contract_id
	LEFT JOIN leasing_request lr ON lc.leasing_request_id = lr.id
	LEFT JOIN (SELECT MAX(id) as id FROM leasing_overdue GROUP BY leasing_contract_id ) ro_notDel
	ON (ro_notDel.id = ro.id)
	WHERE ro.delete_flag=0
	GROUP BY ro.leasing_contract_id;

	IF t_error = 1 THEN  
       ROLLBACK;  
  ELSE  
       COMMIT;  
  END IF;  
  SELECT t_error INTO success;-- 返回标识位的结果0为正常提交，1为失败回滚；


END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for rent_overdue_update_proc
-- ----------------------------
DROP PROCEDURE IF EXISTS `rent_overdue_update_proc`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `rent_overdue_update_proc`(IN `t_date` timestamp,OUT `success` int)
BEGIN
-- 时间输入格式：'yyy-MM-dd',需带引号 

	DECLARE t_error INTEGER DEFAULT 0;   -- 事务回滚标识位
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;     

	START TRANSACTION; 

	SELECT COUNT(id) INTO @retNum FROM rent_overdue;
	IF @retNum != 0 THEN UPDATE rent_overdue SET delete_flag = '1';
	END IF;

	INSERT INTO rent_overdue(rent_id,create_time,modify_time,default_interest,default_interest_received,default_interest_remained,overdue_rent,overdue_total,overdue_days,
		lessee_id,lessee_name,lessee_contact,rent_code,leasing_contract_id,delete_flag,creator,modifier)
	SELECT 
		r.rent_id,
		t_date,
		t_date,
		SUM(r.default_interest), -- 应收罚息
		SUM(r.default_interest_received), -- 实收罚息
		CASE WHEN r.exempt_amount IS NULL THEN SUM(r.default_interest_remained) ELSE SUM(r.default_interest_remained-r.exempt_amount) END as default_interest_remained, -- 剩余罚息
		SUM(r.overdue_rent), -- 逾期租金
		CASE WHEN r.exempt_amount IS NULL THEN SUM(r.overdue_rent+r.default_interest_remained) ELSE SUM(r.overdue_rent+r.default_interest_remained-r.exempt_amount) END as overdue_total, -- 逾期总金额
		DATEDIFF(t_date,MIN(r.payment_date)) as overdue_days, -- 逾期天数
		lr.lessee_id, -- 承租人编号
		CASE WHEN l.type = 1 THEN l.name WHEN l.type = 2 THEN l.corp_name ELSE NULL END, -- 承租人名称
		CASE WHEN l.type = 1 THEN l.mobile WHEN l.type = 2 THEN l.corp_contract_phone ELSE NULL END, -- 承租人联系电话
		r0.rent_code, -- 租金表号
		lc.id as leasing_contract_id, -- 租赁合同编码
		0, -- 删除标记
		1, -- 创建者
		1 -- 修改者
	FROM rent_period r 
	-- LEFT JOIN rent_overdue ro on r.rent_id = ro.rent_id
	-- LEFT JOIN (SELECT MAX(id) as id FROM rent_overdue GROUP BY rent_id,leasing_contract_id ) ro_notDel ON (ro_notDel.id = ro.id)
	LEFT JOIN rent r0 ON r0.id = r.rent_id
	LEFT JOIN rent_master rm ON rm.id = r0.rent_master_id
	LEFT JOIN leasing_request lr ON lr.id = rm.leasing_request_id
	LEFT JOIN leasing_contract lc ON lc.leasing_request_id = lr.id
	LEFT JOIN lessee l ON l.id = lr.lessee_id
	WHERE r.payment_date<t_date
	AND (r.principal_remained+r.interest_remained+r.default_interest_remained)>0
	GROUP BY r.rent_id;

	IF t_error = 1 THEN  
       ROLLBACK;  
  ELSE  
       COMMIT;  
  END IF;  
  SELECT t_error INTO success;-- 返回标识位的结果0为正常提交，1为失败回滚；

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for rent_period_update_proc
-- ----------------------------
DROP PROCEDURE IF EXISTS `rent_period_update_proc`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `rent_period_update_proc`(IN `t_date` timestamp,OUT `success` int)
BEGIN
-- 时间输入格式：'yyy-MM-dd',需带引号

	DECLARE t_error INTEGER DEFAULT 0;  -- 事务回滚标识位
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;  
  
  START TRANSACTION; 
                    
	SELECT s.ELE_NAME INTO @percentage FROM sys_element s WHERE s.ELE_GROUP_ID = '57' AND s.ELE_CODE = '0';

	UPDATE rent_period r 
	SET r.default_interest = r.default_interest+(CASE WHEN r.exempt_amount IS NULL THEN r.principal_remained+r.interest_remained+r.default_interest_remained ELSE r.principal_remained+r.interest_remained+r.default_interest_remained-r.exempt_amount END)*@percentage,
			r.default_interest_remained = r.default_interest_remained+(CASE WHEN r.exempt_amount IS NULL THEN r.principal_remained+r.interest_remained+r.default_interest_remained ELSE r.principal_remained+r.interest_remained+r.default_interest_remained-r.exempt_amount END)*@percentage;

	UPDATE rent_period r 
	INNER JOIN (SELECT id ,CASE WHEN payment_date<t_date THEN SUM(principal_remained+interest_remained) ELSE 0 END AS overdue_rent,CASE WHEN payment_date<t_date THEN DATEDIFF(t_date,MIN(payment_date)) ELSE 0 END AS overdue_days FROM rent_period GROUP BY id) r1 
	ON r.id = r1.id
	SET r.overdue_rent = CASE WHEN (r.principal_remained+r.interest_remained+r.default_interest_remained)>0 THEN r1.overdue_rent ELSE 0 END,
			r.overdue_days = CASE WHEN (r.principal_remained+r.interest_remained+r.default_interest_remained)>0 THEN r1.overdue_days ELSE 0 END;

	IF t_error = 1 THEN  
      ROLLBACK;  
  ELSE  
      COMMIT;  
  END IF;  
  SELECT t_error INTO success;   -- 返回标识位的结果0为正常提交，1为失败回滚；

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for test_pram_out
-- ----------------------------
DROP PROCEDURE IF EXISTS `test_pram_out`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `test_pram_out`(IN `t_date` timestamp,OUT `success` varchar(10))
BEGIN
	
-- 事务回滚测试案例 
-- 参数规则  第一参数为时间，第二参数为接收是否成功的值：'2020-2-2',@receivedStr

	DECLARE t_error INTEGER DEFAULT 0;   -- 事务回滚标识位
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;     

	START TRANSACTION; 

	INSERT INTO bank (id) VALUES('361'); -- 成功
	INSERT INTO bank (id) VALUES('36'); -- 失败，已存在id为36的记录

	IF t_error = 1 THEN  
       ROLLBACK;  -- 回滚
  ELSE  
       COMMIT;  -- 提交
  END IF;  
  
	SELECT t_error INTO success; -- 返回标识位的结果0为正常提交，1为失败回滚；
	
	IF success = 1 THEN  
       SELECT success,'已回滚事务'; 
  ELSE  
       SELECT success,'已提交事务'; 
  END IF; 
	


END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for test_pram_out_copy
-- ----------------------------
DROP PROCEDURE IF EXISTS `test_pram_out_copy`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `test_pram_out_copy`(IN `t_date` timestamp,OUT `success` varchar(10))
BEGIN
	
-- 事务回滚测试案例 
-- 参数规则  第一参数为时间，第二参数为接收是否成功的值：'2020-2-2',@receivedStr

	DECLARE t_error INTEGER DEFAULT 0;   -- 事务回滚标识位
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;     

	START TRANSACTION; 

	INSERT INTO bank (id) VALUES('361'); -- 成功
	INSERT INTO bank (id) VALUES('36'); -- 失败，已存在id为36的记录

	IF t_error = 1 THEN  
       ROLLBACK;  -- 回滚
  ELSE  
       COMMIT;  -- 提交
  END IF;  
  
	SELECT t_error INTO success; -- 返回标识位的结果0为正常提交，1为失败回滚；
	
	IF success = 1 THEN  
       SELECT success,'已回滚事务'; 
  ELSE  
       SELECT success,'已提交事务'; 
  END IF; 
	


END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for test_sqlexception_handler
-- ----------------------------
DROP PROCEDURE IF EXISTS `test_sqlexception_handler`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `test_sqlexception_handler`()
BEGIN
	
-- 事务回滚测试案例

	DECLARE t_error INTEGER DEFAULT 0;   -- 事务回滚标识位
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;     

	START TRANSACTION; 

	INSERT INTO bank (id) VALUES('361'); -- 成功
	INSERT INTO bank (id) VALUES('36'); -- 失败，已存在id为36的记录

	IF t_error = 1 THEN  
       ROLLBACK;  -- 回滚
  ELSE  
       COMMIT;  -- 提交
  END IF;  
  SELECT t_error;-- 返回标识位的结果0为正常提交，1为失败回滚；
END
;;
DELIMITER ;
