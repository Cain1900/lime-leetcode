package com.lime.leetcode.set1900;

/**
 * Created by YuHang on 2023/7/11.
 */
public class MaxAlternatingSum1911 {

    public long maxAlternatingSum(int[] nums) {
        //奇数
        long even = nums[0];
        //偶数
        long odd = 0;
        for (int i = 1; i < nums.length; i++){
            //奇数
            even = Math.max(even, odd + nums[i]);
            //偶数
            odd = Math.max(odd, even - nums[i]);
        }
        return even;
    }


    public long maxAlternatingSum1(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }
        //以index结束的最大值
        long[] dp = new long[nums.length];
        //统计到index的最大值
        long[] dpMax = new long[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dpMax[0] = nums[0];
        dpMax[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length; i++){
            //以i结束的最大值
            //情况1: 在以i-1 结束的基础上，用i替换i - 1
            //情况2: 在统计到i-2 最大值基础上，增加 - nums[i - 1] + nums[i]
            dp[i] = Math.max(dp[i - 1] - nums[i - 1] + nums[i] , dpMax[i - 2] - nums[i - 1] + nums[i]);
            //统计到i结束的最大值
            //情况1: 以i结束的值
            //情况2: 统计到i-1 的最大值
            dpMax[i] = Math.max(dpMax[i - 1], dp[i]);
        }
        return dpMax[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,6,7,8};
        MaxAlternatingSum1911 maxAlternatingSum1911 = new MaxAlternatingSum1911();
        System.out.println(maxAlternatingSum1911.maxAlternatingSum(nums));
    }

}
