package com.realaicy.prod.jc.uitl;

import com.realaicy.prod.jc.modules.wx.model.RealTK;
import com.realaicy.prod.jc.modules.wx.model.UserInfoResponse;
import com.realaicy.prod.jc.modules.wx.model.msg.RealMsgContent;
import com.realaicy.prod.jc.modules.wx.model.msg.WxMsgResp;
import com.realaicy.prod.jc.modules.wx.model.msg.WxMsg;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * Created by realaicy on 2017/3/14.
 * <p>
 * xx
 */
public class WxUtil {

    private static final String SENTMSGURI = "https://qyapi.weixin.qq.com/cgi-bin/message/send?";

    private static final String GETUSERINFOURI =
            "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=";

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private static String wxToken = "";
    private static LocalDateTime wxTokenExpire;

    public static void setWxTokenExpire(LocalDateTime wxTokenExpire) {
        WxUtil.wxTokenExpire = wxTokenExpire;
    }

    public static String getWxToken() {

        if (LocalDateTime.now().isAfter(wxTokenExpire)) {
            RealTK realTK = getRestTemplate().getForObject(
                    "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx9605bfbe1e7c2f1e"
                            + "&corpsecret=nm4gMa4Ri3wQDOttvmxU7TaIILeM7M7SYvNiASmmx5e_Trg6_4g3pTYOxM6A54k2",
                    RealTK.class);
            if (realTK.getErrmsg().equals("")) {
                wxToken = realTK.getAccessToken();
                wxTokenExpire = LocalDateTime.now().plusMinutes(110);
            }

        }
        return wxToken;
    }

    public static void setWxToken(String wxToken) {
        WxUtil.wxToken = wxToken;
    }

    public static UserInfoResponse getUserInfoFromCode(String code) {
        return WxUtil.getRestTemplate().getForObject(
                GETUSERINFOURI + WxUtil.getWxToken() + "&code=" + code + "&agentid=2",
                UserInfoResponse.class);
    }


    public static RestTemplate getRestTemplate() {
        return REST_TEMPLATE;
    }

    public static void sentMsgToUser(String msg, String userID) {

        RealMsgContent content = new RealMsgContent();
        content.setContent(msg);

        WxMsg wxMsg = new WxMsg();
        wxMsg.setAgentid(2);
        wxMsg.setMsgtype("text");
        wxMsg.setTouser(userID);
        wxMsg.setText(content);

        WxMsgResp wxMsgResp = getRestTemplate().postForObject(SENTMSGURI + "access_token=" + wxToken, wxMsg, WxMsgResp.class);

    }

}


