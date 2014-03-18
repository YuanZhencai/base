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
'����Դ����';

comment on column DATASOURCEMSTR.ID is
'����Դ��������';

comment on column DATASOURCEMSTR.DATASOURCE_TYPE is
'����Դ����';

comment on column DATASOURCEMSTR.DATASOURCE_CODE is
'����Դ����';

comment on column DATASOURCEMSTR.DATASOURCE_DESC is
'����Դ����';

comment on column DATASOURCEMSTR.DRIVER_CLASSNAME is
'���ݿ���������';

comment on column DATASOURCEMSTR.CONNECT_URL is
'���ݿ������ַ���';

comment on column DATASOURCEMSTR.USERNAME is
'���ݿ������û���';

comment on column DATASOURCEMSTR.PASSWORD is
'���ݿ���������';

comment on column DATASOURCEMSTR.REMARKS is
'��ע';

comment on column DATASOURCEMSTR.DEFUNCT_IND is
'ɾ����ʶ';

comment on column DATASOURCEMSTR.CREATED_BY is
'������ԱID';

comment on column DATASOURCEMSTR.CREATED_DATETIME is
'����ʱ��';

comment on column DATASOURCEMSTR.UPDATED_BY is
'��������ԱID';

comment on column DATASOURCEMSTR.UPDATED_DATETIME is
'������ʱ��';

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
'�ļ�����';

comment on column FILEMSTR.ID is
'�ļ�����ID';

comment on column FILEMSTR.FILE_TYPE is
'�ļ�����
CAT = FLT
FLTPIC: ͼƬ;
FLTRPT: ����;';

comment on column FILEMSTR.FILE_NAME is
'�ļ�����';

comment on column FILEMSTR.FILE_DESC is
'�ļ�����';

comment on column FILEMSTR.FILE_STORE_NAME is
'�ļ��洢����';

comment on column FILEMSTR.FILE_STORE_LOCATION is
'�ļ��洢λ��';

comment on column FILEMSTR.UPLOADED_BY is
'�ϴ���';

comment on column FILEMSTR.UPLOADED_DATETIME is
'�ϴ�ʱ��';

comment on column FILEMSTR.DEFUNCT_IND is
'ɾ����ʶ';

comment on column FILEMSTR.CREATED_BY is
'������';

comment on column FILEMSTR.CREATED_DATETIME is
'����ʱ��';

comment on column FILEMSTR.UPDATED_BY is
'��������';

comment on column FILEMSTR.UPDATED_DATETIME is
'������ʱ��';

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
'�����Զ�ִ�н����';

comment on column RPT_EXECUTIONPLANHISTORY.ID is
'ִ�н����ID';

comment on column RPT_EXECUTIONPLANHISTORY.RPT_EXECUTIONPLANMSTR_ID is
'ִ�мƻ���ID';

comment on column RPT_EXECUTIONPLANHISTORY.RPT_SNAPSHOT_ID is
'�����������';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_RESULT is
'ִ�н����CODE_CAT=''ERT''';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_ERROR_LOG is
'�����쳣Log';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_START_DATETIME is
'ִ�п�ʼʱ��';

comment on column RPT_EXECUTIONPLANHISTORY.EXECUTION_END_DATETIME is
'ִ�н���ʱ��';

comment on column RPT_EXECUTIONPLANHISTORY.CREATED_BY is
'������';

comment on column RPT_EXECUTIONPLANHISTORY.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_EXECUTIONPLANHISTORY.UPDATED_BY is
'����޸���';

comment on column RPT_EXECUTIONPLANHISTORY.UPDATED_DATETIME is
'����޸�ʱ��';

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
'����ִ�мƻ�����';

comment on column RPT_EXECUTIONPLANMSTR.ID is
'ִ�мƻ�����';

comment on column RPT_EXECUTIONPLANMSTR.RPT_REPORTMSTR_ID is
'����ID';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_NAME is
'�ƻ�����';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_TYPE is
'�ƻ����ͣ�CODE_CAT=''PLT''';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_DURATION is
'�ƻ����ڣ�CODE_CAT=''PLD''';

