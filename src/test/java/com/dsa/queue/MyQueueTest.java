package com.dsa.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    private MyQueue<Integer> q;

    @BeforeEach
    void setUp() {
        q = new MyQueue<>();
    }

    @Test
    void enqueueAndDequeueInFifoOrder() {
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
    }

    @Test
    void dequeueOnEmptyReturnsNull() {
        assertNull(q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void dequeueOrThrowOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> q.dequeueOrThrow());
    }

    @Test
    void peekDoesNotRemove() {
        q.enqueue(7);
        assertEquals(7, q.peek());
        assertEquals(7, q.peek());
        assertEquals(1, q.size());
    }

    @Test
    void peekOnEmptyReturnsNull() {
        assertNull(q.peek());
    }

    @Test
    void peekOrThrowOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> q.peekOrThrow());
    }

    @Test
    void sizeTracksCorrectly() {
        assertEquals(0, q.size());
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(2, q.size());
        q.dequeue();
        assertEquals(1, q.size());
    }

    @Test
    void isEmptyOnNewQueue() {
        assertTrue(q.isEmpty());
    }

    @Test
    void clearEmptiesQueue() {
        q.enqueue(1);
        q.enqueue(2);
        q.clear();
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
        assertNull(q.dequeue());
    }

    @Test
    void supportsNullElements() {
        q.enqueue(null);
        q.enqueue(5);
        assertNull(q.dequeue());
        assertEquals(5, q.dequeue());
    }

    @Test
    void containsWorks() {
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertTrue(q.contains(2));
        assertFalse(q.contains(99));
        assertFalse(q.contains(null));   // no null present
    }

    @Test
    void rearResetAfterFullDrain() {
        q.enqueue(1);
        q.enqueue(2);
        q.dequeue();
        q.dequeue();
        assertTrue(q.isEmpty());
        q.enqueue(10);                   // reuse after empty
        q.enqueue(20);
        assertEquals(10, q.dequeue());
        assertEquals(20, q.dequeue());
    }

    @Test
    void toStringFormat() {
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals("[1, 2, 3]", q.toString());
    }
}