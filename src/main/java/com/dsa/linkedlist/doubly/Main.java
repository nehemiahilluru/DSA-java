package com.dsa.linkedlist.doubly;

public class Main {
	
	public static void main(String[] args) {
        MyDoublyLinkedList<Integer> d = new MyDoublyLinkedList<>();
        d.addFirst(20);
        d.addFirst(10);
        d.addLast(30);
        d.addLast(40);
        d.addAtIndex(25, 2);
        System.out.println("Forward : " + d);           // [10, 20, 25, 30, 40]
        System.out.println("Reverse : " + d.toStringReverse()); // [40, 30, 25, 20, 10]
        System.out.println("removeLast: " + d.removeLast());    // 40
        d.reverse();
        System.out.println("reversed: " + d);
    }
}
