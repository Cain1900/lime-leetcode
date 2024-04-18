package com.lime.leetcode.set1700;

/**
 * Created by YuHang on 2023/8/8.
 */
public class MaxAbsoluteSum1749 {

    public int maxAbsoluteSum(int[] nums) {
        int positiveMax = 0, negativeMin = 0;
        int positiveSum = 0, negativeSum = 0;
        for (int num : nums) {
            positiveSum += num;
            positiveMax = Math.max(positiveMax, positiveSum);
            positiveSum = Math.max(0, positiveSum);
            negativeSum += num;
            negativeMin = Math.min(negativeMin, negativeSum);
            negativeSum = Math.min(0, negativeSum);
        }
        return Math.max(positiveMax, -negativeMin);
    }

    public int maxAbsoluteSum2(int[] nums) {
        int max = 0;
        int maxDp = 0, minDp = 0;
        for(int i = 0; i < nums.length; i++){
            maxDp = Math.max(maxDp + nums[i], nums[i]);
            max = Math.max(max, Math.abs(maxDp));
            minDp = Math.min(minDp + nums[i], nums[i]);
            max = Math.max(max, Math.abs(minDp));
        }
        return max;
    }

    public int maxAbsoluteSum1(int[] nums) {
        int[][] dp = new int[nums.length + 1][2];
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            dp[i + 1][0] = Math.max(dp[i][0] + nums[i], nums[i]);
            dp[i + 1][1] = Math.min(dp[i][1] + nums[i], nums[i]);
            max = Math.max(Math.max(max, Math.abs(dp[i + 1][0])), Math.abs(dp[i + 1][1]));
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2,-5,1,-4,3,-2};
        MaxAbsoluteSum1749 maxAbsoluteSum1749 = new MaxAbsoluteSum1749();
        System.out.println(maxAbsoluteSum1749.maxAbsoluteSum(nums));
    }
}
