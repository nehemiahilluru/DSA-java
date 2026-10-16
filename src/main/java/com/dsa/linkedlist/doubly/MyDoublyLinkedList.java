package com.dsa.linkedlist.doubly;


import java.util.Objects;

/**
 * Custom doubly linked list.
 *
 * Advantages over singly:
 *  - O(1) removeLast (singly was O(n))
 *  - Bidirectional traversal
 *  - Easy reverse iteration
 */
public class MyDoublyLinkedList<T> {

    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int size;

    // ---------------------------------------------------------------
    // addFirst — O(1)
    // ---------------------------------------------------------------
    public void addFirst(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // ---------------------------------------------------------------
    // addLast — O(1)
    // ---------------------------------------------------------------
    public void addLast(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // ---------------------------------------------------------------
    // addAtIndex — O(n)
    // ---------------------------------------------------------------
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) { addFirst(data); return; }
        if (index == size) { addLast(data); return; }

        DoublyNode<T> curr = getNode(index);
        DoublyNode<T> newNode = new DoublyNode<>(data);
        DoublyNode<T> prev = curr.prev;

        newNode.prev = prev;
        newNode.next = curr;
        prev.next = newNode;
        curr.prev = newNode;
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
        else head.prev = null;
        return removed;
    }

    // ---------------------------------------------------------------
    // removeLast — O(1) — the big win over singly
    // ---------------------------------------------------------------
    public T removeLast() {
        if (tail == null) return null;
        T removed = tail.data;
        tail = tail.prev;
        size--;
        if (tail == null) head = null;
        else tail.next = null;
        return removed;
    }

    // ---------------------------------------------------------------
    // remove by value — O(n)
    // ---------------------------------------------------------------
    public boolean remove(T data) {
        DoublyNode<T> curr = head;
        while (curr != null) {
            if (Objects.equals(curr.data, data)) {
                if (curr.prev == null) removeFirst();
                else if (curr.next == null) removeLast();
                else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                    size--;
                }
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // ---------------------------------------------------------------
    // get / set / getNode
    // ---------------------------------------------------------------
    private DoublyNode<T> getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        DoublyNode<T> curr;
        if (index < size / 2) {              // forward from head
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {                             // backward from tail
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }
        return curr;
    }

    public T get(int index) { return getNode(index).data; }
    public void set(int index, T data) { getNode(index).data = data; }

    // ---------------------------------------------------------------
    // Utility
    // ---------------------------------------------------------------
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public T getFirst() { return head == null ? null : head.data; }
    public T getLast()  { return tail == null ? null : tail.data; }

    public boolean contains(T data) {
        DoublyNode<T> curr = head;
        while (curr != null) {
            if (Objects.equals(curr.data, data)) return true;
            curr = curr.next;
        }
        return false;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void reverse() {
        DoublyNode<T> curr = head;
        DoublyNode<T> temp = null;
        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoublyNode<T> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public String toStringReverse() {
        StringBuilder sb = new StringBuilder("[");
        DoublyNode<T> curr = tail;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.prev != null) sb.append(", ");
            curr = curr.prev;
        }
        sb.append("]");
        return sb.toString();
    }

    
}