INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 14, 'Wilmar TMS', 'tms', '1', 0, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 15, '����', 'tms:process', '1', 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 16, '����������', 'tms:process:new', '1', 15, 'MENU', '/faces/process/common/processNew-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 17, '���ύ������', 'tms:process:subed', '2', 15, 'MENU', '/faces/process/common/processSubed-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 18, '�Ѵ��������', 'tms:process:dealed', '3', 15, 'MENU', '/faces/process/common/processDealed-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 19, '�����յ�����', 'tms:process:wait', '4', 15, 'MENU', '/faces/process/common/processWait-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 20, '�ѽ��յ�����', 'tms:process:acced', '5', 15, 'MENU', '/faces/process/common/processAccept-list.xhtml') ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 21, '����', 'tms:report', '2', 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 22, '��ծ������ϸ��', 'tms:report:debt', '1', 21, 'MENU', '/faces/report/debtBorrow/debtBorrow-detail.xhtml') ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 23, 'ϵͳ����', 'tms:system', '3', 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 24, '�û�����', 'tms:system:user', '1', 23, 'MENU', '/faces/common/user/index.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 25, '��ɫ����', 'tms:system:role', '2', 23, 'MENU', '/faces/common/role/index.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 26, '�ֵ�����', 'tms:system:dict', '3', 23, 'MENU', '/faces/common/dict/index.xhtml' ) ;

INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 27, '�����ݹ���', 'tms:main', '4', 14, 'MENU', '') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 28, '������Ϣ����', 'tms:main:bank', '1', 27, 'MENU', '/faces/system/bankManage/bankManage-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 29, '��˾��Ϣ����', 'tms:main:company', '2', 27, 'MENU', '/faces/system/companyManage/comManage-list.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 30, 'ϵͳ���̹���', 'tms:main:process', '3', 27, 'MENU', '/faces/system/systemProcess/systemProcess-list.xhtml') ;

--7.16 �µ�2������
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 31, 'Ͷ����Ʋ�Ʒ�����ϸ��', 'tms:report:invest', '2', 21, 'MENU', '/faces/report/inveProduct/inveProduct-detail.xhtml') ;
INSERT INTO RESOURCE ( ID, NAME, CODE, SEQ_NO, PARENT_ID, TYPE, URI) VALUES ( 32, '���Ŷ����ϸ��', 'tms:report:credit', '3', 21, 'MENU', '/faces/report/bankCredit/bankCredit-detail.xhtml') ;
