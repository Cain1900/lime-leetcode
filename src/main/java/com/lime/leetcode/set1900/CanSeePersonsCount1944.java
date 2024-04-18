package com.lime.leetcode.set1900;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by YuHang on 2024/1/5.
 */
public class CanSeePersonsCount1944 {

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        if(n == 1){
            return new int[1];
        }
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(heights[n - 1]);
        for(int i = n - 2; i >= 0; i--){
            while (!stack.isEmpty() && stack.peek() < heights[i]){
                stack.pop();
                ans[i]++;
            }
            if(!stack.isEmpty()){
                ans[i]++;
            }
            stack.push(heights[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] heights = {10,6,8,5,11,9};
        CanSeePersonsCount1944 canSeePersonsCount1944 = new CanSeePersonsCount1944();
        System.out.println(canSeePersonsCount1944.canSeePersonsCount(heights));
    }
}
