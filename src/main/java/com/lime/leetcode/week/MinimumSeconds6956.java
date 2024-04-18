package com.lime.leetcode.week;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuHang on 2023/8/6.
 */
public class MinimumSeconds6956 {

    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, Integer[]> map = new HashMap<>();
        for(int i = 0; i < nums.size(); i++){
            if(!map.containsKey(nums.get(i))){
                map.put(nums.get(i), new Integer[]{i, i, Integer.MIN_VALUE, i});
            }else {
                Integer[] detail = map.get(nums.get(i));
                map.put(nums.get(i), new Integer[]{detail[0], i, Math.max(i - detail[3] - 1, detail[2]), i});
            }
        }
        int minValue = Integer.MAX_VALUE;
        for (Integer item: map.keySet()){
            Integer[] detail = map.get(item);
            minValue = Math.min(minValue, Math.max(detail[0] + nums.size() - detail[3] - 1, detail[2]));
        }
        return minValue/2 +  minValue%2;
    }
}
