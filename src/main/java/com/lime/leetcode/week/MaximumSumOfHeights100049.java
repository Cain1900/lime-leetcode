package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.List;

/**
 * Created by YuHang on 2023/9/24.
 */
public class MaximumSumOfHeights100049 {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        //dp[i][j]表示山顶位置i，从左到右统计到j位置的sum
        long[][] dp1 = new long[n + 1][4];
        //最大高度
        for(int j = 0;  j < n; j++){
            //目的地j
            dp1[j + 1][0] = Integer.MAX_VALUE;
        }
        dp1[0][0] = Integer.MAX_VALUE;
        for(int j = 0;  j < n; j++){
            dp1[j + 1][2] = maxHeights.get(j);
            //目的地j
            for (int i = 0; i <= j; i++){
                //山顶位置i
                //更新遍历到j时最大高低
                dp1[i + 1][0] = Math.min(dp1[i + 1][0], maxHeights.get(j));
                dp1[i + 1][1] +=  dp1[i + 1][0];
            }
        }
        for(int j = n - 1;  j >= 0; j--){
            //目的地j
            for (int i = n - 1; i >= j; i--){
                //山顶位置i
                if(i == j){
                    dp1[i + 1][3] += dp1[i + 1][1];
                }else {
                    dp1[i + 1][2] = Math.min(dp1[i + 1][2], maxHeights.get(j));
                    dp1[i + 1][3] +=  dp1[i + 1][2];
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++){
            ans = Math.max(ans, dp1[i + 1][3]);
        }
        return ans;
    }


    public static void main(String[] args) {
        testMaximumSumOfHeights();
    }

    private static void testMaximumSumOfHeights() {
        List<Integer> maxHeights = Arrays.asList(3,2,5,5,2,3);
        MaximumSumOfHeights100049 maximumSumOfHeights100049 = new MaximumSumOfHeights100049();
        System.out.println(maximumSumOfHeights100049.maximumSumOfHeights(maxHeights));
    }

}
