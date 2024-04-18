package com.lime.leetcode.week;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YuHang on 2023/8/6.
 */
public class FinalString6925 {

    public String finalString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i= 0; i< s.length(); i++){
            if(s.charAt(i) == 'i'){
                stringBuilder.reverse();
            }else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public boolean canSplitArray(List<Integer> nums, int m) {
        if(nums.size() <= 2){
            return true;
        }
        for(int i = 1; i < nums.size(); i++){
            if(nums.get(i - 1) + nums.get(i) >= m){
                return true;
            }
        }
        return false;
    }

    
}
