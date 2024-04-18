package com.lime.leetcode.set1600;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by YuHang on 2023/12/22.
 */
public class MinimumMountainRemovals1671 {

    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        Arrays.fill(left, 1);
        for (int i = 1; i < n; i++){
            for(int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }
        int[] right = new int[n];
        Arrays.fill(right, 1);
        for(int i = n - 1; i >= 0; i--){
            for(int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]){
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 1; i < n - 1; i++){
            if(left[i] > 1 && right[i] > 1){
                max = Math.max(max, left[i] + right[i] - 1);
            }
        }
        return n - max;
    }

    public static void main(String[] args) {
        int[] nums = {100,92,89,77,74,66,64,66,64};
        MinimumMountainRemovals1671 minimumMountainRemovals1671 = new MinimumMountainRemovals1671();
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(nums));
    }
}
