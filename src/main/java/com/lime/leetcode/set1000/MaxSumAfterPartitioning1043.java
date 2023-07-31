package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/4/19.
 */
public class MaxSumAfterPartitioning1043 {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        //max[i][j]表示arr[i]在已知0,1,……,k位置数据时最大值
        int[][] max = new int[k + 1][arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++){
            for (int j = 1; j < k + 1; j++){
                max[j][i] = Math.max(arr[i - 1], max[j - 1][i - 1]);
            }
        }
        int[][] sum = new int[k + 1][arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++){
            //j == 0
            for (int j = 1; j < k + 1; j++){
                sum[1][i] = Math.max(sum[1][i], sum[j][i - 1]);
            }
            sum[1][i] += max[1][i];
            for (int j = 2; j < k + 1 && i - j >= 0; j++){
                sum[j][i] = sum[j - 1][i - 1] + max[j][i];
            }
        }
        int ans = 0;
        for (int j = 1; j < k + 1; j++){
            ans = Math.max(ans, sum[j][arr.length]);
        }
        return ans;
    }

    public int maxSumAfterPartitioning1(int[] arr, int k) {
        int[] max = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++){
            int maxValue = arr[i];
            for (int j = i; j >= 0 && j > i - k; j--){
                maxValue = Math.max(maxValue, arr[j]);
                max[i + 1] = Math.max(max[i + 1], max[j] + maxValue * (i - j + 1));
            }
        }
        return max[arr.length];
    }

    public static void main(String[] args) {
        MaxSumAfterPartitioning1043 maxSumAfterPartitioning1043 = new MaxSumAfterPartitioning1043();
        int[] arr = new int[]{1,15,7,9,2,5,10};
        int k = 3;
        System.out.println(maxSumAfterPartitioning1043.maxSumAfterPartitioning(arr, k));
    }


}
