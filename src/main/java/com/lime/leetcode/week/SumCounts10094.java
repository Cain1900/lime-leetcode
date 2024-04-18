package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/10/28.
 */
public class SumCounts10094 {

    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.size(); i++){
            HashSet<Integer> set = new HashSet<>();
            set.add(nums.get(i));
            map.put(i, set);
            ans++;
        }
        for (int i = 1; i < nums.size(); i++){
            for(int j = 0; j + i < nums.size(); j++){
                Set<Integer> item = map.get(j);
                item.add(nums.get(j + i));
                ans += item.size() * item.size();
            }
        }
        return ans;
    }

    public int sumCounts(int[] nums) {
        long ans = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            HashSet<Integer> set = new HashSet<>();
            set.add(nums[i]);
            map.put(i, set);
            ans++;
        }
        for (int i = 1; i < nums.length; i++){
            for(int j = 0; j + i < nums.length; j++){
                Set<Integer> item = map.get(j);
                item.add(nums[j+ i ]);
                ans = (ans + (long) item.size() * item.size()) % 1000000007;
            }
        }
        return (int) ans;
    }

    public int minChanges(String s) {
        int ans = 0;
        for(int i = 0; i < s.length(); i += 2){
            if(s.charAt(i) != s.charAt(i + 1)){
                ans++;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        //List<Integer> nums = Arrays.asList(1,2,1);
        int[] nums = {1, 2, 1};
        SumCounts10094 sumCounts10094 = new SumCounts10094();
        System.out.println(sumCounts10094.sumCounts(nums));
    }
}
