package com.dsa.linkedlist.doubly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDoublyLinkedListTest {

    private MyDoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyDoublyLinkedList<>();
    }

    // ---------------------------------------------------------------
    // addFirst / addLast
    // ---------------------------------------------------------------
    @Test
    void addFirstInsertsAtHead() {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        assertEquals("[1, 2, 3]", list.toString());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
    }

    @Test
    void addLastInsertsAtTail() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals("[1, 2, 3]", list.toString());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
    }

    @Test
    void addMixedFirstAndLast() {
        list.addLast(2);
        list.addFirst(1);
        list.addLast(3);
        list.addFirst(0);
        assertEquals("[0, 1, 2, 3]", list.toString());
        assertEquals(4, list.size());
    }

    // ---------------------------------------------------------------
    // addAtIndex
    // ---------------------------------------------------------------
    @Test
    void addAtIndexMiddle() {
        list.addLast(1);
        list.addLast(3);
        list.addAtIndex(2, 1);
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void addAtIndexHead() {
        list.addLast(2);
        list.addAtIndex(1, 0);
        assertEquals("[1, 2]", list.toString());
    }

    @Test
    void addAtIndexTail() {
        list.addLast(1);
        list.addAtIndex(2, 1);
        assertEquals("[1, 2]", list.toString());
    }

    @Test
    void addAtIndexNegativeThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(1, -1));
    }

    @Test
    void addAtIndexBeyondSizeThrows() {
        list.addLast(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(2, 5));
    }

    // ---------------------------------------------------------------
    // removeFirst / removeLast
    // ---------------------------------------------------------------
    @Test
    void removeFirstReturnsHead() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(1, list.removeFirst());
        assertEquals("[2, 3]", list.toString());
    }

    @Test
    void removeLastReturnsTail() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.removeLast());
        assertEquals("[1, 2]", list.toString());
    }

    @Test
    void removeFirstOnSingleElementEmptiesList() {
        list.addLast(99);
        assertEquals(99, list.removeFirst());
        assertTrue(list.isEmpty());
        assertNull(list.getFirst());
        assertNull(list.getLast());
    }

    @Test
    void removeLastOnSingleElementEmptiesList() {
        list.addLast(99);
        assertEquals(99, list.removeLast());
        assertTrue(list.isEmpty());
    }

    @Test
    void removeFirstOnEmptyReturnsNull() {
        assertNull(list.removeFirst());
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
        assertEquals(2, list.size());
    }

    @Test
    void removeTailByValue() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.remove(3));
        assertEquals("[1, 2]", list.toString());
        assertEquals(2, list.getLast());
    }

    @Test
    void removeMiddleByValue() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.remove(2));
        assertEquals("[1, 3]", list.toString());
        assertEquals(2, list.size());
    }

    @Test
    void removeMissingValueReturnsFalse() {
        list.addLast(1);
        list.addLast(2);
        assertFalse(list.remove(99));
        assertEquals(2, list.size());
    }

    @Test
    void removeNullValueWorks() {
        list.addLast(null);
        list.addLast(5);
        assertTrue(list.remove(null));
        assertEquals("[5]", list.toString());
    }

    // ---------------------------------------------------------------
    // get / set
    // ---------------------------------------------------------------
    @Test
    void getAtVariousIndices() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(40, list.get(3));
    }

    @Test
    void getFromFrontHalfUsesForwardTraversal() {
        for (int i = 0; i < 10; i++) list.addLast(i);
        assertEquals(1, list.get(1));
    }

    @Test
    void getFromBackHalfUsesBackwardTraversal() {
        for (int i = 0; i < 10; i++) list.addLast(i);
        assertEquals(8, list.get(8));
    }

    @Test
    void getOutOfBoundsThrows() {
        list.addLast(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void setReplacesValue() {
        list.addLast(1);
        list.addLast(2);
        list.set(1, 99);
        assertEquals(99, list.get(1));
    }

    @Test
    void setOutOfBoundsThrows() {
        list.addLast(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, 99));
    }

    // ---------------------------------------------------------------
    // contains / size / isEmpty
    // ---------------------------------------------------------------
    @Test
    void containsFindsValues() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertTrue(list.contains(2));
        assertFalse(list.contains(99));
    }

    @Test
    void sizeTracksCorrectly() {
        assertEquals(0, list.size());
        list.addLast(1);
        list.addLast(2);
        assertEquals(2, list.size());
        list.removeLast();
        assertEquals(1, list.size());
    }

    @Test
    void isEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    // ---------------------------------------------------------------
    // reverse
    // ---------------------------------------------------------------
    @Test
    void reverseInPlace() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.reverse();
        assertEquals("[3, 2, 1]", list.toString());
        assertEquals(3, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    void reverseAfterReverseIsOriginal() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.reverse();
        list.reverse();
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void reverseMaintainsPrevPointers() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.reverse();
        assertEquals("[1, 2, 3]", list.toStringReverse());
    }

    // ---------------------------------------------------------------
    // toStringReverse
    // ---------------------------------------------------------------
    @Test
    void toStringReverseWorks() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals("[3, 2, 1]", list.toStringReverse());
    }

    // ---------------------------------------------------------------
    // clear
    // ---------------------------------------------------------------
    @Test
    void clearEmptiesList() {
        list.addLast(1);
        list.addLast(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertEquals("[]", list.toString());
    }

    // ---------------------------------------------------------------
    // Integration — mixed operations
    // ---------------------------------------------------------------
    @Test
    void mixedOperationsConsistency() {
        list.addLast(1);
        list.addFirst(0);
        list.addLast(2);
        list.addAtIndex(99, 2);           // [0,1,99,2]
        assertEquals("[0, 1, 99, 2]", list.toString());
        list.remove(99);
        assertEquals("[0, 1, 2]", list.toString());
        list.reverse();
        assertEquals("[2, 1, 0]", list.toString());
        list.removeFirst();
        list.removeLast();
        assertEquals("[1]", list.toString());
    }
}