package com.lime.leetcode.set900;

import java.util.*;

/**
 * Created by YuHang on 2023/7/20.
 */
public class MaxSubarraySumCircular918 {

    public int maxSubarraySumCircular(int[] nums) {
        int max = nums[0];
        Deque<int[]> deque = new LinkedList<>();
        Deque<int[]> deque1 = new ArrayDeque<>();
        Queue<int[]> queue = new LinkedList<>();
        Stack<int[]> stack = new Stack<>();


        deque.offer(new int[]{});
        deque.peek();
        deque.poll();

        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, -3, 5};
        MaxSubarraySumCircular918 maxSubarraySumCircular918 = new MaxSubarraySumCircular918();
        System.out.println(maxSubarraySumCircular918.maxSubarraySumCircular(nums));
    }
}
