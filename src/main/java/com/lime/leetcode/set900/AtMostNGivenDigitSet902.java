package com.lime.leetcode.set900;

import jdk.nashorn.internal.runtime.NumberToString;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/21.
 */
public class AtMostNGivenDigitSet902 {

    char[] s;
    int[] items;
    int[] memo;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        s = Integer.toString(n).toCharArray();
        items = new int[digits.length];
        for (int i = 0; i < digits.length; i++){
            items[i] = digits[i].charAt(0) - '0';
        }
        Arrays.sort(items);
        memo = new int[s.length];
        Arrays.fill(memo, -1);
        return dp(0, true, false);
    }

    private int dp(int i, boolean isLimit, boolean isNum) {
        if(i == s.length){
            return isNum? 1: 0;
        }
        if(!isLimit && isNum && memo[i] != -1){
            return memo[i];
        }
        int res = 0;
        if(!isNum){
            res += dp(i + 1, false, false);
        }
        for (int d = 0; d < items.length; d++){
            if(isLimit && s[i] - '0' < items[d]){
                break;
            }
            res += dp(i + 1, isLimit && s[i] - '0' == items[d], true);
        }
        if(!isLimit && isNum){
            memo[i] = res;
        }
        return res;
    }



    public static void main(String[] args) {
        String[] digits = {"1","3","5","7"};
        int n = 100;
        AtMostNGivenDigitSet902 atMostNGivenDigitSet902 = new AtMostNGivenDigitSet902();
        System.out.println(atMostNGivenDigitSet902.atMostNGivenDigitSet(digits, n));
    }
}
