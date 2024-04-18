package com.lime.leetcode.set2500;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/9/19.
 */
public class MinCapability2560 {

    public int[][] cache;
    public int ans = Integer.MAX_VALUE;

    public int minCapability(int[] nums, int k) {
        cache = new int[nums.length][k + 1];
        for(int i = 0; i < nums.length - (k - 1) * 2; i++){
            ans = Math.min(ans, Math.max(nums[i], dfs(nums, i + 2, nums.length, k - 1)));
        }
        return ans;
    }

    public int dfs(int[] nums, int start, int end, int k){
        if(k == 0){
            return 0;
        }
        if(cache[start][k] != 0){
            return cache[start][k];
        }
        //需要预留(k - 1) * 2个位置, 那么当前可选择范围是 [start, end - (k - 1) * 2)
        int min = Integer.MAX_VALUE;
        for(int i = start; i < end - (k - 1) * 2; i++){
            if(nums[i] < ans && nums[i] < min){
                min = Math.min(min, Math.max(nums[i], dfs(nums, i + 2, end, k - 1)));
            }
        }
        cache[start][k] = min;
        return min;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,5,9};
        int k = 2;
        MinCapability2560 minCapability2560 = new MinCapability2560();
        System.out.println(minCapability2560.minCapability(nums, k));
    }

    public int minCapability1(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        //设置min <= max
        while (min <= max){
            int middle = (max + min) / 2;
            int mount = 0;
            boolean preVisited = false;
            for (int i = 0; i < nums.length; i++){
                if(nums[i] <= middle && !preVisited){
                    mount++;
                    preVisited = true;
                }else {
                    preVisited = false;
                }
            }
            if(mount >= k){
                //
                max = middle - 1;
            }else {
                min = middle + 1;
            }
        }
        return min;
    }

}
