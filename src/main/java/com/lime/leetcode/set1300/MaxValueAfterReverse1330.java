package com.lime.leetcode.set1300;

/**
 * Created by YuHang on 2023/5/12.
 */
public class MaxValueAfterReverse1330 {

    public int maxValueAfterReverse(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++){
            sum += Math.abs(nums[i] - nums[i + 1]);
        }
        int max = 0;
        for (int i = 1; i < nums.length - 1; i++){
            //起始位置为0.结束位置是i
            max = Math.max(max, Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));
            //起始位置是i-1.结束位置是 nums.length - 1
            max = Math.max(max, Math.abs(nums[i - 1] - nums[nums.length - 1]) - Math.abs(nums[i - 1] - nums[i]));
        }
        for (int i = 1; i < nums.length - 1; i++){
            for (int j = 1; j < i; j++){
                //起始位置j，结束位置i
                max = Math.max(max, Math.abs(nums[j] - nums[i + 1]) + Math.abs(nums[j - 1] - nums[i]) - Math.abs(nums[j - 1] - nums[j]) - Math.abs(nums[i] - nums[i + 1]));
            }
        }
        return sum + max;
    }

}
