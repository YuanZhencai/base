select 'drop table '||rtrim(TABSCHEMA)||'.'||TABNAME||';' from syscat.tables where tabschema='DB2ADMIN'

drop table DB2ADMIN.DICT;
drop table DB2ADMIN.O;
drop table DB2ADMIN.P;
drop table DB2ADMIN.PERMISSION;
drop table DB2ADMIN.PERSON;
drop table DB2ADMIN.PRODUCT;
drop table DB2ADMIN.PS;
drop table DB2ADMIN.RESOURCE;
drop table DB2ADMIN.RESOURCEMSTR;
drop table DB2ADMIN.ROLE;
drop table DB2ADMIN.ROLEMSTR;
drop table DB2ADMIN.ROLERESOURCE;
drop table DB2ADMIN.S;
drop table DB2ADMIN.SEQUENCE;
drop table DB2ADMIN.USERMSTR;
drop table DB2ADMIN.USERROLE;
drop table DB2ADMIN.USERS;
drop table DB2ADMIN.USER_ROLE;