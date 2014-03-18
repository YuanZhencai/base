--==============================================================
-- DBMS name:      IBM DB2 UDB 9.5 Common Server
-- Created on:     2012-3-11 12:10:11
--==============================================================


alter table RPT_EXECUTIONPLANHISTORY
   drop foreign key FK_REPM_REPH;

alter table RPT_EXECUTIONPLANHISTORY
   drop foreign key F_FK_RP_SS_EPH;

alter table RPT_EXECUTIONPLANMSTR
   drop foreign key F_FK_RP_RM_EPM;

alter table RPT_EXECUTIONPLANPARAMETER
   drop foreign key FK_REPM_REPP;

alter table RPT_EXECUTIONPLANPARAMETER
   drop foreign key F_FK_RP_RP_EPP;

alter table RPT_REPORTFILEHISTORY
   drop foreign key FK_RFH_FM_COMPILED;

alter table RPT_REPORTFILEHISTORY
   drop foreign key FK_RFH_FM_SOURCE;

alter table RPT_REPORTFILEHISTORY
   drop foreign key FK_RFH_RM;

alter table RPT_REPORTMSTR
   drop foreign key F_FK_RP_DSM_RM;

alter table RPT_REPORTMSTR
   drop foreign key FK_RM_FM_COMPILED;

alter table RPT_REPORTMSTR
   drop foreign key FK_RM_FM_SOURCE;

alter table RPT_REPORTMSTR
   drop foreign key FK_RM_RFH;

alter table RPT_REPORTMSTR
   drop foreign key F_FK_RP_SN_RM;

alter table RPT_REPORTPARAMETER
   drop foreign key FK_RRP_RRM;

alter table RPT_ROLE
   drop foreign key F_FK_RP_RM_RR;

alter table RPT_ROLE
   drop foreign key F_FK_RP_RPM_RR;

alter table RPT_SNAPSHOT
   drop foreign key F_FK_RP_RM_SS;

alter table RPT_SNAPSHOTPARAMETER
   drop foreign key F_FK_RP_RP_SSP;

alter table RPT_SNAPSHOTPARAMETER
   drop foreign key FK_RSSP_RSS;

alter table RPT_SUBSCRIPTION
   drop foreign key F_FK_RP_RM_RS;

drop table DATASOURCEMSTR;

drop table FILEMSTR;

drop table RPT_EXECUTIONPLANHISTORY;

drop table RPT_EXECUTIONPLANMSTR;

drop table RPT_EXECUTIONPLANPARAMETER;

drop table RPT_REPORTFILEHISTORY;

drop table RPT_REPORTMSTR;

drop table RPT_REPORTPARAMETER;

drop table RPT_REPORTPARAMETERMSTR;

drop table RPT_ROLE;

drop table RPT_SNAPSHOT;

drop table RPT_SNAPSHOTPARAMETER;

drop table RPT_SUBSCRIPTION;

--==============================================================
-- User: REPORT
--==============================================================
--==============================================================
-- Table: DATASOURCEMSTR
--==============================================================
create table DATASOURCEMSTR
(
   ID                   BIGINT                 not null,
   DATASOURCE_TYPE      VARCHAR(8),
   DATASOURCE_CODE      VARCHAR(200),
   DATASOURCE_DESC      VARCHAR(1000),
   DRIVER_CLASSNAME     VARCHAR(200),
   CONNECT_URL          VARCHAR(2000),
   USERNAME             VARCHAR(200),
   PASSWORD             VARCHAR(200),
   REMARKS              VARCHAR(2000),
   DEFUNCT_IND          CHAR(1)                default 'N',
   CREATED_BY           VARCHAR(50),
   CREATED_DATETIME     TIMESTAMP,
   UPDATED_BY           VARCHAR(50),
   UPDATED_DATETIME     TIMESTAMP,
   constraint P_PK_DATASOURCEMST primary key (ID)
);

comment on table DATASOURCEMSTR is
'数据源主表';

comment on column DATASOURCEMSTR.ID is
'数据源主表主键';

comment on column DATASOURCEMSTR.DATASOURCE_TYPE is
'数据源分类';

comment on column DATASOURCEMSTR.DATASOURCE_CODE is
'数据源代码';

comment on column DATASOURCEMSTR.DATASOURCE_DESC is
'数据源描述';

comment on column DATASOURCEMSTR.DRIVER_CLASSNAME is
'数据库驱动类名';

comment on column DATASOURCEMSTR.CONNECT_URL is
'数据库连接字符串';

comment on column DATASOURCEMSTR.USERNAME is
'数据库连接用户名';

comment on column DATASOURCEMSTR.PASSWORD is
'数据库连接密码';

comment on column DATASOURCEMSTR.REMARKS is
'备注';

comment on column DATASOURCEMSTR.DEFUNCT_IND is
'删除标识';

comment on column DATASOURCEMSTR.CREATED_BY is
'创建人员ID';

