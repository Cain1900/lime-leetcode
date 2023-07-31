package com.lime.leetcode.set900;

/**
 * Created by YuHang on 2023/7/13.
 */
public class MinFallingPathSum931 {

    private static int min = Integer.MAX_VALUE;

    public int minFallingPathSum(int[][] matrix) {
        int[][] sum = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            sum[0][i] = matrix[0][i];
        }
        for (int i = 1; i < matrix.length; i++){
            //遍历每一行
            for (int j = 0; j < matrix.length; j++){
            //遍历每一列
                sum[i][j] = matrix[i][j] + sum[i - 1][j];
                if(j > 0){
                    sum[i][j] = Math.min(sum[i][j], matrix[i][j] + sum[i - 1][j - 1]);
                }
                if(j < matrix.length -1){
                    sum[i][j] = Math.min(sum[i][j], matrix[i][j] + sum[i - 1][j + 1]);
                }
            }
        }
        int min = sum[matrix.length - 1][0];
        for (int j = 1; j < matrix.length; j++){
            min = Integer.min(min, sum[matrix.length - 1][j]);
        }
        return min;
    }


    public int minFallingPathSum1(int[][] matrix) {
        for (int n = 0; n < matrix.length; n++){
            dp(matrix, 0, n, matrix[0][n]);
        }
        return min;
    }

    public void dp(int[][] matrix, int m, int n, int sum){
        if(m == matrix.length - 1){
            min = Math.min(min, sum);
            return;
        }
        for (int i = Math.max(0, n - 1); i <= n + 1 && i < matrix.length; i++){
            dp(matrix, m + 1, i, sum + matrix[m + 1][i]);
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{2,1,3},{6,5,4},{7,8,9}};
        int[][] matrix = new int[][]{{-48}};
        MinFallingPathSum931 minFallingPathSum931 = new MinFallingPathSum931();
        System.out.println(minFallingPathSum931.minFallingPathSum(matrix));
    }
}
