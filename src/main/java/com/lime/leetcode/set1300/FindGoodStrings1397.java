package com.lime.leetcode.set1300;

import com.lime.leetcode.set2800.NumberOfBeautifulIntegers2827;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/21.
 */
public class FindGoodStrings1397 {

    private static final int MOD = (int) 1e9 + 7;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        return calculate(s2) -calculate(s1) + 1;
    }

    char[] s;
    int[] memo;

    public int calculate(String str){
        s = str.toCharArray();
        memo = new int[s.length];
        Arrays.fill(memo, -1);
        return dp(0, new StringBuilder(),  true);
    }

    private int dp(int i, StringBuilder builder, boolean isLimit) {
        if(i == s.length){
            return 1;
        }
        if(!isLimit && memo[i] != -1){
            return memo[i];
        }
        int res = 0;
        int up = isLimit? s[i] - 'a' + 1: 26;
        for (int d = 1; d <= up; d++){
            char c = (char) ('a' + (d - 1));
            res = (res +  dp(i + 1, builder.append(c), isLimit && d == up)) % MOD;
            builder.deleteCharAt(builder.length() - 1);
        }
        if(!isLimit){
            memo[i] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 2;
        String s1 = "aa";
        String s2 = "da";
        String evil = "b";
        FindGoodStrings1397 findGoodStrings1397  = new FindGoodStrings1397();
        System.out.println(findGoodStrings1397.findGoodStrings(2, s1, s2, evil));
    }
}
