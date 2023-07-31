package com.lime.leetcode.set2300;

/**
 * Created by YuHang on 2023/6/6.
 */
public class EqualPairs2352 {

    public int equalPairs(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid.length; j++){
                boolean isSame = true;
                for (int k = 0; k < grid.length; k ++){
                    if(grid[i][k] != grid[k][j]){
                        isSame = false;
                        break;
                    }
                }
                if(isSame){
                    ans++;
                }
            }
        }
        return ans;
    }

}
