DROP INDEX `IDX_BIZOBJ_PARENTID` ON `ymyx_bizobject`;

DROP INDEX `IDX_BA_BOID` ON `ymyx_bizobj_authobj_link`;

DROP INDEX `IDX_BA_AOID` ON `ymyx_bizobj_authobj_link`;

DROP INDEX `IDX_BIZOP_BIZOBJID` ON `ymyx_biz_operation`;

DROP INDEX `IDX_DATAINDEX_OBJID` ON `ymyx_data_index`;

DROP INDEX `IDX_DATA_ITME_OBJID` ON `ymyx_data_item`;

DROP INDEX `IDX_EMPLOYEE_ORGID` ON `ymyx_employee`;

DROP INDEX `IDX_EMPLOYEE_DEPTID` ON `ymyx_employee`;

DROP INDEX `IDX_EMPLOYEE_EMPCODE` ON `ymyx_employee`;

DROP INDEX `IDX_FUNC_PARENT_ID` ON `ymyx_function`;

DROP INDEX `IDX_FUNC_BIZOBJID` ON `ymyx_function`;

DROP INDEX `IDX_YMYX_ORGAN_CODE` ON `ymyx_organ`;

DROP INDEX `IDX_YMYX_ORGAN_PARENT` ON `ymyx_organ`;

DROP INDEX `IDX_RA_ROLEID` ON `ymyx_role_auth`;

DROP INDEX `IDX_RA_OPID` ON `ymyx_role_auth`;

DROP INDEX `IDX_YMYX_USER_USERNAME` ON `ymyx_user`;

DROP INDEX `IDX_YMYX_USER_ORGID` ON `ymyx_user`;

DROP INDEX `IDX_YMYX_USER_CODE` ON `ymyx_user`;

DROP INDEX `IDX_UA_USERID` ON `ymyx_user_auth`;

DROP INDEX `IDX_UA_POSID` ON `ymyx_user_auth`;

DROP INDEX `IDX_POSITION_ORG_LINK_POSID` ON `ymyx_position_org_link `;

DROP INDEX `IDX_POSITION_ORG_LINK_ORGID` ON `ymyx_position_org_link `;



ALTER TABLE `ymyx_auth_obj`DROP PRIMARY KEY;

ALTER TABLE `ymyx_bizobject`DROP PRIMARY KEY;

ALTER TABLE `ymyx_bizobj_authobj_link`DROP PRIMARY KEY;

ALTER TABLE `ymyx_bizobj_entry_auth_obj`DROP PRIMARY KEY;

ALTER TABLE `ymyx_biz_operation`DROP PRIMARY KEY;

ALTER TABLE `ymyx_biz_oper_type`DROP PRIMARY KEY;

ALTER TABLE `ymyx_custom_user_menu`DROP PRIMARY KEY;

ALTER TABLE `ymyx_data_index`DROP PRIMARY KEY;

ALTER TABLE `ymyx_data_item`DROP PRIMARY KEY;

ALTER TABLE `ymyx_data_model`DROP PRIMARY KEY;

ALTER TABLE `ymyx_data_object`DROP PRIMARY KEY;

ALTER TABLE `ymyx_employee`DROP PRIMARY KEY;

ALTER TABLE `ymyx_function`DROP PRIMARY KEY;

ALTER TABLE `ymyx_organ`DROP PRIMARY KEY;

ALTER TABLE `ymyx_organ_type`DROP PRIMARY KEY;

ALTER TABLE `ymyx_position`DROP PRIMARY KEY;

ALTER TABLE `ymyx_position_auth`DROP PRIMARY KEY;

ALTER TABLE `ymyx_role`DROP PRIMARY KEY;

ALTER TABLE `ymyx_role_auth`DROP PRIMARY KEY;

ALTER TABLE `ymyx_user`DROP PRIMARY KEY;

ALTER TABLE `ymyx_user_auth`DROP PRIMARY KEY;

ALTER TABLE `ymyx_user_type`DROP PRIMARY KEY;

ALTER TABLE `ymyx_position_org_link `DROP PRIMARY KEY;



DROP TABLE `ymyx_auth_obj` CASCADE;

DROP TABLE `ymyx_bizobject` CASCADE;

