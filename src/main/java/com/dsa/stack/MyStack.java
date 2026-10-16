package com.dsa.stack;

import java.util.NoSuchElementException;
import java.util.Objects;

import com.dsa.common.Node;

/**
 * Custom generic Stack implementation using a singly linked list.
 *
 * Features:
 *  - LIFO (Last-In, First-Out) semantics
 *  - O(1) push, O(1) pop, O(1) peek
 *  - Supports null elements
 *  - Both lenient (null on empty) and strict (throws on empty) APIs
 *
 * @param <T> element type
 */
public class MyStack<T> {

    private Node<T> top;
    private int size;

    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    // ---------------------------------------------------------------
    // push — add to top — O(1)
    // ---------------------------------------------------------------
    public void push(T data) {
        Node<T> newNode = new Node<>(data, top);
        top = newNode;
        size++;
    }

    // ---------------------------------------------------------------
    // pop — remove from top — O(1)
    // Returns null if empty (lenient)
    // ---------------------------------------------------------------
    public T pop() {
        if (top == null) return null;
        T removed = top.data;
        top = top.next;
        size--;
        return removed;
    }

    // ---------------------------------------------------------------
    // popOrThrow — strict variant
    // ---------------------------------------------------------------
    public T popOrThrow() {
        if (top == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        return pop();
    }

    // ---------------------------------------------------------------
    // peek — return top without removing — O(1)
    // ---------------------------------------------------------------
    public T peek() {
        return top == null ? null : top.data;
    }

    // ---------------------------------------------------------------
    // peekOrThrow — strict variant
    // ---------------------------------------------------------------
    public T peekOrThrow() {
        if (top == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        return top.data;
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
        top = null;
        size = 0;
    }

    // ---------------------------------------------------------------
    // contains — O(n)
    // ---------------------------------------------------------------
    public boolean contains(T data) {
        Node<T> temp = top;
        while (temp != null) {
            if (Objects.equals(temp.data, data)) return true;
            temp = temp.next;
        }
        return false;
    }

    // ---------------------------------------------------------------
    // toString — top-to-bottom
    // ---------------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> temp = top;
        while (temp != null) {
            sb.append(temp.data);
            if (temp.next != null) sb.append(", ");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // ---------------------------------------------------------------
    // Demo
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(null);
        stack.push(40);

        System.out.println("Stack         : " + stack);          // top-first
        System.out.println("size          : " + stack.size());
        System.out.println("peek          : " + stack.peek());   // 40
        System.out.println("pop           : " + stack.pop());    // 40
        System.out.println("pop           : " + stack.pop());    // null
        System.out.println("after pops    : " + stack);
        System.out.println("contains 10   : " + stack.contains(10));
        System.out.println("isEmpty       : " + stack.isEmpty());

        stack.clear();
        System.out.println("after clear   : " + stack + " | empty=" + stack.isEmpty());
        System.out.println("pop empty     : " + stack.pop());    // null
    }
}