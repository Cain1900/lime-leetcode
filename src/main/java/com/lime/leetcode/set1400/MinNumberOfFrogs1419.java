package com.lime.leetcode.set1400;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/5/6.
 */
public class MinNumberOfFrogs1419 {

    public int minNumberOfFrogs(String croakOfFrogs){
        if (croakOfFrogs.length() % 5 != 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('c', 0);
            put('r', 1);
            put('o', 2);
            put('a', 3);
            put('k', 4);
        }};
        int[] nums = new int[5];
        int res = 0, frogNum = 0;
        for(int i = 0; i < croakOfFrogs.length(); i++){
            int index = getIndex(croakOfFrogs.charAt(i));
            if(index == 0){
                nums[0]++;
                frogNum++;
                if(nums[4] > 0){
                    nums[4]--;
                }
            }else if(nums[index - 1] > 0){
                nums[index - 1]--;
                nums[index]++;
                if(index == 4){
                    frogNum--;
                }
            }else {
                return -1;
            }
        }
        if (frogNum > 0) {
            return -1;
        }
        return nums[4];
    }

    public int getIndex(char c){
        if(c == 'c'){
            return 0;
        }else if(c == 'r'){
            return 1;
        }else if(c == 'o'){
            return 2;
        }else if(c == 'a'){
            return 3;
        }else {
            return 4;
        }
    }

    public static void main(String[] args) {
        String croakOfFrogs = "croakcroak";
        MinNumberOfFrogs1419 minNumberOfFrogs1419 = new MinNumberOfFrogs1419();
        System.out.println(minNumberOfFrogs1419.minNumberOfFrogs(croakOfFrogs));
    }
}