comment on column DATASOURCEMSTR.CREATED_DATETIME is
'创建时间';

comment on column DATASOURCEMSTR.UPDATED_BY is
'最后更新人员ID';

comment on column DATASOURCEMSTR.UPDATED_DATETIME is
'最后更新时间';

--==============================================================
-- Table: FILEMSTR
--==============================================================
create table FILEMSTR
(
   ID                   BIGINT                 not null,
   FILE_TYPE            VARCHAR(8)             not null,
   FILE_NAME            VARCHAR(200)           not null,
   FILE_DESC            VARCHAR(1000),
   FILE_STORE_NAME      VARCHAR(200)           not null,
   FILE_STORE_LOCATION  VARCHAR(400)           not null,
   UPLOADED_BY          VARCHAR(50)            not null,
   UPLOADED_DATETIME    TIMESTAMP              not null,
   DEFUNCT_IND          CHAR(1)                not null default 'N',
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50)            not null,
   UPDATED_DATETIME     TIMESTAMP              not null,
   constraint PK_FILEMSTR primary key (ID)
);

comment on table FILEMSTR is
'文件主表';

comment on column FILEMSTR.ID is
'文件主表ID';

comment on column FILEMSTR.FILE_TYPE is
'文件类型
CAT = FLT
FLTPIC: 图片;
FLTRPT: 报表;';

comment on column FILEMSTR.FILE_NAME is
'文件名称';

comment on column FILEMSTR.FILE_DESC is
'文件描述';

comment on column FILEMSTR.FILE_STORE_NAME is
'文件存储名称';

comment on column FILEMSTR.FILE_STORE_LOCATION is
'文件存储位置';

comment on column FILEMSTR.UPLOADED_BY is
'上传人';

comment on column FILEMSTR.UPLOADED_DATETIME is
'上传时间';

comment on column FILEMSTR.DEFUNCT_IND is
'删除标识';

comment on column FILEMSTR.CREATED_BY is
'创建人';

comment on column FILEMSTR.CREATED_DATETIME is
'创建时间';

comment on column FILEMSTR.UPDATED_BY is
'最后更新人';

comment on column FILEMSTR.UPDATED_DATETIME is
'最后更新时间';

--==============================================================
-- Table: RPT_EXECUTIONPLANHISTORY
--==============================================================
create table RPT_EXECUTIONPLANHISTORY
(
   ID                   BIGINT                 not null,
   RPT_EXECUTIONPLANMSTR_ID BIGINT                 not null,
   RPT_SNAPSHOT_ID      BIGINT,
   EXECUTION_RESULT     VARCHAR(8)             not null,
   EXECUTION_ERROR_LOG  VARCHAR(3000),
   EXECUTION_START_DATETIME TIMESTAMP              not null,
   EXECUTION_END_DATETIME TIMESTAMP,
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50)            not null,
   UPDATED_DATETIME     TIMESTAMP              not null,
   constraint PK_RPT_EXECUTIONPLANHISTORY primary key (ID)
);

comment on table RPT_EXECUTIONPLANHISTORY is
'报表自动执行结果表';

comment on column RPT_EXECUTIONPLANHISTORY.ID is
'执行结果表ID';

comment on column RPT_EXECUTIONPLANHISTORY.RPT_EXECUTIONPLANMSTR_ID is
'执行计划表ID';

comment on column RPT_EXECUTIONPLANHISTORY.RPT_SNAPSHOT_ID is
'报表快照主键';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_RESULT is
'执行结果，CODE_CAT=''ERT''';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_ERROR_LOG is
'报错异常Log';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_START_DATETIME is
'执行开始时间';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_END_DATETIME is
'执行结束时间';

comment on column RPT_EXECUTIONPLANHISTORY.CREATED_BY is
'创建人';

comment on column RPT_EXECUTIONPLANHISTORY.CREATED_DATETIME is
'创建时间';

comment on column RPT_EXECUTIONPLANHISTORY.UPDATED_BY is
'最后修改人';

comment on column RPT_EXECUTIONPLANHISTORY.UPDATED_DATETIME is
'最后修改时间';

--==============================================================
-- Table: RPT_EXECUTIONPLANMSTR
--==============================================================
create table RPT_EXECUTIONPLANMSTR
(
   ID                   BIGINT                 not null,
   RPT_REPORTMSTR_ID    BIGINT                 not null,
   PLAN_NAME            VARCHAR(200)           not null,
   PLAN_TYPE            VARCHAR(8)             not null,
   PLAN_DURATION        VARCHAR(8),
   EXECUTION_DAY        SMALLINT,
   EXECUTION_TIMEPOINT  TIMESTAMP              not null,
   PLAN_START_DATE      TIMESTAMP,
   PLAN_END_DATE        TIMESTAMP,
   PAUSE_IND            CHAR(1)                not null default 'N',
   AUTO_PUBLISH_IND     CHAR(1)                not null default 'N',
   REMARKS              VARCHAR(600),
   DEFUNCT_IND          CHAR(1)                not null default 'N',
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50)            not null,
   UPDATED_DATETIME     TIMESTAMP              not null,
   constraint PK_RPT_EXECUTIONPLANMSTR primary key (ID)
);

