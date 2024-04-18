package com.lime.leetcode.week;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by YuHang on 2023/8/5.
 */
public class InsertGreatestCommonDivisors6940 {

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while(node != null && node.next != null){
            node.next = new ListNode(maxCommonDivisor(node.val, node.next.val), node.next);
            node = node.next.next;
        }
        return head;
    }

    public ListNode insertGreatestCommonDivisors2(ListNode head) {
        return new ListNode(head.val,
                head.next == null ? null
                        : new ListNode(BigInteger.valueOf(head.val).gcd(BigInteger.valueOf(head.next.val)).intValue(),
                        insertGreatestCommonDivisors(head.next)));
    }

    public int maxCommonDivisor(int x, int y){
        while (y!=0)
        {
            int a = x % y;
            x = y;
            y = a;
        }
        return x;
    }



    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
