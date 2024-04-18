package com.lime.leetcode.set1400;

/**
 * Created by YuHang on 2023/8/17.
 */
public class Ways1444 {

    public int ways(String[] pizza, int k) {
        int rows = pizza.length;
        int cols = pizza[0].length();
        int[][][] dp = new int[k + 1][rows + 1][cols + 1];
        int[][] apples = new int[rows + 1][cols + 1];
        for (int i = rows - 1; i >= 0; i--){
            for (int j = cols - 1; j >= 0; j--){
                apples[i][j] = (pizza[i].charAt(j) == 'A'? 1: 0) + apples[i + 1][j] + apples[i][j + 1] - apples[i + 1][j + 1];
                dp[1][i][j] = apples[i][j] > 0 ? 1 : 0;
            }
        }
        for (int t = 2; t <= k; t++){
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < cols; j++){
                    //需要计算dp[t][i][j]，以t-1作为切入点
                    //水平切，切线位置ii: 切面上方苹果数apples[i][j] - apples[ii][j] > 0, 方案数 dp[t - 1][ii][j]
                    for(int ii = i; ii < rows; ii++){
                        if(apples[i][j] > apples[ii][j]){
                            dp[t][i][j] = (dp[t][i][j] + dp[t - 1][ii][j]) % 1000000007;
                        }
                    }
                    //垂直切，切线位置jj: 切面左方苹果数apples[i][j] - apples[i][jj] > 0, 方案数 dp[t - 1][i][jj]
                    for (int jj = j; jj < cols; jj++){
                        if(apples[i][j] > apples[i][jj]){
                            dp[t][i][j] = (dp[t][i][j] + dp[t - 1][i][jj]) % 1000000007;
                        }
                    }
                }
            }
        }
        return dp[k][0][0];
    }
}
