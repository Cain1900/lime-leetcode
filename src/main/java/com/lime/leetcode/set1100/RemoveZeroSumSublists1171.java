package com.lime.leetcode.set1100;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/6/11.
 */
public class RemoveZeroSumSublists1171 {

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> seen = new HashMap<>();
        int prefixSum = 0;
        ListNode node = dummy;
        while (node != null){
            prefixSum += node.val;
            seen.put(prefixSum, node);
            node = node.next;
        }
        prefixSum = 0;
        node = dummy;
        while (node != null){
            prefixSum += node.val;
            node.next = seen.get(prefixSum).next;
            node = node.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
