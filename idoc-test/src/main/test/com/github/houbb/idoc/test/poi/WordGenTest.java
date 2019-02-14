///*
// * Copyright (c)  2019. houbinbin Inc.
// * idoc All rights reserved.
// */
//
//package com.github.houbb.idoc.test.poi;
//
//import org.apache.poi.xwpf.usermodel.Borders;
//import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.xwpf.usermodel.XWPFTable;
//import org.junit.Test;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * <p> </p>
// *
// * poi导出word：包括**普通的段落以及表格**。常用的**api**已经以注释的方式写了进去
// * https://blog.csdn.net/jatham/article/details/75508401
// *
// * [生成 word 详解](http://www.cnblogs.com/qingruihappy/p/8443403.html)
// * <pre> Created: 2019/2/14 7:16 AM  </pre>
// * <pre> Project: idoc  </pre>
// *
// *  基本搞定！！！！
// * @author houbinbin
// */
//public class WordGenTest {
//
//    @Test
//    public void genTest() throws IOException {
//        final String path = "/Users/houbinbin/code/_github/idoc/idoc-core/src/main/resources/idoc/word/gen-idoc-word-all.docx";
//
//        XWPFDocument doc = new XWPFDocument();// 创建Word文件
//
//        // 1. 创建段落
//        XWPFParagraph p = doc.createParagraph();// 新建一个段落
//        p.setAlignment(ParagraphAlignment.CENTER);// 设置段落的对齐方式
//        p.setBorderBottom(Borders.DOUBLE);//设置下边框
//        p.setBorderTop(Borders.DOUBLE);//设置上边框
//        p.setBorderRight(Borders.DOUBLE);//设置右边框
//        p.setBorderLeft(Borders.DOUBLE);//设置左边框
//
//        //1.1 标题
//        XWPFRun r = p.createRun();//创建段落文本
//        r.setText("POI创建的Word段落文本");
//        r.setBold(true);//设置为粗体
//
//        // 1.2 正文段落
//        p = doc.createParagraph();// 新建一个段落
//        r = p.createRun();
//        r.setText("POI读写Excel功能强大、操作简单。");
//
//        //2. 创建一个表格
//        XWPFTable table= doc.createTable(3, 3);
//        table.getRow(0).getCell(0).setText("表格1");
//        table.getRow(1).getCell(1).setText("表格2");
//        table.getRow(2).getCell(2).setText("表格3");
//        FileOutputStream out = new FileOutputStream(path);
//        doc.write(out);
//        out.close();
//    }
//
//}
