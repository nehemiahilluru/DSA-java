package com.dsa.map;

import java.util.Objects;

/**
 * Custom HashMap implementation using separate chaining for collision resolution.
 *
 * Features:
 *  - Generic key-value storage
 *  - Null key support (one null key allowed, like java.util.HashMap)
 *  - Automatic resizing when load factor exceeds 0.75
 *  - O(1) average put/get/remove
 *  - O(1) insertion via head-insertion in chains
 *
 * @param <K> key type
 * @param <V> value type
 */
public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node<K, V>[] buckets;
    private int capacity;   // number of buckets
    private int size;       // number of key-value entries

    // ---------------------------------------------------------------
    // Node — internal linked-list node for chaining
    // ---------------------------------------------------------------
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // ---------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = initialCapacity;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    // ---------------------------------------------------------------
    // Index computation
    // ---------------------------------------------------------------
    private int getIndex(K key) {
        if (key == null) return 0;
        int h = key.hashCode();
        // clear sign bit so result is always non-negative
        return (h & 0x7FFFFFFF) % capacity;
    }

    // ---------------------------------------------------------------
    // put — insert or update
    // ---------------------------------------------------------------
    public void put(K key, V value) {
        if ((double) size / capacity >= LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (buckets[index] == null) {
            buckets[index] = newNode;
            size++;
            return;
        }

        Node<K, V> temp = buckets[index];
        while (temp != null) {
            if (Objects.equals(temp.key, key)) {
                temp.value = value;   // overwrite existing
                return;
            }
            temp = temp.next;
        }

        // insert at head — O(1)
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    // ---------------------------------------------------------------
    // get — return value or null
    // ---------------------------------------------------------------
    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> temp = buckets[index];
        while (temp != null) {
            if (Objects.equals(temp.key, key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    // ---------------------------------------------------------------
    // getOrDefault — return value or a fallback
    // ---------------------------------------------------------------
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    // ---------------------------------------------------------------
    // containsKey
    // ---------------------------------------------------------------
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> temp = buckets[index];
        while (temp != null) {
            if (Objects.equals(temp.key, key)) return true;
            temp = temp.next;
        }
        return false;
    }

    // ---------------------------------------------------------------
    // containsValue
    // ---------------------------------------------------------------
    public boolean containsValue(V value) {
        for (Node<K, V> node : buckets) {
            while (node != null) {
                if (Objects.equals(node.value, value)) return true;
                node = node.next;
            }
        }
        return false;
    }

    // ---------------------------------------------------------------
    // remove — returns removed value or null
    // ---------------------------------------------------------------
    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> temp = buckets[index];
        Node<K, V> prev = null;

        while (temp != null) {
            if (Objects.equals(temp.key, key)) {
                V removed = temp.value;
                if (prev == null) {
                    buckets[index] = temp.next;   // removing head
                } else {
                    prev.next = temp.next;        // bypass node
                }
                size--;
                return removed;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }

    // ---------------------------------------------------------------
    // size / isEmpty / clear
    // ---------------------------------------------------------------
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    // ---------------------------------------------------------------
    // resize — double capacity and rehash all entries
    // ---------------------------------------------------------------
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        capacity = capacity * 2;
        buckets = new Node[capacity];
        size = 0;

        for (Node<K, V> node : oldBuckets) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    // ---------------------------------------------------------------
    // toString — standard Map-like output
    // ---------------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Node<K, V> node : buckets) {
            while (node != null) {
                if (!first) sb.append(", ");
                sb.append(node.key).append("=").append(node.value);
                first = false;
                node = node.next;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // ---------------------------------------------------------------
    // Demo main
    // ---------------------------------------------------------------
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