comment on column RPT_EXECUTIONPLANMSTR.EXECUTION_DAY is
'��ÿ�����ڵĵڼ���ִ��';

comment on column RPT_EXECUTIONPLANMSTR.EXECUTION_TIMEPOINT is
'ִ��ʱ�䣬��ʱ�ƻ�ʱΪȷ�е�����ʱ�䣬���ڼƻ�ʱֻ��ʱ�����������';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_START_DATE is
'��ʼ����';

comment on column RPT_EXECUTIONPLANMSTR.PLAN_END_DATE is
'��������';

comment on column RPT_EXECUTIONPLANMSTR.PAUSE_IND is
'��ͣ��־';

comment on column RPT_EXECUTIONPLANMSTR.AUTO_PUBLISH_IND is
'�Զ�������ʶ';

comment on column RPT_EXECUTIONPLANMSTR.REMARKS is
'ע��';

comment on column RPT_EXECUTIONPLANMSTR.DEFUNCT_IND is
'ɾ����־';

comment on column RPT_EXECUTIONPLANMSTR.CREATED_BY is
'������ID';

comment on column RPT_EXECUTIONPLANMSTR.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_EXECUTIONPLANMSTR.UPDATED_BY is
'����޸���ID';

comment on column RPT_EXECUTIONPLANMSTR.UPDATED_DATETIME is
'����޸�ʱ��';

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
'����ִ�мƻ�������';

comment on column RPT_EXECUTIONPLANPARAMETER.ID is
'ִ�мƻ�����ID';

comment on column RPT_EXECUTIONPLANPARAMETER.RPT_EXECUTIONPALNMSTR_ID is
'ִ�мƻ�����ID';

comment on column RPT_EXECUTIONPLANPARAMETER.RPT_REPORTPARAMETER_ID is
'�������ID';

comment on column RPT_EXECUTIONPLANPARAMETER.PARAMETER_VALUE_TYPE is
'����ȡֵ���ͣ�CODE_CAT=''PVT''';

comment on column RPT_EXECUTIONPLANPARAMETER.PARAMETER_VALUE is
'����ֵ';

comment on column RPT_EXECUTIONPLANPARAMETER.PARAMETER_VALUE_DESC is
'��������';

comment on column RPT_EXECUTIONPLANPARAMETER.DURATION_BEFORE is
'ǰ�������ڣ�0��ʾ��ǰ����';

comment on column RPT_EXECUTIONPLANPARAMETER.DAY_IN_DURATION is
'�����ڵĵڼ���';

comment on column RPT_EXECUTIONPLANPARAMETER.TIME_IN_DAY is
'һ���еľ���ʱ���';

comment on column RPT_EXECUTIONPLANPARAMETER.DEFUNCT_IND is
'ɾ����־';

comment on column RPT_EXECUTIONPLANPARAMETER.CREATED_BY is
'������ID';

comment on column RPT_EXECUTIONPLANPARAMETER.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_EXECUTIONPLANPARAMETER.UPDATED_BY is
'����޸���ID';

comment on column RPT_EXECUTIONPLANPARAMETER.UPDATED_DATETIME is
'����޸�ʱ��';

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
'�����ļ���Ϣ��';

comment on column RPT_REPORTFILEHISTORY.ID is
'�����ļ���Ϣ��ʷ������';

comment on column RPT_REPORTFILEHISTORY.REPORTMSTR_ID is
'��������ID';

comment on column RPT_REPORTFILEHISTORY.FILEMSTR_SOURCE_ID is
'�ļ���Ϣ��ID';

comment on column RPT_REPORTFILEHISTORY.FILEMSTR_COMPILED_ID is
'�ϴ��ļ���Ϣ������';

comment on column RPT_REPORTFILEHISTORY.CREATED_BY is
'�ϴ��޸���';

comment on column RPT_REPORTFILEHISTORY.CREATED_DATETIME is
'�ϴ��޸�ʱ��';

comment on column RPT_REPORTFILEHISTORY.UPDATED_BY is
'������';

