package com.lime.leetcode.week;

import java.util.Arrays;

/**
 * Created by YuHang on 2024/2/3.
 */
public class Week20240203 {

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        int[][] dp = new int[n][2];
        for(int i = 0; i < n; i++){
            sum[i + 1] = sum[i] + nums[i];
            dp[i][0] = nums[i];
            dp[i][1] = i;
        }
        long ans = Long.MIN_VALUE;
        Arrays.sort(dp, (a, b) -> a[0] != b[0]? a[0] - b[0]: a[1] - b[1]);
        int pre = 0;
        int cur = 1;
        while(cur < n){
            int diff = dp[cur][0] - dp[pre][0];
            if(diff > k){
                pre++;
            }else if(diff < k) {
                cur++;
            }else {

                ans = Math.max(ans, sum[Math.max(dp[cur][1], dp[pre][1]) + 1] - sum[Math.min(dp[cur][1], dp[pre][1])]);
                if(cur < n - 1 && dp[cur][0] == dp[cur + 1][0]){
                    if(dp[cur][1] > dp[pre][1]){
                        cur++;
                    }else {
                        pre++;
                    }
                }else if(dp[pre][0] == dp[pre + 1][0]){
                    pre++;
                }
            }
        }
        return ans == Long.MIN_VALUE? 0: ans;
    }
    private static void testMaximumSubarraySum(Week20240203 week20240203) {
        int[] nums = {5,3,3};
        int k = 2;
        System.out.println(week20240203.maximumSubarraySum(nums, k));

    }

    public static void main(String[] args) {
        Week20240203 week20240203 = new Week20240203();
        testMaximumSubarraySum(week20240203);
    }

}
