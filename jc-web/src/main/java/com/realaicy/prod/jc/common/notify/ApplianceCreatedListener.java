package com.realaicy.prod.jc.common.notify;

import com.realaicy.prod.jc.common.event.CreationEvent;
import com.realaicy.prod.jc.common.event.JCBaseEvent;
import com.realaicy.prod.jc.common.event.handler.WX;
import com.realaicy.prod.jc.modules.pj.model.Appliance;
import com.realaicy.prod.jc.modules.system.model.EventAction;
import com.realaicy.prod.jc.modules.system.service.EventActionService;
import com.realaicy.prod.jc.uitl.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by realaicy on 2017/3/15.
 * <p>
 * xxx
 */
@Component
public class ApplianceCreatedListener {

    private final EventActionService eventActionService;
    private final WX wxservice;

    @Autowired
    public ApplianceCreatedListener(EventActionService eventActionService, WX wxservice) {
        this.eventActionService = eventActionService;
        this.wxservice = wxservice;
    }

    @EventListener
    public void handleApplicationCreatedEvent(CreationEvent<Appliance> applicationCreationEvent) {
        System.out.println("handleApplicationCreatedEvent");


        for (EventAction ea : eventActionService.findByName(((JCBaseEvent) applicationCreationEvent).getEventKey())) {
            if (ea.getEventAction().equals("WX")) {
                wxservice.dowork(ea, SpringSecurityUtil.getNameOfCurrentPrincipal());
            }
        }
    }
}
