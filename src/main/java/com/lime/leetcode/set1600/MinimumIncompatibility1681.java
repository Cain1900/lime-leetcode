package com.lime.leetcode.set1600;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by YuHang on 2023/6/28.
 */
public class MinimumIncompatibility1681 {

    public int minimumIncompatibility(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++){
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            maxCount = Math.max(maxCount, countMap.get(nums[i]));
        }
        if(maxCount > k){
            return -1;
        }
        Set<Integer>[] numSets = new Set[k];
        for (int i = 0; i < k; i++){
            numSets[i] = new HashSet<>();
        }
        return 0;
    }

   /* public static void main(String[] args) {
        int[] nums = new int[]{1,2,1,4};
        int k = 2;
        MinimumIncompatibility1681 minimumIncompatibility1681 = new MinimumIncompatibility1681();
        System.out.println(minimumIncompatibility1681.minimumIncompatibility(nums, k));
    }*/

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int one = target - nums[i];
            if(map.keySet().contains(one)){
                return new int[]{map.get(one), i};
            }else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        MinimumIncompatibility1681 minimumIncompatibility1681 = new MinimumIncompatibility1681();
        System.out.println(minimumIncompatibility1681.twoSum(nums, target));
    }



}
