package com.dsa.linkedlist.circular;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyCircularLinkedListTest {

    private MyCircularLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyCircularLinkedList<>();
    }

    // ---------------------------------------------------------------
    // addFirst / addLast
    // ---------------------------------------------------------------
    @Test
    void addFirstOnEmpty() {
        list.addFirst(1);
        assertEquals("[1]", list.toString());
        assertEquals(1, list.size());
    }

    @Test
    void addFirstMultiple() {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        assertEquals("[1, 2, 3]", list.toString());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
    }

    @Test
    void addLastOnEmpty() {
        list.addLast(1);
        assertEquals("[1]", list.toString());
    }

    @Test
    void addLastMultiple() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals("[1, 2, 3]", list.toString());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
    }

    // ---------------------------------------------------------------
    // Circular property — tail.next must point to head
    // ---------------------------------------------------------------
    @Test
    void tailNextLoopsBackToHead() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        // Access tail through reflection-free path — use contains to force traversal
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        // If the loop back to head was broken, contains would infinitely loop or miss
    }

    @Test
    void singleElementPointsToItself() {
        list.addLast(42);
        assertTrue(list.contains(42));
        assertEquals(1, list.size());
        assertEquals(42, list.getFirst());
        assertEquals(42, list.getLast());
    }

    // ---------------------------------------------------------------
    // removeFirst
    // ---------------------------------------------------------------
    @Test
    void removeFirstFromMultiple() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(1, list.removeFirst());
        assertEquals("[2, 3]", list.toString());
        assertEquals(2, list.size());
    }

    @Test
    void removeFirstFromSingleEmptiesList() {
        list.addLast(99);
        assertEquals(99, list.removeFirst());
        assertTrue(list.isEmpty());
        assertNull(list.getFirst());
        assertNull(list.getLast());
    }

    @Test
    void removeFirstOnEmptyReturnsNull() {
        assertNull(list.removeFirst());
    }

    @Test
    void removeFirstMaintainsCircularLink() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.removeFirst();                     // [2, 3]
        assertTrue(list.contains(3));           // traversal still works
        assertTrue(list.contains(2));
        assertEquals(2, list.size());
    }

    // ---------------------------------------------------------------
    // removeLast
    // ---------------------------------------------------------------
    @Test
    void removeLastFromMultiple() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.removeLast());
        assertEquals("[1, 2]", list.toString());
        assertEquals(2, list.getLast());
    }

    @Test
    void removeLastFromSingleEmptiesList() {
        list.addLast(99);
        assertEquals(99, list.removeLast());
        assertTrue(list.isEmpty());
    }

    @Test
    void removeLastOnEmptyReturnsNull() {
        assertNull(list.removeLast());
    }

    // ---------------------------------------------------------------
    // remove by value
    // ---------------------------------------------------------------
    @Test
    void removeHeadByValue() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.remove(1));
        assertEquals("[2, 3]", list.toString());
    }

    @Test
    void removeTailByValue() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.remove(3));
        assertEquals("[1, 2]", list.toString());
        assertEquals(2, list.getLast());       // tail must have been updated
    }

    @Test
    void removeMiddleByValue() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.remove(2));
        assertEquals("[1, 3]", list.toString());
    }

    @Test
    void removeOnlyElement() {
        list.addLast(1);
        assertTrue(list.remove(1));
        assertTrue(list.isEmpty());
    }

    @Test
    void removeMissingReturnsFalse() {
        list.addLast(1);
        list.addLast(2);
        assertFalse(list.remove(99));
        assertEquals(2, list.size());
    }

    @Test
    void removeNullValue() {
        list.addLast(null);
        list.addLast(1);
        assertTrue(list.remove(null));
        assertEquals("[1]", list.toString());
    }

    // ---------------------------------------------------------------
    // contains
    // ---------------------------------------------------------------
    @Test
    void containsFindsAllElements() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertFalse(list.contains(99));
    }

    @Test
    void containsOnEmptyReturnsFalse() {
        assertFalse(list.contains(1));
    }

    // ---------------------------------------------------------------
    // size / isEmpty
    // ---------------------------------------------------------------
    @Test
    void sizeIncrementsAndDecrements() {
        list.addLast(1);
        list.addLast(2);
        assertEquals(2, list.size());
        list.removeFirst();
        assertEquals(1, list.size());
    }

    @Test
    void isEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmptyAfterAllRemoved() {
        list.addLast(1);
        list.removeFirst();
        assertTrue(list.isEmpty());
    }

    // ---------------------------------------------------------------
    // clear
    // ---------------------------------------------------------------
    @Test
    void clearEmptiesList() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
    }

    @Test
    void clearOnEmptyDoesNotThrow() {
        assertDoesNotThrow(() -> list.clear());
        assertTrue(list.isEmpty());
    }

    @Test
    void addingAfterClearWorks() {
        list.addLast(1);
        list.addLast(2);
        list.clear();
        list.addLast(10);
        list.addLast(20);
        assertEquals("[10, 20]", list.toString());
        assertEquals(2, list.size());
    }

    // ---------------------------------------------------------------
    // toString — must not infinite-loop
    // ---------------------------------------------------------------
    @Test
    void toStringOnEmpty() {
        assertEquals("[]", list.toString());
    }

    @Test
    void toStringOnSingleElement() {
        list.addLast(7);
        assertEquals("[7]", list.toString());
    }

    @Test
    void toStringOnMultiple() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals("[1, 2, 3]", list.toString());
    }

    // ---------------------------------------------------------------
    // Integration — heavy mixed operations
    // ---------------------------------------------------------------
    @Test
    void mixedOperationsConsistency() {
        list.addLast(1);
        list.addLast(2);
        list.addFirst(0);                  // [0, 1, 2]
        assertEquals("[0, 1, 2]", list.toString());

        list.remove(1);                    // [0, 2]
        assertEquals("[0, 2]", list.toString());

        list.addLast(3);                   // [0, 2, 3]
        list.addFirst(-1);                 // [-1, 0, 2, 3]
        assertEquals("[-1, 0, 2, 3]", list.toString());

        list.removeLast();                 // [-1, 0, 2]
        assertEquals("[-1, 0, 2]", list.toString());
        assertEquals(2, list.getLast());

        list.removeFirst();                // [0, 2]
        assertEquals("[0, 2]", list.toString());
        assertEquals(0, list.getFirst());
        assertEquals(2, list.getLast());
    }

    @Test
    void manyOperationsDoNotBreakCircularLink() {
        for (int i = 0; i < 20; i++) list.addLast(i);
        for (int i = 0; i < 10; i++) list.removeFirst();
        for (int i = 0; i < 5; i++) list.addLast(100 + i);
        // If circular link is broken, contains() would loop forever or miss
        assertTrue(list.contains(19));
        assertTrue(list.contains(104));
        assertFalse(list.contains(0));    // removed earlier
        assertEquals(15, list.size());
    }
}