comment on table RPT_EXECUTIONPLANMSTR is
'报表执行计划主表';

comment on column RPT_EXECUTIONPLANMSTR.ID is
'执行计划主键';

comment on column RPT_EXECUTIONPLANMSTR.RPT_REPORTMSTR_ID is
'报表ID';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_NAME is
'计划名称';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_TYPE is
'计划类型，CODE_CAT=''PLT''';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_DURATION is
'计划周期，CODE_CAT=''PLD''';

comment on column RPT_EXECUTIONPLANMSTR.EXECUTION_DAY is
'在每个周期的第几天执行';

comment on column RPT_EXECUTIONPLANMSTR.EXECUTION_TIMEPOINT is
'执行时间，临时计划时为确切的日期时间，长期计划时只有时间是有意义的';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_START_DATE is
'开始日期';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_END_DATE is
'结束日期';

comment on column RPT_EXECUTIONPLANMSTR.PAUSE_IND is
'暂停标志';

comment on column RPT_EXECUTIONPLANMSTR.AUTO_PUBLISH_IND is
'自动发布标识';

comment on column RPT_EXECUTIONPLANMSTR.REMARKS is
'注释';

comment on column RPT_EXECUTIONPLANMSTR.DEFUNCT_IND is
'删除标志';

comment on column RPT_EXECUTIONPLANMSTR.CREATED_BY is
'创建人ID';

comment on column RPT_EXECUTIONPLANMSTR.CREATED_DATETIME is
'创建时间';

comment on column RPT_EXECUTIONPLANMSTR.UPDATED_BY is
'最近修改人ID';

comment on column RPT_EXECUTIONPLANMSTR.UPDATED_DATETIME is
'最后修改时间';

--==============================================================
-- Table: RPT_EXECUTIONPLANPARAMETER
--==============================================================
create table RPT_EXECUTIONPLANPARAMETER
(
   ID                   BIGINT                 not null,
   RPT_EXECUTIONPALNMSTR_ID BIGINT                 not null,
   RPT_REPORTPARAMETER_ID BIGINT                 not null,
   PARAMETER_VALUE_TYPE VARCHAR(8)             not null,
   PARAMETER_VALUE      VARCHAR(300),
   PARAMETER_VALUE_DESC VARCHAR(300),
   DURATION_BEFORE      SMALLINT,
   DAY_IN_DURATION      SMALLINT,
   TIME_IN_DAY          TIMESTAMP,
   DEFUNCT_IND          CHAR(1),
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50)            not null,
   UPDATED_DATETIME     TIMESTAMP              not null,
   constraint PK_RPT_EXECUTIONPLANPARAMETER primary key (ID)
);

comment on table RPT_EXECUTIONPLANPARAMETER is
'报表执行计划参数表';

comment on column RPT_EXECUTIONPLANPARAMETER.ID is
'执行计划参数ID';

comment on column RPT_EXECUTIONPLANPARAMETER.RPT_EXECUTIONPALNMSTR_ID is
'执行计划主表ID';

comment on column RPT_EXECUTIONPLANPARAMETER.RPT_REPORTPARAMETER_ID is
'报表参数ID';

comment on column RPT_EXECUTIONPLANPARAMETER.PARAMETER_VALUE_TYPE is
'参数取值类型，CODE_CAT=''PVT''';

comment on column RPT_EXECUTIONPLANPARAMETER.PARAMETER_VALUE is
'参数值';

comment on column RPT_EXECUTIONPLANPARAMETER.PARAMETER_VALUE_DESC is
'参数描述';

comment on column RPT_EXECUTIONPLANPARAMETER.DURATION_BEFORE is
'前几个周期，0表示当前周期';

comment on column RPT_EXECUTIONPLANPARAMETER.DAY_IN_DURATION is
'周期内的第几天';

comment on column RPT_EXECUTIONPLANPARAMETER.TIME_IN_DAY is
'一天中的具体时间点';

comment on column RPT_EXECUTIONPLANPARAMETER.DEFUNCT_IND is
'删除标志';

comment on column RPT_EXECUTIONPLANPARAMETER.CREATED_BY is
'创建人ID';

comment on column RPT_EXECUTIONPLANPARAMETER.CREATED_DATETIME is
'创建时间';

comment on column RPT_EXECUTIONPLANPARAMETER.UPDATED_BY is
'最近修改人ID';

comment on column RPT_EXECUTIONPLANPARAMETER.UPDATED_DATETIME is
'最后修改时间';

