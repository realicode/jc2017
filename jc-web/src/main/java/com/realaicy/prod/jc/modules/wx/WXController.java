package com.realaicy.prod.jc.modules.wx;

import com.realaicy.prod.jc.modules.wx.lib.AesException;
import com.realaicy.prod.jc.modules.wx.lib.WXBizMsgCrypt;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * The type Hello controller.
 */
@RestController
@RequestMapping("/wx")
public class WXController {

    /**
     * Index string.
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String hello(HttpServletRequest request) {

        String sToken = "uPRr6GaYYP6JkHFT";
        String sCorpID = "wx9605bfbe1e7c2f1e";
        String sEncodingAESKey = "WTdGKSTzsffjbSZCrRb15rsevY3vaPa7Qrta9IdggHd";

        WXBizMsgCrypt wxcpt = null;
        try {
            wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        } catch (AesException e) {
            e.printStackTrace();
        }

        String signature = request.getParameter("msg_signature");   // 微信加密签名
        String timestamp = request.getParameter("timestamp");   // 时间戳
        String nonce = request.getParameter("nonce");           // 随机数
        String sVerifyEchoStr = request.getParameter("echostr");       //
        String echostr;

        try {
            echostr = wxcpt.verifyURL(signature, timestamp,
                    nonce, sVerifyEchoStr);
            System.out.println("verifyurl echostr: " + echostr);
            // 验证URL成功，将sEchoStr返回
            // HttpUtils.SetResponse(sEchoStr);
            return echostr;
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/t2")
    public String processMsgTest() {
            return "ok";
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/test")
    public String processMsg(HttpServletRequest request, @RequestBody String postData) {

        String sToken = "uPRr6GaYYP6JkHFT";
        String sCorpID = "wx9605bfbe1e7c2f1e";
        String sEncodingAESKey = "WTdGKSTzsffjbSZCrRb15rsevY3vaPa7Qrta9IdggHd";
        WXBizMsgCrypt wxcpt = null;
        try {
            wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        } catch (AesException e) {
            e.printStackTrace();
        }

        String sMsgSignature = request.getParameter("msg_signature");   // 微信加密签名
        String sTimeStamp = request.getParameter("timestamp");   // 时间戳
        String sNonce = request.getParameter("nonce");           // 随机数

        try {
            String sMsg = wxcpt.decryptMsg(sMsgSignature, sTimeStamp, sNonce, postData);
            System.out.println("after decrypt msg: " + sMsg);
            // : 解析出明文xml标签的内容进行处理
            // For example:
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(sMsg);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Content");
            String content = nodelist1.item(0).getTextContent();
            System.out.println("Content：" + content);

        } catch (Exception e) {
            // TODO
            // 解密失败，失败原因请查看异常
            e.printStackTrace();
        }
        return "OK";
    }
}