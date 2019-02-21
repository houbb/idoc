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
