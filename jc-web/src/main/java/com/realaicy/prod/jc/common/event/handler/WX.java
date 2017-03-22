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


    private final EventMsgTemRepos eventMsgTemRepos;
    private UserService userService;


    @Autowired
    public WX(UserService userService, EventMsgTemRepos eventMsgTemRepos) {
        this.eventMsgTemRepos = eventMsgTemRepos;
        this.userService = userService;
    }

    @Override
    public void dowork(EventAction eventAction, String extMsg) {
        if (eventAction.getSubscriberID() != null) {
            User user = userService.findOne(eventAction.getSubscriberID());
            String tmp1 = eventMsgTemRepos.getOne(eventAction.getMsgTemID()).getName().replace("$$$$", user.getUserinfo().getNickname());
            String tmp2 = tmp1.replace("@@@@", extMsg);
            WxUtil.sentMsgToUser(tmp2, user.getUserinfo().getWxuserid());

        }
    }
}
