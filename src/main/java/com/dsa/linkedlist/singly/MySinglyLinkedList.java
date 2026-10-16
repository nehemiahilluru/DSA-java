package com.dsa.linkedlist.singly;

import java.util.NoSuchElementException;
import java.util.Objects;

import com.dsa.common.Node;

/**
 * Custom singly linked list.
 *
 * Features:
 *  - addFirst / addLast / addAtIndex
 *  - removeFirst / removeLast / removeAtIndex
 *  - get / set / contains / indexOf
 *  - size / isEmpty / clear
 *  - reverse (in-place, O(n) time, O(1) space)
 *  - toString for printing
 */
public class MySinglyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MySinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ---------------------------------------------------------------
    // addFirst — O(1)
    // ---------------------------------------------------------------
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data, head);
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    // ---------------------------------------------------------------
    // addLast — O(1) thanks to tail pointer
    // ---------------------------------------------------------------
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // ---------------------------------------------------------------
    // addAtIndex — O(n)
    // ---------------------------------------------------------------
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        if (index == 0) { addFirst(data); return; }
        if (index == size) { addLast(data); return; }

        Node<T> prev = getNode(index - 1);
        Node<T> newNode = new Node<>(data, prev.next);
        prev.next = newNode;
        size++;
    }

    // ---------------------------------------------------------------
    // removeFirst — O(1)
    // ---------------------------------------------------------------
    public T removeFirst() {
        if (head == null) return null;
        T removed = head.data;
        head = head.next;
        size--;
        if (head == null) tail = null;
        return removed;
    }

    // ---------------------------------------------------------------
    // removeLast — O(n) for singly (no prev pointer)
    // ---------------------------------------------------------------
    public T removeLast() {
        if (head == null) return null;
        if (head == tail) {                      // single element
            T removed = head.data;
            head = tail = null;
            size--;
            return removed;
        }
        Node<T> prev = head;
        while (prev.next != tail) {
            prev = prev.next;
        }
        T removed = tail.data;
        prev.next = null;
        tail = prev;
        size--;
        return removed;
    }

    // ---------------------------------------------------------------
    // removeAtIndex — O(n)
    // ---------------------------------------------------------------
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<T> prev = getNode(index - 1);
        Node<T> target = prev.next;
        T removed = target.data;
        prev.next = target.next;
        size--;
        return removed;
    }

    // ---------------------------------------------------------------
    // remove by value — O(n)
    // ---------------------------------------------------------------
    public boolean remove(T data) {
        if (head == null) return false;
        if (Objects.equals(head.data, data)) { removeFirst(); return true; }

        Node<T> prev = head;
        while (prev.next != null) {
            if (Objects.equals(prev.next.data, data)) {
                if (prev.next == tail) tail = prev;
                prev.next = prev.next.next;
                size--;
                return true;
            }
            prev = prev.next;
        }
        return false;
    }

    // ---------------------------------------------------------------
    // getNode / get / set
    // ---------------------------------------------------------------
    private Node<T> getNode(int index) {
        Node<T> temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        return temp;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        return getNode(index).data;
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        getNode(index).data = data;
    }

    // ---------------------------------------------------------------
    // contains / indexOf
    // ---------------------------------------------------------------
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    public int indexOf(T data) {
        Node<T> temp = head;
        int idx = 0;
        while (temp != null) {
            if (Objects.equals(temp.data, data)) return idx;
            temp = temp.next;
            idx++;
        }
        return -1;
    }

    // ---------------------------------------------------------------
    // reverse — in-place, O(n) time, O(1) space
    // ---------------------------------------------------------------
    public void reverse() {
        Node<T> prev = null;
        Node<T> curr = head;
        tail = head;                        // old head becomes new tail
        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }
   
    // ---------------------------------------------------------------
    // Utility
    // ---------------------------------------------------------------
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public T getFirst() { return head == null ? null : head.data; }
    public T getLast()  { return tail == null ? null : tail.data; }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.data);
            if (temp.next != null) sb.append(", ");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

}