comment on column RPT_REPORTFILEHISTORY.UPDATED_DATETIME is
'����ʱ��';

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
'��������';

comment on column RPT_REPORTMSTR.ID is
'������������';

comment on column RPT_REPORTMSTR.REPORT_CATEGORY is
'CAT= RPC ���������
�������';

comment on column RPT_REPORTMSTR.REPORT_CODE is
'������';

comment on column RPT_REPORTMSTR.REPORT_NAME is
'������';

comment on column RPT_REPORTMSTR.REPORT_MODE is
'CAT= RPM ������ģʽ
����ģʽ 
RPMP:�������� 
RPMS:ͳ�Ʊ���';

comment on column RPT_REPORTMSTR.DATASOURCE_TYPE is
'����Դ����';

comment on column RPT_REPORTMSTR.DATASOURCEMSTR_ID is
'����ԴID';

comment on column RPT_REPORTMSTR.FILEMSTR_SOURCE_ID is
'�ϴ��ļ���Ϣ������';

comment on column RPT_REPORTMSTR.FILEMSTR_COMPILED_ID is
'�ϴ��ļ���Ϣ������';

comment on column RPT_REPORTMSTR.REPORTFILEHISTORY_ID is
'�����ļ���Ϣ��ʷ������';

comment on column RPT_REPORTMSTR.RPT_SNAPSHOT_ID is
'�����������';

comment on column RPT_REPORTMSTR.SNAPSHOT_TITLE_PATTERN is
'���汨�����ʱ����ĸ�ʽ���磺${subspecialtyMstrId}ĳĳ����(${startDate} - ${endDate})';

comment on column RPT_REPORTMSTR.REMARKS is
'��ע';

comment on column RPT_REPORTMSTR.DEFUNCT_IND is
'ɾ����־';

comment on column RPT_REPORTMSTR.CREATED_BY is
'������';

comment on column RPT_REPORTMSTR.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_REPORTMSTR.UPDATED_BY is
'�޸���';

comment on column RPT_REPORTMSTR.UPDATED_DATETIME is
'�޸�ʱ��';

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
'����������ñ�';

comment on column RPT_REPORTPARAMETER.ID is
'����������ñ�����';

comment on column RPT_REPORTPARAMETER.REPORT_PARAMETER_CODE is
'����������ƣ��ͱ����ļ��ж���Ĳ�������һ��';

comment on column RPT_REPORTPARAMETER.SEQ_NO is
'�����ֶ�˳���';

comment on column RPT_REPORTPARAMETER.UI_LABEL is
'�������ʾ����ʾ';

comment on column RPT_REPORTPARAMETER.MANDATORY_IND is
'Y:��ѡ������N:��ѡ����';

comment on column RPT_REPORTPARAMETER.DISPLAY_IND is
'�Ƿ���ʾ�ò���';

comment on column RPT_REPORTPARAMETER.EDIT_IND is
'����ֵ�Ƿ�ɱ༭';

comment on column RPT_REPORTPARAMETER.DEFAULT_VALUE_TYPE is
'CAT = DVT  
����Ĭ��ֵ���� 
DVTSSC:��ǰ��¼�����Ϣ 
DVTADH:�Զ���Ĭ�ϲ��� 
DVTPCS:ȡ���� 
DVTOFS:ƫ������λ 
DVTSQL:SQL��ѯ�����λ';

comment on column RPT_REPORTPARAMETER.JAVA_DATA_TYPE is
'������Java���� 
��String, Integer, BigDecimal,Timestamp';

comment on column RPT_REPORTPARAMETER.REPORTMSTR_ID is
'��������ID';

comment on column RPT_REPORTPARAMETER.SQL_STATEMENT is
'�������SQL���';

comment on column RPT_REPORTPARAMETER.DEFUNCT_IND is
'ɾ����־';

comment on column RPT_REPORTPARAMETER.CREATED_BY is
'������';

comment on column RPT_REPORTPARAMETER.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_REPORTPARAMETER.UPDATED_BY is
'����޸���';

