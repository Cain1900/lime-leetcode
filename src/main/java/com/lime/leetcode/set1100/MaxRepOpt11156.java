package com.lime.leetcode.set1100;

/**
 * Created by YuHang on 2023/6/3.
 */
public class MaxRepOpt11156 {
    public int maxRepOpt1(String text) {
        int[] count = new int[text.length()];
        count[0] = 1;
        for(int i = 1; i < text.length(); i++){
            if(text.charAt(i) != text.charAt(i - 1)){
                count[i] = 1;
            }else {
                count[i] = count[i - 1] + 1;
            }
        }
        return 1;
    }

}
