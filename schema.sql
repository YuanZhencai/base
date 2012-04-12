    drop table dict;

    drop table ID_GEN;
    
    drop table PERSON;

    drop table PRODUCT;

    drop table TestUser;    

    alter table user_role 
        drop constraint FK143BF46AE384B0C4;

    alter table user_role 
        drop constraint FK143BF46A88AF74A4;

    drop table permission;

    drop table resource;

    drop table role;

    drop table user_role;

    drop table users;
    
    create table dict (
        id int8 not null,
        CODE varchar(30) not null,
        DEFUNCT_IND smallint,
        NAME varchar(30) not null,
        PARENT_CODE varchar(30),
        VALUE varchar(30),
        primary key (id)
    );

    create table ID_GEN (
         sequence_name varchar(255),
         sequence_next_hi_value int4
    ) ;
    

    create table PERSON (
        id int8 not null,
        CREATED_BY varchar(30),
        CREATED_DATETIME timestamp,
        DEFUNCT_IND smallint,
        UPDATED_BY varchar(30),
        UPDATED_DATETIME timestamp,
        ADDRESS varchar(100),
        BIRTHDAY timestamp,
        EMAIL varchar(50),
        NAME varchar(50) not null,
        NATIONALITY varchar(50),
        PHONE varchar(20),
        REMARKS varchar(500),
        SEX varchar(10),
        VIP smallint,
        primary key (id)
    );

    create table PRODUCT (
        id int8 not null,
        CREATED_BY varchar(30),
        CREATED_DATETIME timestamp,
        DEFUNCT_IND smallint,
        UPDATED_BY varchar(30),
        UPDATED_DATETIME timestamp,
        AVAILABLE smallint,
        CATEGORY varchar(255),
        CODE varchar(255),
        DESCRIBE varchar(1000),
        NAME varchar(255),
        PRICE float8,
        PRODUCTION_DATE timestamp,
        primary key (id)
    );

    create table TestUser (
        id varchar(255) not null,
        firstName varchar(255),
        lastName varchar(255),
        primary key (id)
    );
    
    create table permission (
        id int8 not null,
        roleid int8,
        url varchar(255),
        primary key (id)
    );

    create table resource (
        id int8 not null,
        is_leaf bool,
        is_menu bool,
        key_name varchar(255) not null,
        level int4,
        name varchar(40),
        number varchar(255),
        parent_id int8,
        url varchar(100),
        primary key (id)
    );

    create table role (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

    create table user_role (
        user_id int8 not null,
        role_id int8 not null
    );

    create table users (
        id int8 not null,
        email varchar(255),
        loginName varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );

    alter table user_role 
        add constraint FK143BF46AE384B0C4 
        foreign key (role_id) 
        references role;

    alter table user_role 
        add constraint FK143BF46A88AF74A4 
        foreign key (user_id) 
        references users;

    