comment on column RPT_REPORTPARAMETER.UPDATED_DATETIME is
'����޸�ʱ��';

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
'�����������ID';

comment on column RPT_REPORTPARAMETERMSTR.ID is
'���������������';

comment on column RPT_REPORTPARAMETERMSTR.REPORT_PARAMETER_MSTR_CODE is
'�������MSTR CODE';

comment on column RPT_REPORTPARAMETERMSTR.UI_LABEL is
'�������ʾ����ʾ';

comment on column RPT_REPORTPARAMETERMSTR.UI_LABEL_LANG1 is
'�������ʾ����ʾ(����1��';

comment on column RPT_REPORTPARAMETERMSTR.EDIT_IND is
'����ֵ�Ƿ�ɱ༭';

comment on column RPT_REPORTPARAMETERMSTR.UI_TYPE is
'CAT= RUT 
����ؼ����� 
RUTTXT:�ı������ 
RUTDDL:�����б� 
RUTCKB:��ѡ�� 
RUTDAT:��������� 
RUTDTM:����ʱ������� 
RUTPKS:ȡ����';

comment on column RPT_REPORTPARAMETERMSTR.DEFAULT_VALUE_TYPE is
'CAT = DVT 
����Ĭ��ֵ���� 
DVTSSC:��ǰ��¼�����Ϣ 
DVTADH:�Զ���Ĭ�ϲ��� 
DVTPCS:ȡ���� 
DVTOFS:ƫ������λ 
DVTSQL:SQL��ѯ�����λ';

comment on column RPT_REPORTPARAMETERMSTR.DEFAULT_VALUE is
'����Ĭ��ֵ 
��DEFAULT_VALUE_TYPE=DVTADH ʱ��  
�������UI����Ϊ�ı��򣨼�UI_TYPE=RUTTXT��ʱ����ô�����Ͼ�������ֶε�ֵ��䣬��ΪĬ��ֵ��  
�������UI����Ϊ�����б���UI_TYPE=RUTDDL��ʱ����ô������������ֶε�ֵ��ΪCODE���ж�λ������ֶε�ֵһ����һ��DropDown List ��CODE����  
�������UI����Ϊ��ѡ�򣨼�UI_TYPE=RUTCKB��ʱ����ô���ֶε�ֵһ���ǡ�Y����N������Yʱ�Զ��򹴣�����ѡ��  
�������UI����Ϊ���ڣ���UI_TYPE=RUTDAT��ʱ��Ĭ��ֵ��ʽΪYYYY-MM-DD��������SYSDATE��Ŀǰ������RUTDTM��������ʱ��������  
  
��DEFAULT_VALUE_TYPE=DVTSSC ʱ����������Ĭ��ֵ��SESSION�е����ݣ����ֶ����п��ܵ�ȡֵ���£�  
USERMSTR_ID  
CAREPROVIDER_ID  
LOCATIONMSTR_ID  
SUBSPECIALTYMSTR_ID  
WARDMSTR_ID  
����UI_TYPEֻ�������ı���������б�';

comment on column RPT_REPORTPARAMETERMSTR.JAVA_DATA_TYPE is
'������Java���� 
��String, Integer, BigDecimal,Timestamp';

comment on column RPT_REPORTPARAMETERMSTR.SQL_STATEMENT is
'�������SQL���';

comment on column RPT_REPORTPARAMETERMSTR.PICKSHELL_VIEW_TARGET_FIELD is
'PICKSHELL�����Ŀ���ֶ�';

comment on column RPT_REPORTPARAMETERMSTR.PICKSHELL_VIEW_SHOW_FIELD is
'PICKSHELL��ʾ�������ֶ�';

comment on column RPT_REPORTPARAMETERMSTR.DEFUNCT_IND is
'ɾ����־';

comment on column RPT_REPORTPARAMETERMSTR.CREATED_BY is
'�ϴ��޸���';

comment on column RPT_REPORTPARAMETERMSTR.CREATED_DATETIME is
'�ϴ��޸�ʱ��';

