INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 14, 'Wilmar TMS', 'tms', 1, 0, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 15, '流程', 'tms:process', 1, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 16, '发起新流程', 'tms:process:new', 1, 15, 'MENU', '/faces/process/common/processNew-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 17, '已提交的流程', 'tms:process:subed', 2, 15, 'MENU', '/faces/process/common/processSubed-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 18, '已处理的任务', 'tms:process:dealed', 3, 15, 'MENU', '/faces/process/common/processDealed-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 19, '待接收的任务', 'tms:process:wait', 4, 15, 'MENU', '/faces/process/common/processWait-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 20, '已接收的任务', 'tms:process:acced', 5, 15, 'MENU', '/faces/process/common/processAccept-list.xhtml') ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 21, '报表', 'tms:report', 2, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 22, '外债申请明细表', 'tms:report:debt', 1, 21, 'MENU', '/faces/report/debtBorrow/debtBorrow-detail.xhtml') ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 23, '系统配置', 'tms:system', 3, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 24, '用户管理', 'tms:system:user', 1, 23, 'MENU', '/faces/common/user/index.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 25, '角色管理', 'tms:system:role', 2, 23, 'MENU', '/faces/common/role/index.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 26, '字典表管理', 'tms:system:dict', 3, 23, 'MENU', '/faces/common/dict/index.xhtml' ) ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 27, '主数据管理', 'tms:main', 4, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 28, '银行信息管理', 'tms:main:bank', 1, 27, 'MENU', '/faces/system/bankManage/bankManage-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 29, '公司信息管理', 'tms:main:company', 2, 27, 'MENU', '/faces/system/companyManage/comManage-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 30, '系统流程管理', 'tms:main:process', 3, 27, 'MENU', '/faces/system/systemProcess/systemProcess-list.xhtml') ;

--7.16 新的2个报表
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 31, '投资理财产品额度明细表', 'tms:report:invest', 2, 21, 'MENU', '/faces/report/inveProduct/inveProduct-detail.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 32, '授信额度明细表', 'tms:report:credit', 3, 21, 'MENU', '/faces/report/bankCredit/bankCredit-detail.xhtml') ;


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
