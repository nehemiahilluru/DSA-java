package com.dsa.queue;

public class Main {
    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<>();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(null);       // null allowed
        q.enqueue(40);

        System.out.println("Queue         : " + q);
        System.out.println("size          : " + q.size());
        System.out.println("peek          : " + q.peek());        // 10
        System.out.println("dequeue       : " + q.dequeue());     // 10
        System.out.println("dequeue       : " + q.dequeue());     // 20
        System.out.println("after dequeue : " + q);
        System.out.println("contains 30   : " + q.contains(30));  // true
        System.out.println("isEmpty       : " + q.isEmpty());     // false

        q.clear();
        System.out.println("after clear   : " + q + " | empty=" + q.isEmpty());
        System.out.println("dequeue empty : " + q.dequeue());     // null
    }
}
