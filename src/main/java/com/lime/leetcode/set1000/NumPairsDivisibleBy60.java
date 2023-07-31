package com.lime.leetcode.set1000;

import java.util.*;

/**
 * Created by YuHang on 2023/5/7.
 */
public class NumPairsDivisibleBy60 {


    public int numPairsDivisibleBy60(int[] time) {
        int[] count = new int[60];
        for (int i = 0; i < time.length; i++){
            count[time[i] % 60]++;
        }
        int ans = 0;
        for (int i = 0; i <= 30; i++){
            if(i == 0 || i == 30){
                if(count[i] % 2 == 0){
                    ans += count[i] / 2 * (count[i] - 1);
                }else {
                    ans += (count[i] - 1) / 2 * count[i];
                }
            }else {
                ans += count[i] * count[60 - i];
            }
        }
        return ans;
    }

    public int numPairsDivisibleBy60_1(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < time.length; i++){
            int remainder = time[i] % 60;
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        int ans = 0;
        for (Integer item: map.keySet()){
            if(item == 0 || item == 30){
                Integer count = map.get(item);
                if(count % 2 == 0){
                    ans += count / 2 * (count - 1);
                }else {
                    ans += (count - 1) / 2 * count;
                }
            }else if(item < 30 && map.containsKey(60 - item)){
                ans += map.get(item) * map.get(60 - item);
            }
        }
        return ans;
    }


}
