package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/9/24.
 */
public class MaximumOddBinaryNumber8048 {

    public String maximumOddBinaryNumber(String s) {
        int num1 = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                num1++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num1 - 1; i++){
            stringBuilder.append("1");
        }
        for (int i = num1 - 1; i < s.length() - 1; i++){
            stringBuilder.append("0");
        }
        return stringBuilder.append("1").toString();
    }
}
