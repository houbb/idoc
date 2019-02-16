package com.github.houbb.idoc.core.core.impl;


import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.exception.IDocRuntimeException;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.util.ConsoleProgressBar;
import com.github.houbb.idoc.core.core.ExecuteService;
import com.github.houbb.idoc.core.util.JavaClassUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.paradise.common.util.ArrayUtil;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 抽象执行服务
 *
 * @author bbhou
 * @version 0.0.1
 * @since 0.0.1, 2017/11/12
 */
public abstract class AbstractExecuteService implements ExecuteService {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(AbstractExecuteService.class);

    /**
     * 项目
     */
    protected MavenProject project;

    /**
     * 编码
     */
    protected String encoding;

    /**
     * 包含文件的正则
     */
    protected String includes;

    /**
     * 排除文件的正则
     */
    protected String excludes;


    public AbstractExecuteService() {
    }

    /**
     *  抽象执行服务
     *
     * @param mavenProject maven项目
     * @param encoding     编码
     */
    public AbstractExecuteService(MavenProject mavenProject, String encoding) {
        this.project = mavenProject;
        this.encoding = encoding;
        log.debug("Initial with mavenProject: {} and encoding: {}", mavenProject, encoding);
    }

    /**
     * 核心执行方法
     *
     * @throws IDocRuntimeException if any
     */
    @Override
    public void execute() throws IDocRuntimeException {
        //1. 执行之前
        beforeExecute();

        //2. 执行
        JavaClass[] javaClasses = getJavaClassArray();
        if (ArrayUtil.isEmpty(javaClasses)) {
            log.warn("JavaClass array is empty, stop execute()!");
            return;
        }

        //3. 构建对应的对象信息
        List<DocClass> docClassList = new ArrayList<>();
        IHandler<JavaClass, DocClass> javaClassHandler = configJavaClassHandler();

        int totalNum = javaClasses.length;
        log.info("共计 【" + totalNum + "】 个文件待处理，请耐心等待。进度如下：");
        ConsoleProgressBar cpb = new ConsoleProgressBar(0, totalNum,
                100, '=');
        for (int i = 0; i < javaClasses.length; i++) {
            final JavaClass javaClass = javaClasses[i];
            DocClass docClass = javaClassHandler.handle(javaClass);
            docClassList.add(docClass);
            cpb.show((long) (i + 1));
        }

        //3. 执行之后
        afterExecute(docClassList);
    }

    /**
     * 指定 Java class 的处理方式
     *
     * @return Exception if any
     */
    protected abstract IHandler<JavaClass, DocClass> configJavaClassHandler();

    /**
     * 执行之前
     *
     * @throws IDocRuntimeException if any
     */
    protected abstract void beforeExecute() throws IDocRuntimeException;

    /**
     * 执行之后
     * @param docClassList 列表
     * @throws IDocRuntimeException if any
     */
    protected abstract void afterExecute(final List<DocClass> docClassList) throws IDocRuntimeException;

    /**
     * 获取 java 类数组
     *
     * @return java class array
     */
    JavaClass[] getJavaClassArray() {
        try {
            log.debug("Get java class with project: {}, encoding: {}, includes: {}, excludes: {}",
                    project, encoding, includes, excludes);
            JavaClass[] javaClasses = JavaClassUtil.getInstance().getQdoxClasses(project, encoding, includes, excludes);
            log.debug("Get java class with result: {}", Arrays.toString(javaClasses));
            return javaClasses;
        } catch (IOException | MojoExecutionException e) {
            log.error("AbstractExecuteService meet ex: " + e, e);
            return new JavaClass[]{};
        }
    }

    /**
     * 得到包括
     *
     * @return java.lang.String
     */
    public String getIncludes() {
        return includes;
    }

    /**
     * 设置包括
     *
     * @param includes 包括
     * @return com.github.houbb.gen.maven.plugin.core.impl.AbstractExecuteService
     */
    public AbstractExecuteService setIncludes(String includes) {
        this.includes = includes;
        log.debug("Config set includes: {}", includes);
        return this;
    }

    /**
     * 得到排除
     *
     * @return java.lang.String
     */
    public String getExcludes() {
        return excludes;
    }

    /**
     * 集排除
     *
     * @param excludes 排除
     * @return com.github.houbb.gen.maven.plugin.core.impl.AbstractExecuteService
     */
    public AbstractExecuteService setExcludes(String excludes) {
        this.excludes = excludes;
        log.debug("Config set excludes: {}", excludes);
        return this;
    }

}
