package com.github.houbb.idoc.test.poi;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author binbin.hou
 * date 2019/2/19
 */
public class IdentityHashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new IdentityHashMap<>();
        map.put("A", "A-value-1");
        map.put("A", "A-value-2");
        map.put(new String("B"), "B-value-2");
        map.put(new String("B"), "B-value-2");
        System.out.println(map);
    }

}
