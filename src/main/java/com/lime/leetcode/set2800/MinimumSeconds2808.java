package com.lime.leetcode.set2800;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuHang on 2024/1/30.
 */
public class MinimumSeconds2808 {

    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            Integer item = nums.get(i);
            if(map.containsKey(item)){
                int[] longs = map.get(item);
                longs[3] = Math.max(longs[3], i - longs[2] - 1);
                longs[1] = i;
                longs[2] = i;
            }else {
                int[] longs = new int[4];
                longs[0] = i;
                longs[1] = i;
                longs[2] = i;
                longs[3] = 0;
                map.put(item, longs);
            }
        }
        int min = n;
        for (Integer item: map.keySet()){
            int[] longs = map.get(item);
            min = Math.min(min, Math.max(longs[3], n + longs[0] - longs[1] - 1));
        }
        return ((min + 1 ) / 2);
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,1,2);
        MinimumSeconds2808 minimumSeconds2808 = new MinimumSeconds2808();
        System.out.println(minimumSeconds2808.minimumSeconds(nums));
    }
}
