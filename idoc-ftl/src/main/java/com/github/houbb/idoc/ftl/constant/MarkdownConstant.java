package com.github.houbb.idoc.ftl.constant;

import com.github.houbb.paradise.common.constant.MavenConstant;

/**
 * markdown 常量
 * @author binbin.hou
 * @since 0.0.1
 */
public final class MarkdownConstant {

    /**
     * 模板
     */
    public static class Template {
        /**
         * idoc markdown 基础文件夹
         */
        public static final String IDOC_MARKDOWN_BASE_PACKAGE = "/idoc/markdown";

        /**
         * idoc markdown 所有的信息生成在一起
         */
        public static final String IDOC_MARKDOWN_ALL_IN_ONE_FTL = "idoc-markdown-all.ftl";
    }

    public static class Generate {

        /**
         * markdown 的基础类文件夹
         */
        public static final String IDOC_MARKDOWN_BASE_PACAKGE = MavenConstant.SRC_MAIN_RESOURCES_PATH+"idoc-gen";

        /**
         * idoc markdown 所有的信息生成在一起
         */
        public static final String IDOC_MARKDOWN_ALL_IN_ONE = "idoc-markdown-all.md";
    }

}
