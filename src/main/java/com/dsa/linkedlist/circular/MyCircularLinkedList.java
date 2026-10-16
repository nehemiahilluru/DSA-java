package com.dsa.linkedlist.circular;

import java.util.Objects;

import com.dsa.common.Node;

/**
 * Custom circular singly linked list.
 *
 * Features:
 *  - tail.next always points to head (last node loops back)
 *  - O(1) addFirst and addLast
 *  - No null terminator — traversal stops when we loop back
 */
public class MyCircularLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
            newNode.next = newNode;          // points to itself
        } else {
            newNode.next = head;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
            newNode.next = newNode;
        } else {
            newNode.next = head;             // loops to head
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (head == null) return null;
        T removed = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head;                // maintain circular link
        }
        size--;
        return removed;
    }

    public T removeLast() {
        if (head == null) return null;
        T removed = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            Node<T> prev = head;
            while (prev.next != tail) {
                prev = prev.next;
            }
            prev.next = head;
            tail = prev;
        }
        size--;
        return removed;
    }

    public boolean remove(T data) {
        if (head == null) return false;
        if (Objects.equals(head.data, data)) { removeFirst(); return true; }

        Node<T> prev = head;
        Node<T> curr = head.next;
        while (curr != head) {               // stop when we loop back
            if (Objects.equals(curr.data, data)) {
                prev.next = curr.next;
                if (curr == tail) tail = prev;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public boolean contains(T data) {
        if (head == null) return false;
        Node<T> curr = head;
        do {
            if (Objects.equals(curr.data, data)) return true;
            curr = curr.next;
        } while (curr != head);
        return false;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public T getFirst() { return head == null ? null : head.data; }
    public T getLast()  { return tail == null ? null : tail.data; }

    public void clear() {
        if (head != null) tail.next = null;   // break the cycle so GC can clean up
        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<T> curr = head;
        do {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != head) sb.append(", ");
        } while (curr != head);
        sb.append("]");
        return sb.toString();
    }

}