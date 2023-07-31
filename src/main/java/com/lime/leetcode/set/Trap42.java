package com.lime.leetcode.set;

import java.util.Stack;

/**
 * Created by YuHang on 2023/7/23.
 */
public class Trap42 {

    public int trap2(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++){
            while (!stack.isEmpty() && height[stack.peek()] < height[i]){
                Integer pop = stack.pop();
                if(stack.isEmpty()){
                    break;
                }
                if(height[stack.peek()] > height[pop]){
                    sum += (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - height[pop]);
                }
            }
            stack.push(i);
        }
        return sum;
    }

    public int trap1(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++){
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            rightMax[height.length - 1 - i] = Math.max(rightMax[height.length - i], height[height.length - 1 - i]);
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++){
            sum += Math.max(Math.min(leftMax[i], rightMax[i]), height[i]) - height[i];
        }
        return sum;
    }

    public int trap(int[] height) {
        int sum = 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int left = 1;
        int right = height.length - 2;
        for (int i = 1; i < height.length - 1; i++){
            if(height[left - 1] < height[right + 1]){
                leftMax = Math.max(leftMax, height[left - 1]);
                if(leftMax > height[left]){
                    sum += leftMax - height[left];
                }
                left++;
            }else {
                rightMax = Math.max(rightMax, height[right + 1]);
                if(rightMax > height[right]){
                    sum += rightMax - height[right];
                }
                right--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        Trap42 trap42 = new Trap42();
        System.out.println(trap42.trap(height));
        System.out.println(trap42.trap1(height));
        System.out.println(trap42.trap2(height));
    }
}
