package com.realaicy.prod.jc.realglobal.realerror;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/g/realerror")
public class RealErrorController {

    @Value("${loginurl}")
    private String loginUrl;


    @RequestMapping("/session/expire")
    @ResponseBody
    public String sessionExpire() {
        System.out.println("");
        return "expire";
    }

    @RequestMapping("/session/invalid")
    @ResponseBody
    public String sessionInvalid(@RequestHeader(value = "User-Agent") String userAgent) {
        System.out.println("userAgent: " + userAgent);
        return "invalid";
    }


    @RequestMapping("/session/realinvalid")
    public ResponseEntity<?> sessionInvalidV2(@RequestHeader(value = "User-Agent") String userAgent,
                                              @RequestHeader(value = "x-requested-with",
                                                      required = false) String ifAjax) {

        System.out.println("Realaicy userAgent V2: " + userAgent);

        if (ifAjax != null && ifAjax.equals("XMLHttpRequest")) {
            System.out.println("Realaicy userAgent V2: " + "Ajax");
            return new ResponseEntity<>("invalid", HttpStatus.OK);

        } else {
            System.out.println("Realaicy userAgent V2: " + "normal");
            HttpHeaders headers = new HttpHeaders();
            //headers.add("Location", "http://127.0.0.1:48080/");
            if (userAgent.contains("mobile")) {
                headers.add("Location", "http://www.baidu.com/");
                return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
            }
            headers.add("Location", loginUrl);

            return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
        }
    }

    @RequestMapping("/session/expire2")
    public ResponseEntity<?> fetchWellData() {
        return new ResponseEntity<>("real error haha", HttpStatus.FORBIDDEN);
    }
}
