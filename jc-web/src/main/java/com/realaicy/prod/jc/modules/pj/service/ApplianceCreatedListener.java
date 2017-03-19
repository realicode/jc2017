package com.realaicy.prod.jc.modules.pj.service;

import com.realaicy.prod.jc.common.event.CreationEvent;
import com.realaicy.prod.jc.common.event.JCBaseEvent;
import com.realaicy.prod.jc.common.event.handler.WX;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.service.EventActionService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by realaicy on 2017/3/15.
 * <p>
 * xxx
 */
@Component
public class ApplianceCreatedListener {

    private final EventActionService eventActionService;
    private final WX wxservice;
    private final UserService userService;

    @Autowired
    public ApplianceCreatedListener(EventActionService eventActionService, WX wxservice, UserService userService) {
        this.eventActionService = eventActionService;
        this.wxservice = wxservice;
        this.userService = userService;
    }

    @EventListener
    public void handleApplicationCreatedEvent(CreationEvent<BigInteger, Appliance> applicationCreationEvent) {
        System.out.println("handleApplicationCreatedEvent");


        for (EventAction ea : eventActionService.findByName(((JCBaseEvent) applicationCreationEvent).getEventKey())) {
            if (ea.getEventAction().equals("WX")) {
                wxservice.dowork(ea, SpringSecurityUtil.getNameOfCurrentPrincipal());
            }

            if (ea.getEventAction().equals("WORK")) {

                MyWork  work = new MyWork();
                work.setUser(userService.findByUsername(StaticParams.TODOWORK.userSecretaryWym));
                work.setCreaterID(BigInteger.ONE);
                work.setUpdaterID(work.getCreaterID());
                work.setCreateTime(new Date());
                work.setUpdateTime(work.getCreateTime());
                work.setName("");
                work.setMainTitle("");
                work.setSubTitle("");
                work.setDeadline(Date.from(LocalDateTime.now().plusDays(2L).atZone(ZoneId.systemDefault()).toInstant()));
                work.setWorkLevel(Short.valueOf("5"));
                work.setWorkType("work");

                work.setWorkUri("/pj/apply/confirm?realactiontype=affirm&applyid="+applicationCreationEvent.getCreatedEntityID());

            }

        }
    }
}