--==============================================================
-- Table: RPT_REPORTFILEHISTORY
--==============================================================
create table RPT_REPORTFILEHISTORY
(
   ID                   BIGINT                 not null,
   REPORTMSTR_ID        BIGINT                 not null,
   FILEMSTR_SOURCE_ID   BIGINT,
   FILEMSTR_COMPILED_ID BIGINT,
   REMARKS              VARCHAR(600),
   VERSION_NO           NUMERIC(3),
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50),
   UPDATED_DATETIME     TIMESTAMP,
   constraint PK_REPORTFILEHISTORY primary key (ID)
);

comment on table RPT_REPORTFILEHISTORY is
'报表文件信息表';

comment on column RPT_REPORTFILEHISTORY.ID is
'报表文件信息历史表主键';

comment on column RPT_REPORTFILEHISTORY.REPORTMSTR_ID is
'报表主表ID';

comment on column RPT_REPORTFILEHISTORY.FILEMSTR_SOURCE_ID is
'文件信息表ID';

comment on column RPT_REPORTFILEHISTORY.FILEMSTR_COMPILED_ID is
'上传文件信息表主键';

comment on column RPT_REPORTFILEHISTORY.CREATED_BY is
'上次修改人';

comment on column RPT_REPORTFILEHISTORY.CREATED_DATETIME is
'上次修改时间';

comment on column RPT_REPORTFILEHISTORY.UPDATED_BY is
'更新者';

comment on column RPT_REPORTFILEHISTORY.UPDATED_DATETIME is
'更新时间';

--==============================================================
-- Table: RPT_REPORTMSTR
--==============================================================
create table RPT_REPORTMSTR
(
   ID                   BIGINT                 not null,
   REPORT_CATEGORY      VARCHAR(8)             not null,
   REPORT_CODE          VARCHAR(50)            not null,
   REPORT_NAME          VARCHAR(300)           not null,
   REPORT_MODE          VARCHAR(8),
   DATASOURCE_TYPE      VARCHAR(8),
   DATASOURCEMSTR_ID    BIGINT,
   FILEMSTR_SOURCE_ID   BIGINT,
   FILEMSTR_COMPILED_ID BIGINT,
   REPORTFILEHISTORY_ID BIGINT,
   RPT_SNAPSHOT_ID      BIGINT,
   SNAPSHOT_TITLE_PATTERN BIGINT,
   REMARKS              VARCHAR(300),
   DEFUNCT_IND          CHAR(1)                not null default 'N',
   CREATED_BY           VARCHAR(50),
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50),
   UPDATED_DATETIME     TIMESTAMP              not null,
   constraint PK_RPT_REPORTMSTR primary key (ID),
   constraint UK_RRPM_REPORT_CODE unique (REPORT_CODE),
   constraint UK_RRPM_REPORT_NAME unique (REPORT_NAME)
);

comment on table RPT_REPORTMSTR is
'报表主表';

comment on column RPT_REPORTMSTR.ID is
'报表主表主键';

comment on column RPT_REPORTMSTR.REPORT_CATEGORY is
'CAT= RPC ，报表分类
报表分类';

comment on column RPT_REPORTMSTR.REPORT_CODE is
'报表编号';

comment on column RPT_REPORTMSTR.REPORT_NAME is
'报表名';

comment on column RPT_REPORTMSTR.REPORT_MODE is
'CAT= RPM ，报表模式
报表模式 
RPMP:联机报表 
RPMS:统计报表';

comment on column RPT_REPORTMSTR.DATASOURCE_TYPE is
'数据源类型';

comment on column RPT_REPORTMSTR.DATASOURCEMSTR_ID is
'数据源ID';

comment on column RPT_REPORTMSTR.FILEMSTR_SOURCE_ID is
'上传文件信息表主键';

comment on column RPT_REPORTMSTR.FILEMSTR_COMPILED_ID is
'上传文件信息表主键';

comment on column RPT_REPORTMSTR.REPORTFILEHISTORY_ID is
'报表文件信息历史表主键';

comment on column RPT_REPORTMSTR.RPT_SNAPSHOT_ID is
'报表快照主键';

comment on column RPT_REPORTMSTR.SNAPSHOT_TITLE_PATTERN is
'保存报表快照时标题的格式。如：${subspecialtyMstrId}某某报表(${startDate} - ${endDate})';

comment on column RPT_REPORTMSTR.REMARKS is
'备注';

comment on column RPT_REPORTMSTR.DEFUNCT_IND is
'删除标志';

comment on column RPT_REPORTMSTR.CREATED_BY is
'创建人';

comment on column RPT_REPORTMSTR.CREATED_DATETIME is
'创建时间';

comment on column RPT_REPORTMSTR.UPDATED_BY is
'修改人';

comment on column RPT_REPORTMSTR.UPDATED_DATETIME is
'修改时间';

