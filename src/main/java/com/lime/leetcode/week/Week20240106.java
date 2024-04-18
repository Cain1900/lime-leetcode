package com.lime.leetcode.week;

import java.util.Arrays;

/**
 * Created by YuHang on 2024/1/6.
 */
public class Week20240106 {
    public int minOperations(int[] nums, int k) {
        long sum = k;
        for(int i = 0; i < nums.length; i++){
            sum ^= nums[i];
        }
        int count = 0;//记录1的个数
        while(sum != 0){
            sum = sum & (sum - 1);
            count++;
        }
        return count;
    }

    public int minimumOperationsToMakeEqual(int x, int y) {
        if(x <= y){
            return y - x;
        }
        int divisor5 = x / 5;
        int divisor11 = x / 11;
        int i1 = 1 + x - divisor11 * 11 + minimumOperationsToMakeEqual(divisor11, y);
        int i2 = 1 + divisor11 * 11 + 11 - x + minimumOperationsToMakeEqual(divisor11 + 1, y);
        int i3 = 1 + x - divisor5 * 5 + minimumOperationsToMakeEqual(divisor5, y);
        int i4 = 1 + divisor5 * 5 + 5 - x + minimumOperationsToMakeEqual(divisor5 + 1, y);
        int i5 = x - y;
        /*System.out.println(new StringBuilder().append("x = ").append(x)
                .append(", y = ").append(y)
                .append(", l11 = ").append(i1)
                .append(", m11 = ").append(i2)
                .append(", l5 = ").append(i3)
                .append(", m5 = ").append(i4).toString());*/
        return Math.min(i1, Math.min(i2, Math.min(i3, Math.min(i4, i5))));
    }

    public static void main(String[] args) {
        //testMinimumOperationsToMakeEqual();
        testNumberOfPowerfulInt();
    }


    private static void testMinimumOperationsToMakeEqual() {
        Week20240106 week20240106 = new Week20240106();
        System.out.println(week20240106.minimumOperationsToMakeEqual(4, 3));
    }

    private static void testNumberOfPowerfulInt() {
        Week20240106 week20240106 = new Week20240106();
        System.out.println(week20240106.numberOfPowerfulInt(20, 1159, 5, "20"));
    }

    char[] upChar;
    long[] memo;
    int minLength;
    int maxLimit;
    boolean countLimit;

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        minLength = s.length();
        maxLimit = limit;
        long up = countNum(finish, s);
        long down = countNum(start - 1, s);
        return up - down;
    }

    public long countNum(long up, String s){
        //有上界
        upChar = Long.toString(up).toCharArray();
        if(upChar.length < minLength){
            return 0;
        }
        if(upChar.length == minLength){
            return up >= Long.valueOf(s)? 1: 0;
        }
        countLimit = (up % Math.pow(10, minLength) >= Long.valueOf(s));
        memo = new long[upChar.length];
        Arrays.fill(memo, -1);
        return dp(0, true, false);
    }

    private long dp(int i, boolean isLimit, boolean isNum) {
        if(i == upChar.length - minLength){
            return isLimit && !countLimit? 0: 1;
        }
        if(!isLimit && isNum && memo[i] != -1){
            return memo[i];
        }
        long res = 0;
        if(!isNum){
            //没有填数字
            res += dp(i + 1, false, false);
        }

        for (int d = isNum? 0: 1; d <= maxLimit ; d++){
            if(isLimit && d > upChar[i] - '0'){
                break;
            }
            res += dp(i + 1, isLimit && upChar[i] - '0' == d, true);
        }
        if(!isLimit && isNum){
            memo[i] = res;
        }
        return res;
    }
}