DROP TABLE `ymyx_bizobj_authobj_link` CASCADE;

DROP TABLE `ymyx_bizobj_entry_auth_obj` CASCADE;

DROP TABLE `ymyx_biz_operation` CASCADE;

DROP TABLE `ymyx_biz_oper_type` CASCADE;

DROP TABLE `ymyx_custom_user_menu` CASCADE;

DROP TABLE `ymyx_data_index` CASCADE;

DROP TABLE `ymyx_data_item` CASCADE;

DROP TABLE `ymyx_data_model` CASCADE;

DROP TABLE `ymyx_data_object` CASCADE;

DROP TABLE `ymyx_employee` CASCADE;

DROP TABLE `ymyx_function` CASCADE;

DROP TABLE `ymyx_organ` CASCADE;

DROP TABLE `ymyx_organ_type` CASCADE;

DROP TABLE `ymyx_position` CASCADE;

DROP TABLE `ymyx_position_auth` CASCADE;

DROP TABLE `ymyx_role` CASCADE;

DROP TABLE `ymyx_role_auth` CASCADE;

DROP TABLE `ymyx_user` CASCADE;

DROP TABLE `ymyx_user_auth` CASCADE;

DROP TABLE `ymyx_user_type` CASCADE;

DROP TABLE `ymyx_position_org_link ` CASCADE;



CREATE TABLE `ymyx_auth_obj` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权对象编码',

`NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权对象名称',

`PARENT_ID` varchar(36) NULL COMMENT '上级节点ID',

`PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '普通每级4位共9级',

`GRADE` int(11) NOT NULL COMMENT '级数(树形显示)',

`ISDETAIL` varchar(1) NOT NULL COMMENT '是否明细',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否启用',

`TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '0:行权限;1:列权限',

`DATAMODELID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认为NULL或者\"\"，表示使用系统默认模型。否则使用指定的模型',

`YEARFLAG` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',

`PROFILE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,

`DESCRIPTION` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='数据授权对象--主要针对行数据权限'
;



CREATE TABLE `ymyx_bizobject` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',

`PARENT_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级业务对象',

`BIZOBJ_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务对象名称',

`BIZOBJ_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务对象编码',

`URL` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URI',

`GRADE` int NULL COMMENT '级数(树结构显示)',

`SEQ` int(11) NULL DEFAULT NULL COMMENT '顺序号',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',

`PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',

`DESCRIPTION` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

PRIMARY KEY (`ID`) ,

INDEX `IDX_BIZOBJ_PARENTID` (`PARENT_ID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='系统业务对象'
;



CREATE TABLE `ymyx_bizobj_authobj_link` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`BO_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`AO_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`REFELEMENTID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仅在授权对象是行授权的时有效。',

PRIMARY KEY (`ID`) ,

INDEX `IDX_BA_BOID` (`BO_ID`),

INDEX `IDX_BA_AOID` (`AO_ID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='业务对象关联的授权对象'
;



CREATE TABLE `ymyx_bizobj_entry_auth_obj` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`BIZOBJID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`BIZOPID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`AO_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='业务对象进入操作的授权对象'
;



CREATE TABLE `ymyx_biz_operation` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',

`BIZOBJ_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务对象ID',

`BIZOPER_NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务操作名称',

`BIZOPER_CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务操作编码',

`URL` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URI',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

PRIMARY KEY (`ID`) ,