--==============================================================
-- Table: RPT_REPORTPARAMETER
--==============================================================
create table RPT_REPORTPARAMETER
(
   ID                   BIGINT                 not null,
   REPORT_PARAMETER_CODE VARCHAR(30)            not null,
   SEQ_NO               SMALLINT               not null,
   UI_LABEL             VARCHAR(50),
   MANDATORY_IND        CHAR(1)                not null default 'Y',
   DISPLAY_IND          CHAR(1)                not null default 'Y',
   EDIT_IND             CHAR(1)                not null default 'Y',
   DEFAULT_VALUE_TYPE   VARCHAR(8),
   DEFAULT_VALUE        VARCHAR(200),
   JAVA_DATA_TYPE       VARCHAR(20)            not null,
   REPORTMSTR_ID        BIGINT,
   REMARKS              VARCHAR(600),
   SQL_STATEMENT        VARCHAR(2000),
   DEFUNCT_IND          CHAR(1)                not null default 'N',
   CREATED_BY           VARCHAR(50),
   CREATED_DATETIME     TIMESTAMP,
   UPDATED_BY           VARCHAR(50),
   UPDATED_DATETIME     TIMESTAMP,
   constraint PK_RPT_REPORTPARAMETER primary key (ID)
);

comment on table RPT_REPORTPARAMETER is
'报表参数设置表';

comment on column RPT_REPORTPARAMETER.ID is
'报表参数设置表主键';

comment on column RPT_REPORTPARAMETER.REPORT_PARAMETER_CODE is
'报表参数名称，和报表文件中定义的参数名称一致';

comment on column RPT_REPORTPARAMETER.SEQ_NO is
'参数字段顺序号';

comment on column RPT_REPORTPARAMETER.UI_LABEL is
'输入框显示的提示';

comment on column RPT_REPORTPARAMETER.MANDATORY_IND is
'Y:必选参数，N:可选参数';

comment on column RPT_REPORTPARAMETER.DISPLAY_IND is
'是否显示该参数';

comment on column RPT_REPORTPARAMETER.EDIT_IND is
'参数值是否可编辑';

comment on column RPT_REPORTPARAMETER.DEFAULT_VALUE_TYPE is
'CAT = DVT  
参数默认值类型 
DVTSSC:当前登录相关信息 
DVTADH:自定义默认参数 
DVTPCS:取词器 
DVTOFS:偏移量定位 
DVTSQL:SQL查询结果定位';

comment on column RPT_REPORTPARAMETER.JAVA_DATA_TYPE is
'参数的Java类型 
如String, Integer, BigDecimal,Timestamp';

comment on column RPT_REPORTPARAMETER.REPORTMSTR_ID is
'报表主表ID';

comment on column RPT_REPORTPARAMETER.SQL_STATEMENT is
'报表相关SQL语句';

comment on column RPT_REPORTPARAMETER.DEFUNCT_IND is
'删除标志';

comment on column RPT_REPORTPARAMETER.CREATED_BY is
'创建人';

comment on column RPT_REPORTPARAMETER.CREATED_DATETIME is
'创建时间';

comment on column RPT_REPORTPARAMETER.UPDATED_BY is
'最后修改人';

comment on column RPT_REPORTPARAMETER.UPDATED_DATETIME is
'最后修改时间';

--==============================================================
-- Table: RPT_REPORTPARAMETERMSTR
--==============================================================
create table RPT_REPORTPARAMETERMSTR
(
   ID                   BIGINT                 not null,
   REPORT_PARAMETER_MSTR_CODE VARCHAR(30),
   UI_LABEL             VARCHAR(50),
   UI_LABEL_LANG1       VARCHAR(50),
   EDIT_IND             CHAR(1)                not null default 'N',
   UI_TYPE              VARCHAR(8),
   DEFAULT_VALUE_TYPE   VARCHAR(8),
   DEFAULT_VALUE        VARCHAR(200),
   JAVA_DATA_TYPE       VARCHAR(20),
   SQL_STATEMENT        VARCHAR(500),
   PICKSHELL_SEARCHVIEW_TYPE VARCHAR(50),
   PICKSHELL_VIEW_TARGET_FIELD VARCHAR(30),
   PICKSHELL_VIEW_SHOW_FIELD VARCHAR(30),
   DEFUNCT_IND          CHAR(1)                not null default 'N',
   CREATED_BY           VARCHAR(50),
   CREATED_DATETIME     TIMESTAMP,
   UPDATED_BY           VARCHAR(50),
   UPDATED_DATETIME     TIMESTAMP,
   PICKSHELL_FILTER_VALUE VARCHAR(200),
   REMARKS              VARCHAR(600),
   constraint PK_REPORTPARAMETERMSTR primary key (ID)
);

comment on table RPT_REPORTPARAMETERMSTR is
'报表参数主表ID';

comment on column RPT_REPORTPARAMETERMSTR.ID is
'报表参数主表主键';

comment on column RPT_REPORTPARAMETERMSTR.REPORT_PARAMETER_MSTR_CODE is
'报表参数MSTR CODE';

comment on column RPT_REPORTPARAMETERMSTR.UI_LABEL is
'输入框显示的提示';

comment on column RPT_REPORTPARAMETERMSTR.UI_LABEL_LANG1 is
'输入框显示的提示(语言1）';

