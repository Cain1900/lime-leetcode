package com.lime.leetcode.set600;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/21.
 */
public class FindIntegers600 {

    char[] s;
    int[][] memo;

    /**
     * 整数的二进制表示中不存在连续的 1 。
     * * * */

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        memo = new int[s.length][2];
        for (int i = 0; i < s.length; i++){
            Arrays.fill(memo[i], -1);
        }
        return dp(0, 0,  true);
    }

    private int dp(int i, int pre, boolean isLimit) {
        if(i == s.length){
            return 1;
        }
        if(!isLimit && memo[i][pre] != -1){
            return memo[i][pre];
        }
        int res = 0;
        int up = isLimit? s[i] - '0': 1;
        res += dp(i + 1, 0, isLimit && up == 0);
        if(pre == 0 && up == 1){
            res += dp(i + 1, 1, isLimit);
        }
        if(!isLimit){
            memo[i][pre] = res;
        }
        return res;
    }
}
