package com.realaicy.prod.jc.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by realaicy on 2017/2/12.
 * <p>
 * 在application.properties配置的属性前缀，
 * <p>
 * 在类中的属性就不用使用{@value}进行注入了。
 *
 * @author Realaicy
 * @version v.0.1
 */
@ConfigurationProperties(prefix = "com.realaicy.jc.study")
public class StudyProperties {

    private String name;

    private String age;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(String age) {
        this.age = age;
    }
}
