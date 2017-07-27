package com.realaicy.prod.jc.modules.pj.service;

import com.realaicy.prod.jc.common.event.PJDateExpertPubEvent;
import com.realaicy.prod.jc.common.event.PJPreConfEvent;
import com.realaicy.prod.jc.common.event.PreRecruitPubEvent;
import com.realaicy.prod.jc.common.event.handler.WX;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.repos.EventMsgTemRepos;
import com.realaicy.prod.jc.modules.system.service.EventActionService;
import com.realaicy.prod.jc.modules.system.service.UserService;
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
public class PJListener {

    private final EventActionService eventActionService;
    private final WX wxservice;
    private final UserService userService;
    private final MyWorkService myWorkService;
    private final ProjectFacadeService facadeService;
    private final EventMsgTemRepos eventMsgTemRepos;

    private static Logger logger = LoggerFactory.getLogger(PJListener.class);


    @Autowired
    public PJListener(EventActionService eventActionService, WX wxservice,
                      UserService userService, MyWorkService myWorkService,
                      ProjectFacadeService facadeService, EventMsgTemRepos eventMsgTemRepos) {
        this.eventActionService = eventActionService;
        this.wxservice = wxservice;
        this.userService = userService;
        this.myWorkService = myWorkService;
        this.facadeService = facadeService;
        this.eventMsgTemRepos = eventMsgTemRepos;
    }


    @EventListener
    public void handlePJPreConfEvent(PJPreConfEvent pjPreConfEvent) {
        logger.debug("PJPreConfEvent");

        String projectName = facadeService.findOne(pjPreConfEvent.getPjid()).getName();

        handelDefault(pjPreConfEvent.getEventKey(), projectName,
                PJ_PRE_CHECKITEM_DO_WORKURI, PJ_PRECONF_VIEWURI,
                pjPreConfEvent.getCheckitemruntimeid(), pjPreConfEvent.getCheckerid());
    }

    @EventListener
    public void handlePJPreRecruitEvent(PreRecruitPubEvent preRecruitPubEvent) {
        logger.debug("preRecruitPubEvent");

        String projectName = facadeService.findOne(preRecruitPubEvent.getPjid()).getName();

        handelDefault(preRecruitPubEvent.getEventKey(), projectName,
                PJ_PRE_CHECKITEM_DO_WORKURI, PJ_PRECONF_VIEWURI,
                preRecruitPubEvent.getCheckitemruntimeid(), preRecruitPubEvent.getCheckerid());
    }

    @EventListener
    public void handlePJDateExpertEvent(PJDateExpertPubEvent pjDateExpertPubEvent) {
        logger.debug("pjDateExpertPubEvent");

        String projectName = facadeService.findOne(pjDateExpertPubEvent.getPjid()).getName();

        handelDefault(pjDateExpertPubEvent.getEventKey(), projectName,
                PJ_DATE_EXPERT_WORKURI, PJ_DATE_EXPERT_VIEWURI,
                pjDateExpertPubEvent.getCheckitemruntimeid(), BigInteger.valueOf(6));
    }

    private void handelDefault(String eventKey, String applicationName,
                               String workUri, String viewUri, BigInteger id, BigInteger workerid) {
        for (EventAction ea : eventActionService.findByName(eventKey)) {
            if (ea.getEventAction().equals("WX")) {
                wxservice.dowork(ea, applicationName);
            }

            if (ea.getEventAction().equals("WORK")) {
                String str = eventMsgTemRepos.getOne(ea.getMsgTemID()).getName();

                MyWork work = new MyWork();
                work.setUser(userService.findOne(workerid));
                work.setCreaterID(BigInteger.ONE);
                work.setUpdaterID(work.getCreaterID());
                work.setCreateTime(new Date());
                work.setUpdateTime(work.getCreateTime());
                String tmp1 = str.replace("$$$$", applicationName);
                work.setMainTitle(tmp1.split("###")[1]);
                work.setSubTitle(tmp1.split("###")[0]);
                work.setName(work.getSubTitle());

                work.setDeadline(Date.from(LocalDateTime.now().plusDays(WORK_DEFAULT_PERIOD).
                        atZone(ZoneId.systemDefault()).toInstant()));
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
