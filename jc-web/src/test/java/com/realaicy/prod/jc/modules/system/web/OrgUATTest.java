package com.realaicy.prod.jc.modules.system.web;

import com.realaicy.prod.jc.JcUATTestContext;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JcUATTestContext.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(StaticParams.SPRINGPROFILES.TEST_UAT)
@Ignore
public  class OrgUATTest{

    @SuppressWarnings("unused")
    @LocalServerPort
    private int port;

    @Autowired
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void  aaa(){
        driver.get("http://127.0.0.1:"+port+"/login");
//        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("username")).sendKeys("realaicy");
        driver.findElement(By.id("password")).sendKeys("11111111");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("real_submit")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
