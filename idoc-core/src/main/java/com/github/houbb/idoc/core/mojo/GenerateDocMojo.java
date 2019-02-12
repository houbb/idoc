package com.github.houbb.idoc.core.mojo;

import com.github.houbb.idoc.core.core.impl.AbstractExecuteService;
import com.github.houbb.idoc.core.core.impl.GenerateDocService;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
@Mojo(name = "idoc")
public class GenerateDocMojo extends AbstractMojo {

    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(GenerateDocMojo.class);

    //region 配置参数的定义
    /**
     * 文档生成接口指定
     * 1. 指定实现类的全称
     * 2. 用户不指定，则使用系统默认的方式，用户指定则使用用户指定的方式。
     */
    @Parameter(property = "generates", required = false)
    private String[] generates;

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
    //endregion




    /**
     * 执行
     * @throws org.apache.maven.plugin.MojoExecutionException if any
     * @throws org.apache.maven.plugin.MojoFailureException if any
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info("------------------------------------ Start generate doc");

        AbstractExecuteService executeService = new GenerateDocService(project, encoding, generates);
        try {
            executeService.setExcludes(excludes).setIncludes(includes);
            executeService.execute();
        } catch (Exception e) {
            log.error("Generate doc execute meet ex: "+e, e);
        }

        log.info("------------------------------------ Finish generate doc");
    }


}
