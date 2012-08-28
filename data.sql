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

insert into P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) values ('1', '���', 'shenbo', '111111111111111111', 'sh@163.com', '��', '02111111111', '18600000000', '001', '�ɱ�����', 'N');
insert into P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) values ('2', 'wilmar_cas', 'fnadmin', '222222222222222222', 'wilmar_cas@sina.com', 'Ů', '02011111111', '18611111111', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('3', '�¶�', 'fnadmin1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('4', '��ͨ������', 'org1user1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('5', '����������', 'org1cwjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('6', '�����ܾ���', 'org1zjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('7', '�����ʽ��λ', 'org1zj1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('8', '�����ɱ����', 'org1cbkj1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('9', '��˾ó������', 'org1mynq1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('10', '��˾ó�׾���', 'org1myjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('11', 'Ʒ��ת���ܼ�', 'gro1pzzhzj', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('12', '���Ż�Ʋ���', 'gro1kj1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('13', '�ʽ�������', 'gro1zj1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('14', '�ʽ𲿵�����', 'gro1zj2', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('15', '�ʽ������', 'gro1zj3', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('16', '�ʽ���ծ��', 'gro1zj4', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('17', '�ʽ�������', 'gro1zj5', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('18', '�ʽ��ž���', 'gro1zjjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('19', '�����ʽ��ܼ�', 'gro1zjzj', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('20', '������Ŀ����', 'gro1xmjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('21', '�����ʽ�ƻ�', 'gro1jh1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('22', '���Ų����ܼ�', 'gro1cwzj', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('23', '�¼����쵼', 'singzj', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');

INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('24', '��ͨ������2', 'org2user1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('25', '��������2', 'org2cwjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('26', '�����ܾ���2', 'org2zjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('27', '�����ʽ��2', 'org2zj1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('28', '�������2', 'org2cbkj1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('29', 'ó������2', 'org2mynq1', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');
INSERT INTO P (ID, NACHN, NAME2, ICNUM, EMAIL, GESCH, TELNO, CELNO, BUKRS, KOSTL, DEFUNCT_IND) VALUES ('30', 'ó�׾���2', 'org2myjl', '3333333333333333333', 'chenlong@wcs-global.com', 'Ů', '02081181181', '18611111112', '001', '�ɱ�����', 'N');

insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('755', '2', '������', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('756', '2', '�����ܾ���', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('757', '2', '����������', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('758', '1', '������Ŀ����', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('759', '1', '�����ʽ�3', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('760', '2', '�����ʽ��λ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('761', '2', '�����ɱ����', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('762', '2', '��˾ó������ ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('763', '2', '��˾ó�׾��� ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('764', '1', '����Ʒ��ת�� ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('765', '1', '���Ż�Ʋ��� ', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('766', '1', '�����ʽ�1', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('767', '1', '�����ʽ�2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('768', '1', '�����ʽ�4', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('769', '1', '�����ʽ�5', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('770', '1', '�����ʽ���', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('771', '1', '�����ʽ��ܼ�', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('772', '1', '�����ʽ�ƻ�', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('773', '1', '���Ų����ܼ�', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('774', '1', '�¼����쵼', null, null, null, 'N');

insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('775', '3', '������2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('776', '3', '��������2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('777', '3', '�����ܾ���2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('778', '3', '�����ʽ��2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('779', '3', '�������2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('780', '3', 'ó������ 2', null, null, null, 'N');
insert into S (ID, OID, STEXT, KOSTL, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('781', '3', 'ó�׾��� 2', null, null, null, 'N');

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

insert into O (ID, BUKRS, STEXT, PARENT, KOSTL, ZHRZZCJID, ZHRZZDWID, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('1', '001', '�溣����', null, null, '', null, null, null, 'N');
insert into O (ID, BUKRS, STEXT, PARENT, KOSTL, ZHRZZCJID, ZHRZZDWID, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('2', '002', '�溣����ɶ�', '1', null, '', null, null, null, 'N');
insert into O (ID, BUKRS, STEXT, PARENT, KOSTL, ZHRZZCJID, ZHRZZDWID, ZHRTXXLID, ZHRTXXLMS, DEFUNCT_IND) values ('3', '003', '�溣�����Ϻ�', '1', null, '', null, null, null, 'N');
