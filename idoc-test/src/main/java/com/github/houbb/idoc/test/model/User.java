package com.github.houbb.idoc.test.model;

import java.util.Date;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public User getMate() {
        return mate;
    }

    public void setMate(User mate) {
        this.mate = mate;
    }
}
