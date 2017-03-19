

-- ----------------------------
--  Records of `jc_sys_org`
-- ----------------------------
BEGIN;
-- INSERT INTO jc_sys_org VALUES ('10001', '4', 'h2_测试机构', 'h2_测试机构Alias', null, '全国分布', '天津', null, '刘旭东', null, null, '1', null, '2017-03-05 16:01:59', '1', '2017-03-12 17:15:34', '1', 0)

insert into jc_sys_org (id, createtime, createrid, custom_code, status, updatetime, updaterid, f_deleted, org_address, contact_name, org_name, alias1, alias2, org_province, org_region, org_type) values (null, '2017-03-05 16:01:59', '6', '0', '1', '2017-03-12 17:15:34', '6', 0, '', '刘旭东', 'h2_测试机构_用于创建异常', 'h2_测试机构Alias', '', '天津', '全国分布', '4')
insert into jc_sys_org (id, createtime, createrid, custom_code, status, updatetime, updaterid, f_deleted, org_address, contact_name, org_name, alias1, alias2, org_province, org_region, org_type) values (null, '2017-03-05 16:01:59', '6', '0', '1', '2017-03-12 17:15:34', '6', 0, '', '用于编辑', 'h2_测试机构_用于编辑', 'h2_测试机构Alias', '', '天津', '全国分布', '4')
insert into jc_sys_org (id, createtime, createrid, custom_code, status, updatetime, updaterid, f_deleted, org_address, contact_name, org_name, alias1, alias2, org_province, org_region, org_type) values (null, '2017-03-05 16:01:59', '6', '0', '1', '2017-03-12 17:15:34', '6', 0, '', '用于删除', 'h2_测试机构_用于删除', 'h2_测试机构Alias', '', '天津', '全国分布', '4')
insert into jc_sys_org (id, createtime, createrid, custom_code, status, updatetime, updaterid, f_deleted, org_address, contact_name, org_name, alias1, alias2, org_province, org_region, org_type) values (null, '2017-03-05 16:01:59', '6', '0', '1', '2017-03-12 17:15:34', '6', 0, '', '用于查询', 'h2_测试机构_用于查询', 'h2_测试机构Alias', '', '天津', '全国分布', '4')

COMMIT;
SET FOREIGN_KEY_CHECKS = 1;
