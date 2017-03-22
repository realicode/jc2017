package com.realaicy.prod.jc.test;

import org.junit.Test;

/**
 * Created by realaicy on 2017/3/20.
 * xxs
 */
public class TempTest {

    @Test
    public void atemp(){
        String str="尊敬的 $$$$ 秘书长：有一个新的稽查项目(@@@@)申请已经提交，请尽快登录系统进行确认";
        String tmp1 = str.replace("$$$$", "王雨萌");
        System.out.println(tmp1);
        System.out.println(tmp1.replace("@@@@","项目1"));
    }
}
