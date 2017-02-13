package com.realaicy.prod.jc.common.monitor;

import com.realaicy.prod.jc.common.properties.StudyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

/**
 * Created by realaicy on 2017/2/12.
 * xxx
 */
@Component
@Order(value = 1)
public class StartupInfoCommandLineRunner implements CommandLineRunner {

    private ApplicationContext context;
    private StudyProperties studyProperties;
    private Logger logger =  LoggerFactory.getLogger(this.getClass());


    /**
     * Sets study properties.
     *
     * @param studyProperties the study properties
     */
    @Autowired
    public void setStudyProperties(StudyProperties studyProperties) {
        this.studyProperties = studyProperties;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("realaicy:-----------");
//        System.out.println("studyProperties: "+studyProperties.getName()+"||" + studyProperties.getAge());

        logger.debug("studyProperties : name = {}. age =  {}.", studyProperties.getName(), studyProperties.getAge());


        String[] beanNames = context.getBeanNamesForAnnotation(Repository.class);
        String[] beanNames2 = context.getBeanNamesForAnnotation(Controller.class);

        logger.debug("Repository注解beanNames个数: {}", beanNames.length);
        logger.debug("Controller注解beanNames个数: {}", beanNames2.length);

//        System.out.println("Repository注解beanNames个数：" + beanNames.length);
        for (String bn : beanNames) {
            logger.debug("beanname : {}", bn);
        }
        for (String bn2 : beanNames2) {
            logger.debug("beanname : {}", bn2);
        }
    }
}
