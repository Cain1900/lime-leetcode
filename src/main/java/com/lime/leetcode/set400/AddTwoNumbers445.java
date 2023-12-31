package com.lime.leetcode.set400;

import java.util.Stack;

/**
 * Created by YuHang on 2023/7/3.
 */
public class AddTwoNumbers445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = null;
        Stack<Integer> stack1 = new Stack();
        while (l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        Stack<Integer> stack2 = new Stack();
        while (l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int more = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || more != 0){
            int x = stack1.isEmpty()? 0: stack1.pop();
            int y = stack2.isEmpty()? 0: stack2.pop();
            int sum = x + y + more;
            more = sum/10;
            sum %= 10;
            ans = new ListNode(sum, ans);
        }
        return ans;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
