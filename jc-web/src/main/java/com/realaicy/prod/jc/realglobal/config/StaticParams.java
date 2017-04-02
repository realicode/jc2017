package com.realaicy.prod.jc.realglobal.config;

/**
 * Created by realaicy on 16/7/15.
 * 常量
 */
public class StaticParams {


    public static final class REALCACHE {
        public static final String DEFAULTTOTALBYTES = "100M";
        public static final String DEFAULTBYTES = "10M";
    }

    public static final class REALSTATUS {
        public static final Short MYWORK_NOTSTART = Short.valueOf("1");
        public static final Short MYWORK_DONE = Short.valueOf("3");

        public static final Short CHECKITEM_DONE = Short.valueOf("3");

        public static final Short PJAPPLY_INI = Short.valueOf("1");
        public static final Short PJAPPLY_ERR_IN_INI = Short.valueOf("4101");
        public static final Short PJAPPLY_CONFIRMED = Short.valueOf("2");
        public static final Short PJAPPLY_ERR_IN_CONFIRM = Short.valueOf("4201");
        public static final Short PJAPPLY_APPROVED = Short.valueOf("3");
        public static final Short PJAPPLY_PROVIDECONTRACT_DONE = Short.valueOf("4");
        public static final Short PJAPPLY_PROVIDESOLUTION_DONE = Short.valueOf("5");
        public static final Short PJAPPLY_FINAL_DONE = Short.valueOf("6");

    }

    public static final class TODOWORK {

        public static final Short WORK_DEFAULT_LEVEL = 5;
        public static final Short WORK_DEFAULT_STATUS = 1;
        public static final String WORK_DEFAULT_TYPE = "work";
        public static final Long WORK_DEFAULT_PERIOD = 2L;


        public static final String USER_SUSER_ADMIN = "realaicy";
        public static final String USER_SECRETARY_WYM = "wym";
        public static final String USER_DIRECTOR = "zhaoy";
        public static final String APPLY_CONFIRM_KEY = "Appliance_Confirm";
        public static final String APPLY_CREATION_KEY = "Appliance_Creation";
        public static final String APPLY_APPROVE_KEY = "Appliance_Approve";
        public static final String APPLY_PROVIDECONTRACT_KEY = "Appliance_ProvideContract";
        public static final String APPLY_PROVIDESOLUTION_KEY = "Appliance_ProvideSolution";
        public static final String APPLY_FINAL_KEY = "Appliance_Final";

        public static final String PJ_PRECONF_KEY = "PJ_PRE_CONF";



        public static final String APPLY_PROVIDECONTRACT_WORKURI = "/pj/apply/providecontract?realactiontype=new&applyid=";
        public static final String APPLY_PROVIDECONTRACT_VIEWURI = "/pj/apply/show/";

        public static final String APPLY_FINAL_WORKURI = "/pj/apply/final?realactiontype=new&applyid=";
        public static final String APPLY_FINAL_VIEWURI = "/pj/apply/show/";


        public static final String PJ_PRE_CHECKITEM_DO_WORKURI = "/pj/pre/docheck?realactiontype=new&checkitemruntimeid=";
        public static final String PJ_PRECONF_VIEWURI = "/pj/pre/show/?realactiontype=new&checkitemruntimeid=";

    }

    public static final class BTNTYPE {
        public static final String PASS = "pass";
        public static final String DENY = "deny";
    }

    public static final class REALACTIONTYPE {
        public static final String PJ_AFFIRM = "affirm";
        public static final String PJ_APPROVE = "approve";
        public static final String PJ_BUILDCONTRACT_NEW = "new";
    }


    public static final class SPRINGPROFILES {
        public static final String PRODUCTION = "prod";
        public static final String DEVELOP = "develop";
        public static final String TEST_UAT = "test_uat";
        public static final String TEST_IT = "test_it";
        public static final String TEST_SERVICE = "test_service";
        public static final String TEST_WEB = "test_web";

    }

    public static final class SESSIONKEY {
        public static final String USERID = "userid";
        public static final String USERNAME = "username";

    }


    public static final class WS {
        public static final String DEFAULTTOTALBYTES = "100M";
        public static final String DEFAULTBYTES = "10M";

        public static final int DEFAULTSIZE = 1000;
    }

    public static final class SMS {
        public static final int MAX_SENTCODE_PER_MOBILE_INONEDAY = 5;
        public static final int MAX_SENTCODE_PER_IP_INONEDAY = 5;
        public static final int MAX_MIN_BETWEEN_SENTANDRECIVE = 15;


    }

    public static final class FUNCMAX {
        public static final int CHECKUSERNAME = 10;
        public static final int CHECKSMSCODE = 5;
        public static final int REGISUSER = 5;

    }

    public static final class FILEUPLOAD {
        public static final int MAXBYTESPERFILE = 20971520;

    }

    public static final class REALNUM {
        public static final int N1 = 1;
        public static final int N2 = 2;
        public static final int N3 = 3;
        public static final int N4 = 4;
        public static final int N5 = 5;

        public static final int N20 = 20;
        public static final int N10 = 10;
        public static final int N200 = 200;
        public static final int SMSCODE_N = 999999;

    }

    public static final class USERROLE {
        public static final String ROLE_ADMIN = "admin";
        public static final String ROLE_USER = "user";

    }

    public static final class PATHREGX {
        public static final String NOAUTH = "/smartadmin/noauth/**";
        public static final String CSS = "/smartadmin/css/**";
        public static final String JS = "/smartadmin/js/**";
        public static final String SB_ALL = "/smartadmin/**";
        public static final String REALAICY_ALL = "/realaicy/**";
        public static final String REALRES = "/realres/**";
        public static final String IMG = "/smartadmin/img/**";
        public static final String FONT = "/smartadmin/fonts/**";
        public static final String STATIC = "/static/**";
        public static final String AUTHADMIN = "/smartadmin/admin/**";
        public static final String AUTHUSER = "/smartadmin/user/**";
        public static final String TEMP_TEST = "/g/realerror/**";
        public static final String SIGNUP = "/signup";
        public static final String REGIS = "/regisuser";

        public static final String SIGNUP_CHECKUSERNAME = "/system/user/checkusername";
        public static final String SIGNUP_SENTMBCODE = "/sms/signup/sentcode";
        public static final String WX_TEST = "/wx/test";
        public static final String WX_TEST2 = "/wx/t2";

        public static final String WX_TEST3 = "/temptest/**";
        public static final String WX = "/wx/**";


    }
}
