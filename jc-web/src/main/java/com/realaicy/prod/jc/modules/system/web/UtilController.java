package com.realaicy.prod.jc.modules.system.web;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.realaicy.prod.jc.uitl.NetUtil;
import com.realaicy.prod.jc.uitl.OtherUtil;
import com.realaicy.prod.jc.uitl.RealCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.SMS.*;

/**
 * Created by realaicy on 16/7/15.
 * 登录
 */

@Controller
public class UtilController {

    private static Logger log = LoggerFactory.getLogger(UtilController.class);

    private static IAcsClient client;

    static {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIxg9cV6LBS4BK", "LBctkeTpFWEWrrxtGwnAu76VjXuZLn");
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        client = new DefaultAcsClient(profile);
    }


    @ResponseBody
    @RequestMapping(value = "/sms/signup/sentcode", method = RequestMethod.GET)
    public String sentCodeToMobile(@RequestParam(value = "mobilenum") String mobilenum,
                                   HttpServletRequest request) {

        String clientIP = NetUtil.getClientIpAddress(request);
        LocalDate nowDate = LocalDate.now();
        if (RealCacheUtil.IP_BLACK_LIST.contains(clientIP) || RealCacheUtil.SMS_BLACK_LIST.contains(mobilenum)) {
            return "ERROR";
        } else if (RealCacheUtil.IP_WHITE_LIST.contains(clientIP)) {

            return doSent(mobilenum, clientIP, nowDate);

        } else if (!RealCacheUtil.IPADRESS_CANSENT_TABLE.contains(clientIP, nowDate)
                || RealCacheUtil.IPADRESS_CANSENT_TABLE.get(clientIP, nowDate) < MAX_SENTCODE_PER_IP_INONEDAY) {
            // IPTable中没有约束 或者 IPTable中有约束但是没有超过阀值 （总之，IP没有问题。。。）
            if (!RealCacheUtil.MOBILENUMBER_CANSENT_TABLE.contains(mobilenum, nowDate)
                    || RealCacheUtil.MOBILENUMBER_CANSENT_TABLE.get(mobilenum, nowDate) < MAX_SENTCODE_PER_MOBILE_INONEDAY) {
                // mobile table中没有约束 或者 mobile Table中有约束但是没有超过阀值 （总之，mobile没有问题。。。）
                return doSent(mobilenum, clientIP, nowDate);
            }
        }
        return "ERROR";
    }

    private String doSent(String mobilenum, String clientIP, LocalDate nowDate) {

        String code = OtherUtil.getSixRandSMSCode();

        SingleSendSmsRequest request = new SingleSendSmsRequest();
        try {
            request.setSignName("测试专用");
            request.setTemplateCode("SMS_43085007");
            request.setParamString("{\"code\":\"" + code + "\"}");
            request.setRecNum(mobilenum);
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            return "Error:";
        }

        log.info("IP:{} Sent {} to {}", clientIP, code, mobilenum);

        RealCacheUtil.SMS_CODE_MAP.put(mobilenum, OtherUtil.getSixRandSMSCode(),
                LocalDateTime.now().plusMinutes(MAX_MIN_BETWEEN_SENTANDRECIVE));

        if (RealCacheUtil.IPADRESS_CANSENT_TABLE.get(clientIP, nowDate) != null) {
            RealCacheUtil.IPADRESS_CANSENT_TABLE.put(clientIP, nowDate,
                    RealCacheUtil.IPADRESS_CANSENT_TABLE.get(clientIP, nowDate) + 1);
        } else {
            RealCacheUtil.IPADRESS_CANSENT_TABLE.put(clientIP, nowDate, 1);
        }

        if (RealCacheUtil.MOBILENUMBER_CANSENT_TABLE.get(mobilenum, nowDate) != null) {
            RealCacheUtil.MOBILENUMBER_CANSENT_TABLE.put(mobilenum, nowDate,
                    RealCacheUtil.MOBILENUMBER_CANSENT_TABLE.get(mobilenum, nowDate) + 1);
        } else {
            RealCacheUtil.MOBILENUMBER_CANSENT_TABLE.put(mobilenum, nowDate, 1);
        }

        RealCacheUtil.SMS_CODE_MAP.put(mobilenum, code, LocalDateTime.now());

        return "OK";
    }
}