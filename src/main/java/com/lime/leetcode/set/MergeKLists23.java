package com.lime.leetcode.set;

import java.util.PriorityQueue;

/**
 * Created by YuHang on 2023/8/12.
 */
public class MergeKLists23 {

    public ListNode mergeKLists(ListNode[] lists){
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {
        if(start == end){
            return lists[start];
        }
        if(start > end){
            return null;
        }
        int middle = ( start + end ) / 2;
        return merge(merge(lists, start, middle), merge(lists, middle + 1, end));
    }

    private ListNode merge(ListNode a, ListNode b){
        ListNode head = new ListNode();
        ListNode tail = head;
        while (a != null && b != null){
            if(a.val < b.val){
                tail.next = a;
                tail = tail.next;
                a = a.next;
            }else {
                tail.next = b;
                tail = tail.next;
                b = b.next;
            }
        }
        if(a != null){
            tail.next = a;
        }
        if(b != null){
            tail.next = b;
        }
        return head.next;
    }

    public ListNode mergeKList1(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode tail = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null){
                queue.offer(lists[i]);
            }
        }
        while (!queue.isEmpty()){
            ListNode poll = queue.poll();
            if(poll.next != null){
                queue.offer(poll.next);
            }
            tail.next = poll;
            tail = tail.next;
            tail.next = null;
        }
        return head.next;
    }

    private class ListNode{
        int val;
        ListNode next;

        ListNode(){}

        ListNode(int val){
            this.val = val;
        }

        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

}
