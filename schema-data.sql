insert into dict (id, code, defunct_ind, name, parent_code, value) values (1, 'SEX', false, '性别', null, null);
insert into dict (id, code, defunct_ind, name, parent_code, value) values (2, 'SEX001', false, '男', 'SEX', null);
insert into dict (id, code, defunct_ind, name, parent_code, value) values (3, 'SEX002', false, '女', 'SEX', null);
insert into dict (id, code, defunct_ind, name, parent_code, value) values (4, 'PROT', false, '商品类型', null, null);
insert into dict (id, code, defunct_ind, name, parent_code, value) values (5, 'PROT001', false, '服装', 'PROT', null);
insert into dict (id, code, defunct_ind, name, parent_code, value) values (6, 'PROT002', false, '食品', 'PROT', null);

insert into sequence (seq_name, seq_count) values ('SEQ_GEN', 0);

insert into users (id, email, loginname, name, password) values (1, 'Jo@test.com', 'admin', 'Jo yu', 'admin');
insert into users (id, email, loginname, name, password) values (2, 'Jo@test.com', 'user', 'Jo Yu', 'user');

insert into role (id, name) values (1, 'admin');
insert into role (id, name) values (2, 'user');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);

insert into permission (id, roleid, permission) values (1, 1, 'user_userManage');
insert into permission (id, roleid, permission) values (2, 1, 'user_roleManage');
insert into permission (id, roleid, permission) values (3, 1, 'user_resourceManage');
insert into permission (id, roleid, permission) values (4, 1, 'user_systemManage');
insert into permission (id, roleid, permission) values (5, 1, 'user_permissionManage');
insert into permission (id, roleid, permission) values (6, 2, 'user_userManage');

insert into resource (id, is_leaf, is_menu, key_name, level, name, number, parent_id, url) values (1, false, true, 'user_menu', 0, '菜单', 'M1', 0, '');
insert into resource (id, is_leaf, is_menu, key_name, level, name, number, parent_id, url) values (2, false, true, 'user_systemManage', 1, '系统管理', 'M2', 1, '');
insert into resource (id, is_leaf, is_menu, key_name, level, name, number, parent_id, url) values (3, false, true, 'user_permissionManage', 2, '权限管理', 'M201', 2, '');
insert into resource (id, is_leaf, is_menu, key_name, level, name, number, parent_id, url) values (4, true, true, 'user_userManage', 3, '用户管理', 'M20101', 3, '/faces/permissions/user/list.xhtml');
insert into resource (id, is_leaf, is_menu, key_name, level, name, number, parent_id, url) values (5, true, true, 'user_roleManage', 3, '角色管理', 'M20102', 3, '/faces/permissions/role/list.xhtml');
insert into resource (id, is_leaf, is_menu, key_name, level, name, number, parent_id, url) values (6, true, true, 'user_resourceManage', 3, '资源管理', 'M10103', 3, '/faces/permissions/resource/list.xhtml');