INDEX `IDX_BIZOP_BIZOBJID` (`BIZOBJ_ID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='业务操作'
;



CREATE TABLE `ymyx_biz_oper_type` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',

`OP_TYPE_NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型名称',

`OP_TYPE_CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型编码',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否启用',

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='业务操作类型'
;



CREATE TABLE `ymyx_custom_user_menu` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识主键',

`USERID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户标识',

`NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',

`PATH` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分级码',

`LAYER` int(11) NOT NULL COMMENT '级数',

`ISDETAIL` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否明细',

`FUNCID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联的功能标识',

`SYSFLAG` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统预制标记',

`DESCRIPTION` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='用户自定义菜单'
;



CREATE TABLE `ymyx_data_index` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一性标识',

`OBJECTID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据对象标识符',

`INDEXPOSITION` int(11) NOT NULL COMMENT '索引位置',

`INDEXCODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '索引编码',

`INDEXCONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '索引内容',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

PRIMARY KEY (`ID`) ,

INDEX `IDX_DATAINDEX_OBJID` (`OBJECTID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='数据对象所生成的数据索引'
;



CREATE TABLE `ymyx_data_item` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据项标识符',

`OBJECTID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据对象标识符',

`ITEM_POSITION` int(11) NOT NULL COMMENT '数据项索引',

`ITEM_CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文＋\"_\"',

`ITEM_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`COMMENTS` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据项描述',

`CUSTOMDATATYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`BASEDATATYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CHANGDU` int(11) NULL DEFAULT 0 COMMENT '长度',

`JINGDU` int(11) NULL DEFAULT 0 COMMENT '精度',

`ISPRIMARY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否主键',

`ISMUST` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否必填项',

`ISUNIQUE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否唯一项',

`DEFAULTVALUE` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值',

`ISINIT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否系统初始化表',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '0:停用1:启用',

`ISCREATED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',

`DBCOLUMNCODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库列名称',

`CREATOR_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATE_TIME` datetime NULL DEFAULT NULL,

`UPDATOR_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`UPDATE_TIME` datetime NULL DEFAULT NULL,

PRIMARY KEY (`ID`) ,

INDEX `IDX_DATA_ITME_OBJID` (`OBJECTID`) COMMENT `数据项索引参照数据对象主键ID` 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='数据对象的数据项内容'
;



CREATE TABLE `ymyx_data_model` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据模型唯一性标识',

`NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`DESCRIPTION` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`VERSION` int(11) NULL DEFAULT NULL COMMENT '版本',

`CONTENTS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,

`ENABLE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATE_TIME` date NULL DEFAULT NULL,

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`UPDATE_TIME` date NULL DEFAULT NULL,

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='数据模型'
;



CREATE TABLE `ymyx_data_object` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据对象标识符',

`OBJECT_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文+\"_\",不允许重复',

`OBJECT_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '中文',

`DESCRIPTION` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`OBJECT_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1:表2:视图3:实体(JSON)',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0:不可用;1:可用',

`ISCREATED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否已创建0:未创建;1:已创建',

`VERSION` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',

`TABLE_NAME` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',

`DBTABLENAME` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库表名',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建日期',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='数据对象'
;



CREATE TABLE `ymyx_employee` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一性标识',

`ORG_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位ID',

`ORG_CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位编号',

`DEPT_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门ID',

`DEPT_CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',

`CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',

`MNEMONICS` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '助记码',

`NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',

`EMP_TYPE` varchar(2) NULL DEFAULT '0' COMMENT '员工类型0:正式员工1:外协人员',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有效',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

`NOTES` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',

PRIMARY KEY (`ID`) ,

INDEX `IDX_EMPLOYEE_ORGID` (`ORG_ID`),

INDEX `IDX_EMPLOYEE_DEPTID` (`DEPT_ID`),

INDEX `IDX_EMPLOYEE_EMPCODE` (`CODE`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='员工信息表(系统真是员工信息)'
;



CREATE TABLE `ymyx_function` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能标识',

`FUNC_CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能编码',

`FUNC_NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能名称',

`FUNC_TYPE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能类型',

`PARENT_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级功能ID',

`BIZOBJID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所对应业务对象',

`ENABLE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',

`PATH` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',

`GRADE` int NULL COMMENT '级数(树结构)',

`SEQ` int NULL COMMENT '顺序号',

`STATE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面转换状态(UI-STATE)',

`URL` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面路径',

`DESCRIPTON` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

PRIMARY KEY (`ID`) ,

INDEX `IDX_FUNC_PARENT_ID` (`PARENT_ID`),

INDEX `IDX_FUNC_BIZOBJID` (`BIZOBJID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='系统功能表--针对UI层配置的系统功能菜单信息'
;



CREATE TABLE `ymyx_organ` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一性标识',

`PARENT_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级组织ID',

`ORG_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',

`ORG_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织编码',

`SHORT_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织简称',

`ORG_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织类型',

`GRADE` int(11) NULL DEFAULT NULL COMMENT '组织级数',

`PATH` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织路径',

`SEQ` int(11) NULL DEFAULT NULL COMMENT '顺序号',

`ENBALE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',

`CREATOR_ID` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人ID',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

`NOTES` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',

PRIMARY KEY (`ID`) ,

INDEX `IDX_YMYX_ORGAN_CODE` (`ORG_CODE`),

INDEX `IDX_YMYX_ORGAN_PARENT` (`PARENT_ID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;



CREATE TABLE `ymyx_organ_type` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',

`CODE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',

`NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;



CREATE TABLE `ymyx_position` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`TYPE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`DESCRIPTION` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATE_TIME` datetime NULL DEFAULT NULL,

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`UPDATE_TIME` datetime NULL DEFAULT NULL,

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='系统岗位信息表'
;



CREATE TABLE `ymyx_position_auth` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`ORGID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`BIZYEAR` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`OWNERID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`BIZOBJID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`BIZOPID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`AO_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`AO_DATA` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`AO_SELECTTYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '01－包含;02－排斥',

`FIELDACCESSFLAG` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`ASSID1` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`ASSID2` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`ASSID3` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;



CREATE TABLE `ymyx_role` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`ROLE_NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`ROLE_CODE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATE_TIME` datetime NULL DEFAULT NULL,

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`UPDATE_TIME` datetime NULL DEFAULT NULL,

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;



CREATE TABLE `ymyx_role_auth` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',

`ROLEID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职责标识',

`BIZOPID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务操作标识',

`ENABLE` varchar(1) NULL DEFAULT '1' COMMENT '是否启用 ',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

PRIMARY KEY (`ID`) ,

INDEX `IDX_RA_ROLEID` (`ROLEID`),

INDEX `IDX_RA_OPID` (`BIZOPID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;



CREATE TABLE `ymyx_user` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一性标识',

`USERNAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名(登录用户名)',

`CODE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编码',

`PASSWORD` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',

`NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名(用户全名显示用)',

`DEFAULT_ORGID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认组织',

`USER_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型',

`ALTERPASSFLAG` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码是否修改',

`PASSVALIDTIME` datetime NULL DEFAULT NULL COMMENT '密码有效日期到',

`LOCK_TIME` datetime NULL DEFAULT NULL COMMENT '锁定时间',

`TEL` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',

`MOBILE` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '移动电话',

`WX` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',

`QQ` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',

`EMAIL` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'EMAIL',

`ADDRESS` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通信地址',

`ENABLE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 1 COMMENT '用户状态0:停用1:启用',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',

`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',

`UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',

`NOTES` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',

PRIMARY KEY (`ID`) ,

UNIQUE INDEX `IDX_YMYX_USER_USERNAME` (`USERNAME`),

INDEX `IDX_YMYX_USER_ORGID` (`DEFAULT_ORGID`),

INDEX `IDX_YMYX_USER_CODE` (`CODE`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='系统用户表'
;



CREATE TABLE `ymyx_user_auth` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',

`USER_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`POS_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATE_TIME` datetime NULL DEFAULT NULL,

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`UPDATE_TIME` datetime NULL DEFAULT NULL,

PRIMARY KEY (`ID`) ,

INDEX `IDX_UA_USERID` (`USER_ID`),

INDEX `IDX_UA_POSID` (`POS_ID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='用户授权表'
;



CREATE TABLE `ymyx_user_type` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`CODE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`NAME` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATE_TIME` date NULL DEFAULT NULL,

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`UPDATE_TIME` date NULL DEFAULT NULL,

PRIMARY KEY (`ID`) 

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
;



CREATE TABLE `ymyx_position_org_link ` (

`ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`POSITION_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`ORG_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,

`ENABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否启用',

`CREATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`CREATE_TIME` datetime NULL DEFAULT NULL,

`UPDATOR_ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,

`UPDATE_TIME` datetime NULL DEFAULT NULL,

PRIMARY KEY (`ID`) ,

INDEX `IDX_POSITION_ORG_LINK_POSID` (`POSITION_ID`),

INDEX `IDX_POSITION_ORG_LINK_ORGID` (`ORG_ID`)

)

ENGINE=InnoDB

DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

COMMENT='组织岗位范围表(岗位组织关联表)'
;



