package com.lime.leetcode.set2800;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/21.
 */
public class CountSteppingNumbers2801 {

    private static final int MOD = (int) 1e9 + 7;

    char[] s;
    int[][] memo;

    public int countSteppingNumbers(String low, String high) {
        if(low.equals("1")){
            return calculate(high);
        }
        return (calculate(high) - calculate(low) + MOD + valid(low)) % MOD;
    }

    public int calculate(String n){
        s = n.toCharArray();
        memo = new int[s.length][12];
        for (int i = 0; i < s.length; i++){
            Arrays.fill(memo[i], -1);
        }
        return dp(0, 11, true, false);
    }

    private int dp(int i, int pre, boolean isLimit, boolean isNum) {
        if(i == s.length){
            return pre > 10? 0: 1;
        }
        if(!isLimit && memo[i][pre] != -1){
            return memo[i][pre];
        }
        int res = 0;
        if(!isNum){
            res = dp(i + 1, 11, false, false) % MOD;
        }
        int up = isLimit? s[i] - '0': 9;
        for (int d = isNum? 0: 1; d <= up ; d++){
            if(pre < 10 && d != pre - 1 && d != pre + 1){
                continue;
            }
            res = (res + dp(i + 1, d, isLimit && d == up, true))% MOD;
        }
        if(!isLimit){
            memo[i][pre] = res;
        }
        return res;
    }

    private int valid(String s) {
        for (int i = 1; i < s.length(); i++)
            if (Math.abs((int) s.charAt(i) - (int) s.charAt(i - 1)) != 1)
                return 0;
        return 1;
    }


    public static void main(String[] args) {
        CountSteppingNumbers2801 countSteppingNumbers2801 = new CountSteppingNumbers2801();
        System.out.println(countSteppingNumbers2801.countSteppingNumbers("1", "11"));
    }

}
