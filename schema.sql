DROP TABLE dict;
DROP TABLE permission;
DROP TABLE person;
DROP TABLE product;
DROP TABLE resource;
DROP TABLE role;
DROP TABLE sequence;
DROP TABLE user_role;
DROP TABLE users;

CREATE TABLE dict(id BIGINT NOT NULL, code CHARACTER VARYING(30) NOT NULL, defunct_ind BOOLEAN, name CHARACTER VARYING(30) NOT NULL, parent_code CHARACTER VARYING(30), value CHARACTER VARYING(30), PRIMARY KEY (id))

CREATE TABLE permission ( id BIGINT NOT NULL, roleid BIGINT, permission CHARACTER VARYING(255), PRIMARY KEY (id) );

CREATE TABLE person ( id BIGINT NOT NULL, address CHARACTER VARYING(100), birthday TIMESTAMP(6) WITHOUT TIME ZONE, created_by CHARACTER VARYING(30), created_datetime TIMESTAMP(6) WITHOUT TIME ZONE, defunct_ind BOOLEAN, email CHARACTER VARYING(50), name CHARACTER VARYING(50) NOT NULL, nationality CHARACTER VARYING(50), phone CHARACTER VARYING(20), remarks CHARACTER VARYING(500), sex CHARACTER VARYING(10), updated_by CHARACTER VARYING(30), updated_datetime TIMESTAMP(6) WITHOUT TIME ZONE, vip SMALLINT, PRIMARY KEY (id) );

CREATE TABLE product ( id BIGINT NOT NULL, available BOOLEAN, category CHARACTER VARYING(255), code CHARACTER VARYING(255), created_by CHARACTER VARYING(30), created_datetime TIMESTAMP(6) WITHOUT TIME ZONE, defunct_ind BOOLEAN, describe CHARACTER VARYING(1000), name CHARACTER VARYING(255), price DOUBLE PRECISION, production_date TIMESTAMP(6) WITHOUT TIME ZONE, updated_by CHARACTER VARYING(30), updated_datetime TIMESTAMP(6) WITHOUT TIME ZONE, PRIMARY KEY (id) );

CREATE TABLE resource ( id BIGINT NOT NULL, is_leaf BOOLEAN, is_menu BOOLEAN, key_name CHARACTER VARYING(255) NOT NULL, level INTEGER, name CHARACTER VARYING(40), number CHARACTER VARYING(255), parent_id BIGINT, url CHARACTER VARYING(100), PRIMARY KEY (id) );

CREATE TABLE role ( id BIGINT NOT NULL, name CHARACTER VARYING(255), PRIMARY KEY (id) );

CREATE TABLE sequence ( seq_name CHARACTER VARYING(50) NOT NULL, seq_count NUMERIC(38,0), PRIMARY KEY (seq_name) );

CREATE TABLE user_role ( user_id BIGINT NOT NULL, role_id BIGINT NOT NULL, PRIMARY KEY (user_id, role_id) );

CREATE TABLE users ( id BIGINT NOT NULL, email CHARACTER VARYING(255), loginname CHARACTER VARYING(255), name CHARACTER VARYING(255), password CHARACTER VARYING(255), PRIMARY KEY (id) );
ALTER TABLE user_role ADD CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES public.role (id);
ALTER TABLE user_role ADD CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES public.users (id);



DROP TABLE  DICT 
;
DROP TABLE  O 
;
DROP TABLE  P 
;
DROP TABLE  PS 
;
DROP TABLE  PU 
;
DROP TABLE  RESOURCEMSTR 
;
DROP TABLE  ROLEMSTR 
;
DROP TABLE  ROLERESOURCE 
;
DROP TABLE  S 
;
DROP TABLE  SYNCLOG 
;
DROP TABLE  USERMSTR 
;
DROP TABLE  USERROLE 
;
-- 
-- TABLE:  DICT  
--

