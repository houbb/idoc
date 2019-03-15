package com.github.houbb.idoc.test.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类型别名测试对象
 * @author binbin.hou
 * @since 0.1.0
 */
public class TypeAliasBean {

    private int age;

    private Integer ageInt;

    private char aChar;

    private Character character;

    private Byte aByte;

    private byte aByte2;

    private long aLong;

    private Long aLong2;

    private short aShort;

    private Short aShort2;

    private Float aFloat;

    private float aFloat2;

    private double aDouble;

    private Double aDouble2;

    private boolean aBoolean;

    private Boolean aBoolean2;

    private List<String> stringList;

    /**
     * TODO: 数组的类型为什么是 String
     */
    private String[] stringArray;

    private Collection<String> stringCollection;

    private Map<String, String> map;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getAgeInt() {
        return ageInt;
    }

    public void setAgeInt(Integer ageInt) {
        this.ageInt = ageInt;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }

    public byte getaByte2() {
        return aByte2;
    }

    public void setaByte2(byte aByte2) {
        this.aByte2 = aByte2;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public Long getaLong2() {
        return aLong2;
    }

    public void setaLong2(Long aLong2) {
        this.aLong2 = aLong2;
    }

    public short getaShort() {
        return aShort;
    }

    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    public Short getaShort2() {
        return aShort2;
    }

    public void setaShort2(Short aShort2) {
        this.aShort2 = aShort2;
    }

    public Float getaFloat() {
        return aFloat;
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

    public float getaFloat2() {
        return aFloat2;
    }

    public void setaFloat2(float aFloat2) {
        this.aFloat2 = aFloat2;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public Double getaDouble2() {
        return aDouble2;
    }

    public void setaDouble2(Double aDouble2) {
        this.aDouble2 = aDouble2;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public Boolean getaBoolean2() {
        return aBoolean2;
    }

    public void setaBoolean2(Boolean aBoolean2) {
        this.aBoolean2 = aBoolean2;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public String[] getStringArray() {
        return stringArray;
    }

    public void setStringArray(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public Collection<String> getStringCollection() {
        return stringCollection;
    }

    public void setStringCollection(Collection<String> stringCollection) {
        this.stringCollection = stringCollection;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
