package com.lime.leetcode.set200;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/21.
 */
public class CountDigitOne233 {

    char[] s;
    int[][] memo;

    public int countDigitOne(int n) {
        s = Integer.toString(n).toCharArray();
        memo = new int[s.length][s.length];
        for (int i = 0; i < s.length; i++){
            Arrays.fill(memo[i], -1);
        }
        return dp(0, 0, true, false);
    }

    private int dp(int i, int mask, boolean isLimit, boolean isNum) {
        if(i == s.length){
            //isNum=true，前面有数字
            return isNum? mask: 0;
        }
        if(!isLimit && isNum && memo[i][mask] != -1){
            return memo[i][mask];
        }
        int res = 0;
        if(!isNum){
            //前面无数字 isNum = false
            //当前位没有设置数字，isLimit是由有数字的高位到低位进行传递的
            res += dp(i + 1, mask, false, false);
        }

        //当前位取值范围
        int up = isLimit? s[i] - '0': 9;
        for (int d = isNum? 0: 1; d <= up; d++){
            res += dp(i + 1, mask + ( d == 1? 1: 0), isLimit & d == up, true);
        }
        if(!isLimit && isNum){
            memo[i][mask] = res;
        }
        return res;
    }
}
