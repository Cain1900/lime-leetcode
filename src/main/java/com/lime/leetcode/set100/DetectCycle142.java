package com.lime.leetcode.set100;

/**
 * Created by YuHang on 2023/7/30.
 */
public class DetectCycle142 {

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true){
            if(fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
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
