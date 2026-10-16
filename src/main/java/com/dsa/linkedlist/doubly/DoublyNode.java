package com.dsa.linkedlist.doubly;


public class DoublyNode<T> {
    public T data;
    public DoublyNode<T> prev;
    public DoublyNode<T> next;

    public DoublyNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}