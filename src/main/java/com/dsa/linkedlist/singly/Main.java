package com.dsa.linkedlist.singly;

public class Main {

	public static void main(String[] args) {
		MySinglyLinkedList<Integer> list = new MySinglyLinkedList<>();
		list.addFirst(20);
		list.addFirst(10);
		list.addLast(30);
		list.addLast(40);
		list.addAtIndex(25, 2); // 10,20,25,30,40
		System.out.println("List          : " + list);
		System.out.println("size          : " + list.size());
		System.out.println("get(2)        : " + list.get(2)); // 25
		System.out.println("indexOf(30)   : " + list.indexOf(30)); // 3
		System.out.println("remove(25)    : " + list.remove(25)); // true
		System.out.println("after remove  : " + list);
		list.reverse();
		System.out.println("reversed      : " + list);
		System.out.println("removeLast    : " + list.removeLast());
		System.out.println("after removeL : " + list);
		list.clear();
		System.out.println("after clear   : " + list + " | empty=" + list.isEmpty());
	}
}