CREATE TABLE  DICT (
     ID                 int8            NOT NULL,
     CODE_CAT           varchar(50)     NOT NULL,
     CODE_KEY           varchar(50)     NOT NULL,
     CODE_VAL           varchar(100)    NOT NULL,
     REMARKS            varchar(200),
     SEQ_NO             int8,
     SYS_IND            char(1)         DEFAULT 'N' NOT NULL,
     LANG               char(5)         DEFAULT 'zh_CN' NOT NULL,
     DEFUNCT_IND        char(1)         DEFAULT 'N' NOT NULL,
     CREATED_BY         varchar(50)     NOT NULL,
     CREATED_DATETIME   timestamp       NOT NULL,
     UPDATED_BY         varchar(50)     NOT NULL,
     UPDATED_DATETIME   timestamp       NOT NULL,
    CONSTRAINT  PK41  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  O  
--

CREATE TABLE  O (
     ID            varchar(20)    NOT NULL,
     BUKRS         varchar(20),
     STEXT         varchar(20),
     PARENT        varchar(20),
     KOSTL         varchar(20),
     ZHRZZCJID     varchar(20),
     ZHRZZDWID     varchar(20),
     ZHRTXXLID     varchar(20),
     ZHRTXXLMS     varchar(50),
     DEFUNCT_IND   char(1)        DEFAULT 'N' NOT NULL,
    CONSTRAINT  PK4_1  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  P  
--

CREATE TABLE  P (
     ID            varchar(20)    NOT NULL,
     NACHN         varchar(20),
     NAME2         varchar(20),
     ICNUM         varchar(30),
     EMAIL         varchar(50),
     GESCH         varchar(20),
     TELNO         varchar(20),
     CELNO         varchar(20),
     BUKRS         varchar(20),
     KOSTL         varchar(20),
     DEFUNCT_IND   char(1)        DEFAULT 'N' NOT NULL,
    CONSTRAINT  PK4_2_3  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  PS  
--

CREATE TABLE  PS (
     ID            varchar(20)    NOT NULL,
     SID           varchar(20)    NOT NULL,
     PID           varchar(20)    NOT NULL,
     DEFUNCT_IND   char(1)        DEFAULT 'N' NOT NULL,
    CONSTRAINT  PK14_1  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  PU  
--

CREATE TABLE  PU (
     ID            varchar(50)    NOT NULL,
     PERNR         varchar(20),
     DEFUNCT_IND   char(1)
)
;



-- 
-- TABLE:  RESOURCEMSTR  
--

CREATE TABLE  RESOURCEMSTR (
     ID                 int8            NOT NULL,
     NAME               varchar(20)     NOT NULL,
     CODE               varchar(50)     NOT NULL,
     SEQ_NO             varchar(255),
     PARENT_ID          int8,
     TYPE               varchar(50)     NOT NULL,
     URI                varchar(200),
     DEFUNCT_IND        char(1)         DEFAULT 'N' NOT NULL,
     CREATED_BY         varchar(50)     NOT NULL,
     CREATED_DATETIME   timestamp       NOT NULL,
     UPDATED_BY         varchar(50)     NOT NULL,
     UPDATED_DATETIME   timestamp       NOT NULL,
    CONSTRAINT  PK17  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  ROLEMSTR  
--

CREATE TABLE  ROLEMSTR (
     ID                 int8           NOT NULL,
     NAME               varchar(20)    NOT NULL,
     "DESC"               varchar(50),
     CODE               varchar(50)    NOT NULL,
     DEFUNCT_IND        char(1)        DEFAULT 'N' NOT NULL,
     CREATED_BY         varchar(50)    NOT NULL,
     CREATED_DATETIME   timestamp      NOT NULL,
     UPDATED_BY         varchar(50)    NOT NULL,
     UPDATED_DATETIME   timestamp      NOT NULL,
    CONSTRAINT  PK9  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  ROLERESOURCE  
--

CREATE TABLE  ROLERESOURCE (
     ID                 int8           NOT NULL,
     RESOURCEMSTR_ID    int8,
     ROLEMSTR_ID        int8,
     DEFUNCT_IND        char(1),
     CREATED_BY         varchar(50),
     CREATED_DATETIME   timestamp,
     UPDATED_BY         varchar(50),
     UPDATED_DATETIME   timestamp,
    CONSTRAINT  PK18  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  S  
--

CREATE TABLE  S (
     ID            varchar(20)    NOT NULL,
     OID           varchar(20)    NOT NULL,
     STEXT         varchar(20),
     KOSTL         varchar(20),
     ZHRTXXLID     varchar(20),
     ZHRTXXLMS     varchar(50),
     DEFUNCT_IND   char(1)        DEFAULT 'N' NOT NULL,
    CONSTRAINT  PK14_2  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  SYNCLOG  
--

CREATE TABLE  SYNCLOG (
     ID              int8           NOT NULL,
     VERSION         timestamp      NOT NULL,
     SYNC_TYPE       varchar(20),
     SYNC_DATETIME   timestamp,
     REMARKS         varchar(50),
    CONSTRAINT  PK52  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  USERMSTR  
--

CREATE TABLE  USERMSTR (
     ID                 int8           NOT NULL,
     AD_ACCOUNT         varchar(50),
     PERNR              varchar(20),
     DEFUNCT_IND        char(1)        DEFAULT 'N' NOT NULL,
     CREATED_BY         varchar(50)    NOT NULL,
     CREATED_DATETIME   timestamp      NOT NULL,
     UPDATED_BY         varchar(50)    NOT NULL,
     UPDATED_DATETIME   timestamp      NOT NULL,
    CONSTRAINT  PK2  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  USERROLE  
--

CREATE TABLE  USERROLE (
     ID                 int8           NOT NULL,
     USERMSTR_ID        int8,
     ROLEMSTR_ID        int8,
     DEFUNCT_IND        char(1),
     CREATED_BY         varchar(50),
     CREATED_DATETIME   timestamp,
     UPDATED_BY         varchar(50),
     UPDATED_DATETIME   timestamp,
    CONSTRAINT  PK14  PRIMARY KEY ( ID )
)
;



-- 
-- TABLE:  ROLERESOURCE  
--

ALTER TABLE  ROLERESOURCE  ADD CONSTRAINT  RefROLEMSTR191  
    FOREIGN KEY ( ROLEMSTR_ID )
    REFERENCES  ROLEMSTR ( ID )
;

ALTER TABLE  ROLERESOURCE  ADD CONSTRAINT  RefRESOURCEMSTR201  
    FOREIGN KEY ( RESOURCEMSTR_ID )
    REFERENCES  RESOURCEMSTR ( ID )
;


-- 
-- TABLE:  USERROLE  
--

ALTER TABLE  USERROLE  ADD CONSTRAINT  RefUSERMSTR1001  
    FOREIGN KEY ( USERMSTR_ID )
    REFERENCES  USERMSTR ( ID )
;

ALTER TABLE  USERROLE  ADD CONSTRAINT  RefROLEMSTR1011  
    FOREIGN KEY ( ROLEMSTR_ID )
    REFERENCES  ROLEMSTR ( ID )
;
