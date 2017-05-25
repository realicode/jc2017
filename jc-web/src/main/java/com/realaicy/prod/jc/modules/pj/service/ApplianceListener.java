package com.realaicy.prod.jc.modules.pj.service;

import com.realaicy.prod.jc.common.event.*;
import com.realaicy.prod.jc.common.event.handler.WX;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.pj.model.ProjectFacade;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.repos.EventMsgTemRepos;
import com.realaicy.prod.jc.modules.system.service.EventActionService;
import com.realaicy.prod.jc.modules.system.service.UserService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.realaicy.prod.jc.realglobal.config.StaticParams.TODOWORK.*;

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
    private final ProjectFacadeService projectFacadeService;
    private final MyWorkService myWorkService;
    private final ApplianceService applianceService;
    private final EventMsgTemRepos eventMsgTemRepos;

    private static Logger logger = LoggerFactory.getLogger(ApplianceListener.class);


    @Autowired
    public ApplianceListener(EventActionService eventActionService, WX wxservice,
                             UserService userService, ProjectFacadeService projectFacadeService, MyWorkService myWorkService,
                             ApplianceService applianceService,
                             EventMsgTemRepos eventMsgTemRepos) {
        this.eventActionService = eventActionService;
        this.wxservice = wxservice;
        this.userService = userService;
        this.projectFacadeService = projectFacadeService;
        this.myWorkService = myWorkService;
        this.applianceService = applianceService;
        this.eventMsgTemRepos = eventMsgTemRepos;
    }

    @EventListener
    public void handleApplicationCreatedEvent(CreationEvent<BigInteger, Appliance> applicationCreationEvent) {
        logger.debug("handleApplicationConfirmDenyEvent");
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
                work.setViewUri("/pj/apply/show/" + applicationCreationEvent.getCreatedEntityID());
                myWorkService.save(work);

            }
        }
    }

    @EventListener
    public void handleApplicationConfirmDenyEvent(ConfirmEvent<BigInteger, Appliance> applicationConfirmEvent) {
        logger.debug("handleApplicationConfirmDenyEvent: {}", applicationConfirmEvent);
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
                work.setViewUri("/pj/apply/show/" + applicationConfirmEvent.getConfirmedEntityID());
                myWorkService.save(work);

            }

        }
    }


    @EventListener
    public void handleApplicationApprovedEvent(ApproveEvent<BigInteger, Appliance> applicationApprovedEvent) {
        logger.debug("handleApplicationApprovedEvent");

        String applicationName = applianceService.findOne(applicationApprovedEvent.getApproveEntityID()).getName();

        handelDefault(((JCBaseEvent) applicationApprovedEvent).getEventKey(), applicationName,
                APPLY_PROVIDECONTRACT_WORKURI, APPLY_PROVIDECONTRACT_VIEWURI,
                applicationApprovedEvent.getApproveEntityID(), USER_SECRETARY_WYM);
    }

    @EventListener
    public void handleApplicationProvideContractEvent(ApplianceProvideContractEvent applianceProvideContractEvent) {
        logger.debug("handleApplicationApprovedEvent");

        String applicationName = applianceService.findOne(applianceProvideContractEvent.getApplyid()).getName();

        handelDefault(applianceProvideContractEvent.getEventKey(), applicationName,
                APPLY_PROVIDECONTRACT_WORKURI, APPLY_PROVIDECONTRACT_VIEWURI,
                applianceProvideContractEvent.getApplyid(), null);

    }

    @EventListener
    public void handleApplicationProvideSolutionEvent(ApplianceProvideSolutionEvent applianceProvideSolutionEvent) {
        logger.debug("handleApplicationProvideSolutionEvent");

        String applicationName = applianceService.findOne(applianceProvideSolutionEvent.getApplyid()).getName();

        handelDefault(applianceProvideSolutionEvent.getEventKey(), applicationName,
                APPLY_FINAL_WORKURI, APPLY_FINAL_VIEWURI,
                applianceProvideSolutionEvent.getApplyid(), USER_DIRECTOR);
    }

    @EventListener
    public void handleApplicationFinalEvent(ApplianceFinalEvent applianceFinalEvent) {
        logger.debug("handleApplicationFinalEvent");

        Appliance appliance = applianceService.findOne(applianceFinalEvent.getApplyid());
        String applicationName = appliance.getName();

        ProjectFacade projectFacade = new ProjectFacade(appliance);
        projectFacadeService.save(projectFacade);

        handelDefault(applianceFinalEvent.getEventKey(), applicationName,
                null, null,
                applianceFinalEvent.getApplyid(), USER_SECRETARY_WYM);
    }

    private void handelDefault(String eventKey, String applicationName,
                               String workUri, String viewUri, BigInteger id, String tmpWorkUserName) {
        for (EventAction ea : eventActionService.findByName(eventKey)) {
            if (ea.getEventAction().equals("WX")) {
                wxservice.dowork(ea, applicationName);
            }

            if (ea.getEventAction().equals("WORK")) {
                String str = eventMsgTemRepos.getOne(ea.getMsgTemID()).getName();

                MyWork work = new MyWork();
                work.setUser(userService.findByUsername(tmpWorkUserName));
                work.setCreaterID(BigInteger.ONE);
                work.setUpdaterID(work.getCreaterID());
                work.setCreateTime(new Date());
                work.setUpdateTime(work.getCreateTime());
                String tmp1 = str.replace("$$$$", applicationName);
                work.setMainTitle(tmp1.split("###")[1]);
                work.setSubTitle(tmp1.split("###")[0]);
                work.setName(work.getSubTitle());

                work.setDeadline(Date.from(LocalDateTime.now().plusDays(WORK_DEFAULT_PERIOD).atZone(ZoneId.systemDefault()).toInstant()));
                work.setWorkLevel(WORK_DEFAULT_LEVEL);
                work.setWorkType(WORK_DEFAULT_TYPE);
                work.setStatus(WORK_DEFAULT_STATUS);
                work.setWorkUri(workUri + id);
                work.setViewUri(viewUri + id);
                myWorkService.save(work);

            }
        }
    }

}
