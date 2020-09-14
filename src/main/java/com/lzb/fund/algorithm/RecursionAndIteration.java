package com.lzb.fund.algorithm;

import com.lzb.struct.ListNode;

public class RecursionAndIteration {

    public static void main(String[] args) {
	// write your code here
        ListNode p5 = new ListNode(5, null);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);
        traverseRe(p1);
        traverseIt(p1);
    }

    // recursion
    private static void traverseRe(ListNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.getVal());
        traverseRe(head.getNext());
    }

    // iteration
    private static void traverseIt(ListNode head) {
        for (ListNode p = head; p != null; p = p.getNext()) {
            System.out.println(p.getVal());
        }
    }
}
