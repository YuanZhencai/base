INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 14, 'Wilmar TMS', 'tms', 1, 0, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 15, '����', 'tms:process', 1, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 16, '����������', 'tms:process:new', 1, 15, 'MENU', '/faces/process/common/processNew-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 17, '���ύ������', 'tms:process:subed', 2, 15, 'MENU', '/faces/process/common/processSubed-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 18, '�Ѵ��������', 'tms:process:dealed', 3, 15, 'MENU', '/faces/process/common/processDealed-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 19, '�����յ�����', 'tms:process:wait', 4, 15, 'MENU', '/faces/process/common/processWait-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 20, '�ѽ��յ�����', 'tms:process:acced', 5, 15, 'MENU', '/faces/process/common/processAccept-list.xhtml') ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 21, '����', 'tms:report', 2, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 22, '��ծ������ϸ��', 'tms:report:debt', 1, 21, 'MENU', '/faces/report/debtBorrow/debtBorrow-detail.xhtml') ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 23, 'ϵͳ����', 'tms:system', 3, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 24, '�û�����', 'tms:system:user', 1, 23, 'MENU', '/faces/common/user/index.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 25, '��ɫ����', 'tms:system:role', 2, 23, 'MENU', '/faces/common/role/index.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 26, '�ֵ�����', 'tms:system:dict', 3, 23, 'MENU', '/faces/common/dict/index.xhtml' ) ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 27, '�����ݹ���', 'tms:main', 4, 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 28, '������Ϣ����', 'tms:main:bank', 1, 27, 'MENU', '/faces/system/bankManage/bankManage-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 29, '��˾��Ϣ����', 'tms:main:company', 2, 27, 'MENU', '/faces/system/companyManage/comManage-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 30, 'ϵͳ���̹���', 'tms:main:process', 3, 27, 'MENU', '/faces/system/systemProcess/systemProcess-list.xhtml') ;

--7.16 �µ�2������
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 31, 'Ͷ����Ʋ�Ʒ�����ϸ��', 'tms:report:invest', 2, 21, 'MENU', '/faces/report/inveProduct/inveProduct-detail.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 32, '���Ŷ����ϸ��', 'tms:report:credit', 3, 21, 'MENU', '/faces/report/bankCredit/bankCredit-detail.xhtml') ;


insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (1, 'ϵͳ����Ա','admin', 'sysadmin', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (504, '��ͨ������', '', 'Requester', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (505, '����������', '', 'FacFinance', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (506, '�����ܾ���', '', 'FacManager', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (510, '�����ʽ��λ��Ա', '', 'FacFundPos', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (511, '�����ɱ����', '', 'FacAccount', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (512, '��˾ó������ ', '', 'ComTrader', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (513, '��˾ó�׾���', '', 'ComTraderM', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (514, '����Ʒ��ת���ܼ�', '', 'CopGv2director', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (515, '���Ż�Ʋ�����Ա', '', 'CopAccount', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (516, '�����ʽ�����Ա-������', '', 'CopFund1', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (517, '�����ʽ�����Ա-������', '', 'CopFund2', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (518, '�����ʽ�����Ա-�����', '', 'CopFund3', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (519, '�����ʽ�����Ա-��ծ������', '', 'CopFund4', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (520, '�����ʽ�����Ա-�����˻�������', '', 'CopFund5', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (521, '�����ʽ��ž���', '', 'CopFundM', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (522, '�����ʽ��ܼ�', '', 'w', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (523, '������Ŀ����', '', 'r', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (524, '�����ʽ�ƻ�Ա', '', 'CopPlanner', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (525, '���Ų����ܼ�', '', 'CopCFO', 'N');
insert into ROLE (ID, NAME, description, CODE, DEFUNCT_IND) values (526, '�¼����쵼', '', 'Singapore', 'N');
