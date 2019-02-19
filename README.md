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

# 变更日志

> [变更日志](doc/CHANGELOG.md)

# 快速入门

## 需要 

jdk1.8+

maven 3.x+

## maven 引入

使用 maven 引入当前 idoc 插件。

可以参考当前项目的 idoc-test 模块。 

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.github.houbb</groupId>
            <artifactId>idoc-core</artifactId>
            <version>0.0.1</version>
            <configuration>
                <isAllInOne>true</isAllInOne>
                <generates>
                    <generate>com.github.houbb.idoc.ftl.api.generator.MarkdownDocGenerator</generate>
                </generates>
                <generateFilters>
                    <generateFilter>com.github.houbb.idoc.test.filter.MyGenerateFilter</generateFilter>
                </generateFilters>
            </configuration>
            <dependencies>
                <dependency>
                    <groupId>com.github.houbb</groupId>
                    <artifactId>idoc-test</artifactId>
                    <version>${project.version}</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
```

### 自定义生成类的过滤器

如果不定义自己的类生成过滤器，则会生成所有的类信息。

一般使用中我们只关心 service 方法，所以添加了类的过滤实现。

## 方法类信息

- QueryUserService.java

```java
/**
 * 查询用户服务类
 * @author binbin.hou
 * @since 0.0.1
 * date 2019/2/12
 */
public interface QueryUserService {

    /**
     * 根据用户信息查询用户
     * @param user 用户信息
     * @return 结果
     * @since 0.0.2,2019/02/12
     */
    public User queryUser(final User user);

}
```

## 执行插件

```
mvn com.github.houbb:idoc-core:0.0.1:idoc
```

- 日志信息

```
[INFO] ------------------------------------ Start generate doc
[INFO] 共计 【4】 个文件待处理，请耐心等待。进度如下：
==================================================================================================== 100%
[INFO] Generator doc with docGenerator: com.github.houbb.idoc.ftl.api.generator.MarkdownDocGenerator
[INFO] Markdown 生成文档文件 all in one 路径: /Users/houbinbin/code/_github/idoc/idoc-test/src/main/resources/idoc-gen/idoc-test-全部文档.md
[INFO] ------------------------------------ Finish generate doc
```

## 文档信息

当前文件路径日志会打印，比如我自己测试的为：

`/Users/houbinbin/code/_github/idoc/idoc-test/src/main/resources/idoc-gen/idoc-test-全部文档.md`

效果如下：

-----------------

# QueryUserService-查询用户服务类

暂无类备注信息

## queryUser-根据用户信息查询用户

暂无方法备注信息

### 方法入参

| 字段 | 说明 | 字段类型 | 是否必填 | 备注 |
|:---|:---|:---|:---|:----|
| user | 用户信息 | com.github.houbb.idoc.test.model.User |  |  |

#### 方法入参详情

- user 入参详情

| 字段 | 说明 | 字段类型 | 是否必填 | 备注 |
|:---|:---|:---|:---|:----|
| name | 名称 | java.lang.String | 是 | 中文名称，请认真填写 |
| age | 年龄 | int |  |  |
| birthday | 生日 | java.util.Date |  |  |
| addressList | 地址 | java.util.List |  |  |
| mate | 伴侣 | com.github.houbb.idoc.test.model.User |  |  |

### 方法出参

| 字段 | 说明 | 字段类型 | 备注 |
|:---|:---|:---|:---|
| name | 名称 | java.lang.String |  中文名称，请认真填写 |
| age | 年龄 | int |   |
| birthday | 生日 | java.util.Date |   |
| addressList | 地址 | java.util.List |   |
| mate | 伴侣 | com.github.houbb.idoc.test.model.User |   |

#### 方法出参详情

暂无入参详情

-----------------

# 插件配置属性简介

| 属性 | 说明 | 是否必填| 默认值 | 备注 |
|:----|:----|:----|:----|:----|
| encoding | 否 | 项目编码 | UTF-8 | |
| includes | 否 | 元数据包含的文件信息 | `**\/*.java` | 默认扫描所有 java 文件 |
| excludes | 否 | 元数据排除的文件信息 | 无 | 默认不排除 |
| isOverwriteWhenExists | 否 | 文档存在时是否覆盖 | true |  | 
| isAllInOne | 否 | 所有类信息是否生成单个文档 | true |  |
| generates | 否 | 文档生成类 | 命令行文档生成信息 |  可以同时指定多个。类名全称。用户自定义参见 `com.github.houbb.idoc.api.core.genenrator.IDocGenerator` |
| generateFilters | 否 | 文档生成类过滤器 | 无 |  可以同时指定多个。类名全称。用户自定义参见 `com.github.houbb.idoc.api.core.filter.IDocGenerateFilter` |

# 设计初衷

## 节约时间

Java 文档一直是一个大问题。

很多项目不写文档，即使写文档，对于开发人员来说也是非常痛苦的。

不写文档的缺点自不用多少，手动写文档的缺点也显而易见：

1. 非常浪费时间，而且会出错。

2. 无法保证及时更新。代码已经变了，但是文档还要同步修改。需要强制人来维护这一种一致性。这很难。

## 为什么不是 swagger-ui

java 的文档有几类：

1. jdk 自带的 doc 生成。这个以前实践给别人用过，别人用 C#，看到 java 的默认文档感觉很痛苦。

就算是我们 java 开发者，也很讨厌看 jdk 的文档。看着不美观，也很累。

2. swagger-ui 是基于 java 注解的文档生成工具。相对而言比较优雅，也非常强大。

但是缺点也是有的。开发人员要写 jdk 原来的注释+注解。注解太多，导致写起来也很痛苦，大部分开发者后来还是选择了放弃。

那么问题来了？我们怎么办才能尽可能的让开发人员，和文档阅读人员都乐于接受呢？

jdk 自带的 doc 就是基于 maven 插件的，本项目也是。

区别如下：

1. 尽可能的保证和 Java 原生注释一致，让开发者很容易就可以使用。

2. 尽可能的信息全面，但是文档简洁。让文档的阅读者享受到等同于手写文档的体验。

3. 将信息的获取和生成区分开。方便用户自己定义自己的输出方式。
