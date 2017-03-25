package com.realaicy.prod.jc.modules.me.service.impl;

import com.realaicy.prod.jc.common.event.ConfirmEvent;
import com.realaicy.prod.jc.common.event.handler.WX;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.pj.service.ApplianceService;
import com.realaicy.prod.jc.modules.system.service.EventActionService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Created by realaicy on 2017/3/15.
 * <p>
 * xxx
 */
@Component
public class MyWorkListener {

    private final EventActionService eventActionService;
    private final WX wxservice;
    private final UserService userService;
    private final MyWorkService myWorkService;
    private final ApplianceService applianceService;


    @Autowired
    public MyWorkListener(EventActionService eventActionService, WX wxservice,
                          UserService userService, MyWorkService myWorkService,
                          ApplianceService applianceService
                          ) {
        this.eventActionService = eventActionService;
        this.wxservice = wxservice;
        this.userService = userService;
        this.myWorkService = myWorkService;
        this.applianceService = applianceService;
    }


    @EventListener
    public void handleMyworkDownEvent(ConfirmEvent<BigInteger, Appliance> applicationConfirmEvent) {

    }

}
