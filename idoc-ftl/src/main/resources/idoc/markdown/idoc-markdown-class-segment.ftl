<#if class??>
# ${class.name}-${class.comment!""}
${class.remark!""}

<#if class.methods??>
<#list class.methods as method>
## ${method.name}-${method.comment!""}
${method.remark!""}

### 方法入参

| 字段 | 说明 | 字段类型 | 是否必填 | 备注 |
|:---|:---|:---|:---|:----|
<#if method.params??>
<#list method.params as param>
| ${param.name} | ${param.comment!""} | ${param.type!""} | ${param.required!""} | ${param.remark!""} |
</#list>
<#else>
暂无入参
</#if>

### 方法出参

| 字段 | 说明 | 字段类型 | 备注 |
|:---|:---|:---|:---|
<#if method.returns??>
<#list method.returns as return>
| ${return.name} | ${return.comment!""} | ${return.type!""} |  ${return.remark!""} |
</#list>
<#else>
暂无出参
</#if>

</#list>

</#if>
</#if>


