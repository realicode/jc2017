package com.realaicy.prod.jc.common.monitor.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by realaicy on 2016/10/6.
 * XXX
 */
public class MCache {
    public Map getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Map cacheSize) {
        this.cacheSize = cacheSize;
    }

    private int sum;

    private Map cacheSize = new LinkedHashMap();

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
