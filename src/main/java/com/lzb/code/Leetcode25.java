package com.lzb.code;

import com.lzb.struct.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 */
public class Leetcode25 {
    // return new head(original tail)
    // input original head(new tail)
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        int cnt = 0;
        while (cnt++ < k) {
            tail = tail.next;
        }
        // reverse
        ListNode cur = head.next;
        ListNode pre = new ListNode();
        while (cur != tail) {
            head.next = pre;
            pre = head;
            head = cur;
            cur = cur.next;
        }
        head.next = reverseKGroup(tail.next, k);
        return tail;
    }
}
