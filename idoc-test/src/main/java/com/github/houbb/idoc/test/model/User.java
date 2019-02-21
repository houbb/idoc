package com.github.houbb.idoc.test.model;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * TODO: 循环依赖的 BUG 处理, User 下面依赖 User 对象
 * @author binbin.hou
 * date 2019/2/12
 */
@Deprecated
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
