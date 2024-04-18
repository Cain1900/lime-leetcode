package com.lime.leetcode.set2800;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuHang on 2024/1/19.
 */
public class MinimumTime2809 {

    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        long sum1 = 0, sum2 = 0;
        List<List<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < n; i++){
            sum1 += nums1.get(i);
            sum2 += nums2.get(i);
            nums.add(Arrays.asList(nums1.get(i), nums2.get(i)));
        }
        Collections.sort(nums, (o1, o2) -> o1.get(1) - o2.get(1));
        if(sum1 <= x){
            return 0;
        }
        int count = 1;
        long[][] dp = new long[n + 1][n + 1];
        while (count <= n){
            for (int i = count - 1; i < n; i++){
                List<Integer> item = nums.get(i);
                //dp[i][count]表示前i个元素选出count个处理
                //dp[i  - 1][count]表示前i - 1个元素选出count个处理
                //dp[i  - 1][count - 1]表示前i - 1个元素选出count - 1个处理, 第i个元素为第count个处理
                dp[i + 1][count] = Math.max(dp[i][count], dp[i][count - 1] + item.get(0) + item.get(1) * count);
            }
            long total = sum1 + sum2 * count - dp[n][count];
            if(total <= x){
                return count;
            }
            count++;
        }
        return -1;
    }
}
