# 写文档

作为一名开发者，每个人都要写代码。

工作中，几乎每一位开发者都要写文档。

因为工作是人和人的协作，产品要写需求文档，开发要写详细设计文档，接口文档。

可是，作为一个懒人，平时最讨厌的一件事情就是写文档。

![文档](文档.png)

写文档最令我不爽的地方是在于**代码备注要改一遍，然后文档再改一遍。**

所有重复的劳作，都是对于我们宝贵摸鱼时间的最大浪费。

于是，我就常常想，能不能只写一遍呢？

# i-doc 项目简介

[idoc](https://github.com/houbb/idoc) 为 java 项目生成项目文档。

基于原生的 java 注释，尽可能的生成简介的文档。用户可以自定义自己的模板，生成自己需要的文档。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/idoc/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/idoc)
[![Build Status](https://www.travis-ci.org/houbb/idoc.svg?branch=master)](https://www.travis-ci.org/houbb/idoc?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/idoc/badge.svg?branch=master)](https://coveralls.io/github/houbb/idoc?branch=master)

实现原理：基于 maven 插件，类似于 javadoc。可以更加灵活，允许用户自定义。

## 特性

- 基于 maven 项目生成包含大部分信息的元数据

- 默认支持 markdown 简化文档的生成，支持自定义模板

- 支持用户自定义文档生成器

- 支持用户自定生成文档的类过滤器

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
            <version>0.3.0</version>
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
mvn com.github.houbb:idoc-core:0.3.0:idoc
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

## 更多生成方式

当然，你可以发现这里只是把元数据进行输出到控台，意义不大。

我们可以根据需求，自定义实现生成类。

比如下面的方式，可以使用内置的 `MarkdownDocGenerator` 输出到 markdown 文件。

```xml
<plugin>
    <groupId>com.github.houbb</groupId>
    <artifactId>idoc-core</artifactId>
    <version>0.3.0</version>
    <configuration>
        <generates>
            <generate>com.github.houbb.idoc.ftl.api.generator.MarkdownDocGenerator</generate>
        </generates>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>com.github.houbb</groupId>
            <artifactId>idoc-ftl</artifactId>
            <version>0.3.0</version>
        </dependency>
    </dependencies>
</plugin>
```

效果可以参考：

> [heaven 文档目录](https://github.com/houbb/heaven/blob/master/doc/gen/heaven-%E7%B4%A2%E5%BC%95.md)

ps: [heaven](https://github.com/houbb/heaven) 项目是个人整理了多年的工具包，几百个类，手写文档估计要很久。

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

# 参数配置说明

为了更加灵活的实现文档的生成和文档元数据的生成，提供如下参数

## 插件配置属性简介

| 属性 | 是否必填 | 说明 | 默认值 | 备注 |
|:----|:----|:----|:----|:----|
| encoding | 否 | 项目编码 | UTF-8 | |
| includes | 否 | 元数据包含的文件信息 | `**\/*.java` | 默认扫描所有 java 文件 |
| excludes | 否 | 元数据排除的文件信息 | 无 | 默认不排除 |
| isOverwriteWhenExists | 否 | 文档存在时是否覆盖 | true |  | 
| isAllInOne | 否 | 所有类信息是否生成单个文档 | true | 命令行文档生成器，此属性无意义。 |
| generates | 否 | 文档生成类 | 命令行文档生成信息 |  可以同时指定多个。类名全称。用户自定义参见 `com.github.houbb.idoc.api.core.genenrator.IDocGenerator` |
| generateFilters | 否 | 文档生成类过滤器 | 无 |  可以同时指定多个。类名全称。用户自定义参见 `com.github.houbb.idoc.api.core.filter.IDocGenerateFilter` |
| targetDir | 否 | 生成目标文件目录 | 无 | 自定义指定文档生成的文件夹 |

## isAllInOne

简单的文档，建议直接生成在一个文件。

如果较为复杂，则可以设为 false，则会按照

## generates 相关问题

默认的命令行文档，主要用于演示和信息查看，不具有实际意义。

建议引入 `idoc-ftl` 模块，使用 `MarkdownDocGenerator` 生成器。

可以同时指定多个。

可引入 `idoc-api` 自行定义。

## generateFilters 建议

实际的文档，主要关心定义的方法。

我们可以针对 DocClass 的包名，过滤只生成 Service 方法文档。

如果是在以前的基础上，则可以加上 `@since` `@version` 等信息的过滤。

可以同时指定多个。

可引入 `idoc-api` 自行定义。

# 自定义 Filter 

可以参考当前项目的 `idoc-test` 模块。 

整体配置如下:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.github.houbb</groupId>
            <artifactId>idoc-core</artifactId>
            <version>0.3.0</version>
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

## 指定文档生成器

指定使用 Markdown 文档生成器，可以同时指定多个。

```xml
<generates>
    <generate>com.github.houbb.idoc.ftl.api.generator.MarkdownDocGenerator</generate>
</generates>
```

### 引入包

MarkdownDocGenerator 在 `idoc-ftl` 模块中，需要引入对应的依赖。

当然 `idoc-core` 默认依赖 `idoc-ftl`。

## 指定文件生成类的过滤器

如果不定义自己的类生成过滤器，则会生成所有的类信息。

一般使用中我们只关心 service 方法，所以添加了类的过滤实现。

实现如下：

### 引入 idoc-api 包

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>idoc-api</artifactId>
    <version>${project.version}</version>
</dependency>
```

### 实现 IDocGenerateFilter

```java
package com.github.houbb.idoc.test.filter;

import com.github.houbb.idoc.api.core.filter.IDocGenerateFilter;
import com.github.houbb.idoc.api.model.metadata.DocClass;

/**
 * 自定义生成过滤器
 * @author binbin.hou
 * @since 0.0.1
 */
public class MyGenerateFilter implements IDocGenerateFilter {

    @Override
    public boolean include(DocClass docClass) {
        if("QueryUserService".equalsIgnoreCase(docClass.getName())) {
            return true;
        }
        return false;
    }

}
```

### 插件中配置使用

```xml
<generateFilters>
    <generateFilter>com.github.houbb.idoc.test.filter.MyGenerateFilter</generateFilter>
</generateFilters>
```

注意，也需要将你定义这个过滤器的 jar 添加依赖，否则无法找到对应的类信息。

```xml
<dependencies>
    <dependency>
        <groupId>com.github.houbb</groupId>
        <artifactId>idoc-test</artifactId>
        <version>${project.version}</version>
    </dependency>
</dependencies>
```


# 类代码信息

## User 信息

```java
/**
 * 用户信息
 * @author binbin.hou
 * @since 0.0.1
 */
public class User {

    /**
     * 名称
     * @require 是
     * @remark 中文名称，请认真填写
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 地址
     */
    private List<Address> addressList;

    /**
     * 伴侣
     */
    private User mate;
    
    //...
}
```

### i-doc 定义的标签

`@require` 表示当前字段是否必填，作为方法入参时。

`@remark` 表示当前字段的备注信息。

## 方法类信息

- QueryUserService.java

```java
/**
 * 查询用户服务类
 * @author binbin.hou
 * @since 0.0.1
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
mvn com.github.houbb:idoc-core:0.3.0:idoc
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

## 文档生成效果

参见文档:

> [idoc-test-全部文档.md](doc/blog/demo/idoc-test-全部文档.md)

# 字段类型别名支持

可以参考当前项目的 `idoc-test` 模块。

## 为什么需要

有时候页面显示类型，希望更加友好。

所以系统内置了一些别名显示，也同时支持自定义别名。
 
# 类型字段的别名

## 系统内置

系统当前版本提供了常见的别名。

详情见 `com.github.houbb.idoc.core.util.JavaTypeAliasUtil`

| 类型 | 别称 |
|:---|:---|
| java.lang.Float | 浮点型 |
| java.lang.Double | 浮点型 |
| java.util.Date | 日期 |
| java.time.LocalDateTime | 日期时间 |
| java.util.Currency | 货币 |
| float | 浮点型 |
| java.lang.Integer | 整型 |
| long | 长整型 |
| java.math.BigDecimal | 数字 |
| java.lang.Character | 字符 |
| java.lang.Long | 长整型 |
| java.lang.Short | 短整型 |
| java.util.Map | 映射 |
| java.time.LocalTime | 时间 |
| java.lang.Boolean | 布尔值 |
| java.math.BigInteger | 数字 |
| java.lang.String | 字符串 |
| java.lang.Byte | 字节 |
| double | 浮点型 |
| byte | 字节 |
| java.util.Collection | 集合 |
| int | 整型 |
| java.util.List | 列表 |
| boolean | 布尔值 |
| java.time.LocalDate | 日期 |
| char | 字符 |
| short | 短整型 |
| void | 空 |
| array | 数组 |
 
## 自定义的方式

可以通过 typeAlias 指定自定义的字段别称。

```xml
<configuration>
    <generateFilters>
        <generateFilter>com.github.houbb.idoc.test.filter.MyGenerateFilter</generateFilter>
    </generateFilters>
    <isAllInOne>true</isAllInOne>
    <typeAliases>
        <typeAlias>
            <key>java.lang.String</key>
            <value>String自定义说明</value>
        </typeAlias>
    </typeAliases>
</configuration>
```

## 优先级

用户自定义的字段别名优先级高于系统默认。

后面定义的别名会直接覆盖前面的别名。

# 测试代码演示

## 对象定义

```java
/**
 * 别名测试
 * @author binbin.hou
 * @since 0.0.1
 */
public class TypeAliasSimpleBean {

    /**
     * 名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## 测试日志

运行测试日志如下：

```
{"comment":"别名测试","docAnnotationList":[],"docFieldList":[{"comment":"名称","name":"name","type":"java.lang.String","typeAlias":"String自定义说明"}],"docMethodList":[{"docMethodParameterList":[],"docMethodReturn":{"fullName":"java.lang.String","name":"String","packageName":"java.lang"},"docTagList":[],"exceptionList":[],"modifiers":["public"],"name":"getName","seeList":[],"signature":"getName()"},{"docMethodParameterList":[{"docAnnotationList":[],"name":"name","type":"java.lang.String","typeAlias":"String自定义说明"}],"docMethodReturn":{},"docTagList":[],"exceptionList":[],"modifiers":["public"],"name":"setName","seeList":[],"signature":"setName(name)"}],"docTagList":[{"lineNum":5,"name":"author","parameters":["binbin.hou"],"value":"binbin.hou"},{"lineNum":6,"name":"since","parameters":["0.0.1"],"value":"0.0.1"}],"fullName":"com.github.houbb.idoc.test.model.TypeAliasSimpleBean","modifiers":["public"],"name":"TypeAliasSimpleBean","packageName":"com.github.houbb.idoc.test.model"}
```

其中 typeAlias 就是字段类型的别名，我们可以用来更加友好的显示字段信息。

# 其他的思考

## 自定义方式的便利性

自定义的方式采用基于 xml 的方式是比较方便。

但是数量比较多的时候就没有那么方便，本来考虑添加对应的配置属性接口，权衡下还是使用了 xml 配置的方式。

## 是否使用 comment 信息？

如果一个字段，没有指定别名，是否使用 comment 信息做替代？

建议使用，当前版本不做处理。

- 为什么使用

比起冗长的类信息，大部分人更乐于看到解释。

如果是针对同构的系统(都是 java 语言)，则可以理解。

如果是针对异构的系统(比如前台是 php)，则不易于理解。

- 为什么不处理

大部分的接口都是常见字段, 性价比不高。

可能存在字段没有些 comment 的情况，会导致判断的复杂性。

## 如果用户不想使用别名

直接修改模板即可，使用原来的字段 `type` 属性即可。

# 开源地址

> [https://github.com/houbb/idoc](https://github.com/houbb/idoc)

当然，这个项目还有很长的路要走。

如果喜欢，欢迎 fork star~

# ROAD-MAP

- [x] 指定输出路径

- [ ] 支持自定义类排序，方法排序

- [ ] 完善 field 列表信息

- [ ] 支持 version author 等基本信息

- [ ] 列表添加序号信息

- [ ] 更多的文档格式

md
html
pdf
excel
word

- [ ] 版本管理

不同版本之间的对比

- [x] 添加索引文件

当为多个文件时

- [x] 优化参数显示

如果没有，则直接显示无


