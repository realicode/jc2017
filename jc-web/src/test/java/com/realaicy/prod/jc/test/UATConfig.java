package com.realaicy.prod.jc.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by realaicy on 2017/3/19.
 * xxx
 */
public class UATConfig {

    @Autowired
    private Environment env;

    @Bean
    public WebDriver webDriver() throws MalformedURLException {
        return new RemoteWebDriver(getRemoteUrl(), getDesiredCapabilities());
    }

    private DesiredCapabilities getDesiredCapabilities() {
        return DesiredCapabilities.firefox();
    }

    private URL getRemoteUrl() throws MalformedURLException {
        return new URL("http://localhost:4445/wd/hub");
    }
}