comment on column RPT_REPORTPARAMETERMSTR.UPDATED_BY is
'����޸���';

comment on column RPT_REPORTPARAMETERMSTR.UPDATED_DATETIME is
'����޸�ʱ��';

comment on column RPT_REPORTPARAMETERMSTR.PICKSHELL_FILTER_VALUE is
'PICKSHELL �Ĺ���ֵ 
���� 
USERMSTR_ID 
CAREPROVIDER_ID 
LOCATIONMSTR_ID 
SUBSPECIALTYMSTR_ID 
WARDMSTR_ID';

comment on column RPT_REPORTPARAMETERMSTR.REMARKS is
'��ע';

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
'��ɫ����ID';

comment on column RPT_ROLE.ROLEMSTR_ID is
'��ɫID';

comment on column RPT_ROLE.REPORTMSTR_ID is
'����ID';

comment on column RPT_ROLE.DEFUNCT_IND is
'ɾ��FLAG';

comment on column RPT_ROLE.CREATED_BY is
'������';

comment on column RPT_ROLE.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_ROLE.UPDATED_BY is
'������';

comment on column RPT_ROLE.UPDATED_DATETIME is
'����ʱ��';

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
'�������';

comment on column RPT_SNAPSHOT.ID is
'�����������';

comment on column RPT_SNAPSHOT.REPORTMSTR_ID is
'����ID';

comment on column RPT_SNAPSHOT.SNAPSHOT_TITLE is
'���ձ���';

comment on column RPT_SNAPSHOT.PUBLISHED_IND is
'������ʶ';

comment on column RPT_SNAPSHOT.PRIVATE_IND is
'ֱͶ��ʶ';

comment on column RPT_SNAPSHOT.REMARKS is
'��ע';

comment on column RPT_SNAPSHOT.PUBLISHED_BY is
'������';

comment on column RPT_SNAPSHOT.PUBLISHED_DATETIME is
'����ʱ��';

comment on column RPT_SNAPSHOT.DEFUNCT_IND is
'ɾ����ʶ';

comment on column RPT_SNAPSHOT.CREATED_BY is
'������';

comment on column RPT_SNAPSHOT.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_SNAPSHOT.UPDATED_BY is
'����޸���';

comment on column RPT_SNAPSHOT.UPDATED_DATETIME is
'����޸�ʱ��';

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
'������ղ�����';

comment on column RPT_SNAPSHOTPARAMETER.ID is
'������ղ���������';

comment on column RPT_SNAPSHOTPARAMETER.RPT_SNAPSHOT_ID is
'�������ID';

comment on column RPT_SNAPSHOTPARAMETER.REPORTPARAMETER_ID is
'����������ñ�����';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_CODE is
'��������';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_DESC is
'��������';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_VALUE is
'����ֵ';

comment on column RPT_SNAPSHOTPARAMETER.PARAMETER_VALUE_DESC is
'����ֵ����';

comment on column RPT_SNAPSHOTPARAMETER.SEQ_NO is
'˳���';

comment on column RPT_SNAPSHOTPARAMETER.CREATED_BY is
'������';

comment on column RPT_SNAPSHOTPARAMETER.CREATED_DATETIME is
'����ʱ��';

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
'�����ı�';

comment on column RPT_SUBSCRIPTION.ID is
'�����ı�����';

comment on column RPT_SUBSCRIPTION.USERMSTR_ID is
'�û�ID';

comment on column RPT_SUBSCRIPTION.REPORTMSTR_ID is
'����ID';

comment on column RPT_SUBSCRIPTION.REMARKS is
'��ע';

comment on column RPT_SUBSCRIPTION.DEFUNCT_IND is
'ɾ����ʶ';

comment on column RPT_SUBSCRIPTION.CREATED_BY is
'������';

comment on column RPT_SUBSCRIPTION.CREATED_DATETIME is
'����ʱ��';

comment on column RPT_SUBSCRIPTION.UPDATED_BY is
'����޸���';

comment on column RPT_SUBSCRIPTION.UPDATED_DATETIME is
'����޸�ʱ��';

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

