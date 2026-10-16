package com.dsa.linkedlist.circular;

public class Main {
	 public static void main(String[] args) {
        MyCircularLinkedList<Integer> c = new MyCircularLinkedList<>();
        c.addLast(10);
        c.addLast(20);
        c.addLast(30);
        c.addFirst(5);                   // 5,10,20,30
        System.out.println("Circular list: " + c);
        System.out.println("size         : " + c.size());
        System.out.println("contains(20) : " + c.contains(20));
        c.remove(20);
        System.out.println("after remove : " + c);
        c.removeLast();
        c.removeFirst();
        System.out.println("final        : " + c + " | empty=" + c.isEmpty());
    }
}
