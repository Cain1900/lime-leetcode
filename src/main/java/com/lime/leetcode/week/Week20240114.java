package com.lime.leetcode.week;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2024/1/14.
 */
public class Week20240114 {

    public int maxFrequencyElements(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            max = Math.max(max, map.get(nums[i]));
        }
        int ans = 0;
        for (Integer item: map.keySet()){
            if(map.get(item) == max){
                ans += max;
            }
        }
        return ans;
    }
}
