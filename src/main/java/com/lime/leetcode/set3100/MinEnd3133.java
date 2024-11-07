package com.lime.leetcode.set3100;

public class MinEnd3133 {

    public long minEnd(int n, int x) {
        int bitCount = 128;
        Long.numberOfLeadingZeros(Long.MAX_VALUE);
        long ans = 0;
        long m = n - 1;
        int j = 0;
        for (int i = 0; i < bitCount; i++){
            if(((x >> i) & 1) == 1){
                //第i位为1,对应结果也为1
                ans |= (1L << i);
            }else {
                //第i位不为1，需要组合选择其中第n个数
                if(((m >> j) & 1) == 1){
                    ans |= (1L << i);
                }
                j++;
            }
        }
        return ans;
    }
}
