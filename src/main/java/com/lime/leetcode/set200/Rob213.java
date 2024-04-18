package com.lime.leetcode.set200;

/**
 * Created by YuHang on 2023/9/17.
 */
public class Rob213 {

    public int rob(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int max = 0;
        int pre = 0;
        int cur = nums[0];
        for(int i = 1; i < nums.length - 1; i++){
            int temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }
        max = Math.max(max, cur);
        pre = 0;
        cur = nums[1];
        for(int i = 2; i < nums.length; i++){
            int temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }
        max = Math.max(max, cur);
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        Rob213 rob213 = new Rob213();
        System.out.println(rob213.rob(nums));
    }
}
