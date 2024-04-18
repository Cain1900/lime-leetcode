package com.lime.leetcode.set1900;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/9/3.
 */
public class EliminateMaximum1921 {

    public int eliminateMaximum(int[] dist, int[] speed) {
        int[] nums = new int[dist.length];
        for (int i = 0; i < dist.length; i++){
            //nums[i] = Math.max(dist[i] / speed[i] + (dist[i] % speed[i] != 0? 1: 0), 1);
            //nums[i] = dist[i] / speed[i] + (dist[i] % speed[i] != 0? 1: 0);
            nums[i] = (dist[i] - 1) / speed[i] + 1;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++){
            if(nums[i] <= i){
                return i;
            }
        }
        return nums.length;
    }
}
