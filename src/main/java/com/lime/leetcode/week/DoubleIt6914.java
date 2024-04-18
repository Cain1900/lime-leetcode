package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by YuHang on 2023/8/13.
 */
public class DoubleIt6914 {

    public ListNode doubleIt(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode pre = head;
        while (pre != null){
            stack.add(pre);
            pre = pre.next;
        }
        int more = 0;
        if(!stack.isEmpty()){
            ListNode pop = stack.pop();
            int val = pop.val * 2 + more;
            pop.val = val % 10 ;
            more = more / 10;
        }
        if(more > 0){
            return new ListNode(more, head);
        }
        return head;
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

    public static void main(String[] args) {
    }
}
