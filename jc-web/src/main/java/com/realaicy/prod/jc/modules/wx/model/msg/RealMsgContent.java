package com.realaicy.prod.jc.modules.wx.model.msg;

import java.io.Serializable;

/**
 * Created by realaicy on 2017/3/13.
 *
 */
public class RealMsgContent implements Serializable {
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

}
