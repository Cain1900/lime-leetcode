package com.lime.leetcode.set2500;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/7/27.
 */
public class DeleteGreatestValue2500 {

    public int deleteGreatestValue(int[][] grid) {
        for (int i = 0; i < grid.length; i++){
            Arrays.sort(grid[i]);
        }
        int sum = 0;
        for (int i = 0; i < grid[0].length; i++){
            int max = grid[0][i];
            for (int j = 1; j < grid.length; j++){
                max = Math.max(max, grid[j][i]);
            }
            sum += max;
        }
        return sum;
    }
}
