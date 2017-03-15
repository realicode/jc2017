package com.realaicy.prod.jc.common.event;

import com.realaicy.prod.jc.modules.system.model.EventAction;

/**
 * Created by realaicy on 2017/3/15.
 * xxx
 */
public interface JCEventHandler {
    void dowork(EventAction eventAction, String extMsg);
}
