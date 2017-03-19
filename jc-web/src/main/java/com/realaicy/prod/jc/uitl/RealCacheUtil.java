package com.realaicy.prod.jc.uitl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.realaicy.prod.jc.modules.system.model.User;
import com.realaicy.prod.jc.realglobal.security.RealUserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    public static final Map<String, String> EVENT_ACTION = new HashMap<>();
}
