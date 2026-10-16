package com.dsa.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    private MyStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack<>();
    }

    // ---------------------------------------------------------------
    // push / pop — LIFO order
    // ---------------------------------------------------------------
    @Test
    void pushAndPopInLifoOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void popOnEmptyReturnsNull() {
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popOrThrowOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> stack.popOrThrow());
    }

    @Test
    void popOrThrowReturnsTop() {
        stack.push(42);
        assertEquals(42, stack.popOrThrow());
    }

    // ---------------------------------------------------------------
    // peek
    // ---------------------------------------------------------------
    @Test
    void peekDoesNotRemove() {
        stack.push(7);
        assertEquals(7, stack.peek());
        assertEquals(7, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void peekOnEmptyReturnsNull() {
        assertNull(stack.peek());
    }

    @Test
    void peekOrThrowOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> stack.peekOrThrow());
    }

    @Test
    void peekOrThrowReturnsTop() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.peekOrThrow());
    }

    // ---------------------------------------------------------------
    // size / isEmpty
    // ---------------------------------------------------------------
    @Test
    void sizeTracksCorrectly() {
        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void isEmptyOnNewStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void isEmptyAfterPush() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void isEmptyAfterPopAll() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    // ---------------------------------------------------------------
    // clear
    // ---------------------------------------------------------------
    @Test
    void clearEmptiesStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.clear();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertNull(stack.pop());
        assertEquals("[]", stack.toString());
    }

    @Test
    void clearOnEmptyDoesNotThrow() {
        assertDoesNotThrow(() -> stack.clear());
        assertTrue(stack.isEmpty());
    }

    @Test
    void pushAfterClearWorks() {
        stack.push(1);
        stack.push(2);
        stack.clear();
        stack.push(99);
        assertEquals(99, stack.peek());
        assertEquals(1, stack.size());
    }

    // ---------------------------------------------------------------
    // contains
    // ---------------------------------------------------------------
    @Test
    void containsFindsElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertTrue(stack.contains(2));
        assertFalse(stack.contains(99));
    }

    @Test
    void containsOnEmptyReturnsFalse() {
        assertFalse(stack.contains(1));
    }

    @Test
    void containsNullElement() {
        stack.push(null);
        assertTrue(stack.contains(null));
    }

    // ---------------------------------------------------------------
    // Null support
    // ---------------------------------------------------------------
    @Test
    void supportsNullElements() {
        stack.push(null);
        stack.push(5);
        assertEquals(5, stack.pop());
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void nullAtBottomDoesNotBreakSize() {
        stack.push(null);
        stack.push(null);
        stack.push(null);
        assertEquals(3, stack.size());
        assertNull(stack.pop());
        assertNull(stack.pop());
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    // ---------------------------------------------------------------
    // toString
    // ---------------------------------------------------------------
    @Test
    void toStringTopFirst() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // top is 3, so print top-to-bottom
        assertEquals("[3, 2, 1]", stack.toString());
    }

    @Test
    void toStringOnEmpty() {
        assertEquals("[]", stack.toString());
    }

    @Test
    void toStringOnSingleElement() {
        stack.push(42);
        assertEquals("[42]", stack.toString());
    }

    // ---------------------------------------------------------------
    // Integration — mixed operations
    // ---------------------------------------------------------------
    @Test
    void mixedOperationsConsistency() {
        stack.push(1);
        stack.push(2);
        stack.push(3);         // [3, 2, 1] top-first
        assertEquals(3, stack.pop());
        stack.push(4);         // [4, 2, 1]
        assertEquals(4, stack.peek());
        assertEquals(4, stack.pop());
        stack.push(5);         // [5, 2, 1]
        assertEquals("[5, 2, 1]", stack.toString());
        assertEquals(3, stack.size());
    }

    @Test
    void largeNumberOfPushesAndPops() {
        for (int i = 0; i < 1000; i++) stack.push(i);
        assertEquals(1000, stack.size());
        for (int i = 999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    void stackIsReusableAfterFullDrain() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }
}