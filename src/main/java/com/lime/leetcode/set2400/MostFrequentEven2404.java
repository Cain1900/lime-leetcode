package com.lime.leetcode.set2400;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/4/13.
 */
public class MostFrequentEven2404 {

    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0){
                if(map.containsKey(nums[i])){
                    map.put(nums[i], map.get(nums[i]) + 1);
                }else {
                    map.put(nums[i], 1);
                }
            }
        }
        int ans = Integer.MAX_VALUE, count = 0;
        for (Integer item: map.keySet()){
            if(map.get(item) > count){
                ans = item;
                count = map.get(item);
            }else if(map.get(item) == count && item < ans){
                ans = item;
            }
        }
        return ans == Integer.MAX_VALUE ? -1: ans;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,4,4,1};
        MostFrequentEven2404 mostFrequentEven2404 = new MostFrequentEven2404();
        System.out.println(mostFrequentEven2404.mostFrequentEven(nums));
    }
}
