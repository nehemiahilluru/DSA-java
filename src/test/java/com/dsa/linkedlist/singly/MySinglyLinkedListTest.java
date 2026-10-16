package com.dsa.linkedlist.singly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySinglyLinkedListTest {

    private MySinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MySinglyLinkedList<>();
    }

    @Test
    void addFirstAndAddLast() {
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);
        assertEquals("[1, 2, 3, 4]", list.toString());
        assertEquals(4, list.size());
    }

    @Test
    void addAtIndexWorks() {
        list.addLast(1);
        list.addLast(3);
        list.addAtIndex(2, 1);
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void addAtIndexOutOfBoundsThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(5, 10));
    }

    @Test
    void removeFirstAndLast() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(1, list.removeFirst());
        assertEquals(3, list.removeLast());
        assertEquals("[2]", list.toString());
    }

    @Test
    void removeAtInvalidIndexThrows() {
        list.addLast(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(5));
    }

    @Test
    void removeByValue() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.remove(2));
        assertEquals("[1, 3]", list.toString());
        assertFalse(list.remove(99));
    }

    @Test
    void getAndSet() {
        list.addLast(1);
        list.addLast(2);
        assertEquals(2, list.get(1));
        list.set(1, 99);
        assertEquals(99, list.get(1));
    }

    @Test
    void containsAndIndexOf() {
        list.addLast(10);
        list.addLast(20);
        assertTrue(list.contains(20));
        assertEquals(1, list.indexOf(20));
        assertEquals(-1, list.indexOf(99));
    }

    @Test
    void reverseInPlace() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.reverse();
        assertEquals("[3, 2, 1]", list.toString());
        assertEquals(1, list.getLast());
        assertEquals(3, list.getFirst());
    }

    @Test
    void sizeDecrementsOnRemove() {
        list.addLast(1);
        list.addLast(2);
        list.removeFirst();
        assertEquals(1, list.size());
    }

    @Test
    void clearEmptiesList() {
        list.addLast(1);
        list.addLast(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.getFirst());
    }

    @Test
    void supportsNullElements() {
        list.addLast(null);
        list.addLast(5);
        assertTrue(list.contains(null));
        assertNull(list.get(0));
    }
}