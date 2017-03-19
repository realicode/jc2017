package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.modules.system.model.Org;
import com.realaicy.prod.jc.test.JcDataBasicRootTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigInteger;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


//@Sql("real-db-m-test.sql")
public class OrgReposBasisTest  extends JcDataBasicRootTest {

    @Autowired
    private OrgRepos orgRepos;

    private static Org orgToBeInsert = new Org();

    //    @Sql("real-db-m-test.sql")
    @BeforeClass
    public static void setupClass() {
        System.out.println("setupClass");
        orgToBeInsert.setName("h2_测试机构_用于创建");
        orgToBeInsert.setType("6");
        orgToBeInsert.setNameAlias1("1");
        orgToBeInsert.setRegion("1");
        orgToBeInsert.setRegion("1");
        orgToBeInsert.setProvince("1");
        orgToBeInsert.setContactName("刘旭东");
        orgToBeInsert.setStatus(Short.valueOf("1"));
        orgToBeInsert.setDeleteFlag(false);
        orgToBeInsert.setCreaterID(BigInteger.valueOf(6L));
        orgToBeInsert.setUpdaterID(BigInteger.valueOf(6L));
        orgToBeInsert.setCreateTime(new Date());
        orgToBeInsert.setUpdateTime(new Date());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createOrgReturnErrorTest() {
        Org org = new Org();
        org.setName("h2_测试机构_用于创建异常");
        org.setType("6");
        org.setNameAlias1("1");
        org.setRegion("1");
        org.setRegion("1");
        org.setProvince("1");
        org.setContactName("1");
        org.setStatus(Short.valueOf("1"));
        org.setDeleteFlag(false);
        org.setCreaterID(BigInteger.valueOf(6L));
        org.setUpdaterID(BigInteger.valueOf(6L));
        org.setCreateTime(new Date());
        org.setUpdateTime(new Date());
        try {
            orgRepos.save(org);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DataIntegrityViolationException.class);
            throw e;
        }
    }


    @Test
    public void createOrgTest() {
        assertThat(orgRepos.findByName("h2_测试机构_用于创建")).isNull();
        orgRepos.save(orgToBeInsert);
        assertThat(orgRepos.findByName("h2_测试机构_用于创建")).isNotNull();
    }

    @Test
    public void deleteOrgTest() {
        assertThat(orgRepos.findByName("h2_测试机构_用于删除")).isNotNull();
        orgRepos.delete(orgRepos.findByName("h2_测试机构_用于删除"));
        assertThat(orgRepos.findByName("h2_测试机构_用于删除")).isNull();
    }

    @Test
    public void editOrgTest() {
        assertThat(orgRepos.findByName("h2_测试机构_用于编辑").getContactName()).isEqualTo("用于编辑");
        Org orgToBeEdit = orgRepos.findByName("h2_测试机构_用于编辑");
        orgToBeEdit.setContactName("修改者");
        orgRepos.save(orgToBeEdit);
        assertThat(orgRepos.findByName("h2_测试机构_用于编辑").getContactName()).isEqualTo("修改者");
    }

    @Test
    public void findOrgTest() {
        orgRepos.countByName("h2_测试机构_用于查询");
        assertThat(orgRepos.findByName("h2_测试机构_用于查询").getContactName()).isEqualTo("用于查询");
    }

    @Test
    public void findByRegionTest() {
        assertThat(orgRepos.findByRegion("全国分布").size()).isEqualTo(4);
    }

    @Test
    public void findByProvinceTest() {
        assertThat(orgRepos.findByProvince("天津").size()).isEqualTo(4);
    }

    @Test
    public void countByNameTest() {
        orgRepos.countByName("h2_测试机构_用于查询");
        assertThat(orgRepos.countByName("h2_测试机构_用于查询")).isEqualTo(1);
    }

}