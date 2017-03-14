package com.realaicy.prod.jc.uitl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.realaicy.prod.jc.modules.wx.model.RealTK;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by realaicy on 2017/3/8.
 * xx
 */
public class RealCacheUtil {
    public static final List<String> SMS_WHITE_LIST = Arrays.asList("13920234616", "13820");
    public static final List<String> SMS_BLACK_LIST = new ArrayList<>();
    public static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1", "192.168.99.6");
    public static final List<String> IP_BLACK_LIST = new ArrayList<>();
    public static final Table<String, LocalDate, Integer> MOBILENUMBER_CANSENT_TABLE = HashBasedTable.create();
    public static final Table<String, LocalDate, Integer> IPADRESS_CANSENT_TABLE = HashBasedTable.create();
    public static final Table<String, String, Integer> IPADRESS_CANUSEFUNC_TABLE = HashBasedTable.create();
    public static final Table<String, String, LocalDateTime> SMS_CODE_MAP = HashBasedTable.create();
    private  static   String wxToken = "";
    private    static LocalDateTime wxTokenExpire;

    public static String getWxToken() {

        if (LocalDateTime.now().isAfter(RealCacheUtil.getWxTokenExpire())) {
            RealTK realTK = NetUtil.getRestTemplate().getForObject(
                    "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx9605bfbe1e7c2f1e"
                            + "&corpsecret=nm4gMa4Ri3wQDOttvmxU7TaIILeM7M7SYvNiASmmx5e_Trg6_4g3pTYOxM6A54k2",
                    RealTK.class);
            RealCacheUtil.setWxToken(realTK.getAccessToken());
            RealCacheUtil.setWxTokenExpire(LocalDateTime.now().plusMinutes(110));
        }
        return wxToken;
    }

    public static void setWxToken(String wxToken) {
        RealCacheUtil.wxToken = wxToken;
    }

    private static LocalDateTime getWxTokenExpire() {
        return wxTokenExpire;
    }


//    public static final Map<String, LocalDateTime> MOBILENUMBER_CANSENT_MAP = new HashMap<>();
//    public static final Map<String, LocalDateTime> IPADRESS_CANSENT_MAP = new HashMap<>();

    public static void setWxTokenExpire(LocalDateTime wxTokenExpire) {
        RealCacheUtil.wxTokenExpire = wxTokenExpire;
    }

}