comment on column RPT_REPORTPARAMETERMSTR.EDIT_IND is
'参数值是否可编辑';

comment on column RPT_REPORTPARAMETERMSTR.UI_TYPE is
'CAT= RUT 
输入控件类型 
RUTTXT:文本输入框 
RUTDDL:下拉列表 
RUTCKB:复选框 
RUTDAT:日期输入框 
RUTDTM:日期时间输入框 
RUTPKS:取词器';

comment on column RPT_REPORTPARAMETERMSTR.DEFAULT_VALUE_TYPE is
'CAT = DVT 
参数默认值类型 
DVTSSC:当前登录相关信息 
DVTADH:自定义默认参数 
DVTPCS:取词器 
DVTOFS:偏移量定位 
DVTSQL:SQL查询结果定位';

comment on column RPT_REPORTPARAMETERMSTR.DEFAULT_VALUE is
'参数默认值 
当DEFAULT_VALUE_TYPE=DVTADH 时：  
如果参数UI类型为文本框（即UI_TYPE=RUTTXT）时，那么界面上就用这个字段的值填充，作为默认值。  
如果参数UI类型为下拉列表（即UI_TYPE=RUTDDL）时，那么界面上用这个字段的值作为CODE进行定位（这个字段的值一定是一个DropDown List 的CODE）。  
如果参数UI类型为复选框（即UI_TYPE=RUTCKB）时，那么该字段的值一定是‘Y’或N。等于Y时自动打勾，否则不选。  
如果参数UI类型为日期（即UI_TYPE=RUTDAT）时，默认值格式为YYYY-MM-DD，或者是SYSDATE。目前不处理‘RUTDTM’即日期时间的情况。  
  
当DEFAULT_VALUE_TYPE=DVTSSC 时，表明参数默认值是SESSION中的内容，该字段所有可能的取值如下：  
USERMSTR_ID  
CAREPROVIDER_ID  
LOCATIONMSTR_ID  
SUBSPECIALTYMSTR_ID  
WARDMSTR_ID  
并且UI_TYPE只可能是文本框或下拉列表。';

comment on column RPT_REPORTPARAMETERMSTR.JAVA_DATA_TYPE is
'参数的Java类型 
如String, Integer, BigDecimal,Timestamp';

comment on column RPT_REPORTPARAMETERMSTR.SQL_STATEMENT is
'报表相关SQL语句';

comment on column RPT_REPORTPARAMETERMSTR.PICKSHELL_VIEW_TARGET_FIELD is
'PICKSHELL捕获的目标字段';

comment on column RPT_REPORTPARAMETERMSTR.PICKSHELL_VIEW_SHOW_FIELD is
'PICKSHELL显示出来的字段';

comment on column RPT_REPORTPARAMETERMSTR.DEFUNCT_IND is
'删除标志';

comment on column RPT_REPORTPARAMETERMSTR.CREATED_BY is
'上次修改人';

comment on column RPT_REPORTPARAMETERMSTR.CREATED_DATETIME is
'上次修改时间';

comment on column RPT_REPORTPARAMETERMSTR.UPDATED_BY is
'最后修改人';

comment on column RPT_REPORTPARAMETERMSTR.UPDATED_DATETIME is
'最后修改时间';

comment on column RPT_REPORTPARAMETERMSTR.PICKSHELL_FILTER_VALUE is
'PICKSHELL 的过滤值 
比如 
USERMSTR_ID 
CAREPROVIDER_ID 
LOCATIONMSTR_ID 
SUBSPECIALTYMSTR_ID 
WARDMSTR_ID';

comment on column RPT_REPORTPARAMETERMSTR.REMARKS is
'备注';

--==============================================================
-- Table: RPT_ROLE
--==============================================================
create table RPT_ROLE
(
   ID                   BIGINT                 not null,
   ROLEMSTR_ID          BIGINT,
   REPORTMSTR_ID        BIGINT,
   DEFUNCT_IND          CHAR(1)                default 'N',
   CREATED_BY           VARCHAR(50),
   CREATED_DATETIME     TIMESTAMP,
   UPDATED_BY           VARCHAR(50),
   UPDATED_DATETIME     TIMESTAMP,
   constraint P_PK_PRT_ROLE primary key (ID)
);

comment on column RPT_ROLE.ID is
'角色报表ID';

comment on column RPT_ROLE.ROLEMSTR_ID is
'角色ID';

comment on column RPT_ROLE.REPORTMSTR_ID is
'报表ID';

comment on column RPT_ROLE.DEFUNCT_IND is
'删除FLAG';

comment on column RPT_ROLE.CREATED_BY is
'创建者';

comment on column RPT_ROLE.CREATED_DATETIME is
'创建时间';

comment on column RPT_ROLE.UPDATED_BY is
'更新着';

comment on column RPT_ROLE.UPDATED_DATETIME is
'更新时间';

