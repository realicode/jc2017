package com.realaicy.prod.jc.modules.pj.service;

import com.realaicy.prod.jc.common.event.ConfirmEvent;
import com.realaicy.prod.jc.common.event.CreationEvent;
import com.realaicy.prod.jc.common.event.JCBaseEvent;
import com.realaicy.prod.jc.common.event.handler.WX;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.repos.EventMsgTemRepos;
import com.realaicy.prod.jc.modules.system.service.EventActionService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
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
public class ApplianceListener {

    private final EventActionService eventActionService;
    private final WX wxservice;
    private final UserService userService;
    private final MyWorkService myWorkService;
    private final ApplianceService applianceService;
    private final EventMsgTemRepos eventMsgTemRepos;


    @Autowired
    public ApplianceListener(EventActionService eventActionService, WX wxservice,
                             UserService userService, MyWorkService myWorkService,
                             ApplianceService applianceService,
                             EventMsgTemRepos eventMsgTemRepos) {
        this.eventActionService = eventActionService;
        this.wxservice = wxservice;
        this.userService = userService;
        this.myWorkService = myWorkService;
        this.applianceService = applianceService;
        this.eventMsgTemRepos = eventMsgTemRepos;
    }

    @EventListener
    public void handleApplicationCreatedEvent(CreationEvent<BigInteger, Appliance> applicationCreationEvent) {
        System.out.println("handleApplicationCreatedEvent");
        String applicationName = applianceService.findOne(applicationCreationEvent.getCreatedEntityID()).getName();


        for (EventAction ea : eventActionService.findByName(((JCBaseEvent) applicationCreationEvent).getEventKey())) {
            if (ea.getEventAction().equals("WX")) {
                wxservice.dowork(ea, applicationName);
            }

            if (ea.getEventAction().equals("WORK")) {
                String str = eventMsgTemRepos.getOne(ea.getMsgTemID()).getName();

                MyWork work = new MyWork();
                work.setUser(userService.findByUsername(StaticParams.TODOWORK.USER_SECRETARY_WYM));
                work.setCreaterID(BigInteger.ONE);
                work.setUpdaterID(work.getCreaterID());
                work.setCreateTime(new Date());
                work.setUpdateTime(work.getCreateTime());

                String tmp1 = str.replace("$$$$", applicationName);
                work.setMainTitle(tmp1.split("###")[1]);
                work.setSubTitle(tmp1.split("###")[0]);
                work.setName(work.getSubTitle());
                work.setDeadline(Date.from(LocalDateTime.now().plusDays(2L).atZone(ZoneId.systemDefault()).toInstant()));
                work.setWorkLevel(Short.valueOf("5"));
                work.setWorkType("work");
                work.setStatus(Short.valueOf("1"));

                work.setWorkUri("/pj/apply/confirm?realactiontype=affirm&applyid=" + applicationCreationEvent.getCreatedEntityID());
                myWorkService.save(work);

            }

        }
    }

    @EventListener
    public void handleApplicationConfirmedEvent(ConfirmEvent<BigInteger, Appliance> applicationConfirmEvent) {
        System.out.println("handleApplicationCreatedEvent");

        String applicationName = applianceService.findOne(applicationConfirmEvent.getConfirmedEntityID()).getName();


        for (EventAction ea : eventActionService.findByName(((JCBaseEvent) applicationConfirmEvent).getEventKey())) {
            if (ea.getEventAction().equals("WX")) {
                wxservice.dowork(ea, applicationName);
            }

            if (ea.getEventAction().equals("WORK")) {
                String str = eventMsgTemRepos.getOne(ea.getMsgTemID()).getName();

                MyWork work = new MyWork();
                work.setUser(userService.findByUsername(StaticParams.TODOWORK.USER_DIRECTOR));
                work.setCreaterID(BigInteger.ONE);
                work.setUpdaterID(work.getCreaterID());
                work.setCreateTime(new Date());
                work.setUpdateTime(work.getCreateTime());
                String tmp1 = str.replace("$$$$", applicationName);
                work.setMainTitle(tmp1.split("###")[1]);
                work.setSubTitle(tmp1.split("###")[0]);
                work.setName(work.getSubTitle());

                work.setDeadline(Date.from(LocalDateTime.now().plusDays(2L).atZone(ZoneId.systemDefault()).toInstant()));
                work.setWorkLevel(Short.valueOf("5"));
                work.setWorkType("work");
                work.setStatus(Short.valueOf("1"));

                work.setWorkUri("/pj/apply/confirm?realactiontype=approve&applyid=" + applicationConfirmEvent.getConfirmedEntityID());
                myWorkService.save(work);

            }

        }
    }
}
