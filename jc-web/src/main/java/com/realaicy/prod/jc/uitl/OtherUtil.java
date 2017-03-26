package com.realaicy.prod.jc.uitl;

import com.aspose.words.License;
import com.realaicy.prod.jc.realglobal.config.StaticParams;

import java.io.FileInputStream;
import java.io.InputStream;


/**
 * Created by realaicy on 2017/3/8.
 * xx
 */
public class OtherUtil {

    private static InputStream license;


    public static int getRandNum(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static String getSixRandSMSCode() {
        return String.valueOf(1 + (int) (Math.random() * StaticParams.REALNUM.SMSCODE_N));
    }


    public static boolean getAsposeLicense() {
        boolean result = false;
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            license = new FileInputStream("G:\\Realtemp\\tempweb\\license.xml"); // 凭证文件
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
