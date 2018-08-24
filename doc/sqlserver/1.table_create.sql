/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2014                    */
/* Created on:     2018/8/19 10:16:50                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.YMMG_GROUP_POSITION_LINK') and o.name = 'FK_GRP_POST_LNK_REF_GROUP')
alter table dbo.YMMG_GROUP_POSITION_LINK
   drop constraint FK_GRP_POST_LNK_REF_GROUP
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.YMMG_GROUP_POSITION_LINK') and o.name = 'FK_GRP_POST_LNK_REF_POST')
alter table dbo.YMMG_GROUP_POSITION_LINK
   drop constraint FK_GRP_POST_LNK_REF_POST
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.YMMG_GROUP_USER_LINK') and o.name = 'FK_GRP_U_LNK_REF_')
alter table dbo.YMMG_GROUP_USER_LINK
   drop constraint FK_GRP_U_LNK_REF_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.YMMG_GROUP_USER_LINK') and o.name = 'FK_GRP_U_LNK_REF_GROUPID')
alter table dbo.YMMG_GROUP_USER_LINK
   drop constraint FK_GRP_U_LNK_REF_GROUPID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.YMMG_GROUP_USER_LINK') and o.name = 'FK_GRP_U_LNK_REF_USERID')
alter table dbo.YMMG_GROUP_USER_LINK
   drop constraint FK_GRP_U_LNK_REF_USERID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.YMMG_USER_ROLE_LINK') and o.name = 'FK_USER_ROLE_REF_ROLEID')
alter table dbo.YMMG_USER_ROLE_LINK
   drop constraint FK_USER_ROLE_REF_ROLEID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('dbo.YMMG_USER_ROLE_LINK') and o.name = 'FK_USER_ROLE_REF_USERID')
alter table dbo.YMMG_USER_ROLE_LINK
   drop constraint FK_USER_ROLE_REF_USERID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_AUTHORIZATION')
            and   name  = 'IDX_PERMISSION_CODE'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_AUTHORIZATION.IDX_PERMISSION_CODE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_AUTHORIZATION')
            and   type = 'U')
   drop table dbo.YMMG_AUTHORIZATION
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BIZ_COLUMN')
            and   type = 'U')
   drop table dbo.YMMG_BIZ_COLUMN
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL')
            and   name  = 'COLUMN_ID_UNIQUE'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BIZ_COLUMN_CONTROL.COLUMN_ID_UNIQUE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL')
            and   type = 'U')
   drop table dbo.YMMG_BIZ_COLUMN_CONTROL
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BIZ_OBJECT')
            and   name  = 'IDX_UNI_BIZ_OBJECT_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BIZ_OBJECT.IDX_UNI_BIZ_OBJECT_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BIZ_OBJECT')
            and   type = 'U')
   drop table dbo.YMMG_BIZ_OBJECT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BIZ_PERMISSION')
            and   name  = 'OBJ_TYPE_OBJ_VAL_UNIQUE_IDX'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BIZ_PERMISSION.OBJ_TYPE_OBJ_VAL_UNIQUE_IDX
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BIZ_PERMISSION')
            and   type = 'U')
   drop table dbo.YMMG_BIZ_PERMISSION
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BIZ_TABLE')
            and   name  = 'IDX_UNI_BIZ_TABLE_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BIZ_TABLE.IDX_UNI_BIZ_TABLE_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BIZ_TABLE')
            and   type = 'U')
   drop table dbo.YMMG_BIZ_TABLE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_BIZ_LINK')
            and   type = 'U')
   drop table dbo.YMMG_BPM_BIZ_LINK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_DEFINITION')
            and   name  = 'BPM_PROCESS_DEF_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_DEFINITION.BPM_PROCESS_DEF_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_DEFINITION')
            and   type = 'U')
   drop table dbo.YMMG_BPM_DEFINITION
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_INSTANCE')
            and   name  = 'IDX_PROINST_PARENTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_INSTANCE.IDX_PROINST_PARENTID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_INSTANCE')
            and   name  = 'IDX_PROINST_BPMINSTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_INSTANCE.IDX_PROINST_BPMINSTID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_INSTANCE')
            and   name  = 'IDX_PROINST_BIZKEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_INSTANCE.IDX_PROINST_BIZKEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_INSTANCE')
            and   type = 'U')
   drop table dbo.YMMG_BPM_INSTANCE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_REMINDER_HISTORY')
            and   type = 'U')
   drop table dbo.YMMG_BPM_REMINDER_HISTORY
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK')
            and   name  = 'IDX_BPMTASK_USERID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK.IDX_BPMTASK_USERID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK')
            and   name  = 'IDX_BPMTASK_TASKID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK.IDX_BPMTASK_TASKID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK')
            and   name  = 'IDX_BPMTASK_PARENTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK.IDX_BPMTASK_PARENTID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK')
            and   name  = 'IDX_BPMTASK_INSTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK.IDX_BPMTASK_INSTID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_TASK')
            and   type = 'U')
   drop table dbo.YMMG_BPM_TASK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
            and   name  = 'IDX_TASKCANDIDATE_TASKID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK_IDENTITYLINK.IDX_TASKCANDIDATE_TASKID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
            and   name  = 'IDX_PERMISSION_CODE__COPY_1'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK_IDENTITYLINK.IDX_PERMISSION_CODE__COPY_1
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
            and   name  = 'IDX_CANDIDATE_INSTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK_IDENTITYLINK.IDX_CANDIDATE_INSTID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
            and   type = 'U')
   drop table dbo.YMMG_BPM_TASK_IDENTITYLINK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK_OPINION')
            and   name  = 'IDX_OPINION_TASK'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK_OPINION.IDX_OPINION_TASK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK_OPINION')
            and   name  = 'IDX_OPINION_SUPINSTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK_OPINION.IDX_OPINION_SUPINSTID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK_OPINION')
            and   name  = 'IDX_OPINION_INSTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK_OPINION.IDX_OPINION_INSTID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_TASK_OPINION')
            and   type = 'U')
   drop table dbo.YMMG_BPM_TASK_OPINION
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_TASK_REMINDER')
            and   type = 'U')
   drop table dbo.YMMG_BPM_TASK_REMINDER
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_BPM_TASK_STACK')
            and   name  = 'IDX_EXESTACK_INSTID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_BPM_TASK_STACK.IDX_EXESTACK_INSTID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_BPM_TASK_STACK')
            and   type = 'U')
   drop table dbo.YMMG_BPM_TASK_STACK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_DATA_DICT')
            and   type = 'U')
   drop table dbo.YMMG_DATA_DICT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_DATA_SOURCE')
            and   name  = 'IDX_DS_UNI_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_DATA_SOURCE.IDX_DS_UNI_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_DATA_SOURCE')
            and   type = 'U')
   drop table dbo.YMMG_DATA_SOURCE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_DATA_SOURCE_DEF')
            and   name  = 'IDX_DSD_UNI_CLS_PATH'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_DATA_SOURCE_DEF.IDX_DSD_UNI_CLS_PATH
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_DATA_SOURCE_DEF')
            and   type = 'U')
   drop table dbo.YMMG_DATA_SOURCE_DEF
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_DB_ID_GENERATOR')
            and   type = 'U')
   drop table dbo.YMMG_DB_ID_GENERATOR
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_DB_UPLOADER')
            and   type = 'U')
   drop table dbo.YMMG_DB_UPLOADER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_FILE')
            and   type = 'U')
   drop table dbo.YMMG_FILE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_FORM_CUST_DIALOG')
            and   name  = 'IDX_UNI_FORM_DIALOG_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_FORM_CUST_DIALOG.IDX_UNI_FORM_DIALOG_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
            and   type = 'U')
   drop table dbo.YMMG_FORM_CUST_DIALOG
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_FORM_DEF')
            and   name  = 'IDX_YMMG_FORM_DEF_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_FORM_DEF.IDX_YMMG_FORM_DEF_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_FORM_DEF')
            and   type = 'U')
   drop table dbo.YMMG_FORM_DEF
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_FORM_TEMPLATE')
            and   type = 'U')
   drop table dbo.YMMG_FORM_TEMPLATE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_GROUP')
            and   type = 'U')
   drop table dbo.YMMG_GROUP
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_GROUP_POSITION_LINK')
            and   name  = 'IDX_GRPPOS_LNK_POST_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_GROUP_POSITION_LINK.IDX_GRPPOS_LNK_POST_ID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_GROUP_POSITION_LINK')
            and   name  = 'IDX_GRPPOS_LNK_GROUP_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_GROUP_POSITION_LINK.IDX_GRPPOS_LNK_GROUP_ID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_GROUP_POSITION_LINK')
            and   type = 'U')
   drop table dbo.YMMG_GROUP_POSITION_LINK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_GROUP_USER_LINK')
            and   name  = 'IDX_GRP_USER_LNK_USER_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_GROUP_USER_LINK.IDX_GRP_USER_LNK_USER_ID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_GROUP_USER_LINK')
            and   name  = 'IDX_GRP_USER_LNK_POSITION_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_GROUP_USER_LINK.IDX_GRP_USER_LNK_POSITION_ID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_GROUP_USER_LINK')
            and   name  = 'IDX_GRP_USER_LNK_GROUP_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_GROUP_USER_LINK.IDX_GRP_USER_LNK_GROUP_ID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_GROUP_USER_LINK')
            and   type = 'U')
   drop table dbo.YMMG_GROUP_USER_LINK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_LOG_ERR')
            and   type = 'U')
   drop table dbo.YMMG_LOG_ERR
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_POSITION')
            and   type = 'U')
   drop table dbo.YMMG_POSITION
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_PROPERTIES')
            and   type = 'U')
   drop table dbo.YMMG_PROPERTIES
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_RESOURCE')
            and   type = 'U')
   drop table dbo.YMMG_RESOURCE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_RESOURCE_LINK')
            and   type = 'U')
   drop table dbo.YMMG_RESOURCE_LINK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_ROLE')
            and   type = 'U')
   drop table dbo.YMMG_ROLE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_ROLE_RESOURCE_LINK')
            and   type = 'U')
   drop table dbo.YMMG_ROLE_RESOURCE_LINK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_SCHEDULE_JOB')
            and   type = 'U')
   drop table dbo.YMMG_SCHEDULE_JOB
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
            and   name  = 'IDX_SCH_JOB_LOGJOB_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_SCHEDULE_JOB_LOG.IDX_SCH_JOB_LOGJOB_ID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
            and   type = 'U')
   drop table dbo.YMMG_SCHEDULE_JOB_LOG
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_SCRIPT')
            and   type = 'U')
   drop table dbo.YMMG_SCRIPT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_SERIALNO')
            and   name  = 'IDX_UNI_SERIALNO_ALIAS_VAL'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_SERIALNO.IDX_UNI_SERIALNO_ALIAS_VAL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_SERIALNO')
            and   type = 'U')
   drop table dbo.YMMG_SERIALNO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_SUBSYSTEM')
            and   type = 'U')
   drop table dbo.YMMG_SUBSYSTEM
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_TREE')
            and   name  = 'IDX_YMMG_TREE_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_TREE.IDX_YMMG_TREE_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_TREE')
            and   type = 'U')
   drop table dbo.YMMG_TREE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_TREE_NODE')
            and   name  = 'IDX_YMMG_TREE_NODE_KEY'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_TREE_NODE.IDX_YMMG_TREE_NODE_KEY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_TREE_NODE')
            and   type = 'U')
   drop table dbo.YMMG_TREE_NODE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_USER')
            and   type = 'U')
   drop table dbo.YMMG_USER
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_USER_ROLE_LINK')
            and   name  = 'IDX_UR_LNK_USER_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_USER_ROLE_LINK.IDX_UR_LNK_USER_ID
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_USER_ROLE_LINK')
            and   name  = 'IDX_UR_LNK_ROLE_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_USER_ROLE_LINK.IDX_UR_LNK_ROLE_ID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_USER_ROLE_LINK')
            and   type = 'U')
   drop table dbo.YMMG_USER_ROLE_LINK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
            and   name  = 'IDX_WORKBENCH_LAYOUT_PANEL_ID'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_WORKBENCH_LAYOUT.IDX_WORKBENCH_LAYOUT_PANEL_ID
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
            and   type = 'U')
   drop table dbo.YMMG_WORKBENCH_LAYOUT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('dbo.YMMG_WORKBENCH_PANEL')
            and   name  = 'IDX_WORKBENCH_PANEL_ALIAS'
            and   indid > 0
            and   indid < 255)
   drop index dbo.YMMG_WORKBENCH_PANEL.IDX_WORKBENCH_PANEL_ALIAS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_WORKBENCH_PANEL')
            and   type = 'U')
   drop table dbo.YMMG_WORKBENCH_PANEL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('dbo.YMMG_WORKBENCH_PANEL_TEMPLATE')
            and   type = 'U')
   drop table dbo.YMMG_WORKBENCH_PANEL_TEMPLATE
go


/*==============================================================*/
/* Table: YMMG_AUTHORIZATION                                    */
/*==============================================================*/
create table dbo.YMMG_AUTHORIZATION (
   RIGHTS_ID            varchar(64)          not null,
   RIGHTS_OBJECT        varchar(64)          not null,
   RIGHTS_TARGET        varchar(64)          not null,
   RIGHTS_TYPE          varchar(64)          not null,
   RIGHTS_IDENTITY      varchar(64)          not null,
   RIGHTS_IDENTITY_NAME varchar(255)         not null,
   RIGHTS_PERMISSION_CODE varchar(125)         not null,
   RIGHTS_CREATE_TIME   datetime             not null,
   RIGHTS_CREATE_BY     varchar(64)          not null,
   constraint SYS_C0012346 primary key (RIGHTS_ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_AUTHORIZATION') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION'

end


execute sp_addextendedproperty 'MS_Description',
   '通用资源授权配置',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'ID',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_OBJECT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_OBJECT'

end


execute sp_addextendedproperty 'MS_Description',
   '授权对象表分区用',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_OBJECT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_TARGET')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_TARGET'

end


execute sp_addextendedproperty 'MS_Description',
   '授权目标ID',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_TARGET'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '权限类型',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_IDENTITY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_IDENTITY'

end


execute sp_addextendedproperty 'MS_Description',
   '授权标识',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_IDENTITY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_IDENTITY_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_IDENTITY_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '标识名字',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_IDENTITY_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_PERMISSION_CODE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_PERMISSION_CODE'

end


execute sp_addextendedproperty 'MS_Description',
   '授权CODE=IDENTITY+TYPE',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_PERMISSION_CODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_AUTHORIZATION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_CREATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_CREATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人',
   'user', 'dbo', 'table', 'YMMG_AUTHORIZATION', 'column', 'RIGHTS_CREATE_BY'
go

/*==============================================================*/
/* Index: IDX_PERMISSION_CODE                                   */
/*==============================================================*/




create nonclustered index IDX_PERMISSION_CODE on dbo.YMMG_AUTHORIZATION (RIGHTS_PERMISSION_CODE ASC)
go

/*==============================================================*/
/* Table: YMMG_BIZ_COLUMN                                       */
/*==============================================================*/
create table dbo.YMMG_BIZ_COLUMN (
   ID                   varchar(64)          not null,
   TABLE_ID             varchar(64)          null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(64)          null,
   TYPE                 varchar(64)          null,
   LENGTH               numeric(11)          null,
   IS_DECIMAL           numeric(11)          null,
   REQUIRED             numeric(4)           null,
   IS_PRIMARY           numeric(4)           null,
   DEFAULT_VALUE        varchar(128)         null,
   COMMENTS             varchar(256)         null,
   constraint SYS_C0012285 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BIZ_COLUMN') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN'

end


execute sp_addextendedproperty 'MS_Description',
   '业务字段表',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TABLE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'TABLE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '表ID',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'TABLE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '类型',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'LENGTH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'LENGTH'

end


execute sp_addextendedproperty 'MS_Description',
   '长度',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'LENGTH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_DECIMAL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'IS_DECIMAL'

end


execute sp_addextendedproperty 'MS_Description',
   '是否数字类型',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'IS_DECIMAL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'REQUIRED')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'REQUIRED'

end


execute sp_addextendedproperty 'MS_Description',
   '是否必选,不为空',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'REQUIRED'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_PRIMARY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'IS_PRIMARY'

end


execute sp_addextendedproperty 'MS_Description',
   '是否主键',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'IS_PRIMARY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DEFAULT_VALUE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'DEFAULT_VALUE'

end


execute sp_addextendedproperty 'MS_Description',
   '默认值',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'DEFAULT_VALUE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'COMMENTS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'COMMENTS'

end


execute sp_addextendedproperty 'MS_Description',
   '备注',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN', 'column', 'COMMENTS'
go

/*==============================================================*/
/* Table: YMMG_BIZ_COLUMN_CONTROL                               */
/*==============================================================*/
create table dbo.YMMG_BIZ_COLUMN_CONTROL (
   ID                   varchar(64)          not null,
   COLUMN_ID            varchar(64)          null,
   TYPE                 varchar(64)          null,
   CONFIG               varchar(256)         null,
   VALID_RULE           varchar(256)         null,
   constraint SYS_C0012287 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL'

end


execute sp_addextendedproperty 'MS_Description',
   '字段控件表',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'COLUMN_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'COLUMN_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '字段ID',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'COLUMN_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '控件类型',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CONFIG')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'CONFIG'

end


execute sp_addextendedproperty 'MS_Description',
   '控件配置',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'CONFIG'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_COLUMN_CONTROL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'VALID_RULE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'VALID_RULE'

end


execute sp_addextendedproperty 'MS_Description',
   '验证规则',
   'user', 'dbo', 'table', 'YMMG_BIZ_COLUMN_CONTROL', 'column', 'VALID_RULE'
go

/*==============================================================*/
/* Index: COLUMN_ID_UNIQUE                                      */
/*==============================================================*/




create unique nonclustered index COLUMN_ID_UNIQUE on dbo.YMMG_BIZ_COLUMN_CONTROL (COLUMN_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_BIZ_OBJECT                                       */
/*==============================================================*/
create table dbo.YMMG_BIZ_OBJECT (
   ID                   varchar(64)          not null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(128)         null,
   DESCRIPTION          varchar(256)         null,
   RELATION_JSON        text                 null,
   GROUP_ID             varchar(64)          null,
   GROUP_NAME           varchar(128)         null,
   PERSISTENCE_TYPE     varchar(64)          null,
   constraint SYS_C0012289 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BIZ_OBJECT') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT'

end


execute sp_addextendedproperty 'MS_Description',
   '业务对象',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   'KEY',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RELATION_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'RELATION_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   'RELATION字段用来持久化入库的字符串字段',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'RELATION_JSON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GROUP_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'GROUP_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '分组ID',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'GROUP_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GROUP_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'GROUP_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '分组名称',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'GROUP_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_OBJECT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PERSISTENCE_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'PERSISTENCE_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '持久化类型',
   'user', 'dbo', 'table', 'YMMG_BIZ_OBJECT', 'column', 'PERSISTENCE_TYPE'
go

/*==============================================================*/
/* Index: IDX_UNI_BIZ_OBJECT_KEY                                */
/*==============================================================*/




create unique nonclustered index IDX_UNI_BIZ_OBJECT_KEY on dbo.YMMG_BIZ_OBJECT ("KEY" ASC)
go

/*==============================================================*/
/* Table: YMMG_BIZ_PERMISSION                                   */
/*==============================================================*/
create table dbo.YMMG_BIZ_PERMISSION (
   ID                   varchar(64)          not null,
   BO_KEY               varchar(128)         null,
   OBJ_TYPE             varchar(64)          not null,
   OBJ_VAL              varchar(128)         null,
   BUS_OBJ_MAP_JSON     text                 null,
   RIGHTS_JSON          text                 null,
   constraint SYS_C0012292 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BIZ_PERMISSION') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION'

end


execute sp_addextendedproperty 'MS_Description',
   'BO权限',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_PERMISSION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BO_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'BO_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   'BOKEY',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'BO_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_PERMISSION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OBJ_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'OBJ_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '配置这个权限的对象，可以是表单，流程，或流程节点',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'OBJ_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_PERMISSION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OBJ_VAL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'OBJ_VAL'

end


execute sp_addextendedproperty 'MS_Description',
   '能获取到配置权限的对象的唯一值通常是KEY或ID可以是自定义的.例如某个流程的某个节点可以是 流程KEY.NODEKEY这样的格式',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'OBJ_VAL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_PERMISSION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BUS_OBJ_MAP_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'BUS_OBJ_MAP_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   'BUSOBJMAP的JSON数据',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'BUS_OBJ_MAP_JSON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_PERMISSION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RIGHTS_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'RIGHTS_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   'RIGHTS的JSON数据',
   'user', 'dbo', 'table', 'YMMG_BIZ_PERMISSION', 'column', 'RIGHTS_JSON'
go

/*==============================================================*/
/* Index: OBJ_TYPE_OBJ_VAL_UNIQUE_IDX                           */
/*==============================================================*/




create unique nonclustered index OBJ_TYPE_OBJ_VAL_UNIQUE_IDX on dbo.YMMG_BIZ_PERMISSION (OBJ_TYPE ASC,
  OBJ_VAL ASC)
go

/*==============================================================*/
/* Table: YMMG_BIZ_TABLE                                        */
/*==============================================================*/
create table dbo.YMMG_BIZ_TABLE (
   ID                   varchar(64)          not null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(64)          null,
   COMMENTS             varchar(256)         null,
   DATASOURCE_KEY       varchar(64)          null,
   DATASOURCE_NAME      varchar(128)         null,
   GROUP_ID             varchar(64)          null,
   GROUP_NAME           varchar(128)         null,
   ISEXTERNAL           numeric(6)           null,
   constraint SYS_C0012294 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BIZ_TABLE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE'

end


execute sp_addextendedproperty 'MS_Description',
   '业务表',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '业务表KEY',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '表名',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'COMMENTS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'COMMENTS'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'COMMENTS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DATASOURCE_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'DATASOURCE_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源的别名',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'DATASOURCE_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DATASOURCE_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'DATASOURCE_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源名称',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'DATASOURCE_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GROUP_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'GROUP_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '分组ID',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'GROUP_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GROUP_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'GROUP_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '分组名称',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'GROUP_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BIZ_TABLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ISEXTERNAL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'ISEXTERNAL'

end


execute sp_addextendedproperty 'MS_Description',
   '是否外部表',
   'user', 'dbo', 'table', 'YMMG_BIZ_TABLE', 'column', 'ISEXTERNAL'
go

/*==============================================================*/
/* Index: IDX_UNI_BIZ_TABLE_KEY                                 */
/*==============================================================*/




create unique nonclustered index IDX_UNI_BIZ_TABLE_KEY on dbo.YMMG_BIZ_TABLE ("KEY" ASC)
go

/*==============================================================*/
/* Table: YMMG_BPM_BIZ_LINK                                     */
/*==============================================================*/
create table dbo.YMMG_BPM_BIZ_LINK (
   ID                   varchar(64)          not null,
   DEF_ID               varchar(64)          null,
   INST_ID              varchar(64)          null,
   BIZ_ID               varchar(64)          null,
   BIZ_CODE             varchar(64)          not null,
   constraint SYS_C0012252 primary key (ID, BIZ_CODE)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BPM_BIZ_LINK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK'

end


execute sp_addextendedproperty 'MS_Description',
   '流程实例与业务数据关系表',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_BIZ_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_BIZ_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DEF_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'DEF_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '流程定义ID',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'DEF_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_BIZ_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '流程实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_BIZ_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BIZ_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'BIZ_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '业务主键',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'BIZ_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_BIZ_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BIZ_CODE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'BIZ_CODE'

end


execute sp_addextendedproperty 'MS_Description',
   'BO_CODE',
   'user', 'dbo', 'table', 'YMMG_BPM_BIZ_LINK', 'column', 'BIZ_CODE'
go

/*==============================================================*/
/* Table: YMMG_BPM_DEFINITION                                   */
/*==============================================================*/
create table dbo.YMMG_BPM_DEFINITION (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          not null,
   "KEY"                varchar(64)          not null,
   DESCRIPTION          varchar(1024)        null,
   TYPE_ID              varchar(64)          null,
   STATUS               varchar(40)          null,
   ACT_DEF_ID           varchar(64)          null,
   ACT_MODEL_ID         varchar(64)          null,
   ACT_DEPLOY_ID        varchar(64)          null,
   VERSION              numeric(11)          null,
   MAIN_DEF_ID          varchar(64)          null,
   IS_MAIN              char(1)              null,
   CREATE_BY            varchar(64)          null,
   CREATE_TIME          datetime             null,
   CREATE_ORG_ID        varchar(64)          null,
   UPDATE_BY            varchar(64)          null,
   UPDATE_TIME          datetime             null,
   SUPPORT_MOBILE       numeric(11)          null default 0,
   DEF_SETTING          text                 null,
   REV                  numeric(11)          null,
   constraint SYS_C0012256 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BPM_DEFINITION') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION'

end


execute sp_addextendedproperty 'MS_Description',
   '流程定义',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '流程定义ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '流程名称',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '流程业务主键',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '流程描述',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'TYPE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '所属分类ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'TYPE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STATUS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'STATUS'

end


execute sp_addextendedproperty 'MS_Description',
   '流程状态。草稿、发布、禁用',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'STATUS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACT_DEF_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'ACT_DEF_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'BPMN - 流程定义ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'ACT_DEF_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACT_DEPLOY_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'ACT_DEPLOY_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'BPMN - 流程发布ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'ACT_DEPLOY_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'VERSION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'VERSION'

end


execute sp_addextendedproperty 'MS_Description',
   '版本 - 当前版本号',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'VERSION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MAIN_DEF_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'MAIN_DEF_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '版本 - 主版本流程ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'MAIN_DEF_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_MAIN')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'IS_MAIN'

end


execute sp_addextendedproperty 'MS_Description',
   '版本 - 是否主版本',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'IS_MAIN'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'CREATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'CREATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_ORG_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'CREATE_ORG_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '创建者所属组织ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'CREATE_ORG_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'UPDATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '更新人ID',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'UPDATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_DEFINITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'UPDATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '更新时间',
   'user', 'dbo', 'table', 'YMMG_BPM_DEFINITION', 'column', 'UPDATE_TIME'
go

/*==============================================================*/
/* Index: BPM_PROCESS_DEF_KEY                                   */
/*==============================================================*/




create nonclustered index BPM_PROCESS_DEF_KEY on dbo.YMMG_BPM_DEFINITION ("KEY" ASC)
go

/*==============================================================*/
/* Table: YMMG_BPM_INSTANCE                                     */
/*==============================================================*/
create table dbo.YMMG_BPM_INSTANCE (
   ID                   varchar(64)          not null,
   SUBJECT              varchar(128)         not null,
   DEF_ID               varchar(64)          not null,
   ACT_DEF_ID           varchar(64)          null,
   DEF_KEY              varchar(128)         null,
   DEF_NAME             varchar(128)         not null,
   BIZ_KEY              varchar(64)          null,
   STATUS               varchar(40)          null,
   END_TIME             datetime             null,
   DURATION             numeric(20)          null,
   TYPE_ID              varchar(64)          null,
   ACT_INST_ID          varchar(64)          null,
   CREATE_BY            varchar(64)          null,
   CREATOR              varchar(64)          null,
   CREATE_TIME          datetime             null,
   CREATE_ORG_ID        varchar(64)          null,
   UPDATE_BY            varchar(64)          null,
   UPDATE_TIME          datetime             null,
   IS_FORMMAL           char(1)              not null,
   PARENT_INST_ID       varchar(64)          null,
   IS_FORBIDDEN         numeric(6)           null,
   DATA_MODE            varchar(20)          null,
   SUPPORT_MOBILE       numeric(11)          null default 0,
   SUPER_NODE_ID        varchar(50)          null,
   constraint SYS_C0012262 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BPM_INSTANCE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE'

end


execute sp_addextendedproperty 'MS_Description',
   '流程实例',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '流程实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SUBJECT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'SUBJECT'

end


execute sp_addextendedproperty 'MS_Description',
   '流程实例标题',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'SUBJECT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DEF_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DEF_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '流程定义ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DEF_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACT_DEF_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'ACT_DEF_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'BPMN流程定义ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'ACT_DEF_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DEF_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DEF_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '流程定义KEY',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DEF_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DEF_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DEF_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '流程名称',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DEF_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BIZ_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'BIZ_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '关联数据业务主键',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'BIZ_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STATUS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'STATUS'

end


execute sp_addextendedproperty 'MS_Description',
   '实例状态',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'STATUS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'END_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'END_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '实例结束时间',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'END_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DURATION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DURATION'

end


execute sp_addextendedproperty 'MS_Description',
   '持续时间(MS)',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'DURATION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'TYPE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '所属分类ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'TYPE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACT_INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'ACT_INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'BPMN流程实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'ACT_INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATOR')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATOR'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATOR'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_ORG_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATE_ORG_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '创建者所属组织ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'CREATE_ORG_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'UPDATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '更新人ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'UPDATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'UPDATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '更新时间',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'UPDATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_FORMMAL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'IS_FORMMAL'

end


execute sp_addextendedproperty 'MS_Description',
   '是否正式数据',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'IS_FORMMAL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PARENT_INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'PARENT_INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '父实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'PARENT_INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_FORBIDDEN')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'IS_FORBIDDEN'

end


execute sp_addextendedproperty 'MS_Description',
   '禁止',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'IS_FORBIDDEN'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_INSTANCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SUPER_NODE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'SUPER_NODE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '父流程定义节点ID',
   'user', 'dbo', 'table', 'YMMG_BPM_INSTANCE', 'column', 'SUPER_NODE_ID'
go

/*==============================================================*/
/* Index: IDX_PROINST_BIZKEY                                    */
/*==============================================================*/




create nonclustered index IDX_PROINST_BIZKEY on dbo.YMMG_BPM_INSTANCE (BIZ_KEY ASC)
go

/*==============================================================*/
/* Index: IDX_PROINST_BPMINSTID                                 */
/*==============================================================*/




create nonclustered index IDX_PROINST_BPMINSTID on dbo.YMMG_BPM_INSTANCE (ACT_INST_ID ASC)
go

/*==============================================================*/
/* Index: IDX_PROINST_PARENTID                                  */
/*==============================================================*/




create nonclustered index IDX_PROINST_PARENTID on dbo.YMMG_BPM_INSTANCE (PARENT_INST_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_BPM_REMINDER_HISTORY                             */
/*==============================================================*/
create table dbo.YMMG_BPM_REMINDER_HISTORY (
   ID                   varchar(64)          not null,
   INST_ID              varchar(255)         null,
   INST_NAME            varchar(255)         null,
   NODE_NAME            varchar(255)         null,
   NODE_ID              varchar(255)         null,
   EXECUTE_DATE         varchar(255)         null,
   REMIND_TYPE          varchar(255)         null,
   USER_ID              varchar(255)         null,
   NOTE                 varchar(255)         null,
   constraint SYS_C0012264 primary key (ID)
)
go

/*==============================================================*/
/* Table: YMMG_BPM_TASK                                         */
/*==============================================================*/
create table dbo.YMMG_BPM_TASK (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          not null,
   SUBJECT              varchar(128)         not null,
   INST_ID              varchar(64)          not null,
   TASK_ID              varchar(64)          null,
   ACT_INST_ID          varchar(64)          null,
   ACT_EXECUTION_ID     varchar(64)          null,
   NODE_ID              varchar(64)          null,
   DEF_ID               varchar(64)          not null,
   ASSIGNEE_ID          varchar(64)          null,
   ASSIGNEE_NAMES       varchar(500)         null,
   STATUS               varchar(64)          not null,
   PRIORITY             numeric(11)          null,
   DUE_TIME             datetime             null,
   TASK_TYPE            varchar(64)          null,
   PARENT_ID            varchar(64)          null,
   TYPE_ID              varchar(64)          null,
   CREATE_TIME          datetime             not null,
   CREATE_BY            varchar(64)          null,
   SUPPORT_MOBILE       numeric(11)          null default 0,
   BACK_NODE            varchar(64)          null,
   constraint SYS_C0012272 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BPM_TASK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK'

end


execute sp_addextendedproperty 'MS_Description',
   '流程任务',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '任务ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '任务名称',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SUBJECT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'SUBJECT'

end


execute sp_addextendedproperty 'MS_Description',
   '待办事项标题',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'SUBJECT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '关联 - 流程实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TASK_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'TASK_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '关联的任务ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'TASK_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACT_INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ACT_INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'ACTIVITI 实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ACT_INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACT_EXECUTION_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ACT_EXECUTION_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'ACTIVITI 执行ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ACT_EXECUTION_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NODE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'NODE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '关联 - 任务节点ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'NODE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DEF_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'DEF_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '关联 - 流程定义ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'DEF_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ASSIGNEE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ASSIGNEE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '任务执行人ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'ASSIGNEE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STATUS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'STATUS'

end


execute sp_addextendedproperty 'MS_Description',
   '任务状态',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'STATUS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PRIORITY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'PRIORITY'

end


execute sp_addextendedproperty 'MS_Description',
   '任务优先级',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'PRIORITY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DUE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'DUE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '任务到期时间',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'DUE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TASK_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'TASK_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '任务类型',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'TASK_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PARENT_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'PARENT_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '父任务ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'PARENT_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'TYPE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '分类ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'TYPE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '任务创建时间',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BACK_NODE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'BACK_NODE'

end


execute sp_addextendedproperty 'MS_Description',
   '返回节点',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK', 'column', 'BACK_NODE'
go

/*==============================================================*/
/* Index: IDX_BPMTASK_INSTID                                    */
/*==============================================================*/




create nonclustered index IDX_BPMTASK_INSTID on dbo.YMMG_BPM_TASK (INST_ID ASC)
go

/*==============================================================*/
/* Index: IDX_BPMTASK_PARENTID                                  */
/*==============================================================*/




create nonclustered index IDX_BPMTASK_PARENTID on dbo.YMMG_BPM_TASK (PARENT_ID ASC)
go

/*==============================================================*/
/* Index: IDX_BPMTASK_TASKID                                    */
/*==============================================================*/




create nonclustered index IDX_BPMTASK_TASKID on dbo.YMMG_BPM_TASK (TASK_ID ASC)
go

/*==============================================================*/
/* Index: IDX_BPMTASK_USERID                                    */
/*==============================================================*/




create nonclustered index IDX_BPMTASK_USERID on dbo.YMMG_BPM_TASK (ASSIGNEE_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_BPM_TASK_IDENTITYLINK                            */
/*==============================================================*/
create table dbo.YMMG_BPM_TASK_IDENTITYLINK (
   ID                   varchar(64)          not null,
   TASK_ID              varchar(64)          null,
   INST_ID              varchar(64)          null,
   TYPE                 varchar(20)          null,
   IDENTITY_NAME        varchar(64)          null,
   IDENTITY_ID          varchar(20)          null,
   PERMISSION_CODE      varchar(64)          null,
   constraint SYS_C0012274 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK'

end


execute sp_addextendedproperty 'MS_Description',
   '任务候选人',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TASK_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'TASK_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '任务ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'TASK_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '候选人类型',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IDENTITY_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'IDENTITY_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'IDENTITY_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_IDENTITYLINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IDENTITY_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'IDENTITY_ID'

end


execute sp_addextendedproperty 'MS_Description',
   'ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_IDENTITYLINK', 'column', 'IDENTITY_ID'
go

/*==============================================================*/
/* Index: IDX_CANDIDATE_INSTID                                  */
/*==============================================================*/




create nonclustered index IDX_CANDIDATE_INSTID on dbo.YMMG_BPM_TASK_IDENTITYLINK (INST_ID ASC)
go

/*==============================================================*/
/* Index: IDX_PERMISSION_CODE__COPY_1                           */
/*==============================================================*/




create nonclustered index IDX_PERMISSION_CODE__COPY_1 on dbo.YMMG_BPM_TASK_IDENTITYLINK (PERMISSION_CODE ASC)
go

/*==============================================================*/
/* Index: IDX_TASKCANDIDATE_TASKID                              */
/*==============================================================*/




create nonclustered index IDX_TASKCANDIDATE_TASKID on dbo.YMMG_BPM_TASK_IDENTITYLINK (TASK_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_BPM_TASK_OPINION                                 */
/*==============================================================*/
create table dbo.YMMG_BPM_TASK_OPINION (
   ID                   varchar(64)          not null,
   INST_ID              varchar(64)          not null,
   SUP_INST_ID          varchar(64)          null,
   TASK_ID              varchar(64)          null,
   TASK_KEY             varchar(64)          null,
   TASK_NAME            varchar(255)         null,
   TOKEN                varchar(64)          null,
   ASSIGN_INFO          varchar(2000)        null,
   APPROVER             varchar(64)          null,
   APPROVER_NAME        varchar(64)          null,
   APPROVE_TIME         datetime             null,
   OPINION              varchar(2000)        null,
   STATUS               varchar(64)          not null,
   FORM_ID              varchar(64)          null,
   CREATE_BY            varchar(255)         null,
   CREATE_TIME          datetime             null,
   DURATION             numeric(20)          null,
   constraint SYS_C0012278 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BPM_TASK_OPINION') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION'

end


execute sp_addextendedproperty 'MS_Description',
   '流程任务审批记录',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '意见ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '流程实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SUP_INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'SUP_INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '父流程实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'SUP_INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TASK_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TASK_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '任务ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TASK_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TASK_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TASK_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '任务定义KEY',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TASK_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TASK_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TASK_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '任务名称',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TASK_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TOKEN')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TOKEN'

end


execute sp_addextendedproperty 'MS_Description',
   '任务令牌',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'TOKEN'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ASSIGN_INFO')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'ASSIGN_INFO'

end


execute sp_addextendedproperty 'MS_Description',
   '任务分配情况',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'ASSIGN_INFO'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'APPROVER')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'APPROVER'

end


execute sp_addextendedproperty 'MS_Description',
   '审批人',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'APPROVER'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'APPROVER_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'APPROVER_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '审批人名字',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'APPROVER_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'APPROVE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'APPROVE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '审批时间',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'APPROVE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OPINION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'OPINION'

end


execute sp_addextendedproperty 'MS_Description',
   '审批意见',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'OPINION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STATUS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'STATUS'

end


execute sp_addextendedproperty 'MS_Description',
   '审批状态,START=发起流程;AWAITING_CHECK=待审批;AGREE=同意;AGAINST=反对;RETURN=驳回;ABANDON=弃权;RETRIEVE=追回',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'STATUS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'FORM_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'FORM_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '表单定义ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'FORM_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '执行开始时间',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_OPINION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DURATION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'DURATION'

end


execute sp_addextendedproperty 'MS_Description',
   '持续时间(MS)',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_OPINION', 'column', 'DURATION'
go

/*==============================================================*/
/* Index: IDX_OPINION_INSTID                                    */
/*==============================================================*/




create nonclustered index IDX_OPINION_INSTID on dbo.YMMG_BPM_TASK_OPINION (INST_ID ASC)
go

/*==============================================================*/
/* Index: IDX_OPINION_SUPINSTID                                 */
/*==============================================================*/




create nonclustered index IDX_OPINION_SUPINSTID on dbo.YMMG_BPM_TASK_OPINION (SUP_INST_ID ASC)
go

/*==============================================================*/
/* Index: IDX_OPINION_TASK                                      */
/*==============================================================*/




create nonclustered index IDX_OPINION_TASK on dbo.YMMG_BPM_TASK_OPINION (TASK_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_BPM_TASK_REMINDER                                */
/*==============================================================*/
create table dbo.YMMG_BPM_TASK_REMINDER (
   ID                   varchar(64)          not null,
   TASK_ID              varchar(64)          null,
   NAME                 varchar(64)          null,
   REL_DATE             varchar(64)          null,
   DUE_ACTION           varchar(64)          null,
   DUE_SCRIPT           varchar(255)         null,
   DUE_DATE             datetime             null,
   IS_SEND_MSG          numeric(11)          null,
   MSG_SEND_DATE        datetime             null,
   MSG_INTERVAL         numeric(11)          null,
   MSG_COUNT            numeric(11)          null,
   MSG_TYPE             varchar(64)          null,
   HTML_MSG             varchar(255)         null,
   PLAIN_MSG            varchar(255)         null,
   WARNINGSET           varchar(255)         null,
   TRIGGER_DATE         datetime             null,
   constraint SYS_C0012280 primary key (ID)
)
go

/*==============================================================*/
/* Table: YMMG_BPM_TASK_STACK                                   */
/*==============================================================*/
create table dbo.YMMG_BPM_TASK_STACK (
   ID                   varchar(64)          not null,
   TASK_ID              varchar(255)         null,
   INST_ID              varchar(64)          null,
   PARENT_ID            varchar(64)          null,
   NODE_ID              varchar(64)          not null,
   NODE_NAME            varchar(125)         null,
   START_TIME           datetime             null,
   END_TIME             datetime             null,
   IS_MULITI_TASK       numeric(6)           null,
   PATH                 varchar(512)         null,
   constraint SYS_C0012283 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_BPM_TASK_STACK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK'

end


execute sp_addextendedproperty 'MS_Description',
   '流程执行堆栈树',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TASK_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'TASK_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '任务ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'TASK_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'INST_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'INST_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '流程实例ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'INST_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PARENT_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'PARENT_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '父ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'PARENT_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NODE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'NODE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '节点ID',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'NODE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'START_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'START_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '开始时间',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'START_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'END_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'END_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '结束时间',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'END_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_MULITI_TASK')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'IS_MULITI_TASK'

end


execute sp_addextendedproperty 'MS_Description',
   '1=是;0=否',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'IS_MULITI_TASK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_BPM_TASK_STACK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PATH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'PATH'

end


execute sp_addextendedproperty 'MS_Description',
   '路径',
   'user', 'dbo', 'table', 'YMMG_BPM_TASK_STACK', 'column', 'PATH'
go

/*==============================================================*/
/* Index: IDX_EXESTACK_INSTID                                   */
/*==============================================================*/




create nonclustered index IDX_EXESTACK_INSTID on dbo.YMMG_BPM_TASK_STACK (INST_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_DATA_DICT                                        */
/*==============================================================*/
create table dbo.YMMG_DATA_DICT (
   ID                   varchar(64)          not null,
   PARENT_ID            varchar(64)          null,
   "KEY"                varchar(255)         not null,
   NAME                 varchar(255)         not null,
   DICT_KEY             varchar(255)         not null,
   TYPE_ID              varchar(64)          null,
   SEQ                  numeric(11)          null,
   DICT_TYPE            varchar(10)          not null,
   IS_DELETE            varchar(1)           null,
   CREATE_TIME          datetime             not null,
   constraint SYS_C0012353 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_DATA_DICT') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT'

end


execute sp_addextendedproperty 'MS_Description',
   '数据字典',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   'ID',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PARENT_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'PARENT_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '上级ID',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'PARENT_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   'KEY',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   'NAME',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DICT_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'DICT_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '字典KEY',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'DICT_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'TYPE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '分组ID',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'TYPE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SEQ')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'SEQ'

end


execute sp_addextendedproperty 'MS_Description',
   '排序',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'SEQ'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DICT_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'DICT_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   'DICT/NODE字典项',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'DICT_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_DELETE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'IS_DELETE'

end


execute sp_addextendedproperty 'MS_Description',
   '是否删除',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'IS_DELETE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_DICT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_DATA_DICT', 'column', 'CREATE_TIME'
go

/*==============================================================*/
/* Table: YMMG_DATA_SOURCE                                      */
/*==============================================================*/
create table dbo.YMMG_DATA_SOURCE (
   ID                   varchar(64)          not null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(64)          null,
   DESCRIPTION          varchar(256)         null,
   DB_TYPE              varchar(64)          null,
   CLASS_PATH           varchar(100)         null,
   ATTRIBUTES_JSON      text                 null,
   constraint SYS_C0012355 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_DATA_SOURCE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源名称',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源的描述',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DB_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'DB_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '数据库类型',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'DB_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CLASS_PATH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'CLASS_PATH'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源全路径',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'CLASS_PATH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ATTRIBUTES_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'ATTRIBUTES_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   '属性配置',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE', 'column', 'ATTRIBUTES_JSON'
go

/*==============================================================*/
/* Index: IDX_DS_UNI_KEY                                        */
/*==============================================================*/




create unique nonclustered index IDX_DS_UNI_KEY on dbo.YMMG_DATA_SOURCE ("KEY" ASC)
go

/*==============================================================*/
/* Table: YMMG_DATA_SOURCE_DEF                                  */
/*==============================================================*/
create table dbo.YMMG_DATA_SOURCE_DEF (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          null,
   CLASS_PATH           varchar(100)         null,
   ATTRIBUTES_JSON      text                 null,
   constraint SYS_C0012357 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_DATA_SOURCE_DEF') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源模板',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源名称',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CLASS_PATH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'CLASS_PATH'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源全路径',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'CLASS_PATH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DATA_SOURCE_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ATTRIBUTES_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'ATTRIBUTES_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   '属性配置',
   'user', 'dbo', 'table', 'YMMG_DATA_SOURCE_DEF', 'column', 'ATTRIBUTES_JSON'
go

/*==============================================================*/
/* Index: IDX_DSD_UNI_CLS_PATH                                  */
/*==============================================================*/




create nonclustered index IDX_DSD_UNI_CLS_PATH on dbo.YMMG_DATA_SOURCE_DEF (CLASS_PATH ASC)
go

/*==============================================================*/
/* Table: YMMG_DB_ID_GENERATOR                                  */
/*==============================================================*/
create table dbo.YMMG_DB_ID_GENERATOR (
   ID                   varchar(64)          not null,
   START_VALUE          numeric(11)          not null,
   MAXIMUM              numeric(11)          not null,
   MACHINE              varchar(255)         not null,
   constraint SYS_C0012409 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_DB_ID_GENERATOR') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR'

end


execute sp_addextendedproperty 'MS_Description',
   '数据库ID增长表',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DB_ID_GENERATOR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   'ID',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DB_ID_GENERATOR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'START_VALUE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'START_VALUE'

end


execute sp_addextendedproperty 'MS_Description',
   '开始ID值',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'START_VALUE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DB_ID_GENERATOR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MAXIMUM')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'MAXIMUM'

end


execute sp_addextendedproperty 'MS_Description',
   '当前允许最大的ID值',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'MAXIMUM'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_DB_ID_GENERATOR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MACHINE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'MACHINE'

end


execute sp_addextendedproperty 'MS_Description',
   '机器名称',
   'user', 'dbo', 'table', 'YMMG_DB_ID_GENERATOR', 'column', 'MACHINE'
go

/*==============================================================*/
/* Table: YMMG_DB_UPLOADER                                      */
/*==============================================================*/
create table dbo.YMMG_DB_UPLOADER (
   ID                   varchar(64)          not null,
   BYTES                binary(1)            null,
   constraint SYS_C0012296 primary key (ID)
)
go

/*==============================================================*/
/* Table: YMMG_FILE                                             */
/*==============================================================*/
create table dbo.YMMG_FILE (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          null,
   UPLOADER             varchar(128)         null,
   PATH                 varchar(256)         null,
   CREATE_TIME          datetime             null,
   CREATE_BY            varchar(64)          null,
   UPDATE_TIME          datetime             null,
   UPDATE_BY            varchar(64)          null,
   VERSION              numeric(11)          null,
   IS_DELETE            numeric(4)           null,
   constraint SYS_C0012359 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_FILE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE'

end


execute sp_addextendedproperty 'MS_Description',
   '系统附件',
   'user', 'dbo', 'table', 'YMMG_FILE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '附件名',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPLOADER')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'UPLOADER'

end


execute sp_addextendedproperty 'MS_Description',
   '上传器',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'UPLOADER'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'CREATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人ID',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'CREATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'UPDATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '更新时间',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'UPDATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'UPDATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '更新人ID',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'UPDATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'VERSION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'VERSION'

end


execute sp_addextendedproperty 'MS_Description',
   '版本号',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'VERSION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FILE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_DELETE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'IS_DELETE'

end


execute sp_addextendedproperty 'MS_Description',
   '逻辑删除标记',
   'user', 'dbo', 'table', 'YMMG_FILE', 'column', 'IS_DELETE'
go

/*==============================================================*/
/* Table: YMMG_FORM_CUST_DIALOG                                 */
/*==============================================================*/
create table dbo.YMMG_FORM_CUST_DIALOG (
   ID                   varchar(64)          not null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(128)         not null,
   DESCRIPTION          varchar(256)         null,
   STYLE                varchar(32)          null,
   DATASOURCE_KEY       varchar(64)          null,
   DATASOURCE_NAME      varchar(128)         null,
   OBJ_TYPE             varchar(32)          null,
   OBJ_NAME             varchar(64)          not null,
   PAGE                 numeric(4)           null,
   PAGE_SIZE            numeric(11)          null,
   WIDTH                numeric(11)          null,
   HEIGHT               numeric(11)          null,
   IS_SYSTEM            numeric(4)           null,
   MULTIPLE             numeric(4)           null,
   TREE_CONFIG_JSON     varchar(512)         null,
   DISPLAY_FIELDS_JSON  text                 null,
   CONDITION_FIELDS_JSON text                 null,
   RETURN_FIELDS_JSON   text                 null,
   SORT_FIELDS_JSON     text                 null,
   DATA_SOURCE_FROM     varchar(64)          null,
   constraint SYS_C0012300 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG'

end


execute sp_addextendedproperty 'MS_Description',
   '自定义对话框',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STYLE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'STYLE'

end


execute sp_addextendedproperty 'MS_Description',
   '显示类型',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'STYLE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DATASOURCE_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DATASOURCE_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源别名',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DATASOURCE_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DATASOURCE_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DATASOURCE_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '数据源名字',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DATASOURCE_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OBJ_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'OBJ_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '对象类型',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'OBJ_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OBJ_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'OBJ_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '对象名称',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'OBJ_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PAGE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'PAGE'

end


execute sp_addextendedproperty 'MS_Description',
   '是否分页',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'PAGE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PAGE_SIZE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'PAGE_SIZE'

end


execute sp_addextendedproperty 'MS_Description',
   '分页大小',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'PAGE_SIZE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'WIDTH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'WIDTH'

end


execute sp_addextendedproperty 'MS_Description',
   '弹出框的宽度',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'WIDTH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'HEIGHT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'HEIGHT'

end


execute sp_addextendedproperty 'MS_Description',
   '弹出框的高度',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'HEIGHT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_SYSTEM')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'IS_SYSTEM'

end


execute sp_addextendedproperty 'MS_Description',
   '是否系统内置',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'IS_SYSTEM'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MULTIPLE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'MULTIPLE'

end


execute sp_addextendedproperty 'MS_Description',
   '是否多选',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'MULTIPLE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TREE_CONFIG_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'TREE_CONFIG_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   '树形的配置信息，JSON字段',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'TREE_CONFIG_JSON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DISPLAY_FIELDS_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DISPLAY_FIELDS_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   '显示字段',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DISPLAY_FIELDS_JSON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CONDITION_FIELDS_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'CONDITION_FIELDS_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   '条件字段的JSON',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'CONDITION_FIELDS_JSON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RETURN_FIELDS_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'RETURN_FIELDS_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   '返回字段JSON',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'RETURN_FIELDS_JSON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SORT_FIELDS_JSON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'SORT_FIELDS_JSON'

end


execute sp_addextendedproperty 'MS_Description',
   '排序字段',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'SORT_FIELDS_JSON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_CUST_DIALOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DATA_SOURCE_FROM')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DATA_SOURCE_FROM'

end


execute sp_addextendedproperty 'MS_Description',
   '数据来源类型:interface、database',
   'user', 'dbo', 'table', 'YMMG_FORM_CUST_DIALOG', 'column', 'DATA_SOURCE_FROM'
go

/*==============================================================*/
/* Index: IDX_UNI_FORM_DIALOG_KEY                               */
/*==============================================================*/




create unique nonclustered index IDX_UNI_FORM_DIALOG_KEY on dbo.YMMG_FORM_CUST_DIALOG ("KEY" ASC)
go

/*==============================================================*/
/* Table: YMMG_FORM_DEF                                         */
/*==============================================================*/
create table dbo.YMMG_FORM_DEF (
   ID                   varchar(64)          not null,
   TYPE                 varchar(64)          null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(128)         null,
   DESCRIPTION          varchar(256)         null,
   GROUP_ID             varchar(64)          null,
   GROUP_NAME           varchar(128)         null,
   BO_KEY               varchar(64)          null,
   BO_NAME              varchar(128)         null,
   HTML                 text                 null,
   CREATE_TIME          datetime             null,
   CREATE_BY            varchar(64)          null,
   CREATOR              varchar(128)         null,
   UPDATE_TIME          datetime             null,
   UPDATE_BY            varchar(64)          null,
   UPDATOR              varchar(128)         null,
   VERSION              numeric(11)          null,
   IS_DELETE            numeric(4)           null,
   constraint SYS_C0012302 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_FORM_DEF') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF'

end


execute sp_addextendedproperty 'MS_Description',
   '表单',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '分类(PC/MOBILE)',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   'KEY',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GROUP_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'GROUP_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '分组ID',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'GROUP_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GROUP_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'GROUP_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '分组名称',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'GROUP_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BO_KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'BO_KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '业务对象KEY',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'BO_KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BO_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'BO_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '业务对象名称',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'BO_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'HTML')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'HTML'

end


execute sp_addextendedproperty 'MS_Description',
   'HTML',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'HTML'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'CREATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人ID',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'CREATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATOR')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'CREATOR'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人名字',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'CREATOR'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'UPDATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '更新时间',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'UPDATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'UPDATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '更新人ID',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'UPDATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATOR')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'UPDATOR'

end


execute sp_addextendedproperty 'MS_Description',
   '更新人名字',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'UPDATOR'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'VERSION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'VERSION'

end


execute sp_addextendedproperty 'MS_Description',
   '版本号',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'VERSION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_DEF')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_DELETE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'IS_DELETE'

end


execute sp_addextendedproperty 'MS_Description',
   '逻辑删除标记',
   'user', 'dbo', 'table', 'YMMG_FORM_DEF', 'column', 'IS_DELETE'
go

/*==============================================================*/
/* Index: IDX_YMMG_FORM_DEF_KEY                                 */
/*==============================================================*/




create unique nonclustered index IDX_YMMG_FORM_DEF_KEY on dbo.YMMG_FORM_DEF ("KEY" ASC)
go

/*==============================================================*/
/* Table: YMMG_FORM_TEMPLATE                                    */
/*==============================================================*/
create table dbo.YMMG_FORM_TEMPLATE (
   ID                   varchar(64)          not null,
   NAME                 varchar(128)         null,
   FORM_TYPE            varchar(64)          null,
   TYPE                 varchar(32)          null,
   HTML                 text                 null,
   DESCRIPTION          varchar(400)         null,
   EDITABLE             numeric(4)           null,
   "KEY"                varchar(64)          null,
   constraint SYS_C0012304 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_FORM_TEMPLATE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE'

end


execute sp_addextendedproperty 'MS_Description',
   '表单模版',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '模板ID',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '模板名称',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'FORM_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'FORM_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '表单类型(PC/MOBILE/VUEPC)',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'FORM_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '模板类型',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'HTML')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'HTML'

end


execute sp_addextendedproperty 'MS_Description',
   '模板内容',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'HTML'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '模板描述',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'EDITABLE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'EDITABLE'

end


execute sp_addextendedproperty 'MS_Description',
   '是否可以编辑',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'EDITABLE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_FORM_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', 'dbo', 'table', 'YMMG_FORM_TEMPLATE', 'column', 'KEY'
go

/*==============================================================*/
/* Table: YMMG_GROUP                                            */
/*==============================================================*/
create table dbo.YMMG_GROUP (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          not null,
   PARENT_ID            varchar(64)          null,
   SEQ                  numeric(11)          null default 100,
   CODE                 varchar(64)          not null,
   GRADE                varchar(64)          null,
   DESCRIPTION          varchar(500)         null,
   constraint SYS_C0012308 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_GROUP') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP'

end


execute sp_addextendedproperty 'MS_Description',
   '组织架构',
   'user', 'dbo', 'table', 'YMMG_GROUP'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_GROUP')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_GROUP', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_GROUP')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GRADE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP', 'column', 'GRADE'

end


execute sp_addextendedproperty 'MS_Description',
   '级别',
   'user', 'dbo', 'table', 'YMMG_GROUP', 'column', 'GRADE'
go

/*==============================================================*/
/* Table: YMMG_GROUP_POSITION_LINK                              */
/*==============================================================*/
create table dbo.YMMG_GROUP_POSITION_LINK (
   ID                   varchar(64)          not null,
   GROUP_ID             varchar(64)          null,
   POSITION_ID          varchar(64)          null,
   POSITION_NAME        varchar(64)          null,
   POSITION_CODE        varchar(64)          null,
   JOB_NAME             varchar(64)          null,
   constraint SYS_C0012310 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_GROUP_POSITION_LINK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK'

end


execute sp_addextendedproperty 'MS_Description',
   '组织关联关系',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_GROUP_POSITION_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'POSITION_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK', 'column', 'POSITION_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '岗位名称',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK', 'column', 'POSITION_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_GROUP_POSITION_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'POSITION_CODE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK', 'column', 'POSITION_CODE'

end


execute sp_addextendedproperty 'MS_Description',
   '岗位编码',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK', 'column', 'POSITION_CODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_GROUP_POSITION_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'JOB_NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK', 'column', 'JOB_NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '职务名称',
   'user', 'dbo', 'table', 'YMMG_GROUP_POSITION_LINK', 'column', 'JOB_NAME'
go

/*==============================================================*/
/* Index: IDX_GRPPOS_LNK_GROUP_ID                               */
/*==============================================================*/




create nonclustered index IDX_GRPPOS_LNK_GROUP_ID on dbo.YMMG_GROUP_POSITION_LINK (GROUP_ID ASC)
go

/*==============================================================*/
/* Index: IDX_GRPPOS_LNK_POST_ID                                */
/*==============================================================*/




create nonclustered index IDX_GRPPOS_LNK_POST_ID on dbo.YMMG_GROUP_POSITION_LINK (POSITION_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_GROUP_USER_LINK                                  */
/*==============================================================*/
create table dbo.YMMG_GROUP_USER_LINK (
   ID                   varchar(64)          not null,
   GROUP_ID             varchar(64)          not null,
   USER_ID              varchar(64)          not null,
   IS_MASTER            numeric(11)          not null default 0,
   POSITION_ID          varchar(64)          null,
   constraint SYS_C0012319 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_GROUP_USER_LINK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP_USER_LINK'

end


execute sp_addextendedproperty 'MS_Description',
   '用户组织关系',
   'user', 'dbo', 'table', 'YMMG_GROUP_USER_LINK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_GROUP_USER_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_MASTER')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_GROUP_USER_LINK', 'column', 'IS_MASTER'

end


execute sp_addextendedproperty 'MS_Description',
   '0:非主部门，1：主部门',
   'user', 'dbo', 'table', 'YMMG_GROUP_USER_LINK', 'column', 'IS_MASTER'
go

/*==============================================================*/
/* Index: IDX_GRP_USER_LNK_GROUP_ID                             */
/*==============================================================*/




create nonclustered index IDX_GRP_USER_LNK_GROUP_ID on dbo.YMMG_GROUP_USER_LINK (GROUP_ID ASC)
go

/*==============================================================*/
/* Index: IDX_GRP_USER_LNK_POSITION_ID                          */
/*==============================================================*/




create nonclustered index IDX_GRP_USER_LNK_POSITION_ID on dbo.YMMG_GROUP_USER_LINK (POSITION_ID ASC)
go

/*==============================================================*/
/* Index: IDX_GRP_USER_LNK_USER_ID                              */
/*==============================================================*/




create nonclustered index IDX_GRP_USER_LNK_USER_ID on dbo.YMMG_GROUP_USER_LINK (USER_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_LOG_ERR                                          */
/*==============================================================*/
create table dbo.YMMG_LOG_ERR (
   ID                   varchar(50)          not null,
   ACCOUNT              varchar(20)          null,
   IP                   varchar(20)          null,
   URL                  varchar(1500)        null,
   CONTENT              text                 null,
   CREATE_TIME          datetime             null,
   STACK_TRACE          text                 null,
   constraint SYS_C0012361 primary key (ID)
)
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_LOG_ERR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_LOG_ERR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACCOUNT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'ACCOUNT'

end


execute sp_addextendedproperty 'MS_Description',
   '帐号',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'ACCOUNT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_LOG_ERR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IP')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'IP'

end


execute sp_addextendedproperty 'MS_Description',
   'IP来源',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'IP'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_LOG_ERR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'URL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'URL'

end


execute sp_addextendedproperty 'MS_Description',
   '错误URL',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'URL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_LOG_ERR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CONTENT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'CONTENT'

end


execute sp_addextendedproperty 'MS_Description',
   '出错信息',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'CONTENT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_LOG_ERR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '出错时间',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_LOG_ERR')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STACK_TRACE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'STACK_TRACE'

end


execute sp_addextendedproperty 'MS_Description',
   '出错异常堆栈',
   'user', 'dbo', 'table', 'YMMG_LOG_ERR', 'column', 'STACK_TRACE'
go

/*==============================================================*/
/* Table: YMMG_POSITION                                         */
/*==============================================================*/
create table dbo.YMMG_POSITION (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          not null,
   CODE                 varchar(64)          not null,
   POST_LEVEL           varchar(64)          null,
   DESCRIPTION          varchar(500)         null,
   constraint SYS_C0012314 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_POSITION') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_POSITION'

end


execute sp_addextendedproperty 'MS_Description',
   '岗位定义表',
   'user', 'dbo', 'table', 'YMMG_POSITION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_POSITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名称',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_POSITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'CODE'

end


execute sp_addextendedproperty 'MS_Description',
   '编码',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'CODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_POSITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'POST_LEVEL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'POST_LEVEL'

end


execute sp_addextendedproperty 'MS_Description',
   '职务级别',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'POST_LEVEL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_POSITION')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_POSITION', 'column', 'DESCRIPTION'
go

/*==============================================================*/
/* Table: YMMG_PROPERTIES                                       */
/*==============================================================*/
create table dbo.YMMG_PROPERTIES (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          null,
   ALIAS                varchar(64)          null,
   CATEGORY             varchar(64)          null,
   VALUE                varchar(500)         null,
   ENCRYPT              numeric(11)          null,
   UPDATE_BY            varchar(64)          null,
   UPDATE_TIME          datetime             null,
   CREATE_BY            varchar(64)          null,
   CREATE_TIME          datetime             null,
   DESCRIPTION          varchar(500)         null,
   ENVIRONMENT          varchar(64)          null,
   constraint SYS_C0012363 primary key (ID)
)
go

/*==============================================================*/
/* Table: YMMG_RESOURCE                                         */
/*==============================================================*/
create table dbo.YMMG_RESOURCE (
   ID                   varchar(50)          not null,
   SYSTEM_ID            varchar(50)          null,
   ALIAS                varchar(50)          null,
   NAME                 varchar(50)          null,
   DEFAULT_URL          varchar(50)          null,
   ENABLE_MENU          numeric(11)          null,
   HAS_CHILDREN         numeric(11)          null,
   OPENED               numeric(11)          null,
   ICON                 varchar(50)          null,
   NEW_WINDOW           numeric(11)          null,
   SEQ                  numeric(20)          null,
   PARENT_ID            varchar(50)          null,
   CREATE_TIME          datetime             null,
   constraint SYS_C0012369 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_RESOURCE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE'

end


execute sp_addextendedproperty 'MS_Description',
   '子系统资源',
   'user', 'dbo', 'table', 'YMMG_RESOURCE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SYSTEM_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'SYSTEM_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '子系统ID',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'SYSTEM_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ALIAS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ALIAS'

end


execute sp_addextendedproperty 'MS_Description',
   '子系统中独一无二',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ALIAS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '资源名',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DEFAULT_URL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'DEFAULT_URL'

end


execute sp_addextendedproperty 'MS_Description',
   '默认地址',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'DEFAULT_URL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ENABLE_MENU')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ENABLE_MENU'

end


execute sp_addextendedproperty 'MS_Description',
   '显示到菜单(1,显示,0 ,不显示)',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ENABLE_MENU'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'HAS_CHILDREN')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'HAS_CHILDREN'

end


execute sp_addextendedproperty 'MS_Description',
   '是否有子节点',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'HAS_CHILDREN'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ICON')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ICON'

end


execute sp_addextendedproperty 'MS_Description',
   '图标',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'ICON'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NEW_WINDOW')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'NEW_WINDOW'

end


execute sp_addextendedproperty 'MS_Description',
   '打开新窗口',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'NEW_WINDOW'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SEQ')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'SEQ'

end


execute sp_addextendedproperty 'MS_Description',
   '排序',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'SEQ'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PARENT_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'PARENT_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '父节点ID',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'PARENT_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_RESOURCE', 'column', 'CREATE_TIME'
go

/*==============================================================*/
/* Table: YMMG_RESOURCE_LINK                                    */
/*==============================================================*/
create table dbo.YMMG_RESOURCE_LINK (
   ID                   varchar(50)          not null,
   RES_ID               varchar(50)          null,
   NAME                 varchar(50)          null,
   RES_URL              varchar(100)         null,
   constraint SYS_C0012365 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_RESOURCE_LINK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK'

end


execute sp_addextendedproperty 'MS_Description',
   '关联资源',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RES_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'RES_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '资源ID',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'RES_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名称',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RES_URL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'RES_URL'

end


execute sp_addextendedproperty 'MS_Description',
   '资源地址',
   'user', 'dbo', 'table', 'YMMG_RESOURCE_LINK', 'column', 'RES_URL'
go

/*==============================================================*/
/* Table: YMMG_ROLE                                             */
/*==============================================================*/
create table dbo.YMMG_ROLE (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          not null,
   ALIAS                varchar(64)          not null,
   ENABLED              numeric(11)          not null default 1,
   DESCRIPTION          varchar(200)         not null,
   constraint SYS_C0012325 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_ROLE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE'

end


execute sp_addextendedproperty 'MS_Description',
   '角色管理',
   'user', 'dbo', 'table', 'YMMG_ROLE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '角色名称',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ALIAS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'ALIAS'

end


execute sp_addextendedproperty 'MS_Description',
   '英文别名',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'ALIAS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ENABLED')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'ENABLED'

end


execute sp_addextendedproperty 'MS_Description',
   '0:禁用1:启用',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'ENABLED'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_ROLE', 'column', 'DESCRIPTION'
go

/*==============================================================*/
/* Table: YMMG_ROLE_RESOURCE_LINK                               */
/*==============================================================*/
create table dbo.YMMG_ROLE_RESOURCE_LINK (
   ID                   varchar(50)          not null default '',
   SYSTEM_ID            varchar(50)          null,
   RES_ID               varchar(50)          null,
   ROLE_ID              varchar(50)          null,
   constraint SYS_C0012367 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_ROLE_RESOURCE_LINK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK'

end


execute sp_addextendedproperty 'MS_Description',
   '角色资源分配',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SYSTEM_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'SYSTEM_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '系统ID',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'SYSTEM_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RES_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'RES_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '资源ID',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'RES_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_ROLE_RESOURCE_LINK')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ROLE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'ROLE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '角色ID',
   'user', 'dbo', 'table', 'YMMG_ROLE_RESOURCE_LINK', 'column', 'ROLE_ID'
go

/*==============================================================*/
/* Table: YMMG_SCHEDULE_JOB                                     */
/*==============================================================*/
create table dbo.YMMG_SCHEDULE_JOB (
   ID                   varchar(64)          not null,
   NAME                 varchar(50)          not null,
   CATEGORY             varchar(100)         not null,
   DESCRIPTION          varchar(1000)        null,
   INVOKE_TARGET        varchar(500)         not null,
   CRON_EXPRESSION      varchar(50)          not null,
   RUNNING_STATE        varchar(10)          not null,
   IS_CONCURRENT        numeric(4)           not null default 0,
   CREATE_BY            varchar(50)          null,
   CREATE_TIME          datetime             null,
   UPDATE_BY            varchar(50)          null,
   UPDATE_TIME          datetime             null,
   IS_DELETE            numeric(4)           null default 0,
   constraint SYS_C0012377 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_SCHEDULE_JOB') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB'

end


execute sp_addextendedproperty 'MS_Description',
   '系统任务计划',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键编号',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '任务计划名称',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CATEGORY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CATEGORY'

end


execute sp_addextendedproperty 'MS_Description',
   '任务计划分组',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CATEGORY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '任务计划说明',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'INVOKE_TARGET')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'INVOKE_TARGET'

end


execute sp_addextendedproperty 'MS_Description',
   '调用目标',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'INVOKE_TARGET'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CRON_EXPRESSION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CRON_EXPRESSION'

end


execute sp_addextendedproperty 'MS_Description',
   '运行表达式',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CRON_EXPRESSION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RUNNING_STATE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'RUNNING_STATE'

end


execute sp_addextendedproperty 'MS_Description',
   '运行状态',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'RUNNING_STATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_CONCURRENT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'IS_CONCURRENT'

end


execute sp_addextendedproperty 'MS_Description',
   '是否并发执行',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'IS_CONCURRENT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CREATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '创建用户',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CREATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'UPDATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '修改用户',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'UPDATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'UPDATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '修改时间',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'UPDATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_DELETE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'IS_DELETE'

end


execute sp_addextendedproperty 'MS_Description',
   '有效记录:0:正常1:已删除',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB', 'column', 'IS_DELETE'
go

/*==============================================================*/
/* Table: YMMG_SCHEDULE_JOB_LOG                                 */
/*==============================================================*/
create table dbo.YMMG_SCHEDULE_JOB_LOG (
   ID                   varchar(64)          not null,
   JOB_ID               varchar(64)          not null,
   RUN_STATE            varchar(50)          not null,
   RUN_MS               numeric(11)          not null,
   CONTENT              text                 null,
   START_TIME           datetime             not null,
   END_TIME             datetime             not null,
   CREATE_BY            varchar(50)          null,
   CREATE_TIME          datetime             null,
   UPDATE_BY            varchar(50)          null,
   UPDATE_TIME          datetime             null,
   IS_DELETE            numeric(4)           null default 0,
   constraint SYS_C0012384 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG'

end


execute sp_addextendedproperty 'MS_Description',
   '任务计划日志',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '编号',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'JOB_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'JOB_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '任务计划ID',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'JOB_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RUN_STATE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'RUN_STATE'

end


execute sp_addextendedproperty 'MS_Description',
   '执行状态',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'RUN_STATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RUN_MS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'RUN_MS'

end


execute sp_addextendedproperty 'MS_Description',
   '运行毫秒',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'RUN_MS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CONTENT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'CONTENT'

end


execute sp_addextendedproperty 'MS_Description',
   '运行内容',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'CONTENT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'START_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'START_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '运行启动时间',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'START_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'END_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'END_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '运行结束时间',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'END_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'CREATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '创建用户',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'CREATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_BY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'UPDATE_BY'

end


execute sp_addextendedproperty 'MS_Description',
   '修改用户',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'UPDATE_BY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UPDATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'UPDATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '修改时间',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'UPDATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCHEDULE_JOB_LOG')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_DELETE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'IS_DELETE'

end


execute sp_addextendedproperty 'MS_Description',
   '有效记录:0:正常1:已删除',
   'user', 'dbo', 'table', 'YMMG_SCHEDULE_JOB_LOG', 'column', 'IS_DELETE'
go

/*==============================================================*/
/* Index: IDX_SCH_JOB_LOGJOB_ID                                 */
/*==============================================================*/




create nonclustered index IDX_SCH_JOB_LOGJOB_ID on dbo.YMMG_SCHEDULE_JOB_LOG (JOB_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_SCRIPT                                           */
/*==============================================================*/
create table dbo.YMMG_SCRIPT (
   ID                   varchar(64)          not null,
   NAME                 varchar(128)         null,
   SCRIPT               text                 null,
   CATEGORY             varchar(128)         null,
   NOTES                varchar(512)         null,
   constraint SYS_C0012386 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_SCRIPT') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCRIPT'

end


execute sp_addextendedproperty 'MS_Description',
   '常用脚本',
   'user', 'dbo', 'table', 'YMMG_SCRIPT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCRIPT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCRIPT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '脚本名称',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCRIPT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SCRIPT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'SCRIPT'

end


execute sp_addextendedproperty 'MS_Description',
   '脚本',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'SCRIPT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCRIPT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CATEGORY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'CATEGORY'

end


execute sp_addextendedproperty 'MS_Description',
   '脚本分类',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'CATEGORY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SCRIPT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NOTES')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'NOTES'

end


execute sp_addextendedproperty 'MS_Description',
   '备注',
   'user', 'dbo', 'table', 'YMMG_SCRIPT', 'column', 'NOTES'
go

/*==============================================================*/
/* Table: YMMG_SERIALNO                                         */
/*==============================================================*/
create table dbo.YMMG_SERIALNO (
   ID                   varchar(64)          not null,
   NAME                 varchar(64)          null,
   ALIAS                varchar(20)          null,
   "RULE"               varchar(128)         null,
   GEN_TYPE             numeric(6)           null,
   NO_LENGTH            numeric(11)          null,
   CUR_DATE             varchar(20)          null,
   INIT_VALUE           numeric(11)          null,
   CUR_VALUE            numeric(11)          null,
   STEP                 numeric(6)           null,
   constraint SYS_C0012388 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_SERIALNO') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO'

end


execute sp_addextendedproperty 'MS_Description',
   '流水号生成',
   'user', 'dbo', 'table', 'YMMG_SERIALNO'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名称',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ALIAS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'ALIAS'

end


execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'ALIAS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RULE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'RULE'

end


execute sp_addextendedproperty 'MS_Description',
   '规则',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'RULE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'GEN_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'GEN_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '生成类型',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'GEN_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NO_LENGTH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'NO_LENGTH'

end


execute sp_addextendedproperty 'MS_Description',
   '流水号长度',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'NO_LENGTH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CUR_DATE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'CUR_DATE'

end


execute sp_addextendedproperty 'MS_Description',
   '当前日期',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'CUR_DATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'INIT_VALUE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'INIT_VALUE'

end


execute sp_addextendedproperty 'MS_Description',
   '初始值',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'INIT_VALUE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CUR_VALUE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'CUR_VALUE'

end


execute sp_addextendedproperty 'MS_Description',
   '当前值',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'CUR_VALUE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SERIALNO')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STEP')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'STEP'

end


execute sp_addextendedproperty 'MS_Description',
   '步长',
   'user', 'dbo', 'table', 'YMMG_SERIALNO', 'column', 'STEP'
go

/*==============================================================*/
/* Index: IDX_UNI_SERIALNO_ALIAS_VAL                            */
/*==============================================================*/




create nonclustered index IDX_UNI_SERIALNO_ALIAS_VAL on dbo.YMMG_SERIALNO (ALIAS ASC,
  CUR_VALUE ASC)
go

/*==============================================================*/
/* Table: YMMG_SUBSYSTEM                                        */
/*==============================================================*/
create table dbo.YMMG_SUBSYSTEM (
   ID                   varchar(50)          not null,
   NAME                 varchar(50)          null,
   ALIAS                varchar(50)          null,
   LOGO                 varchar(50)          null,
   ENABLED              numeric(11)          null,
   HOME_URL             varchar(100)         null,
   BASE_URL             varchar(50)          null,
   TENANT               varchar(50)          null,
   NOTES                varchar(200)         null,
   CREATOR_ID           varchar(50)          null,
   CREATOR              varchar(50)          null,
   CREATE_TIME          datetime             null,
   IS_DEFAULT           numeric(11)          null,
   constraint SYS_C0012390 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_SUBSYSTEM') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM'

end


execute sp_addextendedproperty 'MS_Description',
   '子系统定义',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '系统名称',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ALIAS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'ALIAS'

end


execute sp_addextendedproperty 'MS_Description',
   '系统别名',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'ALIAS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'LOGO')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'LOGO'

end


execute sp_addextendedproperty 'MS_Description',
   'LOGO地址',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'LOGO'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ENABLED')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'ENABLED'

end


execute sp_addextendedproperty 'MS_Description',
   '是否可用1:可用0:不可用',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'ENABLED'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'HOME_URL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'HOME_URL'

end


execute sp_addextendedproperty 'MS_Description',
   '主页地址',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'HOME_URL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'BASE_URL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'BASE_URL'

end


execute sp_addextendedproperty 'MS_Description',
   '基础地址',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'BASE_URL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TENANT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'TENANT'

end


execute sp_addextendedproperty 'MS_Description',
   '租户名称',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'TENANT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NOTES')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'NOTES'

end


execute sp_addextendedproperty 'MS_Description',
   '备注',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'NOTES'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATOR_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'CREATOR_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人ID',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'CREATOR_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATOR')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'CREATOR'

end


execute sp_addextendedproperty 'MS_Description',
   '创建人',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'CREATOR'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_SUBSYSTEM')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_DEFAULT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'IS_DEFAULT'

end


execute sp_addextendedproperty 'MS_Description',
   '是否默认1:可用0:不可用',
   'user', 'dbo', 'table', 'YMMG_SUBSYSTEM', 'column', 'IS_DEFAULT'
go

/*==============================================================*/
/* Table: YMMG_TREE                                             */
/*==============================================================*/
create table dbo.YMMG_TREE (
   ID                   varchar(64)          not null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(256)         null,
   DESCRIPTION          varchar(256)         null,
   IS_SYSTEM            numeric(4)           null,
   constraint SYS_C0012392 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_TREE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE'

end


execute sp_addextendedproperty 'MS_Description',
   '系统树',
   'user', 'dbo', 'table', 'YMMG_TREE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'IS_SYSTEM')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'IS_SYSTEM'

end


execute sp_addextendedproperty 'MS_Description',
   '是否系统内置树',
   'user', 'dbo', 'table', 'YMMG_TREE', 'column', 'IS_SYSTEM'
go

/*==============================================================*/
/* Index: IDX_YMMG_TREE_KEY                                     */
/*==============================================================*/




create unique nonclustered index IDX_YMMG_TREE_KEY on dbo.YMMG_TREE ("KEY" ASC)
go

/*==============================================================*/
/* Table: YMMG_TREE_NODE                                        */
/*==============================================================*/
create table dbo.YMMG_TREE_NODE (
   ID                   varchar(64)          not null,
   "KEY"                varchar(64)          null,
   NAME                 varchar(128)         null,
   DESCRIPTION          varchar(256)         null,
   TREE_ID              varchar(64)          null,
   PARENT_ID            varchar(64)          null,
   PATH                 varchar(512)         null,
   SEQ                  numeric(4)           null,
   constraint SYS_C0012394 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_TREE_NODE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE'

end


execute sp_addextendedproperty 'MS_Description',
   '系统树节点',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'ID'

end


execute sp_addextendedproperty 'MS_Description',
   '主键',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TREE_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'TREE_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '所属树ID',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'TREE_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PARENT_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'PARENT_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '父ID',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'PARENT_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PATH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'PATH'

end


execute sp_addextendedproperty 'MS_Description',
   '路径 EG:PPPID.PPID.PID',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'PATH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_TREE_NODE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SEQ')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'SEQ'

end


execute sp_addextendedproperty 'MS_Description',
   '排序号',
   'user', 'dbo', 'table', 'YMMG_TREE_NODE', 'column', 'SEQ'
go

/*==============================================================*/
/* Index: IDX_YMMG_TREE_NODE_KEY                                */
/*==============================================================*/




create unique nonclustered index IDX_YMMG_TREE_NODE_KEY on dbo.YMMG_TREE_NODE ("KEY" ASC,
  TREE_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_USER                                             */
/*==============================================================*/
create table dbo.YMMG_USER (
   ID                   varchar(64)          not null,
   FULLNAME             varchar(255)         not null,
   ACCOUNT              varchar(255)         not null,
   PASSWORD             varchar(64)          not null,
   EMAIL                varchar(64)          null,
   MOBILE               varchar(32)          null,
   WEIXIN               varchar(64)          null,
   CREATE_TIME          datetime             null,
   ADDRESS              varchar(512)         null,
   PHOTO                varchar(255)         null,
   SEX                  varchar(10)          null,
   USERFROM             varchar(64)          null,
   STATUS               numeric(11)          not null default 1,
   constraint SYS_C0012331 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_USER') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER'

end


execute sp_addextendedproperty 'MS_Description',
   '用户表',
   'user', 'dbo', 'table', 'YMMG_USER'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'FULLNAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'FULLNAME'

end


execute sp_addextendedproperty 'MS_Description',
   '姓名',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'FULLNAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ACCOUNT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'ACCOUNT'

end


execute sp_addextendedproperty 'MS_Description',
   '账号',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'ACCOUNT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PASSWORD')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'PASSWORD'

end


execute sp_addextendedproperty 'MS_Description',
   '密码',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'PASSWORD'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'EMAIL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'EMAIL'

end


execute sp_addextendedproperty 'MS_Description',
   '邮箱',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'EMAIL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MOBILE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'MOBILE'

end


execute sp_addextendedproperty 'MS_Description',
   '手机号码',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'MOBILE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'WEIXIN')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'WEIXIN'

end


execute sp_addextendedproperty 'MS_Description',
   '微信号',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'WEIXIN'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'CREATE_TIME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ADDRESS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'ADDRESS'

end


execute sp_addextendedproperty 'MS_Description',
   '地址',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'ADDRESS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PHOTO')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'PHOTO'

end


execute sp_addextendedproperty 'MS_Description',
   '头像',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'PHOTO'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SEX')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'SEX'

end


execute sp_addextendedproperty 'MS_Description',
   '性别：男，女，未知',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'SEX'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'USERFROM')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'USERFROM'

end


execute sp_addextendedproperty 'MS_Description',
   '来源',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'USERFROM'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_USER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STATUS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'STATUS'

end


execute sp_addextendedproperty 'MS_Description',
   '0:禁用，1正常',
   'user', 'dbo', 'table', 'YMMG_USER', 'column', 'STATUS'
go

/*==============================================================*/
/* Table: YMMG_USER_ROLE_LINK                                   */
/*==============================================================*/
create table dbo.YMMG_USER_ROLE_LINK (
   ID                   varchar(64)          not null,
   ROLE_ID              varchar(64)          not null,
   USER_ID              varchar(64)          not null,
   ENABLED              numeric(11)          not null default 1,
   constraint SYS_C0012336 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_USER_ROLE_LINK') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_USER_ROLE_LINK'

end


execute sp_addextendedproperty 'MS_Description',
   '用户角色关联表',
   'user', 'dbo', 'table', 'YMMG_USER_ROLE_LINK'
go

/*==============================================================*/
/* Index: IDX_UR_LNK_ROLE_ID                                    */
/*==============================================================*/




create nonclustered index IDX_UR_LNK_ROLE_ID on dbo.YMMG_USER_ROLE_LINK (ROLE_ID ASC)
go

/*==============================================================*/
/* Index: IDX_UR_LNK_USER_ID                                    */
/*==============================================================*/




create nonclustered index IDX_UR_LNK_USER_ID on dbo.YMMG_USER_ROLE_LINK (USER_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_WORKBENCH_LAYOUT                                 */
/*==============================================================*/
create table dbo.YMMG_WORKBENCH_LAYOUT (
   ID                   varchar(64)          not null,
   PANEL_ID             varchar(255)         not null,
   CUST_WIDTH           numeric(11)          null,
   CUST_HEIGHT          numeric(11)          null,
   SEQ                  numeric(11)          null,
   USER_ID              varchar(64)          not null,
   CREATE_TIME          datetime             null,
   constraint SYS_C0012398 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_WORKBENCH_LAYOUT') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT'

end


execute sp_addextendedproperty 'MS_Description',
   '工作台布局',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PANEL_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'PANEL_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '面板ID',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'PANEL_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CUST_WIDTH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'CUST_WIDTH'

end


execute sp_addextendedproperty 'MS_Description',
   '自定义宽',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'CUST_WIDTH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CUST_HEIGHT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'CUST_HEIGHT'

end


execute sp_addextendedproperty 'MS_Description',
   '自定义高',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'CUST_HEIGHT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'SEQ')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'SEQ'

end


execute sp_addextendedproperty 'MS_Description',
   '排序',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'SEQ'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'USER_ID')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'USER_ID'

end


execute sp_addextendedproperty 'MS_Description',
   '用户ID',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'USER_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_LAYOUT')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CREATE_TIME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'CREATE_TIME'

end


execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_LAYOUT', 'column', 'CREATE_TIME'
go

/*==============================================================*/
/* Index: IDX_WORKBENCH_LAYOUT_PANEL_ID                         */
/*==============================================================*/




create nonclustered index IDX_WORKBENCH_LAYOUT_PANEL_ID on dbo.YMMG_WORKBENCH_LAYOUT (PANEL_ID ASC)
go

/*==============================================================*/
/* Table: YMMG_WORKBENCH_PANEL                                  */
/*==============================================================*/
create table dbo.YMMG_WORKBENCH_PANEL (
   ID                   varchar(64)          not null,
   ALIAS                varchar(255)         not null,
   NAME                 varchar(255)         not null default '',
   TYPE                 varchar(64)          null,
   DESCRIPTION          varchar(500)         null,
   DATA_TYPE            varchar(64)          null,
   DATA_SOURCE          varchar(255)         null,
   AUTO_REFRESH         numeric(11)          null default 0,
   WIDTH                numeric(11)          null,
   HEIGHT               numeric(11)          null,
   DISPLAY_CONTENT      text                 null,
   MORE_URL             varchar(255)         null,
   CREATE_TIME          datetime             null,
   CREATE_BY            varchar(64)          null,
   UPDATE_TIME          datetime             null,
   UPDATE_BY            varchar(64)          null,
   DELETE_FLAG          varchar(10)          null,
   constraint SYS_C0012402 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_WORKBENCH_PANEL') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL'

end


execute sp_addextendedproperty 'MS_Description',
   '工作台面板',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ALIAS')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'ALIAS'

end


execute sp_addextendedproperty 'MS_Description',
   '标识',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'ALIAS'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'NAME'

end


execute sp_addextendedproperty 'MS_Description',
   '名字',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '类型',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DATA_TYPE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DATA_TYPE'

end


execute sp_addextendedproperty 'MS_Description',
   '数据类型',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DATA_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DATA_SOURCE')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DATA_SOURCE'

end


execute sp_addextendedproperty 'MS_Description',
   '数据来源',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DATA_SOURCE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'AUTO_REFRESH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'AUTO_REFRESH'

end


execute sp_addextendedproperty 'MS_Description',
   '自动刷新',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'AUTO_REFRESH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'WIDTH')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'WIDTH'

end


execute sp_addextendedproperty 'MS_Description',
   '宽',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'WIDTH'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'HEIGHT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'HEIGHT'

end


execute sp_addextendedproperty 'MS_Description',
   '高',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'HEIGHT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DISPLAY_CONTENT')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DISPLAY_CONTENT'

end


execute sp_addextendedproperty 'MS_Description',
   '展示内容',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'DISPLAY_CONTENT'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MORE_URL')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'MORE_URL'

end


execute sp_addextendedproperty 'MS_Description',
   '更多链接',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL', 'column', 'MORE_URL'
go

/*==============================================================*/
/* Index: IDX_WORKBENCH_PANEL_ALIAS                             */
/*==============================================================*/




create nonclustered index IDX_WORKBENCH_PANEL_ALIAS on dbo.YMMG_WORKBENCH_PANEL (ALIAS ASC)
go

/*==============================================================*/
/* Table: YMMG_WORKBENCH_PANEL_TEMPLATE                         */
/*==============================================================*/
create table dbo.YMMG_WORKBENCH_PANEL_TEMPLATE (
   ID                   varchar(64)          not null,
   "KEY"                varchar(255)         null,
   NAME                 varchar(255)         null,
   DESCRIPTION          varchar(500)         null,
   HTML                 text                 null,
   constraint SYS_C0012404 primary key (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('dbo.YMMG_WORKBENCH_PANEL_TEMPLATE') and minor_id = 0)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE'

end


execute sp_addextendedproperty 'MS_Description',
   '工作台面板模板',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'KEY')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE', 'column', 'KEY'

end


execute sp_addextendedproperty 'MS_Description',
   '模板KEY',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE', 'column', 'KEY'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'DESCRIPTION')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE', 'column', 'DESCRIPTION'

end


execute sp_addextendedproperty 'MS_Description',
   '模板描述',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE', 'column', 'DESCRIPTION'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('dbo.YMMG_WORKBENCH_PANEL_TEMPLATE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'HTML')
)
begin
   execute sp_dropextendedproperty 'MS_Description',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE', 'column', 'HTML'

end


execute sp_addextendedproperty 'MS_Description',
   '模板内容',
   'user', 'dbo', 'table', 'YMMG_WORKBENCH_PANEL_TEMPLATE', 'column', 'HTML'
go

alter table dbo.YMMG_GROUP_POSITION_LINK
   add constraint FK_GRP_POST_LNK_REF_GROUP foreign key (GROUP_ID)
      references dbo.YMMG_GROUP (ID)
         on delete  no action
go

alter table dbo.YMMG_GROUP_POSITION_LINK
   add constraint FK_GRP_POST_LNK_REF_POST foreign key (POSITION_ID)
      references dbo.YMMG_POSITION (ID)
         on delete  no action
go

alter table dbo.YMMG_GROUP_USER_LINK
   add constraint FK_GRP_U_LNK_REF_ foreign key (POSITION_ID)
      references dbo.YMMG_GROUP_POSITION_LINK (ID)
         on delete  no action
go

alter table dbo.YMMG_GROUP_USER_LINK
   add constraint FK_GRP_U_LNK_REF_GROUPID foreign key (GROUP_ID)
      references dbo.YMMG_GROUP (ID)
         on delete no action
go

alter table dbo.YMMG_GROUP_USER_LINK
   add constraint FK_GRP_U_LNK_REF_USERID foreign key (USER_ID)
      references dbo.YMMG_USER (ID)
         on delete  no action
go

alter table dbo.YMMG_USER_ROLE_LINK
   add constraint FK_USER_ROLE_REF_ROLEID foreign key (ROLE_ID)
      references dbo.YMMG_ROLE (ID)
         on delete  no action
go

alter table dbo.YMMG_USER_ROLE_LINK
   add constraint FK_USER_ROLE_REF_USERID foreign key (USER_ID)
      references dbo.YMMG_USER (ID)
         on delete  no action
go



--创建QUARTZ

--# thanks to George Papastamatopoulos for submitting this ... and Marko Lahma for
--# updating it.
--#
--# In your Quartz properties file, you'll need to set
--# org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.MSSQLDelegate
--#
--# you shouse enter your DB instance's name on the next line in place of "enter_db_name_here"
--#
--#
--# From a helpful (but anonymous) Quartz user:
--#
--# Regarding this error message:
--#
--#     [Microsoft][SQLServer 2000 Driver for JDBC]Can't start a cloned connection while in manual transaction mode.
--#
--#
--#     I added "SelectMethod=cursor;" to my Connection URL in the config file.
--#     It Seems to work, hopefully no side effects.
--#
--#		example:
--#		"jdbc:microsoft:sqlserver://dbmachine:1433;SelectMethod=cursor";
--#
--# Another user has pointed out that you will probably need to use the
--# JTDS driver
--#

USE iEMP
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[FK_QRTZ_TRIGGERS_QRTZ_JOB_DETAILS]') AND OBJECTPROPERTY(id, N'ISFOREIGNKEY') = 1)
ALTER TABLE [dbo].[QRTZ_TRIGGERS] DROP CONSTRAINT FK_QRTZ_TRIGGERS_QRTZ_JOB_DETAILS
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[FK_QRTZ_CRON_TRIGGERS_QRTZ_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISFOREIGNKEY') = 1)
ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS] DROP CONSTRAINT FK_QRTZ_CRON_TRIGGERS_QRTZ_TRIGGERS
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[FK_QRTZ_SIMPLE_TRIGGERS_QRTZ_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISFOREIGNKEY') = 1)
ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] DROP CONSTRAINT FK_QRTZ_SIMPLE_TRIGGERS_QRTZ_TRIGGERS
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[FK_QRTZ_SIMPROP_TRIGGERS_QRTZ_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISFOREIGNKEY') = 1)
ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] DROP CONSTRAINT FK_QRTZ_SIMPROP_TRIGGERS_QRTZ_TRIGGERS
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_CALENDARS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_CALENDARS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_CRON_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_CRON_TRIGGERS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_BLOB_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_BLOB_TRIGGERS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_FIRED_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_FIRED_TRIGGERS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_PAUSED_TRIGGER_GRPS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_SCHEDULER_STATE]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_SCHEDULER_STATE]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_LOCKS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_LOCKS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_JOB_DETAILS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_JOB_DETAILS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_SIMPLE_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_SIMPROP_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS]
GO

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[QRTZ_TRIGGERS]') AND OBJECTPROPERTY(id, N'ISUSERTABLE') = 1)
DROP TABLE [dbo].[QRTZ_TRIGGERS]
GO

CREATE TABLE [dbo].[QRTZ_CALENDARS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [CALENDAR_NAME] [VARCHAR] (200)  NOT NULL ,
  [CALENDAR] [IMAGE] NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_CRON_TRIGGERS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [TRIGGER_NAME] [VARCHAR] (200)  NOT NULL ,
  [TRIGGER_GROUP] [VARCHAR] (200)  NOT NULL ,
  [CRON_EXPRESSION] [VARCHAR] (120)  NOT NULL ,
  [TIME_ZONE_ID] [VARCHAR] (80)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_FIRED_TRIGGERS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [ENTRY_ID] [VARCHAR] (95)  NOT NULL ,
  [TRIGGER_NAME] [VARCHAR] (200)  NOT NULL ,
  [TRIGGER_GROUP] [VARCHAR] (200)  NOT NULL ,
  [INSTANCE_NAME] [VARCHAR] (200)  NOT NULL ,
  [FIRED_TIME] [BIGINT] NOT NULL ,
  [SCHED_TIME] [BIGINT] NOT NULL ,
  [PRIORITY] [INTEGER] NOT NULL ,
  [STATE] [VARCHAR] (16)  NOT NULL,
  [JOB_NAME] [VARCHAR] (200)  NULL ,
  [JOB_GROUP] [VARCHAR] (200)  NULL ,
  [IS_NONCONCURRENT] [VARCHAR] (1)  NULL ,
  [REQUESTS_RECOVERY] [VARCHAR] (1)  NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [TRIGGER_GROUP] [VARCHAR] (200)  NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_SCHEDULER_STATE] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [INSTANCE_NAME] [VARCHAR] (200)  NOT NULL ,
  [LAST_CHECKIN_TIME] [BIGINT] NOT NULL ,
  [CHECKIN_INTERVAL] [BIGINT] NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_LOCKS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [LOCK_NAME] [VARCHAR] (40)  NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_JOB_DETAILS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [JOB_NAME] [VARCHAR] (200)  NOT NULL ,
  [JOB_GROUP] [VARCHAR] (200)  NOT NULL ,
  [DESCRIPTION] [VARCHAR] (250) NULL ,
  [JOB_CLASS_NAME] [VARCHAR] (250)  NOT NULL ,
  [IS_DURABLE] [VARCHAR] (1)  NOT NULL ,
  [IS_NONCONCURRENT] [VARCHAR] (1)  NOT NULL ,
  [IS_UPDATE_DATA] [VARCHAR] (1)  NOT NULL ,
  [REQUESTS_RECOVERY] [VARCHAR] (1)  NOT NULL ,
  [JOB_DATA] [IMAGE] NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [TRIGGER_NAME] [VARCHAR] (200)  NOT NULL ,
  [TRIGGER_GROUP] [VARCHAR] (200)  NOT NULL ,
  [REPEAT_COUNT] [BIGINT] NOT NULL ,
  [REPEAT_INTERVAL] [BIGINT] NOT NULL ,
  [TIMES_TRIGGERED] [BIGINT] NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [TRIGGER_NAME] [VARCHAR] (200)  NOT NULL ,
  [TRIGGER_GROUP] [VARCHAR] (200)  NOT NULL ,
  [STR_PROP_1] [VARCHAR] (512) NULL,
  [STR_PROP_2] [VARCHAR] (512) NULL,
  [STR_PROP_3] [VARCHAR] (512) NULL,
  [INT_PROP_1] [INT] NULL,
  [INT_PROP_2] [INT] NULL,
  [LONG_PROP_1] [BIGINT] NULL,
  [LONG_PROP_2] [BIGINT] NULL,
  [DEC_PROP_1] [NUMERIC] (13,4) NULL,
  [DEC_PROP_2] [NUMERIC] (13,4) NULL,
  [BOOL_PROP_1] [VARCHAR] (1) NULL,
  [BOOL_PROP_2] [VARCHAR] (1) NULL,
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_BLOB_TRIGGERS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [TRIGGER_NAME] [VARCHAR] (200)  NOT NULL ,
  [TRIGGER_GROUP] [VARCHAR] (200)  NOT NULL ,
  [BLOB_DATA] [IMAGE] NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[QRTZ_TRIGGERS] (
  [SCHED_NAME] [VARCHAR] (120)  NOT NULL ,
  [TRIGGER_NAME] [VARCHAR] (200)  NOT NULL ,
  [TRIGGER_GROUP] [VARCHAR] (200)  NOT NULL ,
  [JOB_NAME] [VARCHAR] (200)  NOT NULL ,
  [JOB_GROUP] [VARCHAR] (200)  NOT NULL ,
  [DESCRIPTION] [VARCHAR] (250) NULL ,
  [NEXT_FIRE_TIME] [BIGINT] NULL ,
  [PREV_FIRE_TIME] [BIGINT] NULL ,
  [PRIORITY] [INTEGER] NULL ,
  [TRIGGER_STATE] [VARCHAR] (16)  NOT NULL ,
  [TRIGGER_TYPE] [VARCHAR] (8)  NOT NULL ,
  [START_TIME] [BIGINT] NOT NULL ,
  [END_TIME] [BIGINT] NULL ,
  [CALENDAR_NAME] [VARCHAR] (200)  NULL ,
  [MISFIRE_INSTR] [SMALLINT] NULL ,
  [JOB_DATA] [IMAGE] NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_CALENDARS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_CALENDARS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [CALENDAR_NAME]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_CRON_TRIGGERS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_FIRED_TRIGGERS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_FIRED_TRIGGERS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [ENTRY_ID]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_PAUSED_TRIGGER_GRPS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [TRIGGER_GROUP]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_SCHEDULER_STATE] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_SCHEDULER_STATE] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [INSTANCE_NAME]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_LOCKS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_LOCKS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [LOCK_NAME]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_JOB_DETAILS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_JOB_DETAILS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [JOB_NAME],
    [JOB_GROUP]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_SIMPLE_TRIGGERS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_SIMPROP_TRIGGERS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_TRIGGERS] WITH NOCHECK ADD
  CONSTRAINT [PK_QRTZ_TRIGGERS] PRIMARY KEY  CLUSTERED
  (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  )  ON [PRIMARY]
GO

ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS] ADD
  CONSTRAINT [FK_QRTZ_CRON_TRIGGERS_QRTZ_TRIGGERS] FOREIGN KEY
  (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  ) REFERENCES [dbo].[QRTZ_TRIGGERS] (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  ) ON DELETE CASCADE
GO

ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] ADD
  CONSTRAINT [FK_QRTZ_SIMPLE_TRIGGERS_QRTZ_TRIGGERS] FOREIGN KEY
  (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  ) REFERENCES [dbo].[QRTZ_TRIGGERS] (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  ) ON DELETE CASCADE
GO

ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] ADD
  CONSTRAINT [FK_QRTZ_SIMPROP_TRIGGERS_QRTZ_TRIGGERS] FOREIGN KEY
  (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  ) REFERENCES [dbo].[QRTZ_TRIGGERS] (
    [SCHED_NAME],
    [TRIGGER_NAME],
    [TRIGGER_GROUP]
  ) ON DELETE CASCADE
GO

ALTER TABLE [dbo].[QRTZ_TRIGGERS] ADD
  CONSTRAINT [FK_QRTZ_TRIGGERS_QRTZ_JOB_DETAILS] FOREIGN KEY
  (
    [SCHED_NAME],
    [JOB_NAME],
    [JOB_GROUP]
  ) REFERENCES [dbo].[QRTZ_JOB_DETAILS] (
    [SCHED_NAME],
    [JOB_NAME],
    [JOB_GROUP]
  )
GO



---创建流程引擎
create table ACT_GE_PROPERTY (
    NAME_ nvarchar(64),
    VALUE_ nvarchar(300),
    REV_ int,
    primary key (NAME_)
);

create table ACT_GE_BYTEARRAY (
    ID_ nvarchar(64),
    REV_ int,
    NAME_ nvarchar(255),
    DEPLOYMENT_ID_ nvarchar(64),
    BYTES_  varbinary(max),
    GENERATED_ tinyint,
    primary key (ID_)
);

insert into ACT_GE_PROPERTY
values ('common.schema.version', '6.3.1.0', 1);

insert into ACT_GE_PROPERTY
values ('next.dbid', '1', 1);

create table ACT_RU_IDENTITYLINK (
    ID_ nvarchar(64),
    REV_ int,
    GROUP_ID_ nvarchar(255),
    TYPE_ nvarchar(255),
    USER_ID_ nvarchar(255),
    TASK_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    primary key (ID_)
);

create index ACT_IDX_IDENT_LNK_USER on ACT_RU_IDENTITYLINK(USER_ID_);
create index ACT_IDX_IDENT_LNK_GROUP on ACT_RU_IDENTITYLINK(GROUP_ID_);
create index ACT_IDX_IDENT_LNK_SCOPE on ACT_RU_IDENTITYLINK(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_IDENT_LNK_SCOPE_DEF on ACT_RU_IDENTITYLINK(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

insert into ACT_GE_PROPERTY values ('identitylink.schema.version', '6.3.1.0', 1);
create table ACT_RU_TASK (
    ID_ nvarchar(64),
    REV_ int,
    EXECUTION_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    TASK_DEF_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    NAME_ nvarchar(255),
    PARENT_TASK_ID_ nvarchar(64),
    DESCRIPTION_ nvarchar(4000),
    TASK_DEF_KEY_ nvarchar(255),
    OWNER_ nvarchar(255),
    ASSIGNEE_ nvarchar(255),
    DELEGATION_ nvarchar(64),
    PRIORITY_ int,
    CREATE_TIME_ datetime,
    DUE_DATE_ datetime,
    CATEGORY_ nvarchar(255),
    SUSPENSION_STATE_ int,
    TENANT_ID_ nvarchar(255) default '',
    FORM_KEY_ nvarchar(255),
    CLAIM_TIME_ datetime,
    IS_COUNT_ENABLED_ tinyint,
    VAR_COUNT_ int,
    ID_LINK_COUNT_ int,
    SUB_TASK_COUNT_ int,
    primary key (ID_)
);

create index ACT_IDX_TASK_CREATE on ACT_RU_TASK(CREATE_TIME_);
create index ACT_IDX_TASK_SCOPE on ACT_RU_TASK(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TASK_SUB_SCOPE on ACT_RU_TASK(SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TASK_SCOPE_DEF on ACT_RU_TASK(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

insert into ACT_GE_PROPERTY values ('task.schema.version', '6.3.1.0', 1);
create table ACT_RU_VARIABLE (
    ID_ nvarchar(64) not null,
    REV_ int,
    TYPE_ nvarchar(255) not null,
    NAME_ nvarchar(255) not null,
    EXECUTION_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    TASK_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    BYTEARRAY_ID_ nvarchar(64),
    DOUBLE_ double precision,
    LONG_ numeric(19,0),
    TEXT_ nvarchar(4000),
    TEXT2_ nvarchar(4000),
    primary key (ID_)
);

create index ACT_IDX_RU_VAR_SCOPE_ID_TYPE on ACT_RU_VARIABLE(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_RU_VAR_SUB_ID_TYPE on ACT_RU_VARIABLE(SUB_SCOPE_ID_, SCOPE_TYPE_);

create index ACT_IDX_VARIABLE_BA on ACT_RU_VARIABLE(BYTEARRAY_ID_);
alter table ACT_RU_VARIABLE
    add constraint ACT_FK_VAR_BYTEARRAY
    foreign key (BYTEARRAY_ID_)
    references ACT_GE_BYTEARRAY (ID_);

insert into ACT_GE_PROPERTY values ('variable.schema.version', '6.3.1.0', 1);
create table ACT_RU_JOB (
    ID_ nvarchar(64) NOT NULL,
    REV_ int,
    TYPE_ nvarchar(255) NOT NULL,
    LOCK_EXP_TIME_ datetime,
    LOCK_OWNER_ nvarchar(255),
    EXCLUSIVE_ bit,
    EXECUTION_ID_ nvarchar(64),
    PROCESS_INSTANCE_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    RETRIES_ int,
    EXCEPTION_STACK_ID_ nvarchar(64),
    EXCEPTION_MSG_ nvarchar(4000),
    DUEDATE_ datetime NULL,
    REPEAT_ nvarchar(255),
    HANDLER_TYPE_ nvarchar(255),
    HANDLER_CFG_ nvarchar(4000),
    CUSTOM_VALUES_ID_ nvarchar(64),
    CREATE_TIME_ datetime2 NULL,
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_RU_TIMER_JOB (
    ID_ nvarchar(64) NOT NULL,
    REV_ int,
    TYPE_ nvarchar(255) NOT NULL,
    LOCK_EXP_TIME_ datetime,
    LOCK_OWNER_ nvarchar(255),
    EXCLUSIVE_ bit,
    EXECUTION_ID_ nvarchar(64),
    PROCESS_INSTANCE_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    RETRIES_ int,
    EXCEPTION_STACK_ID_ nvarchar(64),
    EXCEPTION_MSG_ nvarchar(4000),
    DUEDATE_ datetime NULL,
    REPEAT_ nvarchar(255),
    HANDLER_TYPE_ nvarchar(255),
    HANDLER_CFG_ nvarchar(4000),
    CUSTOM_VALUES_ID_ nvarchar(64),
    CREATE_TIME_ datetime2 NULL,
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_RU_SUSPENDED_JOB (
    ID_ nvarchar(64) NOT NULL,
    REV_ int,
    TYPE_ nvarchar(255) NOT NULL,
    EXCLUSIVE_ bit,
    EXECUTION_ID_ nvarchar(64),
    PROCESS_INSTANCE_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    RETRIES_ int,
    EXCEPTION_STACK_ID_ nvarchar(64),
    EXCEPTION_MSG_ nvarchar(4000),
    DUEDATE_ datetime NULL,
    REPEAT_ nvarchar(255),
    HANDLER_TYPE_ nvarchar(255),
    HANDLER_CFG_ nvarchar(4000),
    CUSTOM_VALUES_ID_ nvarchar(64),
    CREATE_TIME_ datetime2 NULL,
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_RU_DEADLETTER_JOB (
    ID_ nvarchar(64) NOT NULL,
    REV_ int,
    TYPE_ nvarchar(255) NOT NULL,
    EXCLUSIVE_ bit,
    EXECUTION_ID_ nvarchar(64),
    PROCESS_INSTANCE_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    EXCEPTION_STACK_ID_ nvarchar(64),
    EXCEPTION_MSG_ nvarchar(4000),
    DUEDATE_ datetime NULL,
    REPEAT_ nvarchar(255),
    HANDLER_TYPE_ nvarchar(255),
    HANDLER_CFG_ nvarchar(4000),
    CUSTOM_VALUES_ID_ nvarchar(64),
    CREATE_TIME_ datetime2 NULL,
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_RU_HISTORY_JOB (
    ID_ nvarchar(64) NOT NULL,
    REV_ int,
    LOCK_EXP_TIME_ datetime NULL,
    LOCK_OWNER_ nvarchar(255),
    RETRIES_ int,
    EXCEPTION_STACK_ID_ nvarchar(64),
    EXCEPTION_MSG_ nvarchar(4000),
    HANDLER_TYPE_ nvarchar(255),
    HANDLER_CFG_ nvarchar(4000),
    CUSTOM_VALUES_ID_ nvarchar(64),
    ADV_HANDLER_CFG_ID_ nvarchar(64),
    CREATE_TIME_ datetime2 NULL,
    SCOPE_TYPE_ nvarchar(255),
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create index ACT_IDX_JOB_EXCEPTION_STACK_ID on ACT_RU_JOB(EXCEPTION_STACK_ID_);
create index ACT_IDX_JOB_CUSTOM_VALUES_ID on ACT_RU_JOB(CUSTOM_VALUES_ID_);

create index ACT_IDX_TIMER_JOB_EXCEPTION_STACK_ID on ACT_RU_TIMER_JOB(EXCEPTION_STACK_ID_);
create index ACT_IDX_TIMER_JOB_CUSTOM_VALUES_ID on ACT_RU_TIMER_JOB(CUSTOM_VALUES_ID_);

create index ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID on ACT_RU_SUSPENDED_JOB(EXCEPTION_STACK_ID_);
create index ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID on ACT_RU_SUSPENDED_JOB(CUSTOM_VALUES_ID_);

create index ACT_IDX_DEADLETTER_JOB_EXCEPTION_STACK_ID on ACT_RU_DEADLETTER_JOB(EXCEPTION_STACK_ID_);
create index ACT_IDX_DEADLETTER_JOB_CUSTOM_VALUES_ID on ACT_RU_DEADLETTER_JOB(CUSTOM_VALUES_ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_CUSTOM_VALUES
    foreign key (CUSTOM_VALUES_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_CUSTOM_VALUES
    foreign key (CUSTOM_VALUES_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_CUSTOM_VALUES
    foreign key (CUSTOM_VALUES_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_CUSTOM_VALUES
    foreign key (CUSTOM_VALUES_ID_)
    references ACT_GE_BYTEARRAY (ID_);

create index ACT_IDX_JOB_SCOPE on ACT_RU_JOB(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_JOB_SUB_SCOPE on ACT_RU_JOB(SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_JOB_SCOPE_DEF on ACT_RU_JOB(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create index ACT_IDX_TJOB_SCOPE on ACT_RU_TIMER_JOB(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TJOB_SUB_SCOPE on ACT_RU_TIMER_JOB(SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TJOB_SCOPE_DEF on ACT_RU_TIMER_JOB(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create index ACT_IDX_SJOB_SCOPE on ACT_RU_SUSPENDED_JOB(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_SJOB_SUB_SCOPE on ACT_RU_SUSPENDED_JOB(SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_SJOB_SCOPE_DEF on ACT_RU_SUSPENDED_JOB(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create index ACT_IDX_DJOB_SCOPE on ACT_RU_DEADLETTER_JOB(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_DJOB_SUB_SCOPE on ACT_RU_DEADLETTER_JOB(SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_DJOB_SCOPE_DEF on ACT_RU_DEADLETTER_JOB(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

insert into ACT_GE_PROPERTY values ('job.schema.version', '6.3.1.0', 1);
create table ACT_RE_DEPLOYMENT (
    ID_ nvarchar(64),
    NAME_ nvarchar(255),
    CATEGORY_ nvarchar(255),
    KEY_ nvarchar(255),
    TENANT_ID_ nvarchar(255) default '',
    DEPLOY_TIME_ datetime,
    DERIVED_FROM_ nvarchar(64),
    DERIVED_FROM_ROOT_ nvarchar(64),
    PARENT_DEPLOYMENT_ID_ nvarchar(255),
    ENGINE_VERSION_ nvarchar(255),
    primary key (ID_)
);

create table ACT_RE_MODEL (
    ID_ nvarchar(64) not null,
    REV_ int,
    NAME_ nvarchar(255),
    KEY_ nvarchar(255),
    CATEGORY_ nvarchar(255),
    CREATE_TIME_ datetime,
    LAST_UPDATE_TIME_ datetime,
    VERSION_ int,
    META_INFO_ nvarchar(4000),
    DEPLOYMENT_ID_ nvarchar(64),
    EDITOR_SOURCE_VALUE_ID_ nvarchar(64),
    EDITOR_SOURCE_EXTRA_VALUE_ID_ nvarchar(64),
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_RU_EXECUTION (
    ID_ nvarchar(64),
    REV_ int,
    PROC_INST_ID_ nvarchar(64),
    BUSINESS_KEY_ nvarchar(255),
    PARENT_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    SUPER_EXEC_ nvarchar(64),
    ROOT_PROC_INST_ID_ nvarchar(64),
    ACT_ID_ nvarchar(255),
    IS_ACTIVE_ tinyint,
    IS_CONCURRENT_ tinyint,
    IS_SCOPE_ tinyint,
    IS_EVENT_SCOPE_ tinyint,
    IS_MI_ROOT_ tinyint,
    SUSPENSION_STATE_ tinyint,
    CACHED_ENT_STATE_ int,
    TENANT_ID_ nvarchar(255) default '',
    NAME_ nvarchar(255),
    START_ACT_ID_ nvarchar(255),
    START_TIME_ datetime,
    START_USER_ID_ nvarchar(255),
    LOCK_TIME_ datetime,
    IS_COUNT_ENABLED_ tinyint,
    EVT_SUBSCR_COUNT_ int,
    TASK_COUNT_ int,
    JOB_COUNT_ int,
    TIMER_JOB_COUNT_ int,
    SUSP_JOB_COUNT_ int,
    DEADLETTER_JOB_COUNT_ int,
    VAR_COUNT_ int,
    ID_LINK_COUNT_ int,
    CALLBACK_ID_ nvarchar(255),
    CALLBACK_TYPE_ nvarchar(255),
    primary key (ID_)
);

create table ACT_RE_PROCDEF (
    ID_ nvarchar(64) not null,
    REV_ int,
    CATEGORY_ nvarchar(255),
    NAME_ nvarchar(255),
    KEY_ nvarchar(255) not null,
    VERSION_ int not null,
    DEPLOYMENT_ID_ nvarchar(64),
    RESOURCE_NAME_ nvarchar(4000),
    DGRM_RESOURCE_NAME_ nvarchar(4000),
    DESCRIPTION_ nvarchar(4000),
    HAS_START_FORM_KEY_ tinyint,
    HAS_GRAPHICAL_NOTATION_ tinyint,
    SUSPENSION_STATE_ tinyint,
    TENANT_ID_ nvarchar(255) default '',
    DERIVED_FROM_ nvarchar(64),
    DERIVED_FROM_ROOT_ nvarchar(64),
    DERIVED_VERSION_ int not null default 0,
    ENGINE_VERSION_ nvarchar(255),
    primary key (ID_)
);

create table ACT_RU_EVENT_SUBSCR (
    ID_ nvarchar(64) not null,
    REV_ int,
    EVENT_TYPE_ nvarchar(255) not null,
    EVENT_NAME_ nvarchar(255),
    EXECUTION_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    ACTIVITY_ID_ nvarchar(64),
    CONFIGURATION_ nvarchar(255),
    CREATED_ datetime not null,
    PROC_DEF_ID_ nvarchar(64),
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_EVT_LOG (
    LOG_NR_ numeric(19,0) IDENTITY(1,1),
    TYPE_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    EXECUTION_ID_ nvarchar(64),
    TASK_ID_ nvarchar(64),
    TIME_STAMP_ datetime not null,
    USER_ID_ nvarchar(255),
    DATA_ varbinary(max),
    LOCK_OWNER_ nvarchar(255),
    LOCK_TIME_ datetime null,
    IS_PROCESSED_ tinyint default 0,
    primary key (LOG_NR_)
);

create table ACT_PROCDEF_INFO (
	ID_ nvarchar(64) not null,
    PROC_DEF_ID_ nvarchar(64) not null,
    REV_ int,
    INFO_JSON_ID_ nvarchar(64),
    primary key (ID_)
);

create index ACT_IDX_EXEC_BUSKEY on ACT_RU_EXECUTION(BUSINESS_KEY_);
create index ACT_IDX_EXEC_ROOT on ACT_RU_EXECUTION(ROOT_PROC_INST_ID_);
create index ACT_IDX_EVENT_SUBSCR_CONFIG_ on ACT_RU_EVENT_SUBSCR(CONFIGURATION_);
create index ACT_IDX_VARIABLE_TASK_ID on ACT_RU_VARIABLE(TASK_ID_);
create index ACT_IDX_ATHRZ_PROCEDEF on ACT_RU_IDENTITYLINK(PROC_DEF_ID_);
create index ACT_IDX_EXECUTION_PROC on ACT_RU_EXECUTION(PROC_DEF_ID_);
create index ACT_IDX_EXECUTION_PARENT on ACT_RU_EXECUTION(PARENT_ID_);
create index ACT_IDX_EXECUTION_SUPER on ACT_RU_EXECUTION(SUPER_EXEC_);
create index ACT_IDX_EXECUTION_IDANDREV on ACT_RU_EXECUTION(ID_, REV_);
create index ACT_IDX_VARIABLE_EXEC on ACT_RU_VARIABLE(EXECUTION_ID_);
create index ACT_IDX_VARIABLE_PROCINST on ACT_RU_VARIABLE(PROC_INST_ID_);
create index ACT_IDX_IDENT_LNK_TASK on ACT_RU_IDENTITYLINK(TASK_ID_);
create index ACT_IDX_IDENT_LNK_PROCINST on ACT_RU_IDENTITYLINK(PROC_INST_ID_);
create index ACT_IDX_TASK_EXEC on ACT_RU_TASK(EXECUTION_ID_);
create index ACT_IDX_TASK_PROCINST on ACT_RU_TASK(PROC_INST_ID_);
create index ACT_IDX_EXEC_PROC_INST_ID on ACT_RU_EXECUTION(PROC_INST_ID_);
create index ACT_IDX_TASK_PROC_DEF_ID on ACT_RU_TASK(PROC_DEF_ID_);
create index ACT_IDX_EVENT_SUBSCR_EXEC_ID on ACT_RU_EVENT_SUBSCR(EXECUTION_ID_);
create index ACT_IDX_JOB_EXECUTION_ID on ACT_RU_JOB(EXECUTION_ID_);
create index ACT_IDX_JOB_PROCESS_INSTANCE_ID on ACT_RU_JOB(PROCESS_INSTANCE_ID_);
create index ACT_IDX_JOB_PROC_DEF_ID on ACT_RU_JOB(PROC_DEF_ID_);
create index ACT_IDX_TIMER_JOB_EXECUTION_ID on ACT_RU_TIMER_JOB(EXECUTION_ID_);
create index ACT_IDX_TIMER_JOB_PROCESS_INSTANCE_ID on ACT_RU_TIMER_JOB(PROCESS_INSTANCE_ID_);
create index ACT_IDX_TIMER_JOB_PROC_DEF_ID on ACT_RU_TIMER_JOB(PROC_DEF_ID_);
create index ACT_IDX_SUSPENDED_JOB_EXECUTION_ID on ACT_RU_SUSPENDED_JOB(EXECUTION_ID_);
create index ACT_IDX_SUSPENDED_JOB_PROCESS_INSTANCE_ID on ACT_RU_SUSPENDED_JOB(PROCESS_INSTANCE_ID_);
create index ACT_IDX_SUSPENDED_JOB_PROC_DEF_ID on ACT_RU_SUSPENDED_JOB(PROC_DEF_ID_);
create index ACT_IDX_DEADLETTER_JOB_EXECUTION_ID on ACT_RU_DEADLETTER_JOB(EXECUTION_ID_);
create index ACT_IDX_DEADLETTER_JOB_PROCESS_INSTANCE_ID on ACT_RU_DEADLETTER_JOB(PROCESS_INSTANCE_ID_);
create index ACT_IDX_DEADLETTER_JOB_PROC_DEF_ID on ACT_RU_DEADLETTER_JOB(PROC_DEF_ID_);
create index ACT_IDX_INFO_PROCDEF on ACT_PROCDEF_INFO(PROC_DEF_ID_);

alter table ACT_GE_BYTEARRAY
    add constraint ACT_FK_BYTEARR_DEPL
    foreign key (DEPLOYMENT_ID_)
    references ACT_RE_DEPLOYMENT (ID_);

alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
    unique (KEY_,VERSION_, DERIVED_VERSION_, TENANT_ID_);

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PARENT
    foreign key (PARENT_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_SUPER
    foreign key (SUPER_EXEC_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PROCDEF
    foreign key (PROC_DEF_ID_)
    references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_TSKASS_TASK
    foreign key (TASK_ID_)
    references ACT_RU_TASK (ID_);

alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_ATHRZ_PROCEDEF
    foreign key (PROC_DEF_ID_)
    references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_IDL_PROCINST
    foreign key (PROC_INST_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TASK
    add constraint ACT_FK_TASK_EXE
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TASK
    add constraint ACT_FK_TASK_PROCINST
    foreign key (PROC_INST_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TASK
  	add constraint ACT_FK_TASK_PROCDEF
  	foreign key (PROC_DEF_ID_)
  	references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_VARIABLE
    add constraint ACT_FK_VAR_EXE
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_VARIABLE
    add constraint ACT_FK_VAR_PROCINST
    foreign key (PROC_INST_ID_)
    references ACT_RU_EXECUTION(ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_EXECUTION
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
    references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_EXECUTION
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
    references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_EXECUTION
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
    references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_EXECUTION
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
    references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
    references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_EVENT_SUBSCR
    add constraint ACT_FK_EVENT_EXEC
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION(ID_);

alter table ACT_RE_MODEL
    add constraint ACT_FK_MODEL_SOURCE
    foreign key (EDITOR_SOURCE_VALUE_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RE_MODEL
    add constraint ACT_FK_MODEL_SOURCE_EXTRA
    foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RE_MODEL
    add constraint ACT_FK_MODEL_DEPLOYMENT
    foreign key (DEPLOYMENT_ID_)
    references ACT_RE_DEPLOYMENT (ID_);

alter table ACT_PROCDEF_INFO
    add constraint ACT_FK_INFO_JSON_BA
    foreign key (INFO_JSON_ID_)
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_PROCDEF_INFO
    add constraint ACT_FK_INFO_PROCDEF
    foreign key (PROC_DEF_ID_)
    references ACT_RE_PROCDEF (ID_);

alter table ACT_PROCDEF_INFO
    add constraint ACT_UNIQ_INFO_PROCDEF
    unique (PROC_DEF_ID_);

insert into ACT_GE_PROPERTY
values ('schema.version', '6.3.1.0', 1);

insert into ACT_GE_PROPERTY
values ('schema.history', 'create(6.3.1.0)', 1);

create table ACT_HI_IDENTITYLINK (
    ID_ nvarchar(64),
    GROUP_ID_ nvarchar(255),
    TYPE_ nvarchar(255),
    USER_ID_ nvarchar(255),
    TASK_ID_ nvarchar(64),
    CREATE_TIME_ datetime,
    PROC_INST_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    primary key (ID_)
);

create index ACT_IDX_HI_IDENT_LNK_USER on ACT_HI_IDENTITYLINK(USER_ID_);
create index ACT_IDX_HI_IDENT_LNK_SCOPE on ACT_HI_IDENTITYLINK(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_IDENT_LNK_SCOPE_DEF on ACT_HI_IDENTITYLINK(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create table ACT_HI_TASKINST (
    ID_ nvarchar(64) not null,
    REV_ int default 1,
    PROC_DEF_ID_ nvarchar(64),
    TASK_DEF_ID_ nvarchar(64),
    TASK_DEF_KEY_ nvarchar(255),
    PROC_INST_ID_ nvarchar(64),
    EXECUTION_ID_ nvarchar(64),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    SCOPE_DEFINITION_ID_ nvarchar(255),
    NAME_ nvarchar(255),
    PARENT_TASK_ID_ nvarchar(64),
    DESCRIPTION_ nvarchar(4000),
    OWNER_ nvarchar(255),
    ASSIGNEE_ nvarchar(255),
    START_TIME_ datetime not null,
    CLAIM_TIME_ datetime,
    END_TIME_ datetime,
    DURATION_ numeric(19,0),
    DELETE_REASON_ nvarchar(4000),
    PRIORITY_ int,
    DUE_DATE_ datetime,
    FORM_KEY_ nvarchar(255),
    CATEGORY_ nvarchar(255),
    TENANT_ID_ nvarchar(255) default '',
    LAST_UPDATED_TIME_ datetime2,
    primary key (ID_)
);

create index ACT_IDX_HI_TASK_SCOPE on ACT_HI_TASKINST(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_TASK_SUB_SCOPE on ACT_HI_TASKINST(SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_TASK_SCOPE_DEF on ACT_HI_TASKINST(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);
create table ACT_HI_VARINST (
    ID_ nvarchar(64) not null,
    REV_ int default 1,
    PROC_INST_ID_ nvarchar(64),
    EXECUTION_ID_ nvarchar(64),
    TASK_ID_ nvarchar(64),
    NAME_ nvarchar(255) not null,
    VAR_TYPE_ nvarchar(100),
    SCOPE_ID_ nvarchar(255),
    SUB_SCOPE_ID_ nvarchar(255),
    SCOPE_TYPE_ nvarchar(255),
    BYTEARRAY_ID_ nvarchar(64),
    DOUBLE_ double precision,
    LONG_ numeric(19,0),
    TEXT_ nvarchar(4000),
    TEXT2_ nvarchar(4000),
    CREATE_TIME_ datetime,
    LAST_UPDATED_TIME_ datetime2,
    primary key (ID_)
);

create index ACT_IDX_HI_PROCVAR_NAME_TYPE on ACT_HI_VARINST(NAME_, VAR_TYPE_);
create index ACT_IDX_HI_VAR_SCOPE_ID_TYPE on ACT_HI_VARINST(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_VAR_SUB_ID_TYPE on ACT_HI_VARINST(SUB_SCOPE_ID_, SCOPE_TYPE_);

create table ACT_HI_PROCINST (
    ID_ nvarchar(64) not null,
    REV_ int default 1,
    PROC_INST_ID_ nvarchar(64) not null,
    BUSINESS_KEY_ nvarchar(255),
    PROC_DEF_ID_ nvarchar(64) not null,
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ numeric(19,0),
    START_USER_ID_ nvarchar(255),
    START_ACT_ID_ nvarchar(255),
    END_ACT_ID_ nvarchar(255),
    SUPER_PROCESS_INSTANCE_ID_ nvarchar(64),
    DELETE_REASON_ nvarchar(4000),
    TENANT_ID_ nvarchar(255) default '',
    NAME_ nvarchar(255),
    CALLBACK_ID_ nvarchar(255),
    CALLBACK_TYPE_ nvarchar(255),
    primary key (ID_),
    unique (PROC_INST_ID_)
);

create table ACT_HI_ACTINST (
    ID_ nvarchar(64) not null,
    REV_ int default 1,
    PROC_DEF_ID_ nvarchar(64) not null,
    PROC_INST_ID_ nvarchar(64) not null,
    EXECUTION_ID_ nvarchar(64) not null,
    ACT_ID_ nvarchar(255) not null,
    TASK_ID_ nvarchar(64),
    CALL_PROC_INST_ID_ nvarchar(64),
    ACT_NAME_ nvarchar(255),
    ACT_TYPE_ nvarchar(255) not null,
    ASSIGNEE_ nvarchar(255),
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ numeric(19,0),
    DELETE_REASON_ nvarchar(4000),
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_HI_DETAIL (
    ID_ nvarchar(64) not null,
    TYPE_ nvarchar(255) not null,
    PROC_INST_ID_ nvarchar(64),
    EXECUTION_ID_ nvarchar(64),
    TASK_ID_ nvarchar(64),
    ACT_INST_ID_ nvarchar(64),
    NAME_ nvarchar(255) not null,
    VAR_TYPE_ nvarchar(255),
    REV_ int,
    TIME_ datetime not null,
    BYTEARRAY_ID_ nvarchar(64),
    DOUBLE_ double precision,
    LONG_ numeric(19,0),
    TEXT_ nvarchar(4000),
    TEXT2_ nvarchar(4000),
    primary key (ID_)
);

create table ACT_HI_COMMENT (
    ID_ nvarchar(64) not null,
    TYPE_ nvarchar(255),
    TIME_ datetime not null,
    USER_ID_ nvarchar(255),
    TASK_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    ACTION_ nvarchar(255),
    MESSAGE_ nvarchar(4000),
    FULL_MSG_ varbinary(max),
    primary key (ID_)
);

create table ACT_HI_ATTACHMENT (
    ID_ nvarchar(64) not null,
    REV_ integer,
    USER_ID_ nvarchar(255),
    NAME_ nvarchar(255),
    DESCRIPTION_ nvarchar(4000),
    TYPE_ nvarchar(255),
    TASK_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    URL_ nvarchar(4000),
    CONTENT_ID_ nvarchar(64),
    TIME_ datetime,
    primary key (ID_)
);


create index ACT_IDX_HI_PRO_INST_END on ACT_HI_PROCINST(END_TIME_);
create index ACT_IDX_HI_PRO_I_BUSKEY on ACT_HI_PROCINST(BUSINESS_KEY_);
create index ACT_IDX_HI_ACT_INST_START on ACT_HI_ACTINST(START_TIME_);
create index ACT_IDX_HI_ACT_INST_END on ACT_HI_ACTINST(END_TIME_);
create index ACT_IDX_HI_DETAIL_PROC_INST on ACT_HI_DETAIL(PROC_INST_ID_);
create index ACT_IDX_HI_DETAIL_ACT_INST on ACT_HI_DETAIL(ACT_INST_ID_);
create index ACT_IDX_HI_DETAIL_TIME on ACT_HI_DETAIL(TIME_);
create index ACT_IDX_HI_DETAIL_NAME on ACT_HI_DETAIL(NAME_);
create index ACT_IDX_HI_DETAIL_TASK_ID on ACT_HI_DETAIL(TASK_ID_);
create index ACT_IDX_HI_PROCVAR_PROC_INST on ACT_HI_VARINST(PROC_INST_ID_);
create index ACT_IDX_HI_PROCVAR_TASK_ID on ACT_HI_VARINST(TASK_ID_);
create index ACT_IDX_HI_PROCVAR_EXE on ACT_HI_VARINST(EXECUTION_ID_);
create index ACT_IDX_HI_ACT_INST_PROCINST on ACT_HI_ACTINST(PROC_INST_ID_, ACT_ID_);
create index ACT_IDX_HI_ACT_INST_EXEC on ACT_HI_ACTINST(EXECUTION_ID_, ACT_ID_);
create index ACT_IDX_HI_IDENT_LNK_TASK on ACT_HI_IDENTITYLINK(TASK_ID_);
create index ACT_IDX_HI_IDENT_LNK_PROCINST on ACT_HI_IDENTITYLINK(PROC_INST_ID_);
create index ACT_IDX_HI_TASK_INST_PROCINST on ACT_HI_TASKINST(PROC_INST_ID_);


CREATE TABLE ACT_CMMN_DATABASECHANGELOG (ID nvarchar(255) NOT NULL, AUTHOR nvarchar(255) NOT NULL, FILENAME nvarchar(255) NOT NULL, DATEEXECUTED datetime2(3) NOT NULL, ORDEREXECUTED int NOT NULL, EXECTYPE nvarchar(10) NOT NULL, MD5SUM nvarchar(35), DESCRIPTION nvarchar(255), COMMENTS nvarchar(255), TAG nvarchar(255), LIQUIBASE nvarchar(20), CONTEXTS nvarchar(255), LABELS nvarchar(255), DEPLOYMENT_ID nvarchar(10))
GO

CREATE TABLE ACT_CMMN_DEPLOYMENT (ID_ varchar(255) NOT NULL, NAME_ varchar(255), CATEGORY_ varchar(255), KEY_ varchar(255), DEPLOY_TIME_ datetime, PARENT_DEPLOYMENT_ID_ varchar(255), TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_DEPLOYMENT_TENANT_ID_ DEFAULT '', CONSTRAINT PK_ACT_CMMN_DEPLOYMENT PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_CMMN_DEPLOYMENT_RESOURCE (ID_ varchar(255) NOT NULL, NAME_ varchar(255), DEPLOYMENT_ID_ varchar(255), RESOURCE_BYTES_ varbinary(MAX), CONSTRAINT PK_CMMN_DEPLOYMENT_RESOURCE PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_CMMN_DEPLOYMENT_RESOURCE ADD CONSTRAINT ACT_FK_CMMN_RSRC_DPL FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_CMMN_DEPLOYMENT (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_CMMN_RSRC_DPL ON ACT_CMMN_DEPLOYMENT_RESOURCE(DEPLOYMENT_ID_)
GO

CREATE TABLE ACT_CMMN_CASEDEF (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, NAME_ varchar(255), KEY_ varchar(255) NOT NULL, VERSION_ int NOT NULL, CATEGORY_ varchar(255), DEPLOYMENT_ID_ varchar(255), RESOURCE_NAME_ varchar(4000), DESCRIPTION_ varchar(4000), HAS_GRAPHICAL_NOTATION_ bit, TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_CASEDEF_TENANT_ID_ DEFAULT '', CONSTRAINT PK_ACT_CMMN_CASEDEF PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_CMMN_CASEDEF ADD CONSTRAINT ACT_FK_CASE_DEF_DPLY FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_CMMN_DEPLOYMENT (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_CASE_DEF_DPLY ON ACT_CMMN_CASEDEF(DEPLOYMENT_ID_)
GO

CREATE TABLE ACT_CMMN_RU_CASE_INST (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, BUSINESS_KEY_ varchar(255), NAME_ varchar(255), PARENT_ID_ varchar(255), CASE_DEF_ID_ varchar(255), STATE_ varchar(255), START_TIME_ datetime, START_USER_ID_ varchar(255), CALLBACK_ID_ varchar(255), CALLBACK_TYPE_ varchar(255), TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_RU_CASE_INST_TENANT_ID_ DEFAULT '', CONSTRAINT PK_ACT_CMMN_RU_CASE_INST PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_CMMN_RU_CASE_INST ADD CONSTRAINT ACT_FK_CASE_INST_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_CASE_INST_CASE_DEF ON ACT_CMMN_RU_CASE_INST(CASE_DEF_ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_CASE_INST_PARENT ON ACT_CMMN_RU_CASE_INST(PARENT_ID_)
GO

CREATE TABLE ACT_CMMN_RU_PLAN_ITEM_INST (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, CASE_DEF_ID_ varchar(255), CASE_INST_ID_ varchar(255), STAGE_INST_ID_ varchar(255), IS_STAGE_ bit, ELEMENT_ID_ varchar(255), NAME_ varchar(255), STATE_ varchar(255), START_TIME_ datetime, START_USER_ID_ varchar(255), REFERENCE_ID_ varchar(255), REFERENCE_TYPE_ varchar(255), TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_RU_PLAN_ITEM_INST_TENANT_ID_ DEFAULT '', CONSTRAINT PK_CMMN_PLAN_ITEM_INST PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD CONSTRAINT ACT_FK_PLAN_ITEM_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_PLAN_ITEM_CASE_DEF ON ACT_CMMN_RU_PLAN_ITEM_INST(CASE_DEF_ID_)
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD CONSTRAINT ACT_FK_PLAN_ITEM_CASE_INST FOREIGN KEY (CASE_INST_ID_) REFERENCES ACT_CMMN_RU_CASE_INST (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_PLAN_ITEM_CASE_INST ON ACT_CMMN_RU_PLAN_ITEM_INST(CASE_INST_ID_)
GO

CREATE TABLE ACT_CMMN_RU_SENTRY_PART_INST (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, CASE_DEF_ID_ varchar(255), CASE_INST_ID_ varchar(255), PLAN_ITEM_INST_ID_ varchar(255), ON_PART_ID_ varchar(255), IF_PART_ID_ varchar(255), TIME_STAMP_ datetime, CONSTRAINT PK_CMMN_SENTRY_PART_INST PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_CMMN_RU_SENTRY_PART_INST ADD CONSTRAINT ACT_FK_SENTRY_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_SENTRY_CASE_DEF ON ACT_CMMN_RU_SENTRY_PART_INST(CASE_DEF_ID_)
GO

ALTER TABLE ACT_CMMN_RU_SENTRY_PART_INST ADD CONSTRAINT ACT_FK_SENTRY_CASE_INST FOREIGN KEY (CASE_INST_ID_) REFERENCES ACT_CMMN_RU_CASE_INST (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_SENTRY_CASE_INST ON ACT_CMMN_RU_SENTRY_PART_INST(CASE_INST_ID_)
GO

ALTER TABLE ACT_CMMN_RU_SENTRY_PART_INST ADD CONSTRAINT ACT_FK_SENTRY_PLAN_ITEM FOREIGN KEY (PLAN_ITEM_INST_ID_) REFERENCES ACT_CMMN_RU_PLAN_ITEM_INST (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_SENTRY_PLAN_ITEM ON ACT_CMMN_RU_SENTRY_PART_INST(PLAN_ITEM_INST_ID_)
GO

CREATE TABLE ACT_CMMN_RU_MIL_INST (ID_ varchar(255) NOT NULL, NAME_ varchar(255) NOT NULL, TIME_STAMP_ datetime NOT NULL, CASE_INST_ID_ varchar(255) NOT NULL, CASE_DEF_ID_ varchar(255) NOT NULL, ELEMENT_ID_ varchar(255) NOT NULL, CONSTRAINT PK_ACT_CMMN_RU_MIL_INST PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_CMMN_RU_MIL_INST ADD CONSTRAINT ACT_FK_MIL_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_MIL_CASE_DEF ON ACT_CMMN_RU_MIL_INST(CASE_DEF_ID_)
GO

ALTER TABLE ACT_CMMN_RU_MIL_INST ADD CONSTRAINT ACT_FK_MIL_CASE_INST FOREIGN KEY (CASE_INST_ID_) REFERENCES ACT_CMMN_RU_CASE_INST (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_MIL_CASE_INST ON ACT_CMMN_RU_MIL_INST(CASE_INST_ID_)
GO

CREATE TABLE ACT_CMMN_HI_CASE_INST (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, BUSINESS_KEY_ varchar(255), NAME_ varchar(255), PARENT_ID_ varchar(255), CASE_DEF_ID_ varchar(255), STATE_ varchar(255), START_TIME_ datetime, END_TIME_ datetime, START_USER_ID_ varchar(255), CALLBACK_ID_ varchar(255), CALLBACK_TYPE_ varchar(255), TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_HI_CASE_INST_TENANT_ID_ DEFAULT '', CONSTRAINT PK_ACT_CMMN_HI_CASE_INST PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_CMMN_HI_MIL_INST (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, NAME_ varchar(255) NOT NULL, TIME_STAMP_ datetime NOT NULL, CASE_INST_ID_ varchar(255) NOT NULL, CASE_DEF_ID_ varchar(255) NOT NULL, ELEMENT_ID_ varchar(255) NOT NULL, CONSTRAINT PK_ACT_CMMN_HI_MIL_INST PRIMARY KEY (ID_))
GO

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', GETDATE(), 1, '8:8b4b922d90b05ff27483abefc9597aa6', 'createTable tableName=ACT_CMMN_DEPLOYMENT; createTable tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_CMMN_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_CMMN_RSRC_DPL, referencedTableName=ACT_CMMN_DEPLOYMENT; create...', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084415')
GO

ALTER TABLE ACT_CMMN_CASEDEF ADD DGRM_RESOURCE_NAME_ varchar(4000)
GO

ALTER TABLE ACT_CMMN_CASEDEF ADD HAS_START_FORM_KEY_ bit
GO

ALTER TABLE ACT_CMMN_DEPLOYMENT_RESOURCE ADD GENERATED_ bit
GO

ALTER TABLE ACT_CMMN_RU_CASE_INST ADD LOCK_TIME_ datetime
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD ITEM_DEFINITION_ID_ varchar(255)
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD ITEM_DEFINITION_TYPE_ varchar(255)
GO

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', GETDATE(), 3, '8:65e39b3d385706bb261cbeffe7533cbe', 'addColumn tableName=ACT_CMMN_CASEDEF; addColumn tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084415')
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD IS_COMPLETEABLE_ bit
GO

ALTER TABLE ACT_CMMN_RU_CASE_INST ADD IS_COMPLETEABLE_ bit
GO

CREATE NONCLUSTERED INDEX ACT_IDX_PLAN_ITEM_STAGE_INST ON ACT_CMMN_RU_PLAN_ITEM_INST(STAGE_INST_ID_)
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD IS_COUNT_ENABLED_ bit
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD VAR_COUNT_ int
GO

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD SENTRY_PART_INST_COUNT_ int
GO

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', GETDATE(), 5, '8:c01f6e802b49436b4489040da3012359', 'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_PLAN_ITEM_STAGE_INST, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableNam...', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084415')
GO

CREATE TABLE ACT_CMMN_HI_PLAN_ITEM_INST (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, NAME_ varchar(255), STATE_ varchar(255), CASE_DEF_ID_ varchar(255), CASE_INST_ID_ varchar(255), STAGE_INST_ID_ varchar(255), IS_STAGE_ bit, ELEMENT_ID_ varchar(255), ITEM_DEFINITION_ID_ varchar(255), ITEM_DEFINITION_TYPE_ varchar(255), CREATED_TIME_ datetime, LAST_AVAILABLE_TIME_ datetime, LAST_ENABLED_TIME_ datetime, LAST_DISABLED_TIME_ datetime, LAST_STARTED_TIME_ datetime, LAST_SUSPENDED_TIME_ datetime, COMPLETED_TIME_ datetime, OCCURRED_TIME_ datetime, TERMINATED_TIME_ datetime, EXIT_TIME_ datetime, ENDED_TIME_ datetime, LAST_UPDATED_TIME_ datetime, START_USER_ID_ varchar(255), REFERENCE_ID_ varchar(255), REFERENCE_TYPE_ varchar(255), TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_HI_PLAN_ITEM_INST_TENANT_ID_ DEFAULT '', CONSTRAINT PK_ACT_CMMN_HI_PLAN_ITEM_INST PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_CMMN_RU_MIL_INST ADD TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_RU_MIL_INST_TENANT_ID_ DEFAULT ''
GO

ALTER TABLE ACT_CMMN_HI_MIL_INST ADD TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_CMMN_HI_MIL_INST_TENANT_ID_ DEFAULT ''
GO

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', GETDATE(), 7, '8:e40d29cb79345b7fb5afd38a7f0ba8fc', 'createTable tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_MIL_INST; addColumn tableName=ACT_CMMN_HI_MIL_INST', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084415')
GO



CREATE TABLE ACT_DMN_DATABASECHANGELOG (ID nvarchar(255) NOT NULL, AUTHOR nvarchar(255) NOT NULL, FILENAME nvarchar(255) NOT NULL, DATEEXECUTED datetime2(3) NOT NULL, ORDEREXECUTED int NOT NULL, EXECTYPE nvarchar(10) NOT NULL, MD5SUM nvarchar(35), DESCRIPTION nvarchar(255), COMMENTS nvarchar(255), TAG nvarchar(255), LIQUIBASE nvarchar(20), CONTEXTS nvarchar(255), LABELS nvarchar(255), DEPLOYMENT_ID nvarchar(10))
GO

CREATE TABLE ACT_DMN_DEPLOYMENT (ID_ varchar(255) NOT NULL, NAME_ varchar(255), CATEGORY_ varchar(255), DEPLOY_TIME_ datetime, TENANT_ID_ varchar(255), PARENT_DEPLOYMENT_ID_ varchar(255), CONSTRAINT PK_ACT_DMN_DEPLOYMENT PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_DMN_DEPLOYMENT_RESOURCE (ID_ varchar(255) NOT NULL, NAME_ varchar(255), DEPLOYMENT_ID_ varchar(255), RESOURCE_BYTES_ varbinary(MAX), CONSTRAINT PK_ACT_DMN_DEPLOYMENT_RESOURCE PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_DMN_DECISION_TABLE (ID_ varchar(255) NOT NULL, NAME_ varchar(255), VERSION_ int, KEY_ varchar(255), CATEGORY_ varchar(255), DEPLOYMENT_ID_ varchar(255), PARENT_DEPLOYMENT_ID_ varchar(255), TENANT_ID_ varchar(255), RESOURCE_NAME_ varchar(255), DESCRIPTION_ varchar(255), CONSTRAINT PK_ACT_DMN_DECISION_TABLE PRIMARY KEY (ID_))
GO

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'activiti', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', GETDATE(), 1, '8:c8701f1c71018b55029f450b2e9a10a1', 'createTable tableName=ACT_DMN_DEPLOYMENT; createTable tableName=ACT_DMN_DEPLOYMENT_RESOURCE; createTable tableName=ACT_DMN_DECISION_TABLE', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084510')
GO

CREATE TABLE ACT_DMN_HI_DECISION_EXECUTION (ID_ varchar(255) NOT NULL, DECISION_DEFINITION_ID_ varchar(255), DEPLOYMENT_ID_ varchar(255), START_TIME_ datetime, END_TIME_ datetime, INSTANCE_ID_ varchar(255), EXECUTION_ID_ varchar(255), ACTIVITY_ID_ varchar(255), FAILED_ bit CONSTRAINT DF_ACT_DMN_HI_DECISION_EXECUTION_FAILED_ DEFAULT 0, TENANT_ID_ varchar(255), EXECUTION_JSON_ varchar(MAX), CONSTRAINT PK_ACT_DMN_HI_DECISION_EXECUTION PRIMARY KEY (ID_))
GO

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', GETDATE(), 3, '8:47f94b27feb7df8a30d4e338c7bd5fb8', 'createTable tableName=ACT_DMN_HI_DECISION_EXECUTION', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084510')
GO

ALTER TABLE ACT_DMN_HI_DECISION_EXECUTION ADD SCOPE_TYPE_ varchar(255)
GO

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', GETDATE(), 5, '8:ac17eae89fbdccb6e08daf3c7797b579', 'addColumn tableName=ACT_DMN_HI_DECISION_EXECUTION', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084510')
GO

ALTER TABLE ACT_DMN_DECISION_TABLE DROP COLUMN PARENT_DEPLOYMENT_ID_
GO

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', GETDATE(), 7, '8:f73aabc4529e7292c2942073d1cff6f9', 'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_DMN_DECISION_TABLE', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084510')
GO



CREATE TABLE ACT_FO_DATABASECHANGELOG (ID nvarchar(255) NOT NULL, AUTHOR nvarchar(255) NOT NULL, FILENAME nvarchar(255) NOT NULL, DATEEXECUTED datetime2(3) NOT NULL, ORDEREXECUTED int NOT NULL, EXECTYPE nvarchar(10) NOT NULL, MD5SUM nvarchar(35), DESCRIPTION nvarchar(255), COMMENTS nvarchar(255), TAG nvarchar(255), LIQUIBASE nvarchar(20), CONTEXTS nvarchar(255), LABELS nvarchar(255), DEPLOYMENT_ID nvarchar(10))
GO

CREATE TABLE ACT_FO_FORM_DEPLOYMENT (ID_ varchar(255) NOT NULL, NAME_ varchar(255), CATEGORY_ varchar(255), DEPLOY_TIME_ datetime, TENANT_ID_ varchar(255), PARENT_DEPLOYMENT_ID_ varchar(255), CONSTRAINT PK_ACT_FO_FORM_DEPLOYMENT PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_FO_FORM_RESOURCE (ID_ varchar(255) NOT NULL, NAME_ varchar(255), DEPLOYMENT_ID_ varchar(255), RESOURCE_BYTES_ varbinary(MAX), CONSTRAINT PK_ACT_FO_FORM_RESOURCE PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_FO_FORM_DEFINITION (ID_ varchar(255) NOT NULL, NAME_ varchar(255), VERSION_ int, KEY_ varchar(255), CATEGORY_ varchar(255), DEPLOYMENT_ID_ varchar(255), PARENT_DEPLOYMENT_ID_ varchar(255), TENANT_ID_ varchar(255), RESOURCE_NAME_ varchar(255), DESCRIPTION_ varchar(255), CONSTRAINT PK_ACT_FO_FORM_DEFINITION PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_FO_FORM_INSTANCE (ID_ varchar(255) NOT NULL, FORM_DEFINITION_ID_ varchar(255) NOT NULL, TASK_ID_ varchar(255), PROC_INST_ID_ varchar(255), PROC_DEF_ID_ varchar(255), SUBMITTED_DATE_ datetime, SUBMITTED_BY_ varchar(255), FORM_VALUES_ID_ varchar(255), TENANT_ID_ varchar(255), CONSTRAINT PK_ACT_FO_FORM_INSTANCE PRIMARY KEY (ID_))
GO

INSERT INTO ACT_FO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'activiti', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', GETDATE(), 1, '8:033ebf9380889aed7c453927ecc3250d', 'createTable tableName=ACT_FO_FORM_DEPLOYMENT; createTable tableName=ACT_FO_FORM_RESOURCE; createTable tableName=ACT_FO_FORM_DEFINITION; createTable tableName=ACT_FO_FORM_INSTANCE', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084563')
GO

ALTER TABLE ACT_FO_FORM_INSTANCE ADD SCOPE_ID_ varchar(255)
GO

ALTER TABLE ACT_FO_FORM_INSTANCE ADD SCOPE_TYPE_ varchar(255)
GO

ALTER TABLE ACT_FO_FORM_INSTANCE ADD SCOPE_DEFINITION_ID_ varchar(255)
GO

INSERT INTO ACT_FO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', GETDATE(), 3, '8:986365ceb40445ce3b27a8e6b40f159b', 'addColumn tableName=ACT_FO_FORM_INSTANCE', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084563')
GO

ALTER TABLE ACT_FO_FORM_DEFINITION DROP COLUMN PARENT_DEPLOYMENT_ID_
GO

INSERT INTO ACT_FO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', GETDATE(), 5, '8:abf482518ceb09830ef674e52c06bf15', 'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_FO_FORM_DEFINITION', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084563')
GO



CREATE TABLE ACT_CO_DATABASECHANGELOG (ID nvarchar(255) NOT NULL, AUTHOR nvarchar(255) NOT NULL, FILENAME nvarchar(255) NOT NULL, DATEEXECUTED datetime2(3) NOT NULL, ORDEREXECUTED int NOT NULL, EXECTYPE nvarchar(10) NOT NULL, MD5SUM nvarchar(35), DESCRIPTION nvarchar(255), COMMENTS nvarchar(255), TAG nvarchar(255), LIQUIBASE nvarchar(20), CONTEXTS nvarchar(255), LABELS nvarchar(255), DEPLOYMENT_ID nvarchar(10))
GO

CREATE TABLE ACT_CO_CONTENT_ITEM (ID_ varchar(255) NOT NULL, NAME_ varchar(255) NOT NULL, MIME_TYPE_ varchar(255), TASK_ID_ varchar(255), PROC_INST_ID_ varchar(255), CONTENT_STORE_ID_ varchar(255), CONTENT_STORE_NAME_ varchar(255), FIELD_ varchar(400), CONTENT_AVAILABLE_ bit CONSTRAINT DF_ACT_CO_CONTENT_ITEM_CONTENT_AVAILABLE_ DEFAULT 0, CREATED_ datetime, CREATED_BY_ varchar(255), LAST_MODIFIED_ datetime, LAST_MODIFIED_BY_ varchar(255), CONTENT_SIZE_ bigint CONSTRAINT DF_ACT_CO_CONTENT_ITEM_CONTENT_SIZE_ DEFAULT 0, TENANT_ID_ varchar(255), CONSTRAINT PK_ACT_CO_CONTENT_ITEM PRIMARY KEY (ID_))
GO

CREATE NONCLUSTERED INDEX idx_contitem_taskid ON ACT_CO_CONTENT_ITEM(TASK_ID_)
GO

CREATE NONCLUSTERED INDEX idx_contitem_procid ON ACT_CO_CONTENT_ITEM(PROC_INST_ID_)
GO

INSERT INTO ACT_CO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'activiti', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', GETDATE(), 1, '8:7644d7165cfe799200a2abdd3419e8b6', 'createTable tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_taskid, tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_procid, tableName=ACT_CO_CONTENT_ITEM', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084610')
GO

ALTER TABLE ACT_CO_CONTENT_ITEM ADD SCOPE_ID_ varchar(255)
GO

ALTER TABLE ACT_CO_CONTENT_ITEM ADD SCOPE_TYPE_ varchar(255)
GO

CREATE NONCLUSTERED INDEX idx_contitem_scope ON ACT_CO_CONTENT_ITEM(SCOPE_ID_, SCOPE_TYPE_)
GO

INSERT INTO ACT_CO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'flowable', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', GETDATE(), 3, '8:fe7b11ac7dbbf9c43006b23bbab60bab', 'addColumn tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_scope, tableName=ACT_CO_CONTENT_ITEM', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084610')
GO


create table ACT_ID_PROPERTY (
    NAME_ nvarchar(64),
    VALUE_ nvarchar(300),
    REV_ int,
    primary key (NAME_)
);

insert into ACT_ID_PROPERTY
values ('schema.version', '6.3.1.0', 1);

create table ACT_ID_BYTEARRAY (
    ID_ nvarchar(64),
    REV_ int,
    NAME_ nvarchar(255),
    BYTES_  varbinary(max),
    primary key (ID_)
);

create table ACT_ID_GROUP (
    ID_ nvarchar(64),
    REV_ int,
    NAME_ nvarchar(255),
    TYPE_ nvarchar(255),
    primary key (ID_)
);

create table ACT_ID_MEMBERSHIP (
    USER_ID_ nvarchar(64),
    GROUP_ID_ nvarchar(64),
    primary key (USER_ID_, GROUP_ID_)
);

create table ACT_ID_USER (
    ID_ nvarchar(64),
    REV_ int,
    FIRST_ nvarchar(255),
    LAST_ nvarchar(255),
    EMAIL_ nvarchar(255),
    PWD_ nvarchar(255),
    PICTURE_ID_ nvarchar(64),
    TENANT_ID_ nvarchar(255) default '',
    primary key (ID_)
);

create table ACT_ID_INFO (
    ID_ nvarchar(64),
    REV_ int,
    USER_ID_ nvarchar(64),
    TYPE_ nvarchar(64),
    KEY_ nvarchar(255),
    VALUE_ nvarchar(255),
    PASSWORD_ varbinary(max),
    PARENT_ID_ nvarchar(255),
    primary key (ID_)
);

create table ACT_ID_TOKEN (
    ID_ nvarchar(64) not null,
    REV_ int,
    TOKEN_VALUE_ nvarchar(255),
    TOKEN_DATE_ datetime,
    IP_ADDRESS_ nvarchar(255),
    USER_AGENT_ nvarchar(255),
    USER_ID_ nvarchar(255),
    TOKEN_DATA_ nvarchar(2000),
    primary key (ID_)
);

create table ACT_ID_PRIV (
    ID_ nvarchar(64) not null,
    NAME_ nvarchar(255) not null,
    primary key (ID_)
);

create table ACT_ID_PRIV_MAPPING (
    ID_ nvarchar(64) not null,
    PRIV_ID_ nvarchar(64) not null,
    USER_ID_ nvarchar(255),
    GROUP_ID_ nvarchar(255),
    primary key (ID_)
);

alter table ACT_ID_MEMBERSHIP
    add constraint ACT_FK_MEMB_GROUP
    foreign key (GROUP_ID_)
    references ACT_ID_GROUP;

alter table ACT_ID_MEMBERSHIP
    add constraint ACT_FK_MEMB_USER
    foreign key (USER_ID_)
    references ACT_ID_USER (ID_);

alter table ACT_ID_PRIV_MAPPING
    add constraint ACT_FK_PRIV_MAPPING
    foreign key (PRIV_ID_)
    references ACT_ID_PRIV (ID_);

create index ACT_IDX_PRIV_USER on ACT_ID_PRIV_MAPPING(USER_ID_);
create index ACT_IDX_PRIV_GROUP on ACT_ID_PRIV_MAPPING(GROUP_ID_);

alter table ACT_ID_PRIV
    add constraint ACT_UNIQ_PRIV_NAME
    unique (NAME_);


CREATE TABLE ACT_APP_DATABASECHANGELOG (ID nvarchar(255) NOT NULL, AUTHOR nvarchar(255) NOT NULL, FILENAME nvarchar(255) NOT NULL, DATEEXECUTED datetime2(3) NOT NULL, ORDEREXECUTED int NOT NULL, EXECTYPE nvarchar(10) NOT NULL, MD5SUM nvarchar(35), DESCRIPTION nvarchar(255), COMMENTS nvarchar(255), TAG nvarchar(255), LIQUIBASE nvarchar(20), CONTEXTS nvarchar(255), LABELS nvarchar(255), DEPLOYMENT_ID nvarchar(10))
GO

CREATE TABLE ACT_APP_DEPLOYMENT (ID_ varchar(255) NOT NULL, NAME_ varchar(255), CATEGORY_ varchar(255), KEY_ varchar(255), DEPLOY_TIME_ datetime, TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_APP_DEPLOYMENT_TENANT_ID_ DEFAULT '', CONSTRAINT PK_ACT_APP_DEPLOYMENT PRIMARY KEY (ID_))
GO

CREATE TABLE ACT_APP_DEPLOYMENT_RESOURCE (ID_ varchar(255) NOT NULL, NAME_ varchar(255), DEPLOYMENT_ID_ varchar(255), RESOURCE_BYTES_ varbinary(MAX), CONSTRAINT PK_APP_DEPLOYMENT_RESOURCE PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_APP_DEPLOYMENT_RESOURCE ADD CONSTRAINT ACT_FK_APP_RSRC_DPL FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_APP_DEPLOYMENT (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_APP_RSRC_DPL ON ACT_APP_DEPLOYMENT_RESOURCE(DEPLOYMENT_ID_)
GO

CREATE TABLE ACT_APP_APPDEF (ID_ varchar(255) NOT NULL, REV_ int NOT NULL, NAME_ varchar(255), KEY_ varchar(255) NOT NULL, VERSION_ int NOT NULL, CATEGORY_ varchar(255), DEPLOYMENT_ID_ varchar(255), RESOURCE_NAME_ varchar(4000), DESCRIPTION_ varchar(4000), TENANT_ID_ varchar(255) CONSTRAINT DF_ACT_APP_APPDEF_TENANT_ID_ DEFAULT '', CONSTRAINT PK_ACT_APP_APPDEF PRIMARY KEY (ID_))
GO

ALTER TABLE ACT_APP_APPDEF ADD CONSTRAINT ACT_FK_APP_DEF_DPLY FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_APP_DEPLOYMENT (ID_)
GO

CREATE NONCLUSTERED INDEX ACT_IDX_APP_DEF_DPLY ON ACT_APP_APPDEF(DEPLOYMENT_ID_)
GO

INSERT INTO ACT_APP_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', GETDATE(), 1, '8:496fc778bdf2ab13f2e1926d0e63e0a2', 'createTable tableName=ACT_APP_DEPLOYMENT; createTable tableName=ACT_APP_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_APP_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_APP_RSRC_DPL, referencedTableName=ACT_APP_DEPLOYMENT; createIndex...', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084654')
GO


