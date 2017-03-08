package com.realaicy.prod.jc.uitl;

import com.realaicy.prod.jc.realglobal.config.StaticParams;

/**
 * Created by realaicy on 2017/3/8.
 * xx
 */
public class OtherUtil {

    public static int getRandNum(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    public static String  getSixRandSMSCode() {
        return String.valueOf(1 + (int) (Math.random() * StaticParams.REALNUM.SMSCODE_N));
    }
}
