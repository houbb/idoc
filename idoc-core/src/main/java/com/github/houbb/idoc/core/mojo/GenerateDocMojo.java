package com.github.houbb.idoc.core.mojo;

import com.github.houbb.idoc.api.model.common.DocOption;
import com.github.houbb.idoc.api.model.config.DocConfig;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.core.core.impl.AbstractExecuteService;
import com.github.houbb.idoc.core.core.impl.GenerateDocService;
import com.github.houbb.idoc.core.util.JavaTypeAliasUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * mvn 插件核心类
 * @author binbin.hou
 * @since 0.0.1
 */
@Mojo(name = "idoc")
public class GenerateDocMojo extends AbstractMojo {

    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(GenerateDocMojo.class);

    //region 配置参数的定义
    /**
     * The Maven Project Object.
     */
    @Parameter( defaultValue = "${project}", readonly = true, required = true )
    private MavenProject project;

    /**
     * The current user system settings for use in Maven.
     */
    @Parameter( defaultValue = "${settings}", readonly = true, required = true )
    private Settings settings;

    /**
     * The file encoding to use when reading the source files. If the property
     * <code>project.build.sourceEncoding</code> is not set, the platform default encoding is used.
     */
    @Parameter ( property = "encoding", defaultValue = "UTF-8" )
    private String encoding;

    /**
     * Comma separated includes Java files, i.e. <code>&#42;&#42;/&#42;Test.java</code>.
     * <p/>
     * <strong>Note:</strong> default value is {@code **\/*.java}.
     */
    @Parameter ( property = "includes", defaultValue = "**\\/*.java" )
    private String includes;

    /**
     * Comma separated excludes Java files, i.e. <code>&#42;&#42;/&#42;Test.java</code>.
     */
    @Parameter ( property = "excludes")
    private String excludes;

    /**
     * 存在是是否覆盖
     */
    @Parameter ( property = "isOverwriteWhenExists", defaultValue = "true")
    private boolean isOverwriteWhenExists;

    /**
     * 是否所有的信息生成在一个文件
     */
    @Parameter ( property = "isAllInOne", defaultValue = "true")
    private boolean isAllInOne;

    /**
     * 文档生成接口指定
     * 1. 指定实现类的全称
     * 2. 用户不指定，则使用系统默认的方式，用户指定则使用用户指定的方式。
     */
    @Parameter(property = "generates", required = false)
    private String[] generates;

    /**
     * 最后生成文档的包含过滤器
     * 1. 不指定默认所有的类都生成到文档
     * 2. 指定之后，只有符合过滤器条件的类才会被生成到文档中。
     */
    @Parameter(property = "generateFilters", required = false)
    private String[] generateFilters;

    /**
     * 最后生成文档的包含过滤器
     * 1. 不指定默认所有的类都生成到文档
     * 2. 指定之后，只有符合过滤器条件的类才会被生成到文档中。
     */
    @Parameter(property = "typeAliases", required = false)
    private DocOption[] typeAliases;
    //endregion


    /**
     * 执行
     * @throws org.apache.maven.plugin.MojoExecutionException if any
     * @throws org.apache.maven.plugin.MojoFailureException if any
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info("------------------------------------ Start generate doc");

        DocConfig docConfig = buildDocConfig();
        log.info("Generate doc with config：" + docConfig);
        AbstractExecuteService executeService = new GenerateDocService(project, docConfig);
        try {
            executeService.setExcludes(excludes).setIncludes(includes);
            executeService.execute();
        } catch (Exception e) {
            log.error("Generate doc execute meet ex: "+e, e);
        }

        log.info("------------------------------------ Finish generate doc");
    }

    /**
     * 构建配置信息
     * @return 配置
     */
    private DocConfig buildDocConfig() {
        DocConfig docConfig = new DocConfig();
        docConfig.setEncoding(encoding);
        docConfig.setAllInOne(isAllInOne);
        docConfig.setOverwriteWhenExists(isOverwriteWhenExists);
        docConfig.setGenerates(generates);
        docConfig.setGenerateFilters(generateFilters);
        docConfig.setTypeAliases(initTypeAliases());
        return docConfig;
    }

    /**
     * 初始化类型别名信息
     * @return map 信息
     */
    private Map<String, String> initTypeAliases() {
        Map<String, String> defaultAliasMap = JavaTypeAliasUtil.getTypeAliasMap();

        // 系统默认
        Map<String, String> aliasMap = new HashMap<>(defaultAliasMap);

        // 用户自定义信息
        if(ArrayUtil.isNotEmpty(typeAliases)) {
            for(DocOption docOption : typeAliases) {
                aliasMap.put(docOption.getKey(), docOption.getValue());
            }
        }
        return aliasMap;
    }

}
