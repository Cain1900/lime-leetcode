package com.lime.leetcode.set2800;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/22.
 */
public class NumberOfBeautifulIntegers2827 {

    int kk;
    char[] s;
    int[][][] memo;

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        kk = k;
        return calculate(high) - calculate(low) + isvalid(low);
    }

    private int isvalid(int num) {
        if(num % kk != 0){
            return 0;
        }
        int diff = 0;
        while (num > 0){
            if((num % 10) % 2 == 0){
                diff++;
            }else {
                diff--;
            }
            num /= 10;
        }
        return diff == 0? 1: 0;
    }

    public int calculate(int num){
        s = Integer.toString(num).toCharArray();
        memo = new int[s.length][kk][s.length + s.length];
        for (int i = 0; i < s.length; i++){
            for (int j = 0; j < kk; j++){
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dp(0, 0, 0, true, false);
    }

    private int dp(int i, int remainder, int diff, boolean isLimit, boolean isNum) {
        if(i == s.length){
            return isNum && diff == 0 && remainder == 0? 1: 0;
        }
        if(!isLimit && isNum && memo[i][remainder][diff + s.length] != -1){
            return memo[i][remainder][diff + s.length];
        }
        int res = 0;
        if(!isNum){
            res = dp(i + 1, 0, 0, false, false);
        }
        int up = isLimit? s[i] - '0': 9;
        for (int d = isNum? 0: 1; d <= up; d++){
            res += dp(i + 1, (remainder * 10 + d) % kk, diff + (d % 2 == 0? 1: -1), isLimit && d == up, true);
        }
        if(!isLimit && isNum){
            memo[i][remainder][diff + s.length] = res;
        }
        return res;
    }


    public static void main(String[] args) {
        NumberOfBeautifulIntegers2827 numberOfBeautifulIntegers2827 = new NumberOfBeautifulIntegers2827();
        System.out.println(numberOfBeautifulIntegers2827.numberOfBeautifulIntegers(42, 58, 3));
    }

}
