--
--ER/Studio 7.0 SQL Code Generation
-- Company :      WCS
-- Project :      税务平台ER关系图.DM1
-- Author :       fuqiang
--
-- Date Created : Wednesday, June 13, 2012 16:39:21
-- Target DBMS : IBM DB2 UDB 8.x
--

-- 
-- TABLE: DICT 
--

drop table DICT;
drop table O;
drop table P;
drop table PS;
drop table RESOURCEMSTR;
drop table ROLEMSTR;
drop table ROLERESOURCE;
drop table S;
drop table SYNCLOG;
drop table PU;
drop table USERMSTR;
drop table USERROLE;
drop table USR;
drop SEQUENCE SRC;

CREATE SEQUENCE SRC START WITH 500 INCREMENT BY 1 MAXVALUE 999999999 CYCLE NOCACHE;

-- 
-- TABLE: DICT 
--

CREATE TABLE DICT(
    ID                  BIGINT          NOT NULL,
    CODE_CAT            VARCHAR(50)     NOT NULL,
    CODE_KEY            VARCHAR(50)     NOT NULL,
    CODE_VAL            VARCHAR(100)    NOT NULL,
    REMARKS             VARCHAR(200),
    SEQ_NO              BIGINT,
    SYS_IND             CHAR(1)         NOT NULL WITH DEFAULT 'N',
    LANG                CHAR(5)         NOT NULL WITH DEFAULT 'zh_CN',
    DEFUNCT_IND         CHAR(1)         NOT NULL WITH DEFAULT 'N',
    CREATED_BY          VARCHAR(50)     NOT NULL,
    CREATED_DATETIME    TIMESTAMP       NOT NULL,
    UPDATED_BY          VARCHAR(50)     NOT NULL,
    UPDATED_DATETIME    TIMESTAMP       NOT NULL,
    CONSTRAINT PK41 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: O 
--

CREATE TABLE O(
    ID             VARCHAR(20),
    BUKRS          VARCHAR(200),
    STEXT          VARCHAR(200),
    PARENT         VARCHAR(20),
    KOSTL          VARCHAR(200),
    ZHRZZCJID      VARCHAR(200),
    ZHRZZDWID      VARCHAR(200),
    ZHRTXXLID      VARCHAR(200),
    ZHRTXXLMS      VARCHAR(200),
    DEFUNCT_IND    CHAR(1)         WITH DEFAULT 'N'
)
;



-- 
-- TABLE: P 
--

CREATE TABLE P(
    ID             VARCHAR(20),
    NACHN          VARCHAR(200),
    NAME2          VARCHAR(200),
    ICNUM          VARCHAR(200),
    EMAIL          VARCHAR(200),
    GESCH          VARCHAR(200),
    TELNO          VARCHAR(200),
    CELNO          VARCHAR(200),
    BUKRS          VARCHAR(200),
    KOSTL          VARCHAR(200),
    ORGEH          VARCHAR(200),
    DEFUNCT_IND    CHAR(1)         WITH DEFAULT 'N'
)
;



-- 
-- TABLE: PS 
--

CREATE TABLE PS(
    ID             VARCHAR(20),
    SID            VARCHAR(20),
    PID            VARCHAR(20),
    MAIN_IND       VARCHAR(200),
    DEFUNCT_IND    CHAR(1)         WITH DEFAULT 'N'
)
;



-- 
-- TABLE: PU 
--

CREATE TABLE PU(
    ID             VARCHAR(50),
    PERNR          VARCHAR(20),
    DEFUNCT_IND    CHAR(1)
)
;



-- 
-- TABLE: RESOURCEMSTR 
--

CREATE TABLE RESOURCEMSTR(
    ID                  BIGINT          NOT NULL,
    NAME                VARCHAR(20)     NOT NULL,
    CODE                VARCHAR(50)     NOT NULL,
    SEQ_NO              VARCHAR(255),
    PARENT_ID           BIGINT,
    TYPE                VARCHAR(50)     NOT NULL,
    URI                 VARCHAR(200),
    DEFUNCT_IND         CHAR(1)         NOT NULL WITH DEFAULT 'N',
    CREATED_BY          VARCHAR(50)     NOT NULL,
    CREATED_DATETIME    TIMESTAMP       NOT NULL,
    UPDATED_BY          VARCHAR(50)     NOT NULL,
    UPDATED_DATETIME    TIMESTAMP       NOT NULL,
    CONSTRAINT PK17 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: ROLEMSTR 
--

CREATE TABLE ROLEMSTR(
    ID                  BIGINT         NOT NULL,
    NAME                VARCHAR(20)    NOT NULL,
    DESC                VARCHAR(50),
    CODE                VARCHAR(50)    NOT NULL,
    DEFUNCT_IND         CHAR(1)        NOT NULL WITH DEFAULT 'N',
    CREATED_BY          VARCHAR(50)    NOT NULL,
    CREATED_DATETIME    TIMESTAMP      NOT NULL,
    UPDATED_BY          VARCHAR(50)    NOT NULL,
    UPDATED_DATETIME    TIMESTAMP      NOT NULL,
    CONSTRAINT PK9 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: ROLERESOURCE 
--

CREATE TABLE ROLERESOURCE(
    ID                  BIGINT         NOT NULL,
    RESOURCEMSTR_ID     BIGINT,
    ROLEMSTR_ID         BIGINT,
    DEFUNCT_IND         CHAR(1),
    CREATED_BY          VARCHAR(50),
    CREATED_DATETIME    TIMESTAMP,
    UPDATED_BY          VARCHAR(50),
    UPDATED_DATETIME    TIMESTAMP,
    CONSTRAINT PK18 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: S 
--

CREATE TABLE S(
    ID             VARCHAR(20),
    OID            VARCHAR(20),
    STEXT          VARCHAR(200),
    KOSTL          VARCHAR(200),
    ZHRTXXLID      VARCHAR(200),
    ZHRTXXLMS      VARCHAR(200),
    ZHRCJID        VARCHAR(200),
    ZHRCJMS        VARCHAR(200),
    DEFUNCT_IND    CHAR(1)         WITH DEFAULT 'N'
)
;



-- 
-- TABLE: SYNCLOG 
--

CREATE TABLE SYNCLOG(
    ID               BIGINT          NOT NULL,
    VERSION          BIGINT          NOT NULL,
    SYNC_IND         CHAR(1),
    SYNC_TYPE        VARCHAR(20),
    SYNC_DATETIME    TIMESTAMP       WITH DEFAULT CURRENT TIMESTAMP,
    REMARKS          VARCHAR(200),
    CONSTRAINT PK52 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: USERMSTR 
--

CREATE TABLE USERMSTR(
    ID                  BIGINT         NOT NULL,
    AD_ACCOUNT          VARCHAR(50),
    PERNR               VARCHAR(20),
    DEFUNCT_IND         CHAR(1)        NOT NULL WITH DEFAULT 'N',
    CREATED_BY          VARCHAR(50)    NOT NULL,
    CREATED_DATETIME    TIMESTAMP      NOT NULL,
    UPDATED_BY          VARCHAR(50)    NOT NULL,
    UPDATED_DATETIME    TIMESTAMP      NOT NULL,
    CONSTRAINT PK2 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: USERROLE 
--

CREATE TABLE USERROLE(
    ID                  BIGINT         NOT NULL,
    USERMSTR_ID         BIGINT,
    ROLEMSTR_ID         BIGINT,
    DEFUNCT_IND         CHAR(1),
    CREATED_BY          VARCHAR(50),
    CREATED_DATETIME    TIMESTAMP,
    UPDATED_BY          VARCHAR(50),
    UPDATED_DATETIME    TIMESTAMP,
    CONSTRAINT PK14 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: USR 
--

CREATE TABLE USR(
    ID               VARCHAR(50),
    NAME             VARCHAR(200),
    DESC             VARCHAR(1000),
    EMAIL            VARCHAR(200),
    TELNO            VARCHAR(200),
    CELNO            VARCHAR(200),
    SEARCH_PHRASE    VARCHAR(1000),
    DEFUNCT_IND      CHAR(1)
)
;



-- 
-- INDEX: O_INX 
--

CREATE INDEX O_INX ON O(ID)
;
-- 
-- INDEX: P__INX 
--

CREATE INDEX P__INX ON P(ID)
;
-- 
-- INDEX: PS_INX 
--

CREATE INDEX PS_INX ON PS(ID)
;
-- 
-- INDEX: PU_INX 
--

CREATE INDEX PU_INX ON PU(ID)
;
-- 
-- INDEX: S_INX 
--

CREATE INDEX S_INX ON S(ID)
;
-- 
-- INDEX: USR_INX 
--

CREATE INDEX USR_INX ON USR(ID)
;
-- 
-- TABLE: ROLERESOURCE 
--

ALTER TABLE ROLERESOURCE ADD CONSTRAINT RefROLEMSTR19 
    FOREIGN KEY (ROLEMSTR_ID)
    REFERENCES ROLEMSTR(ID)
;

ALTER TABLE ROLERESOURCE ADD CONSTRAINT RefRESOURCEMSTR20 
    FOREIGN KEY (RESOURCEMSTR_ID)
    REFERENCES RESOURCEMSTR(ID)
;


-- 
-- TABLE: USERROLE 
--

ALTER TABLE USERROLE ADD CONSTRAINT RefUSERMSTR100 
    FOREIGN KEY (USERMSTR_ID)
    REFERENCES USERMSTR(ID)
;

ALTER TABLE USERROLE ADD CONSTRAINT RefROLEMSTR101 
    FOREIGN KEY (ROLEMSTR_ID)
    REFERENCES ROLEMSTR(ID)
;


DROP TABLE WF_INSTANCEMSTR
;
DROP TABLE WF_INSTANCEMSTR_PROPERTY
;
DROP TABLE WF_STEPMSTR
;
DROP TABLE WF_STEPMSTR_PROPERTY
;

-- 
-- TABLE: WF_INSTANCEMSTR 
--

CREATE TABLE WF_INSTANCEMSTR(
    ID                  BIGINT          NOT NULL,
    NO                  VARCHAR(50)     NOT NULL,
    TYPE                VARCHAR(100)    NOT NULL,
    REQUEST_BY          VARCHAR(50)     NOT NULL,
    SUBMIT_DATETIME     TIMESTAMP,
    STATUS              VARCHAR(100)    NOT NULL,
    DEFUNCT_IND         CHAR(1)         NOT NULL,
    CREATED_BY          VARCHAR(50)     NOT NULL,
    CREATED_DATETIME    TIMESTAMP       NOT NULL,
    UPDATED_BY          VARCHAR(50)     NOT NULL,
    UPDATED_DATETIME    TIMESTAMP       NOT NULL,
    CONSTRAINT PK41_1 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: WF_INSTANCEMSTR_PROPERTY 
--

CREATE TABLE WF_INSTANCEMSTR_PROPERTY(
    ID                    BIGINT           NOT NULL,
    WF_INSTANCEMSTR_ID    BIGINT,
    NAME                  VARCHAR(100)     NOT NULL,
    VALUE                 VARCHAR(1000),
    CONSTRAINT PK44_1 PRIMARY KEY (ID)
)
;
-- 
-- TABLE: WF_STEPMSTR 
--

CREATE TABLE WF_STEPMSTR(
    ID                    BIGINT         NOT NULL,
    WF_INSTANCEMSTR_ID    BIGINT,
    FROM_STEP_ID          BIGINT,
    NAME                  VARCHAR(50)    NOT NULL,
    CODE                  VARCHAR(50)    NOT NULL,
    CHARGED_BY            VARCHAR(50)    NOT NULL,
    COMPLETED_DATETIME    TIMESTAMP,
    DEAL_METHOD           VARCHAR(50)    NOT NULL,
    DEFUNCT_IND           CHAR(1)        NOT NULL,
    CREATED_BY            VARCHAR(50)    NOT NULL,
    CREATED_DATETIME      TIMESTAMP      NOT NULL,
    UPDATED_BY            VARCHAR(50)    NOT NULL,
    UPDATED_DATETIME      TIMESTAMP      NOT NULL,
    CONSTRAINT PK58 PRIMARY KEY (ID)
)
;



-- 
-- TABLE: WF_STEPMSTR_PROPERTY 
--

CREATE TABLE WF_STEPMSTR_PROPERTY(
    ID                BIGINT           NOT NULL,
    WF_STEPMSTR_ID    BIGINT,
    NAME              VARCHAR(100)     NOT NULL,
    VALUE             VARCHAR(1000),
    CONSTRAINT PK64 PRIMARY KEY (ID)
)
;

-- 
-- TABLE: WF_INSTANCEMSTR_PROPERTY 
--

ALTER TABLE WF_INSTANCEMSTR_PROPERTY ADD CONSTRAINT RefWF_INSTANCEM832 
    FOREIGN KEY (WF_INSTANCEMSTR_ID)
    REFERENCES WF_INSTANCEMSTR(ID)
;


-- 
-- TABLE: WF_STEPMSTR 
--

ALTER TABLE WF_STEPMSTR ADD CONSTRAINT RefWF_INSTANCEM842 
    FOREIGN KEY (WF_INSTANCEMSTR_ID)
    REFERENCES WF_INSTANCEMSTR(ID)
;


-- 
-- TABLE: WF_STEPMSTR_PROPERTY 
--

ALTER TABLE WF_STEPMSTR_PROPERTY ADD CONSTRAINT RefWF_STEPMSTR852 
    FOREIGN KEY (WF_STEPMSTR_ID)
    REFERENCES WF_STEPMSTR(ID)
;



