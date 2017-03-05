package com.realaicy.prod.jc.realglobal.config;

/**
 * Created by realaicy on 16/7/15.
 * 常量
 */
public class StaticParams {
    public static final class PATHREGX {
        public static final String NOAUTH = "/smartadmin/noauth/**";
        public static final String CSS = "/smartadmin/css/**";
        public static final String JS = "/smartadmin/js/**";
        public static final String SB_ALL = "/smartadmin/**";
        public static final String REALAICY_ALL = "/realaicy/**";
        public static final String IMG = "/smartadmin/img/**";
        public static final String FONT = "/smartadmin/fonts/**";
        public static final String STATIC = "/static/**";
        public static final String AUTHADMIN = "/smartadmin/admin/**";
        public static final String AUTHUSER = "/smartadmin/user/**";
        public static final String TEMP_TEST = "/g/realerror/**";
    }

    public static final class REALCACHE {
        public static final String DEFAULTTOTALBYTES = "100M";
        public static final String DEFAULTBYTES = "10M";

        public static final int DEFAULTSIZE = 1000;
    }

    public static final class REALNUM {
        public static final int  N1 = 1;
        public static final int  N2 = 2;
        public static final int  N3 = 3;
        public static final int  N4 = 4;
        public static final int  N5 = 5;

        public static final int N20 = 20;
        public static final int  N10 = 10;
        public static final int  N200 = 200;
    }

    public static final class USERROLE {
        public static final String ROLE_ADMIN = "admin";
        public static final String ROLE_USER = "user";

    }

    public static final class CONSTVAR {
        public static final Short DEFAULT_RESWEIGHT = 1000;

    }

    public static final class FILEPATH {
        public static final String TEMP_USER_PORTRAIT_PATH = "";


    }

}