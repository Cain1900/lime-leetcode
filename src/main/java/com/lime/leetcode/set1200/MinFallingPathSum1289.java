package com.lime.leetcode.set1200;

/**
 * Created by YuHang on 2023/8/10.
 */
public class MinFallingPathSum1289 {

    public int minFallingPathSum(int[][] grid) {
        int firstMin = 0, secondMin = 0;
        int firstIndex = -1;
        for (int i = 0; i < grid.length; i++){
            int curFirstMin = Integer.MAX_VALUE, curSecondMin = Integer.MAX_VALUE;
            int curFirstIndex = -1;
            for (int j = 0; j < grid[0].length; j++){
                int sum = grid[i][j] + (j == firstIndex? secondMin: firstMin);
                if(sum <  curFirstMin){
                    curSecondMin = curFirstMin;
                    curFirstMin = sum;
                    curFirstIndex = j;
                }else if(sum < curSecondMin){
                    curSecondMin = sum;
                }
            }
            firstMin = curFirstMin;
            secondMin = curSecondMin;
            firstIndex = curFirstIndex;
        }
        return firstMin;
    }
}
