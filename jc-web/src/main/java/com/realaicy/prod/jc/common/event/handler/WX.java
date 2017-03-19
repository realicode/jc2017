package com.realaicy.prod.jc.common.event.handler;

import com.realaicy.prod.jc.common.event.JCEventHandler;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.model.UserInfo;
import com.realaicy.prod.jc.modules.system.repos.EventMsgTemRepos;
import com.realaicy.prod.jc.modules.system.service.UserInfoService;
import com.realaicy.prod.jc.uitl.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by realaicy on 2017/3/15.
 * xxx
 */
@Component
public class WX implements JCEventHandler {


    private final UserInfoService userInfoService;
    private final EventMsgTemRepos eventMsgTemRepos;

    @Autowired
    public WX(UserInfoService userInfoService, EventMsgTemRepos eventMsgTemRepos) {
        this.userInfoService = userInfoService;
        this.eventMsgTemRepos = eventMsgTemRepos;
    }

    @Override
    public void dowork(EventAction eventAction, String extMsg) {
        if (eventAction.getSubscriberID() != null) {
            UserInfo userInfo = userInfoService.findOne(eventAction.getSubscriberID());
            WxUtil.sentMsgToUser(eventMsgTemRepos.getOne(eventAction.getMsgTemID()).getName().replace("{$$$$}", userInfo.getNickname()),
                    userInfo.getWxUserID());
        }
    }
}
