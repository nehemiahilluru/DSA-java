package com.dsa.queue;

import java.util.NoSuchElementException;

import com.dsa.common.Node;

/**
 * Custom generic Queue implementation using a singly linked list.
 *
 * Features:
 *  - FIFO (First-In, First-Out) semantics
 *  - O(1) enqueue, O(1) dequeue, O(1) peek
 *  - Supports null elements
 *  - Standard queue operations: enqueue, dequeue, peek, isEmpty, size, clear
 *  - toString() for easy printing and testing
 *
 * @param <T> element type
 */
public class MyQueue<T> {
	
    private Node<T> front;   // points to first element (dequeue end)
    private Node<T> rear;    // points to last element  (enqueue end)
    private int size;

    // ---------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------
    public MyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // ---------------------------------------------------------------
    // enqueue — add to rear — O(1)
    // ---------------------------------------------------------------
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (front == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // ---------------------------------------------------------------
    // dequeue — remove from front — O(1)
    // Returns null if queue is empty (safe behavior)
    // ---------------------------------------------------------------
    public T dequeue() {
        if (front == null) return null;

        T removed = front.data;
        front = front.next;
        size--;

        if (front == null) {
            rear = null;   // queue became empty
        }
        return removed;
    }

    // ---------------------------------------------------------------
    // dequeueOrThrow — strict variant, throws if empty
    // ---------------------------------------------------------------
    public T dequeueOrThrow() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        return dequeue();
    }

    // ---------------------------------------------------------------
    // peek — return front element without removing — O(1)
    // ---------------------------------------------------------------
    public T peek() {
        if (front == null) return null;
        return front.data;
    }

    // ---------------------------------------------------------------
    // peekOrThrow — strict variant, throws if empty
    // ---------------------------------------------------------------
    public T peekOrThrow() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
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
        front = null;
        rear = null;
        size = 0;
    }

    // ---------------------------------------------------------------
    // contains — O(n)
    // ---------------------------------------------------------------
    public boolean contains(T data) {
        Node<T> temp = front;
        while (temp != null) {
            if (java.util.Objects.equals(temp.data, data)) return true;
            temp = temp.next;
        }
        return false;
    }

    // ---------------------------------------------------------------
    // toString — prints front-to-rear
    // ---------------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> temp = front;
        while (temp != null) {
            sb.append(temp.data);
            if (temp.next != null) sb.append(", ");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

   
}