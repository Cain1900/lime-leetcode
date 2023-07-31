package com.lime.leetcode.set;

/**
 * Created by YuHang on 2023/4/1.
 */
public class RotateRight61 {

    public ListNode rotateRight1(ListNode head, int k) {
        ListNode[] headList = new ListNode[k + 1];
        ListNode temp = head;
        int i = 0;
        while (temp.next != null){
            headList[i] = temp;
            temp = temp.next;
            i = (i + 1) % (k + 1);
        }
        temp.next = head;
        headList[i] = temp;
        for (int j = 1; j < k; j++){
            int index = i;
            i = (i - 1 + k + 1) % (k + 1);
            headList[i].next = headList[index];
        }
        ListNode ans = headList[i];
        headList[(i - 1 + k + 1 ) % (k + 1)].next = null;
        return ans;
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode temp = head;
        int count = 0;
        while (temp.next != null){
            count++;
            temp = temp.next;
        }
        temp.next = head;
        temp = head;
        if(count < k ){
            count += 1;
            while(count < k){
                count += count;
            }
            count -= 1;
        }
        while(count > k){
            count--;
            temp = temp.next;
        }
        ListNode ans = temp.next;
        temp.next = null;
        return ans;
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
        //ListNode listNode5 = new ListNode(5, null);
        //ListNode listNode4 = new ListNode(4, listNode5);
        //ListNode listNode3 = new ListNode(3, null);
        ListNode listNode2 = new ListNode(2, null);
        ListNode listNode1 = new ListNode(1, listNode2);
        ListNode listNode0 = new ListNode(0, listNode1);
        RotateRight61 rotateRight61 = new RotateRight61();
        ListNode listNode = rotateRight61.rotateRight(listNode0, 4);
        System.out.println(listNode);

    }
}
