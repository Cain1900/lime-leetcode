package com.lime.leetcode.week;

import java.util.Arrays;

/**
 * Created by YuHang on 2024/4/7.
 */
public class Week20240407 {

    public int longestMonotonicSubarray(int[] nums) {
        int max = 0;
        int pre = nums[0];
        int inc = 1;
        int desc = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > pre){
                inc++;
                max = Math.max(max, desc);
                desc = 1;

            }else if(nums[i] < pre){
                desc++;
                max = Math.max(max, inc);
                inc = 1;

            }else {
                max = Math.max(max, Math.max(inc, desc));
                inc = 1;
                desc = 1;
            }
            pre = nums[i];
        }
        max = Math.max(max, Math.max(inc, desc));
        return max;
    }

    public String getSmallestString(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            int i1 = s.charAt(i) - 'a';
            int min = Math.min(i1, 26 - i1);
            if(k >= min){
                stringBuilder.append('a');
                k -= min;
            }else {
                stringBuilder.append('a' + i1 - k);
                k = 0;
            }
            if(k == 0 && i + 1 < s.length()){
                stringBuilder.append(s.substring(i + 1));
                break;
            }
        }
        return stringBuilder.toString();
    }

    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int mid = nums.length / 2;
        if(nums[mid] < k){
            //mid偏小，需要把mid往后的调大
            while (nums[mid] < k){
                sum += (k - nums[mid]);
                mid++;
            }
        }else if(nums[mid] > k){
            //mid偏大，需要把mid往前的调大
            while (nums[mid] > k){
                sum += (k - nums[mid]);
                mid--;
            }

        }
        return sum;
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        return new int[3];
    }
}
