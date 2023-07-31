package com.lime.leetcode.set2600;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/7/4.
 */
public class MatrixSum2697 {

    public int matrixSum(int[][] nums) {
        for (int i = 0; i < nums.length; i++){
            Arrays.sort(nums[i]);
        }
        int ans = 0;
        for(int j = 0; j < nums[0].length; j++){
            int max = 0;
            for (int i = 0; i < nums.length; i++){
                max = Math.max(max, nums[i][j]);
            }
            ans += max;
        }
        return ans;
    }


}
