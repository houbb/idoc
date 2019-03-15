# i-doc 项目简介

为 java 项目生成项目文档。

基于原生的 java 注释，尽可能的生成简介的文档。用户可以自定义自己的模板，生成自己需要的文档。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/idoc/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/idoc)
[![Build Status](https://www.travis-ci.org/houbb/idoc.svg?branch=master)](https://www.travis-ci.org/houbb/idoc?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/idoc/badge.svg?branch=master)](https://coveralls.io/github/houbb/idoc?branch=master)

## 特性

- 基于 maven 项目生成包含大部分信息的元数据

- 默认支持 markdown 简化文档的生成，支持自定义模板

- 支持用户自定义文档生成器

- 支持用户自定生成文档的类过滤器

## 新特性

- 添加字段类型别名，支持用户自定义

# 变更日志

> [变更日志](doc/CHANGELOG.md)

# 快速入门

## 需要 

jdk1.8+

maven 3.x+

## maven 引入

使用 maven 引入当前 idoc 插件。

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.github.houbb</groupId>
            <artifactId>idoc-core</artifactId>
            <version>0.1.0</version>
        </plugin>
    </plugins>
</build>
```

## 测试对象的创建

为了演示文档，我们创建了一个 Address 对象。

```java
package com.github.houbb.idoc.test.model;

/**
 * 地址
 * @author binbin.hou
 * @since 0.0.1
 */
public class Address {

    /**
     * 城市
     */
    private String country;

    /**
     * 街道
     */
    private String street;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

```

## 执行插件

```
mvn com.github.houbb:idoc-core:0.0.2:idoc
```

## 命令行日志信息

```
[INFO] ------------------------------------ Start generate doc
[INFO] 共计 【1】 个文件待处理，请耐心等待。进度如下：
==================================================================================================== 100%
[INFO] Generator doc with docGenerator: com.github.houbb.idoc.core.api.generator.ConsoleDocGenerator
[INFO] ------------------------------------ 文档信息如下：

[类名] com.github.houbb.idoc.test.model.Address
[类信息] {"comment":"地址","docAnnotationList":[],"docFieldList":[{"comment":"城市","name":"country","type":"java.lang.String"},{"comment":"街道","name":"street","type":"java.lang.String"}],"docMethodList":[{"docMethodParameterList":[],"docMethodReturn":{"fullName":"java.lang.String","name":"String","packageName":"java.lang"},"docTagList":[],"exceptionList":[],"modifiers":["public"],"name":"getCountry","seeList":[],"signature":"getCountry()"},{"docMethodParameterList":[{"docAnnotationList":[],"name":"country","type":"java.lang.String"}],"docMethodReturn":{},"docTagList":[],"exceptionList":[],"modifiers":["public"],"name":"setCountry","seeList":[],"signature":"setCountry(country)"},{"docMethodParameterList":[],"docMethodReturn":{"fullName":"java.lang.String","name":"String","packageName":"java.lang"},"docTagList":[],"exceptionList":[],"modifiers":["public"],"name":"getStreet","seeList":[],"signature":"getStreet()"},{"docMethodParameterList":[{"docAnnotationList":[],"name":"street","type":"java.lang.String"}],"docMethodReturn":{},"docTagList":[],"exceptionList":[],"modifiers":["public"],"name":"setStreet","seeList":[],"signature":"setStreet(street)"}],"docTagList":[{"lineNum":5,"name":"author","parameters":["binbin.hou"],"value":"binbin.hou"},{"lineNum":6,"name":"since","parameters":["0.0.1"],"value":"0.0.1"}],"fullName":"com.github.houbb.idoc.test.model.Address","modifiers":["public"],"name":"Address","packageName":"com.github.houbb.idoc.test.model"}

[INFO] ------------------------------------ Finish generate doc
```

# 进一步学习

[00-项目概览](/doc/blog/文档生成-00-项目概览.md)

[01-设计初衷](/doc/blog/文档生成-01-设计初衷.md)

[02-插件的参数配置](/doc/blog/文档生成-02-插件的参数配置.md)

[03-自定义生成文件过滤器](/doc/blog/文档生成-03-自定义生成文件过滤器.md)

[04-字段类型别名支持](/doc/blog/文档生成-04-字段类型别名支持.md)