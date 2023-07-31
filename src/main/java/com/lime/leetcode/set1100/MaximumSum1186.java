package com.lime.leetcode.set1100;

/**
 * Created by YuHang on 2023/6/27.
 */
public class MaximumSum1186 {

    public int maximumSum(int[] arr) {
        //最长连续子串
        int dp0 = arr[0], dp1 = 0, ans = arr[0];
        for (int i = 1; i < arr.length; i++){
            //最长连续子串:
            dp1 = Math.max(dp1 + arr[i], dp0);
            dp0 = Math.max(arr[i], dp0 + arr[i]);
            ans = Math.max(Math.max(dp0, dp1), ans);
        }
        return ans;
    }

    public int maximumSum2(int[] arr) {
        int dp0 = arr[0], dp1 = 0, res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp1 = Math.max(dp0, dp1 + arr[i]);
            dp0 = Math.max(dp0, 0) + arr[i];
            res = Math.max(res, Math.max(dp0, dp1));
        }
        return res;
    }



    public int maximumSum1(int[] arr) {
        //最长连续子串
        int[] dp = new int[arr.length];
        //删除1个元素的最大总和
        int[] dp1 = new int[arr.length];
        dp[0] = arr[0];
        dp1[0] = arr[0];
        int ans = dp[0];
        for (int i = 1; i < arr.length; i++){
            //最长连续子串:
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            //删除1个元素的最长连续子串:
            // 1、延续之前的删除1个元素结果 dp1[i - 1] + arr[i]
            // 2、删除当前元素 dp[i - 1]
            dp1[i] = Math.max(dp1[i - 1] + arr[i], dp[i - 1]);
            ans = Math.max(Math.max(ans, dp[i]), dp1[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,-1,6,-7,-4,5,-4,7,-6};
        MaximumSum1186 maximumSum1186 = new MaximumSum1186();
        System.out.println(maximumSum1186.maximumSum(arr));
    }
}
