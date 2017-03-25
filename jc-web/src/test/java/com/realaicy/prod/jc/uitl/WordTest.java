package com.realaicy.prod.jc.uitl;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by realaicy on 2017/3/25.
 * xx
 */
public class WordTest {

    @Test
    public void aTest(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orgname", "测试申请机构");
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "G:\\Realtemp\\doc\\contract1.docx", map);
            FileOutputStream fos = new FileOutputStream("G:\\Realtemp\\doc\\contract1_Realaicy.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}