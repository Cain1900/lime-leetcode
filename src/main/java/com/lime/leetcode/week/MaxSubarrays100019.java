package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/9/30.
 */
public class MaxSubarrays100019 {

    public int maxSubarrays(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            pre &= nums[i];
            if(pre == 0){
                ans++;
                pre = Integer.MAX_VALUE;
            }
        }
        return Math.max(ans, 1);
    }

    public int maxSubarrays1(int[] nums) {
        int n = nums.length;
        /**
         * dp[i][j]表示从i到j按位与运算结果
         *
         * dp[i][j] = dp[i][j-1] & nums[j]   i从0遍历到j-1
         * * */
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++){
            dp[i][0] = nums[0];
        }
        for (int i = 1; i < n; i++){
            dp[i][i] = nums[i];
            for (int j = 0; j < i; j++){
                dp[j][i] = dp[j][i - 1] & nums[i];
            }
        }
        if(dp[0][n -1] == 0){
            int ans = 0;
            int pre = 0;
            for(int i = 0; i < n; i++){
                if(dp[pre][i] == dp[0][n -1]){
                    ans++;
                    pre = i + 1;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{5,7,1,3};
        int[] nums = new int[]{22,21,29,22};
        System.out.println(22 & 21);
        System.out.println(22 & 21 & 29);
        System.out.println(22 & 21 & 29 & 22);
        System.out.println(22 & 29);
        System.out.println(21 & 29);
        MaxSubarrays100019 maxSubarrays100019 = new MaxSubarrays100019();
        System.out.println(maxSubarrays100019.maxSubarrays(nums));
    }
}
