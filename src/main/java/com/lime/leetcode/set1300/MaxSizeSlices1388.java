package com.lime.leetcode.set1300;

/**
 * Created by YuHang on 2023/8/18.
 */
public class MaxSizeSlices1388 {

    public int max = Integer.MIN_VALUE;

    public int maxSizeSlices1(int[] slices) {
        int[] eat = new int[slices.length];
        dp(slices, eat, 0, slices.length);
        return max;
    }

    private void dp(int[] slices, int[] eat, int num, int count) {
        if(count == 0){
            max = Math.max(max, num);
        }
        for(int i = 0; i < slices.length; i++){
            if(eat[i] == 1){
                continue;
            }
            eat[i] = 1;
            int j = (i + 1) % slices.length;
            for (; ; j = (j + 1) % slices.length){
                if(eat[j] == 0){
                    eat[j] = 1;
                    break;
                }
            }
            int k = (i + slices.length - 1) % slices.length;
            for (; ; k = (k + slices.length - 1) % slices.length){
                if(eat[k] == 0){
                    eat[k] = 1;
                    break;
                }
            }
            dp(slices, eat, num + slices[i], count - 3);
            eat[i] = 0;
            eat[j] = 0;
            eat[k] = 0;
        }
    }

    public int maxSizeSlices(int[] slices) {
        int length = slices.length;
        int n = length / 3;
        int[][] dp = new int[length][n + 1];
        dp[0][0] = 0;
        dp[0][1] = slices[0];
        dp[1][0] = 0;
        dp[1][1] = Math.max(slices[0], slices[1]);
        for (int i = 2; i < length; i++){
            for (int j = 1; j <= n ; j++){
                dp[i][j] = Math.max(dp[i - 1][j], slices[i] + dp[i - 2][j - 1]);
            }
        }
        return dp[length - 1][n];
    }

    public int calculate(int[] slices) {
        int length = slices.length;
        int n = (length + 1) / 3;
        int[][] dp = new int[length][n + 1];
        dp[0][0] = 0;
        dp[0][1] = slices[0];
        dp[1][0] = 0;
        dp[1][1] = Math.max(slices[0], slices[1]);
        for (int i = 2; i < length; i++){
            for (int j = 1; j <= n ; j++){
                dp[i][j] = Math.max(dp[i - 1][j], slices[i] + dp[i - 2][j - 1]);
            }
        }
        return dp[length - 1][n];
    }
}
