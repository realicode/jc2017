package com.realaicy.prod.jc.common.event.handler;

import com.realaicy.prod.jc.common.event.JCEventHandler;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.modules.system.repos.EventMsgTemRepos;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.uitl.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by realaicy on 2017/3/15.
 * xxx
 */
@Component
public class WX implements JCEventHandler {


    private final UserService userService;
    private final EventMsgTemRepos eventMsgTemRepos;

    @Autowired
    public WX(UserService userService, EventMsgTemRepos eventMsgTemRepos) {
        this.userService = userService;
        this.eventMsgTemRepos = eventMsgTemRepos;
    }

    @Override
    public void dowork(EventAction eventAction, String extMsg) {
        if (eventAction.getSubscriberID() != null) {
            User user = userService.findOne(eventAction.getSubscriberID());
            WxUtil.sentMsgToUser(eventMsgTemRepos.getOne(eventAction.getMsgTemID()).getName().replace("{$$$$}", user.getNickname()),
                    user.getWxUserID());
        }
    }
}
