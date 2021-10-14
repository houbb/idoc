<#if class??>
## ${class.name}-${class.commentFirstLine!"暂无说明"}

${class.remark!class.comment!"暂无说明"}

<#if class.methods??>
<#list class.methods as method>
### ${method.name}-${method.commentFirstLine!"暂无说明"}

${method.remark!method.comment!"暂无说明"}

#### 方法入参

<#if method.params?? && (method.params?size > 0)>
| 字段 | 说明 | 字段类型 | 是否必填 | 备注 |
|:---|:---|:---|:---|:----|
<#list method.params as param>
| ${param.name} | ${param.comment!""} | ${param.typeAlias!""} | ${param.require!""} | ${param.remark!""} |
</#list>
<#else>
暂无入参
</#if>

##### 方法入参详情

<#if method.paramDetails?? && (method.paramDetails?size > 0)>
<#list method.paramDetails?keys as detailName>

- ${detailName} 入参详情

| 字段 | 说明 | 字段类型 | 是否必填 | 备注 |
|:---|:---|:---|:---|:----|
<#list method.paramDetails[detailName] as param>
| ${param.name} | ${param.comment!""} | ${param.typeAlias!""} | ${param.require!""} | ${param.remark!""} |
</#list>
</#list>
<#else>
暂无入参详情
</#if>

#### 方法出参

<#if method.returns?? && (method.returns?size > 0)>
| 字段 | 说明 | 字段类型 | 备注 |
|:---|:---|:---|:---|
<#list method.returns as return>
| ${return.name} | ${return.comment!""} | ${return.typeAlias!""} |  ${return.remark!""} |
</#list>
<#else>
暂无出参
</#if>

##### 方法出参详情

<#if method.returnDetails?? && (method.returnDetails?size > 0)>
<#list method.returnDetails?keys as detailName>

- ${detailName} 出参详情

| 字段 | 说明 | 字段类型 | 备注 |
|:---|:---|:---|:---|
<#list method.returnDetails[detailName] as param>
| ${param.name} | ${param.comment!""} | ${param.typeAlias!""} | ${param.remark!""} |
</#list>
</#list>
<#else>
暂无出参详情
</#if>

</#list>

</#if>
</#if>


