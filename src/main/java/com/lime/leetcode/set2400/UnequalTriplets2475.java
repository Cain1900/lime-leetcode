package com.lime.leetcode.set2400;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/6/13.
 */
public class UnequalTriplets2475 {

    public int unequalTriplets(int[] nums) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int sum = 0;
        int preSum = 0;
        for (Integer key: map.keySet()){
            Integer value = map.get(key);
            sum += preSum * value * (length - preSum - value);
            preSum += value;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,1,2,4};
        UnequalTriplets2475 unequalTriplets2475 = new UnequalTriplets2475();
        System.out.println(unequalTriplets2475.unequalTriplets(nums));
    }
}