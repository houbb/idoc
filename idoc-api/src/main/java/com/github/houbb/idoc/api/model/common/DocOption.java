/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.api.model.common;

import java.io.Serializable;

/**
 * 文档选项
 * @author binbin.hou
 * @since 0.1.0
 */
public class DocOption implements Serializable {

    private static final long serialVersionUID = -8306773716221565448L;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
