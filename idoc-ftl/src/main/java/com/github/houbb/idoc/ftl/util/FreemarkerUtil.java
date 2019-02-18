package com.github.houbb.idoc.ftl.util;

import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import freemarker.template.*;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * Freemarker 工具类
 *
 * @author houbinbin
 * @since 0.0.1
 */
public final class FreemarkerUtil {

    /**    
     *  freemarker util    
     */    
    private FreemarkerUtil(){}

    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(FreemarkerUtil.class);

    /**
     * 组态
     */
    private static Configuration configuration = null;

    /**
     * 获取配置
     *
     * @param encoding 编码
     * @return 配置
     */
    public static Configuration getConfiguration(String encoding) {
        return getConfiguration(encoding, false);
    }

    /**
     * define Configuration
     *
     * @param encoding 编码
     * @param isForce  是否强制 默认为 false
     * @return 配置
     */
    public static Configuration getConfiguration(String encoding, boolean isForce) {
        if (configuration == null
                || isForce) {
            configuration = new Configuration();
            //编码
            configuration.setEncoding(Locale.getDefault(), encoding);

            //// 设置对象的包装器
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        }

        return configuration;
    }

    /**
     * create file by template, htmlName, and modal map;
     * 1. 不存在直接创建
     * 2. 存在 + 覆盖：则删除直接覆盖
     *
     * @param template              模板信息
     * @param targetFilePath        目标路径
     * @param map                   配置属性
     * @param isOverwriteWhenExists 是否覆盖
     * @throws RuntimeException if any
     * @return 是否创建文件
     */
    public static boolean createFile(Template template, String targetFilePath, Map<String, Object> map, boolean isOverwriteWhenExists)
            throws RuntimeException {
        boolean result = true;
        File file = new File(targetFilePath);

        //create parent dir first.
        boolean makeDirs = file.getParentFile().mkdirs();
        log.debug("createFile makeDirs:{}", makeDirs);

        try {
            if (!file.exists()) {
                result = file.createNewFile();
                flushFileContent(template, map, file);
            } else if (file.exists()
                    && isOverwriteWhenExists) {
                flushFileContent(template, map, file);
            }
        } catch (IOException | TemplateException e) {
            log.error("create file meet ex{}", e, e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 刷新文件内容
     *
     * @param template 模板
     * @param map      配置
     * @param file     文件
     * @throws IOException       if any
     * @throws TemplateException if any
     */
    private static void flushFileContent(Template template, Map<String, Object> map, File file)
            throws IOException, TemplateException {
        Writer out = new BufferedWriter(new FileWriter(file));
        template.process(map, out);
        out.flush();
    }

    /**
     * 创建文件
     * [写入文件的几种方式](https://blog.csdn.net/netsnake_/article/details/80395241)
     * @param targetFilePath 目标文件路径
     * @param isOverwriteWhenExists 是否覆盖文件内容
     * @param fileContent 文件内容
     * @return 是否创建成功
     */
    public static boolean createFile(final String targetFilePath,
                                     final boolean isOverwriteWhenExists,
                                     final String fileContent) {
        boolean result = true;
        File file = new File(targetFilePath);

        //create parent dir first.
        boolean makeDirs = file.getParentFile().mkdirs();
        log.debug("createFile makeDirs:{}", makeDirs);

        try(FileWriter writer = new FileWriter(file)) {
            if(file.exists()
                && !isOverwriteWhenExists) {
                log.debug("文件：{} 已经存在，且不用覆盖。", targetFilePath);
                return true;
            }
            if (!file.exists()) {
                result = file.createNewFile();
            }

            //清空原始文件内容
            writer.write("");
            writer.write(fileContent);
        } catch (IOException e) {
            log.error("create file meet ex{}", e, e);
            throw new RuntimeException(e);
        }

        return result;
    }


    /**
     * 获取模板转换后的字符串内容
     * @param template 模板
     * @param map 集合
     * @return 结果
     */
    public static String getTemplateContent(final Template template,
                                      final Map<String, Object> map) {
        try {
            Writer writer = new StringWriter();
            template.process(map, writer);
            return writer.toString();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
