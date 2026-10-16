package com.dsa.map;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);
        map.put("apple", 99);          // overwrite
        map.put(null, 42);             // null key
        map.put("date", null);         // null value

        System.out.println("Map          : " + map);
        System.out.println("size         : " + map.size());
        System.out.println("get apple    : " + map.get("apple"));    // 99
        System.out.println("get null     : " + map.get(null));       // 42
        System.out.println("containsKey  : " + map.containsKey("banana")); // true
        System.out.println("containsValue: " + map.containsValue(20));      // true

        System.out.println("remove banana: " + map.remove("banana")); // 20
        System.out.println("after remove : " + map);
        System.out.println("size         : " + map.size());

        // Force resize by adding many entries
        for (int i = 0; i < 50; i++) {
            map.put("key" + i, i);
        }
        System.out.println("after resize size: " + map.size());   // 55

        map.clear();
        System.out.println("after clear  : " + map + " | empty=" + map.isEmpty());
    }
}
