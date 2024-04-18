package com.lime.leetcode.week;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by YuHang on 2023/8/13.
 */
public class MaxSum6939 {

    public int maxSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            int l = maxNum(nums[i]);
            if(map.containsKey(l)){
                int b = map.get(l);
                max = Math.max(max, b + nums[i]);
                if(nums[i] > b){
                    map.put(l, nums[i]);
                }
            }else {
                map.put(l, nums[i]);
            }
        }
        return max;
    }

    public int maxNum(int i){
        int l = i % 10;
        while (i > 0){
            l = Math.max(l, i % 10);
            i /= 10;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = {51,71,17,24,42};
        MaxSum6939 maxSum6939 = new MaxSum6939();
        System.out.println(maxSum6939.maxSum(nums));
    }

}
