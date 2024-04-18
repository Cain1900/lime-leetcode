package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuHang on 2023/8/19.
 */
public class CountPairs6954 {

    public int countPairs(List<Integer> nums, int target) {
        if(nums.size() == 1){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < nums.size(); i++){
            for(int j = i + 1; j < nums.size(); j++) {
                if(nums.get(i) + nums.get(j) <  target){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(-6);
        nums.add(2);
        nums.add(5);
        nums.add(-2);
        nums.add(-7);
        nums.add(-1);
        nums.add(3);
        CountPairs6954 countPairs6954 = new CountPairs6954();
        System.out.println(countPairs6954.countPairs(nums, -2));
    }
}
