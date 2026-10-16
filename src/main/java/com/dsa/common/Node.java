package com.dsa.common;

/**
 * Generic singly-linked-list node.
 * Shared across linked list, queue, and stack implementations.
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
        this(data, null);
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}