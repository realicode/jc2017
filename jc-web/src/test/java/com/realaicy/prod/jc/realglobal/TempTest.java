package com.realaicy.prod.jc.realglobal;

import com.realaicy.prod.jc.modules.wx.model.msg.RealMsgContent;
import com.realaicy.prod.jc.modules.wx.model.msg.WxMsgResp;
import com.realaicy.prod.jc.modules.wx.model.RealTK;
import com.realaicy.prod.jc.modules.wx.model.msg.WxMsg;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Created by realaicy on 2017/3/13.
 * xxx
 */
public class TempTest {


    @Test
    public void testRealWX(){
        RestTemplate restTemplate = new RestTemplate();
        RealTK realTK = restTemplate.getForObject(
                "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx9605bfbe1e7c2f1e&corpsecret=nm4gMa4Ri3wQDOttvmxU7TaIILeM7M7SYvNiASmmx5e_Trg6_4g3pTYOxM6A54k2",
                RealTK.class);
        System.out.println(realTK.toString());
    }

    @Test
    public void testRealWXPost(){
        RestTemplate restTemplate = new RestTemplate();
        WxMsg realms = new WxMsg();
        RealMsgContent content= new RealMsgContent();
        content.setContent("测试哈哈哈哈");
        realms.setAgentid(2);
        realms.setMsgtype("text");
        realms.setTouser("XudongLiu");
        realms.setText(content);


        WxMsgResp wxMsgResp = restTemplate.postForObject("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=LPLvQrKhNU1-7EWl511c0b3qI-h9kFIZeC1bGKryaMuUrmP13TBUazUD58Vu1Bbr",
                realms,WxMsgResp.class );
        System.out.println(wxMsgResp.toString());
    }

}