--==============================================================
-- Table: RPT_SNAPSHOT
--==============================================================
create table RPT_SNAPSHOT
(
   ID                   BIGINT                 not null,
   REPORTMSTR_ID        BIGINT                 not null,
   SNAPSHOT_TITLE       VARCHAR(300)           not null,
   PUBLISHED_IND        CHAR(1)                not null default 'N',
   PRIVATE_IND          CHAR(1)                not null default 'N',
   REMARKS              VARCHAR(300),
   PUBLISHED_BY         VARCHAR(50),
   PUBLISHED_DATETIME   TIMESTAMP,
   DEFUNCT_IND          CHAR(1)                not null default 'N',
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50)            not null,
   UPDATED_DATETIME     TIMESTAMP              not null,
   constraint PK_RPT_SNAPSHOT primary key (ID)
);

comment on table RPT_SNAPSHOT is
'报表快照';

comment on column RPT_SNAPSHOT.ID is
'报表快照主键';

comment on column RPT_SNAPSHOT.REPORTMSTR_ID is
'报表ID';

comment on column RPT_SNAPSHOT.SNAPSHOT_TITLE is
'快照标题';

comment on column RPT_SNAPSHOT.PUBLISHED_IND is
'发布标识';

comment on column RPT_SNAPSHOT.PRIVATE_IND is
'直投标识';

comment on column RPT_SNAPSHOT.REMARKS is
'备注';

comment on column RPT_SNAPSHOT.PUBLISHED_BY is
'发布人';

comment on column RPT_SNAPSHOT.PUBLISHED_DATETIME is
'发布时间';

comment on column RPT_SNAPSHOT.DEFUNCT_IND is
'删除标识';

comment on column RPT_SNAPSHOT.CREATED_BY is
'创建人';

comment on column RPT_SNAPSHOT.CREATED_DATETIME is
'创建时间';

comment on column RPT_SNAPSHOT.UPDATED_BY is
'最后修改人';

comment on column RPT_SNAPSHOT.UPDATED_DATETIME is
'最后修改时间';

--==============================================================
-- Table: RPT_SNAPSHOTPARAMETER
--==============================================================
create table RPT_SNAPSHOTPARAMETER
(
   ID                   BIGINT                 not null,
   RPT_SNAPSHOT_ID      BIGINT                 not null,
   REPORTPARAMETER_ID   BIGINT,
   PARAMETER_CODE       VARCHAR(30),
   PARAMETER_DESC       VARCHAR(50),
   PARAMETER_VALUE      VARCHAR(300),
   PARAMETER_VALUE_DESC VARCHAR(300),
   SEQ_NO               SMALLINT               not null,
   DEFUNCT_IND          CHAR(1),
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50),
   UPDATED_DATETIME     TIMESTAMP,
   constraint PK_RPT_SNAPSHOTPARAMETER primary key (ID)
);

comment on table RPT_SNAPSHOTPARAMETER is
'报表快照参数表';

comment on column RPT_SNAPSHOTPARAMETER.ID is
'报表快照参数表主键';

comment on column RPT_SNAPSHOTPARAMETER.RPT_SNAPSHOT_ID is
'报表快照ID';

comment on column RPT_SNAPSHOTPARAMETER.REPORTPARAMETER_ID is
'报表参数设置表主键';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_CODE is
'参数编码';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_DESC is
'参数名称';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_VALUE is
'参数值';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_VALUE_DESC is
'参数值描述';

comment on column RPT_SNAPSHOTPARAMETER.SEQ_NO is
'顺序号';

comment on column RPT_SNAPSHOTPARAMETER.CREATED_BY is
'创建人';

comment on column RPT_SNAPSHOTPARAMETER.CREATED_DATETIME is
'创建时间';

--==============================================================
-- Table: RPT_SUBSCRIPTION
--==============================================================
create table RPT_SUBSCRIPTION
(
   ID                   BIGINT                 not null,
   USERMSTR_ID          BIGINT,
   REPORTMSTR_ID        BIGINT                 not null,
   REMARKS              VARCHAR(300),
   DEFUNCT_IND          CHAR(1)                not null default 'N',
   CREATED_BY           VARCHAR(50)            not null,
   CREATED_DATETIME     TIMESTAMP              not null,
   UPDATED_BY           VARCHAR(50)            not null,
   UPDATED_DATETIME     TIMESTAMP              not null,
   constraint PK_RPT_SUBSCRIPTION primary key (ID)
);

comment on table RPT_SUBSCRIPTION is
'报表订阅表';

comment on column RPT_SUBSCRIPTION.ID is
'报表订阅表主键';

comment on column RPT_SUBSCRIPTION.USERMSTR_ID is
'用户ID';

comment on column RPT_SUBSCRIPTION.REPORTMSTR_ID is
'报表ID';

comment on column RPT_SUBSCRIPTION.REMARKS is
'备注';

comment on column RPT_SUBSCRIPTION.DEFUNCT_IND is
'删除标识';

