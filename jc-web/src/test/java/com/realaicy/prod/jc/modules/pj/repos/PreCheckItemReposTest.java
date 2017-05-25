package com.realaicy.prod.jc.modules.pj.repos;

import com.realaicy.prod.jc.modules.auditdb.model.PreCheckItem;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckItemRepos;
import com.realaicy.prod.jc.modules.auditdb.repos.PreCheckItemRunTimeRepos;
import com.realaicy.prod.jc.modules.pj.model.PreInspection;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.repos.UserRepos;
import com.realaicy.prod.jc.test.JcDataRootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by realaicy on 2017/3/29.
 * xx
 */
public class PreCheckItemReposTest extends JcDataRootTest {
    @Autowired
    private PreInspectionRepos preInspectionRepos;

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private PreCheckItemRepos preCheckItemRepos;

    @Autowired
    private PreCheckItemRunTimeRepos preCheckItemRunTimeRepos;

    @Rollback(false)
    @Test
    public void first(){
//        assertThat(preInspectionRepos.findByRegion("华北地区")).extracting("name")
//                .contains("天津市肿瘤医院").doesNotContain("上海肺科医院");

        PreInspection preInspection = new PreInspection();
        preInspection.setName("第一个预稽查");

        User leader =userRepos.findOne(BigInteger.valueOf(187));
        User user1 =userRepos.findOne(BigInteger.valueOf(188));
        User user2 =userRepos.findOne(BigInteger.valueOf(189));

        Set<User> userList = new HashSet<>();
        userList.add(user1);
        userList.add(user2);
        preInspection.setLeader(leader);
        preInspection.setUser(userList);

        PreCheckItem p1 =preCheckItemRepos.findOne(BigInteger.valueOf(1));
        PreCheckItem p2 =preCheckItemRepos.findOne(BigInteger.valueOf(12));
        PreCheckItem p3 =preCheckItemRepos.findOne(BigInteger.valueOf(13));
        PreCheckItem p4 =preCheckItemRepos.findOne(BigInteger.valueOf(14));
        PreCheckItem p5 =preCheckItemRepos.findOne(BigInteger.valueOf(15));


//
//        PreCheckItemRunTime preCheckItemRunTime = new PreCheckItemRunTime();
//        preCheckItemRunTime.setChecker(user1);
//        preCheckItemRunTime.setCheckitem(p1);
//        preCheckItemRunTime.setDeadline(new Date());
//        preCheckItemRunTime.setPreInspection(preInspection);
//        preCheckItemRunTimeRepos.save(preCheckItemRunTime);
//
//
//        PreCheckItemRunTime preCheckItemRunTime2 = new PreCheckItemRunTime();
//        preCheckItemRunTime2.setChecker(user1);
//        preCheckItemRunTime2.setCheckitem(p2);
//        preCheckItemRunTime2.setDeadline(new Date());
//        preCheckItemRunTime2.setPreInspection(preInspection);
//        preCheckItemRunTimeRepos.save(preCheckItemRunTime2);
//
//
//        PreCheckItemRunTime preCheckItemRunTime3 = new PreCheckItemRunTime();
//        preCheckItemRunTime3.setChecker(user1);
//        preCheckItemRunTime3.setCheckitem(p3);
//        preCheckItemRunTime3.setDeadline(new Date());
//        preCheckItemRunTime3.setPreInspection(preInspection);
//        preCheckItemRunTimeRepos.save(preCheckItemRunTime3);
//
//        PreCheckItemRunTime preCheckItemRunTime4 = new PreCheckItemRunTime();
//        preCheckItemRunTime4.setChecker(user1);
//        preCheckItemRunTime4.setCheckitem(p4);
//        preCheckItemRunTime4.setDeadline(new Date());
//        preCheckItemRunTime4.setPreInspection(preInspection);
//        preCheckItemRunTimeRepos.save(preCheckItemRunTime4);
//
//        PreCheckItemRunTime preCheckItemRunTime5 = new PreCheckItemRunTime();
//        preCheckItemRunTime5.setChecker(user1);
//        preCheckItemRunTime5.setCheckitem(p5);
//        preCheckItemRunTime5.setDeadline(new Date());
//        preCheckItemRunTime5.setPreInspection(preInspection);
//        preCheckItemRunTimeRepos.save(preCheckItemRunTime5);



        preInspectionRepos.save(preInspection);

    }



}