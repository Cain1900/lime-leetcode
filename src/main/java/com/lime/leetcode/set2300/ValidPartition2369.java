package com.lime.leetcode.set2300;

/**
 * Created by YuHang on 2024/3/1.
 */
public class ValidPartition2369 {

    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[1] = false;
        if(nums[0] == nums[1]){
            dp[2] = true;
        }else {
            dp[2] = false;
        }
        for (int i = 2; i < n; i++){
            dp[i + 1] = (dp[i - 1] && nums[i] == nums[i - 1]) ||
                    (dp[i - 2] && ((nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2]) || ((nums[i] - nums[i - 1] == 1 && nums[i - 1] - nums[i - 2] == 1))));
        }
        return dp[n];
    }
}
