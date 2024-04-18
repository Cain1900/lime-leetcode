package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2024/1/28.
 */
public class Week20240128 {

    public int countKeyChanges(String s) {
        int pre = s.charAt(0) - 'A';
        int count = 0;
        for(int i = 1; i < s.length(); i++){
            int cur = s.charAt(i) - 'A';
            int diff = Math.abs(cur - pre);
            if(diff != 0 && diff != 32){
                count++;
            }
            pre = cur;
        }
        return count;
    }

    public static void main(String[] args) {
        Week20240128 week20240128 = new Week20240128();
        //testcountKeyChanges(week20240128);
        testMaximumLength(week20240128);
    }

    private static void testMaximumLength(Week20240128 week20240128) {
        int[] nums = {16,9,81,81,64,100,81,1,25,49};
        System.out.println(week20240128.maximumLength(nums));
    }

    private static void testcountKeyChanges(Week20240128 week20240128) {
        String s = "aAbBcC";
        System.out.println(week20240128.countKeyChanges(s));
    }

    public int maximumLength(int[] nums) {
        Arrays.sort(nums);
        Map<Long, Integer[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if(map.containsKey((long)nums[i])){
                Integer[] countAndLength = map.get((long)nums[i]);
                countAndLength[0]++;
                if(countAndLength[0] >= 2 && !map.containsKey((long)nums[i] * nums[i])){
                    map.put((long)nums[i] * nums[i], new Integer[]{0, countAndLength[1] + 1});
                }
                map.put((long)nums[i], countAndLength);
            }else {
                map.put((long)nums[i], new Integer[]{1, 1});
            }
        }
        int max = 1;
        for (Long item: map.keySet()){
            Integer[] countAndLength = map.get(item);
            if(item == 1){
                max = Math.max(max, (countAndLength[0] + 1) / 2 * 2 - 1);
            }else if(countAndLength[0] > 0){
                max = Math.max(max, countAndLength[1] * 2 - 1);
            }
        }
        return max;
    }

    public long flowerGame(int n, int m) {
        long count  = 0;
        for(int i = 1; i <= n; i++){
            if(i % 2 == 0){
                //偶数
                count += ((m + 1) / 2);
            }else {
                //奇数
                count += (m / 2);
            }
        }
        return count;

    }

    int[][] reach;
    int total1;
    int total2;
    int target;

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        reach = new int[jug1Capacity][jug2Capacity];
        total1 = jug1Capacity;
        total2 = jug2Capacity;
        target = targetCapacity;
        return dp(0, 0);
    }

    public boolean dp(int cap1, int cap2){
        if(cap1 == target || cap2 == target || (cap1 + cap2) == target){
            return true;
        }
        return false;
    }


}
