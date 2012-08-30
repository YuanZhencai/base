-- Resource

insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (1, 'base:setting', '系统配置', 0, 1, 'MENU', '');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (2, 'base:setting:user', '用户管理', 1, 1, 'MENU', '/faces/auth/user.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (3, 'base:setting:role', '角色管理', 1, 2, 'MENU', '/faces/auth/role.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (4, 'base:setting:resource', '角色管理', 1, 3, 'MENU', '/faces/auth/res.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (5, 'base:setting:dict', '字典表管理', 1, 4, 'MENU', '/faces/common/dict/index.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (6, 'base:setting', '参数设置', 1, 5, 'MENU', '');

insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (7, 'base:demos', 'Demos', 0, 2, 'MENU', null);
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (8, 'base:demos:showcase', 'Showcase', 7, 1, 'MENU', '/faces/showcase/index.xhtml');


insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (9, 'base:report', '报表', 0, 3, 'MENU', '');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (10, 'base:report:debt', '外债申请明细表', 9, 1, 'MENU', '/faces/report/debtBorrow/debtBorrow-detail.xhtml');

insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (11, 'base:process', '流程', 0, 4, 'MENU', '');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (12, 'base:process:new', '发起新流程', 11, 1, 'MENU', '/faces/process/common/processNew-list.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (13, 'base:process:subed', '已提交的流程', 11, 2, 'MENU', '/faces/process/common/processSubed-list.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (14, 'base:process:dealed', '已处理的任务', 11, 3, 'MENU', '/faces/process/common/processDealed-list.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (15, 'base:process:wait', '待接收的任务', 11, 4, 'MENU', '/faces/process/common/processWait-list.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (16, 'base:process:acced', '已接收的任务', 11, 5, 'MENU', '/faces/process/common/processAccept-list.xhtml');


insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (17, 'base:main', '主数据管理', 0, 5, 'MENU', '');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (18, 'base:main:bank', '银行信息管理', 17, 1, 'MENU', '/faces/system/bankManage/bankManage-list.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (19, 'base:main:company', '公司信息管理', 17, 2, 'MENU', '/faces/system/companyManage/comManage-list.xhtml');
insert into resource (id, code, name, PARENT_ID, SEQ_NO, type, uri) values (20, 'base:main:process', '系统流程管理', 17, 3, 'MENU', '/faces/system/systemProcess/systemProcess-list.xhtml');

-- Role

insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (1, '系统管理员','admin', 'sysadmin', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (504, '普通申请人', '', 'Requester', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (505, '工厂财务经理', '', 'FacFinance', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (506, '工厂总经理', '', 'FacManager', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (510, '工厂资金岗位人员', '', 'FacFundPos', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (511, '工厂成本会计', '', 'FacAccount', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (512, '公司贸易内勤 ', '', 'ComTrader', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (513, '公司贸易经理', '', 'ComTraderM', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (514, '集团品种转向总监', '', 'CopGv2director', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (515, '集团会计部门人员', '', 'CopAccount', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (516, '集团资金部门人员-授信组', '', 'CopFund1', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (517, '集团资金部门人员-担保组', '', 'CopFund2', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (518, '集团资金部门人员-理财组', '', 'CopFund3', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (519, '集团资金部门人员-外债申请组', '', 'CopFund4', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (520, '集团资金部门人员-银行账户申请组', '', 'CopFund5', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (521, '集团资金部门经理', '', 'CopFundM', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (522, '集团资金总监', '', 'w', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (523, '集团项目经理', '', 'r', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (524, '集团资金计划员', '', 'CopPlanner', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (525, '集团财务总监', '', 'CopCFO', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (526, '新加坡领导', '', 'Singapore', 'N');

INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (1, 'shenbo', '1' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (2, 'fnadmin', '2' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (3, 'fnadmin1', '3' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (4, 'org1user1', '4' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (5, 'org1cwjl', '5' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (6, 'org1zjl', '6' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (7, 'org1zj1', '7' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (8, 'org1cbkj1', '8' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (9, 'org1mynq1', '9' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (10, 'org1myjl', '10' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (11, 'gro1pzzhzj', '11' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (12, 'gro1kj1', '12' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (13, 'gro1zj1', '13' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (14, 'gro1zj2', '14' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (15, 'gro1zj3', '15' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (16, 'gro1zj4', '16' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (17, 'gro1zj5', '17' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (18, 'gro1zjjl', '18' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (19, 'gro1zjzj', '19' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (20, 'gro1xmjl', '20' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (21, 'gro1jh1', '21' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (22, 'gro1cwzj', '22' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (23, 'singzj', '23' ) ;

INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (24, 'org2user1', '24' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (25, 'org2cwjl', '25' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (26, 'org2zjl', '26' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (27, 'org2zj1', '27' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (28, 'org2cbkj1', '28' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (29, 'org2mynq1', '29' ) ;
INSERT INTO USERS (ID, AD_ACCOUNT, PERNR) VALUES (30, 'org2myjl', '30' ) ;



INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'shenbo', '1', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'fnadmin', '2', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'fnadmin1', '3', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org1user1', '4', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org1cwjl', '5', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org1zjl', '6', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org1zj1', '7', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org1cbkj1', '8', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org1mynq1', '9', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org1myjl', '10', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1pzzhzj', '11', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1kj1', '12', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1zj1', '13', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1zj2', '14', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1zj3', '15', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1zj4', '16', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1zj5', '17', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1zjjl', '18', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1zjzj', '19', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1xmjl', '20', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1jh1', '21', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'gro1cwzj', '22', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'singzj', '23', 'N' );

INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org2user1', '24', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org2cwjl', '25', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org2zjl', '26', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org2zj1', '27', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org2cbkj1', '28', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org2mynq1', '29', 'N' );
INSERT INTO PU ( ID, PERNR, DEFUNCT_IND ) VALUES ( 'org2myjl', '30', 'N' );
update users set defunct_ind='N'

insert into P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) values ('1', '沈啵', 'shenbo', '111111111111111111', 'sh@163.com', '男', '02111111111', '18600000000', '001', '成本中心', 'N');
insert into P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) values ('2', 'wilmar_cas', 'fnadmin', '222222222222222222', 'wilmar_cas@sina.com', '女', '02011111111', '18611111111', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('3', '陈东', 'fnadmin1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('4', '普通申请人', 'org1user1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('5', '工厂财务经理', 'org1cwjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('6', '工厂总经理', 'org1zjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('7', '工厂资金岗位', 'org1zj1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('8', '工厂成本会计', 'org1cbkj1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('9', '公司贸易内勤', 'org1mynq1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('10', '公司贸易经理', 'org1myjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('11', '品种转向总监', 'gro1pzzhzj', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('12', '集团会计部门', 'gro1kj1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('13', '资金部授信组', 'gro1zj1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('14', '资金部担保组', 'gro1zj2', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('15', '资金部理财组', 'gro1zj3', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('16', '资金部外债组', 'gro1zj4', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('17', '资金部银行组', 'gro1zj5', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('18', '资金部门经理', 'gro1zjjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('19', '集团资金总监', 'gro1zjzj', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('20', '集团项目经理', 'gro1xmjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('21', '集团资金计划', 'gro1jh1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('22', '集团财务总监', 'gro1cwzj', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('23', '新加坡领导', 'singzj', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');

INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('24', '普通申请人2', 'org2user1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('25', '工厂财务经2', 'org2cwjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('26', '工厂总经理2', 'org2zjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('27', '工厂资金岗2', 'org2zj1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('28', '工厂会计2', 'org2cbkj1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('29', '贸易内勤2', 'org2mynq1', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('30', '贸易经理2', 'org2myjl', '3333333333333333333', 'chenlong@wcs-global.com', '女', '02081181181', '18611111112', '001', '成本中心', 'N');

insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('755', '2', '申请人', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('756', '2', '工厂总经理', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('757', '2', '工厂财务经理', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('758', '1', '集团项目经理', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('759', '1', '集团资金部3', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('760', '2', '工厂资金岗位', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('761', '2', '工厂成本会计', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('762', '2', '公司贸易内勤 ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('763', '2', '公司贸易经理 ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('764', '1', '集团品种转向 ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('765', '1', '集团会计部门 ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('766', '1', '集团资金部1', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('767', '1', '集团资金部2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('768', '1', '集团资金部4', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('769', '1', '集团资金部5', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('770', '1', '集团资金经理', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('771', '1', '集团资金总监', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('772', '1', '集团资金计划', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('773', '1', '集团财务总监', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('774', '1', '新加坡领导', null, null, null, 'N');

insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('775', '3', '申请人2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('776', '3', '工厂财务经2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('777', '3', '工厂总经理2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('778', '3', '工厂资金岗2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('779', '3', '工厂会计2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('780', '3', '贸易内勤 2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('781', '3', '贸易经理 2', null, null, null, 'N');

insert into PS (ID, SID, PID, DEFUNCT_IND) values ('1', 756, 1, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('2', 756, 2, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('3', 756, 3, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('4', 755, 4, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('5', 756, 6, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('6', 757, 5, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('7', 758, 20, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('8', 759, 15, 'N');
--insert into PS (ID, SID, PID, DEFUNCT_IND) values ('9', 757, 15, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('10', 760, 7, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('11', 761, 8, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('12', 762, 9, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('13', 763, 10, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('14', 764, 11, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('15', 765, 12, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('16', 766, 13, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('17', 767, 14, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('18', 768, 16, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('19', 769, 17, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('20', 770, 18, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('21', 771, 19, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('22', 758, 20, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('23', 772, 21, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('24', 773, 22, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('25', 774, 23, 'N');

insert into PS (ID, SID, PID, DEFUNCT_IND) values ('26', 775, 24, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('27', 776, 25, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('28', 777, 26, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('29', 778, 27, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('30', 779, 28, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('31', 780, 29, 'N');
insert into PS (ID, SID, PID, DEFUNCT_IND) values ('32', 781, 30, 'N');

insert into O (ID, BUKRS, STEXT, PARENT, KOSTL, ZHRZZCJID, ZHRZZDWID, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('1', '001', '益海嘉里', null, null, '', null, null, null, 'N');
insert into O (ID, BUKRS, STEXT, PARENT, KOSTL, ZHRZZCJID, ZHRZZDWID, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('2', '002', '益海嘉里成都', '1', null, '', null, null, null, 'N');
insert into O (ID, BUKRS, STEXT, PARENT, KOSTL, ZHRZZCJID, ZHRZZDWID, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('3', '003', '益海嘉里上海', '1', null, '', null, null, null, 'N');
