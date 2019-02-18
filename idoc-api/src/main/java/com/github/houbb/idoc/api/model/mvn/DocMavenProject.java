package com.github.houbb.idoc.api.model.mvn;

/**
 * maven 相关配置信息
 * @author binbin.hou
 * @since 0.0.1
 */
public class DocMavenProject {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 组织编号
     */
    private String groupId;

    /**
     * 项目编号
     */
    private String artifactId;

    /**
     * 项目版本
     */
    private String version;

    /**v
     * 项目基础目录
     */
    private String baseDir;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }
}
