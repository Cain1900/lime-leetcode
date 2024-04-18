package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHang on 2023/8/20.
 */
public class MinimumOperations6941 {

    public int minimumOperations(List<Integer> nums) {
        //dp[i][0]:表示第i-1个元素移动到1组次数
        //dp[i][1]:表示第i-1个元素移动到2组次数
        //dp[i][2]:表示第i-1个元素移动到3组次数
        int[][] dp = new int[nums.size() + 1][3];
        for (int i = 0; i < nums.size(); i++){
            //第i个元素移动到1组时，第i-1的元素必须在1组
            dp[i + 1][0] = dp[i][0] + (nums.get(i) == 1? 0: 1);
            //第i个元素移动到1组时，第i-1的元素在1组或者2组
            dp[i + 1][1] = Math.min(dp[i][0], dp[i][1]) + (nums.get(i) == 2? 0: 1);
            //第i个元素移动到1组时，第i-1的元素在任何组都可以
            dp[i + 1][2] = Math.min(dp[i][2], Math.min(dp[i][1], dp[i][0])) + (nums.get(i) == 3? 0: 1);
        }
        return Math.min(dp[nums.size()][2], Math.min(dp[nums.size()][1], dp[nums.size()][0]));
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(2);
        nums.add(1);
        nums.add(3);
        nums.add(2);
        nums.add(1);
        MinimumOperations6941 minimumOperations6941 = new MinimumOperations6941();
        System.out.println(minimumOperations6941.minimumOperations(nums));
    }

}
