package com.lime.leetcode.set100;

import java.util.Stack;

/**
 * Created by YuHang on 2023/7/31.
 */
public class ReorderList141 {

    public void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l1 = head;
        ListNode l2 = slow.next;
        slow.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        while (head != null){
            ListNode temp = head;
            head = head.next;

            temp.next = pre;
            pre = temp;
        }
        return pre;
    }

    public void mergeList(ListNode l1, ListNode l2){
        while (l1 != null && l2 != null){
            ListNode temp = l1.next;
            l1.next = l2;

            l2 = l2.next;

            l1.next.next = temp;
            l1 = l1.next.next;
        }
    }

    public void reorderList1(ListNode head) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        ListNode res = head;
        while (!stack.peek().equals(res) && !stack.peek().equals(res.next)){
            ListNode nextNode = res.next;
            res.next  = stack.pop();
            res.next.next = nextNode;
            res = res.next.next;
        }
        if(stack.peek().equals(res)){
            res.next = null;
        }
        if(stack.peek().equals(res.next)){
            res.next.next = null;
        }
    }

    public static void main(String[] args) {
        ListNode node8 = new ListNode(8, null);
        ListNode node7 = new ListNode(7, node8);
        ListNode node6 = new ListNode(6, node7);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ReorderList141 reorderList141 = new ReorderList141();
        reorderList141.reorderList(node1);
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
