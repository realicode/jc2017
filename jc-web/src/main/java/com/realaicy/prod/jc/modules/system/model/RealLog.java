package com.realaicy.prod.jc.modules.system.model;

import com.realaicy.prod.jc.lib.core.data.jpa.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * 机构实体类
 */
@Entity
@Table(name = "jc_sys_log")
public class RealLog extends BaseEntity<BigInteger> {

    /**
     * 类型
     */
    @Column(name = "LOG_TYPE")
    private String logType;
    /**
     * 名称
     */
    @Column(name = "LOG_TIME")
    private Date logTime;

    /**
     * 客体
     */
    @Column(name = "LOG_CLIENT")
    private String logClient;

    /**
     * 动作
     */
    @Column(name = "LOG_ACTION")
    private String logAction;

    /**
     * 动作
     */
    @Column(name = "LOG_MODULE")
    private String logModule;

    /**
     * 主体
     */
    @OneToOne()
    @JoinColumn(name = "LOG_SUBJECT_ID")
    private User logSubject;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogClient() {
        return logClient;
    }

    public void setLogClient(String logClient) {
        this.logClient = logClient;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public String getLogModule() {
        return logModule;
    }

    public void setLogModule(String logModule) {
        this.logModule = logModule;
    }

    public User getLogSubject() {
        return logSubject;
    }

    public void setLogSubject(User logSubject) {
        this.logSubject = logSubject;
    }
}
