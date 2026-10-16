package com.dsa.map;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private MyHashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    void putAndGet() {
        map.put("a", 1);
        map.put("b", 2);
        assertEquals(1, map.get("a"));
        assertEquals(2, map.get("b"));
        assertNull(map.get("missing"));
    }

    @Test
    void putOverwritesExistingKey() {
        map.put("a", 1);
        map.put("a", 100);
        assertEquals(100, map.get("a"));
        assertEquals(1, map.size());
    }

    @Test
    void supportsNullKey() {
        map.put(null, 42);
        assertEquals(42, map.get(null));
        assertTrue(map.containsKey(null));
    }

    @Test
    void supportsNullValue() {
        map.put("x", null);
        assertTrue(map.containsKey("x"));
        assertNull(map.get("x"));
    }

    @Test
    void removeExistingKey() {
        map.put("a", 1);
        map.put("b", 2);
        assertEquals(1, map.remove("a"));
        assertNull(map.get("a"));
        assertEquals(1, map.size());
    }

    @Test
    void removeMissingKeyReturnsNull() {
        assertNull(map.remove("nope"));
        assertEquals(0, map.size());
    }

    @Test
    void containsKeyAndValue() {
        map.put("a", 1);
        assertTrue(map.containsKey("a"));
        assertTrue(map.containsValue(1));
        assertFalse(map.containsKey("b"));
        assertFalse(map.containsValue(99));
    }

    @Test
    void resizeWorks() {
        for (int i = 0; i < 100; i++) {
            map.put("k" + i, i);
        }
        assertEquals(100, map.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, map.get("k" + i));
        }
    }

    @Test
    void clearEmptiesMap() {
        map.put("a", 1);
        map.put("b", 2);
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("a"));
    }

    @Test
    void getOrDefaultWorks() {
        map.put("a", 1);
        assertEquals(1, map.getOrDefault("a", 99));
        assertEquals(99, map.getOrDefault("b", 99));
    }

    @Test
    void hashCodeMinValueDoesNotThrow() {
        // key whose hashCode is Integer.MIN_VALUE
        Object weirdKey = new Object() {
            @Override public int hashCode() { return Integer.MIN_VALUE; }
            @Override public boolean equals(Object o) { return this == o; }
        };
        assertDoesNotThrow(() -> {
            map.put((String) null, 0); // ensure map is fine
        });
        // Directly test index math via a small hack
        MyHashMap<Object, Integer> m = new MyHashMap<>();
        assertDoesNotThrow(() -> m.put(weirdKey, 1));
        assertEquals(1, m.get(weirdKey));
    }
}