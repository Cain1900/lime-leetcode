package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/4/6.
 */
public class BaseNegTwo1017 {

    public String baseNeg2(int n) {
        if(n == 0 || n == 1){
            return String.valueOf(n);
        }
        StringBuilder stringBuilder = new StringBuilder();
        baseNeg2(stringBuilder, n);
        return stringBuilder.reverse().toString();

    }

    public void baseNeg2(StringBuilder stringBuilder, int n){
        if(n == 0 || n == 1){
            stringBuilder.append(n);
            return;
        }
        int remainder = n % (-2);
        if(remainder == -1){
            stringBuilder.append(1);
            baseNeg2(stringBuilder, (n - 1) / -2);
            return;
        }else {
            stringBuilder.append(remainder);
            baseNeg2(stringBuilder, (n - remainder) / -2);
            return;
        }
    }

    public String baseNeg1(int n) {
        if(n == 0 || n == 1){
            return String.valueOf(n);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (n > 0){
            int remainder = n % (-2);
            if(remainder == -1){
                remainder = 1;
            }
            stringBuilder.append(remainder);
            n = (n - remainder) / -2;
        }
        return stringBuilder.reverse().toString();
    }
}
