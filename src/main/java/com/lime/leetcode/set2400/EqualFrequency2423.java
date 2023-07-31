package com.lime.leetcode.set2400;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/4/29.
 */
public class EqualFrequency2423 {

    public boolean equalFrequency(String word) {
        int[] charCount = new int[26];
        for (char c : word.toCharArray()) {
            charCount[c - 'a']++;
        }
        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int c : charCount) {
            //key: 字符出现的频次
            //value: 这样的字符个数
            if (c > 0) {
                freqCount.put(c, freqCount.getOrDefault(c, 0) + 1);
            }
        }
        if(freqCount.size() == 1){
            //只有一个频次
            Integer freq = freqCount.keySet().iterator().next();
            if(freq == 1 || freqCount.get(freq) == 1 ){
                return true;
            }else {
                return false;
            }
        }
        if(freqCount.size()== 2){
            int[][] key = new int[2][2];
            int i = 0;
            for (Integer item: freqCount.keySet()){
                key[i][0] = item;
                key[i][1] = freqCount.get(item);
                i++;
            }
            if(((key[0][0] - key[1][0] == 1 && key[0][1] == 1) || (key[1][0] - key[0][0] == 1 && key[1][1] == 1))
                    || (key[0][1] == 1 && key[1][1] == 1 && (key[0][0] == 1 || key[1][0] == 1))){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String word = "aaaabbbbccc";
        EqualFrequency2423 equalFrequency2423 = new EqualFrequency2423();
        System.out.println(equalFrequency2423.equalFrequency(word));
    }
}
