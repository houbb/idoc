///*
// * Copyright (c)  2019. houbinbin Inc.
// * idoc All rights reserved.
// */
//
//package com.github.houbb.idoc.test.poi;
//
//import com.deepoove.poi.XWPFTemplate;
//
//import org.junit.Test;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//
///**
// * <p> </p>
// *
// * [poi根据模板导出word（包含图片、动态生成表格、合并单元格）](https://blog.csdn.net/java_xuetu/article/details/79540622)
// * <pre> Created: 2019/2/14 6:30 AM  </pre>
// * <pre> Project: idoc  </pre>
// *
// * @author houbinbin
// */
//public class WordFtlTest {
//
//    @Test
//    public void textTest() throws IOException {
//        final String templatePath = "/Users/houbinbin/code/_github/idoc/idoc-core/src/main/resources/idoc/word/idoc-word-all.docx";
//        final String templateOutPath = "/Users/houbinbin/code/_github/idoc/idoc-core/src/main/resources/idoc/word/test-idoc-word-all.docx";
//
//        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(new HashMap<String, Object>(){{
//            put("today", "2019-02-14");
//        }});
//        FileOutputStream out = new FileOutputStream(templateOutPath);
//        template.write(out);
//        out.flush();
//        out.close();
//        template.close();
//    }
//
//}
