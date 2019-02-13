/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.test.poi;

import com.github.houbb.idoc.test.utils.CustomXWPFDocument;
import com.github.houbb.idoc.test.utils.WorderToNewWordUtils;

import org.junit.Test;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> </p>
 *
 * <pre> Created: 2019/2/14 7:07 AM  </pre>
 * <pre> Project: idoc  </pre>
 *
 * @author houbinbin
 */
public class WordUtilTest {

    @Test
    public void genTest() {
//        final String templatePath = "/Users/houbinbin/code/_github/idoc/idoc-core/src/main/resources/idoc/word/idoc-word-all.docx";
//
//        final String templatePath = "/Users/houbinbin/code/_github/idoc/idoc-core/src/main/resources/idoc/word/idoc-word-all.docx";

        //需要进行文本替换的信息
        Map<String, String> data = new HashMap<String, String>();
        data.put("${author}", "binbin.hou");
        //需要进行动态生成的信息
        Map<String,Object> mapList = new HashMap<String, Object>();


//        CustomXWPFDocument doc = WorderToNewWordUtils.changWord("template/demo.docx",data,mapList);
//        FileOutputStream fopts = new FileOutputStream("D:/呵呵.docx");
//        doc.write(fopts);
//        fopts.close();
    }

}
