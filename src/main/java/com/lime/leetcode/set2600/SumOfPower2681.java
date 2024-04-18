package com.lime.leetcode.set2600;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/1.
 */
public class SumOfPower2681 {

    public int sumOfPower(int[] nums){
        Arrays.sort(nums);
        //以index结束的子数组最小元素之和
        int[] dp = new int[nums.length];
        int[] dpSum = new int[nums.length + 1];
        int sum = 0;
        int mod = 1000000007;
        for (int i = 0; i < nums.length; i++){
            dp[i] = (nums[i] + dpSum[i]) % mod;
            dpSum[i + 1] = (dpSum[i] + dp[i]) % mod;
            sum = (int)((sum + (long) nums[i] * nums[i] %mod * dp[i]) % mod);
            if (sum < 0) {
                sum += mod;
            }
        }
        return sum;
    }
}