comment on column RPT_SUBSCRIPTION.CREATED_BY is
'创建人';

comment on column RPT_SUBSCRIPTION.CREATED_DATETIME is
'创建时间';

comment on column RPT_SUBSCRIPTION.UPDATED_BY is
'最后修改人';

comment on column RPT_SUBSCRIPTION.UPDATED_DATETIME is
'最后修改时间';

alter table RPT_EXECUTIONPLANHISTORY
   add constraint FK_REPM_REPH foreign key (RPT_EXECUTIONPLANMSTR_ID)
      references RPT_EXECUTIONPLANMSTR (ID)
      on delete no action on update restrict;

alter table RPT_EXECUTIONPLANHISTORY
   add constraint F_FK_RP_SS_EPH foreign key (RPT_SNAPSHOT_ID)
      references RPT_SNAPSHOT (ID)
      on delete restrict on update restrict;

alter table RPT_EXECUTIONPLANMSTR
   add constraint F_FK_RP_RM_EPM foreign key (RPT_REPORTMSTR_ID)
      references RPT_REPORTMSTR (ID)
      on delete restrict on update restrict;

alter table RPT_EXECUTIONPLANPARAMETER
   add constraint FK_REPM_REPP foreign key (RPT_EXECUTIONPALNMSTR_ID)
      references RPT_EXECUTIONPLANMSTR (ID)
      on delete no action on update restrict;

alter table RPT_EXECUTIONPLANPARAMETER
   add constraint F_FK_RP_RP_EPP foreign key (RPT_REPORTPARAMETER_ID)
      references RPT_REPORTPARAMETER (ID)
      on delete restrict on update restrict;

alter table RPT_REPORTFILEHISTORY
   add constraint FK_RFH_FM_COMPILED foreign key (FILEMSTR_COMPILED_ID)
      references FILEMSTR (ID)
      on delete no action on update restrict;

alter table RPT_REPORTFILEHISTORY
   add constraint FK_RFH_FM_SOURCE foreign key (FILEMSTR_SOURCE_ID)
      references FILEMSTR (ID)
      on delete no action on update restrict;

alter table RPT_REPORTFILEHISTORY
   add constraint FK_RFH_RM foreign key (REPORTMSTR_ID)
      references RPT_REPORTMSTR (ID)
      on delete no action on update restrict;

alter table RPT_REPORTMSTR
   add constraint F_FK_RP_DSM_RM foreign key (DATASOURCEMSTR_ID)
      references DATASOURCEMSTR (ID)
      on delete restrict on update restrict;

alter table RPT_REPORTMSTR
   add constraint FK_RM_FM_COMPILED foreign key (FILEMSTR_COMPILED_ID)
      references FILEMSTR (ID)
      on delete no action on update restrict;

alter table RPT_REPORTMSTR
   add constraint FK_RM_FM_SOURCE foreign key (FILEMSTR_SOURCE_ID)
      references FILEMSTR (ID)
      on delete no action on update restrict;

alter table RPT_REPORTMSTR
   add constraint FK_RM_RFH foreign key (REPORTFILEHISTORY_ID)
      references RPT_REPORTFILEHISTORY (ID)
      on delete no action on update restrict;

alter table RPT_REPORTMSTR
   add constraint F_FK_RP_SN_RM foreign key (RPT_SNAPSHOT_ID)
      references RPT_SNAPSHOT (ID)
      on delete restrict on update restrict;

alter table RPT_REPORTPARAMETER
   add constraint FK_RRP_RRM foreign key (REPORTMSTR_ID)
      references RPT_REPORTMSTR (ID)
      on delete no action on update restrict;

alter table RPT_ROLE
   add constraint F_FK_RP_RM_RR foreign key (ROLEMSTR_ID)
      references ROLEMSTR (ID)
      on delete restrict on update restrict;

alter table RPT_ROLE
   add constraint F_FK_RP_RPM_RR foreign key (REPORTMSTR_ID)
      references RPT_REPORTMSTR (ID)
      on delete restrict on update restrict;

alter table RPT_SNAPSHOT
   add constraint F_FK_RP_RM_SS foreign key (REPORTMSTR_ID)
      references RPT_REPORTMSTR (ID)
      on delete restrict on update restrict;

alter table RPT_SNAPSHOTPARAMETER
   add constraint F_FK_RP_RP_SSP foreign key (REPORTPARAMETER_ID)
      references RPT_REPORTPARAMETER (ID)
      on delete restrict on update restrict;

alter table RPT_SNAPSHOTPARAMETER
   add constraint FK_RSSP_RSS foreign key (RPT_SNAPSHOT_ID)
      references RPT_SNAPSHOT (ID)
      on delete restrict on update restrict;

alter table RPT_SUBSCRIPTION
   add constraint F_FK_RP_RM_RS foreign key (REPORTMSTR_ID)
      references RPT_REPORTMSTR (ID)
      on delete restrict on update restrict;

