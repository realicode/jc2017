package com.realaicy.prod.jc.temp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Hello controller.
 */
@RestController
public class HelloController {

    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot! This is 刘旭东!";
    }

}