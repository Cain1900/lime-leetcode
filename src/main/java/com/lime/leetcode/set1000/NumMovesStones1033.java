package com.lime.leetcode.set1000;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/4/30.
 */
public class NumMovesStones1033 {

    public int[] numMovesStones(int a, int b, int c) {
        int[] num = {a,b,c};
        Arrays.sort(num);
        if(num[2] - num[0] == 2){
            return new int[]{0,0};
        }
        int min = 2;
        if(Math.min(num[1]-num[0], num[2] - num[1]) == 1){
            min = 1;
        }
        int max = num[2] - num[0] - 1;
        return new int[]{min, max};
    }
}
