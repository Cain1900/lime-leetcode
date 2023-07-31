package com.lime.leetcode.set1000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/4/7.
 */
public class NumMovesStonesII1040 {

    public int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        Arrays.sort(stones);
        if (stones[n - 1] - stones[0] + 1 == n) {
            return new int[]{0, 0};
        }
        int ma = Math.max(stones[n - 2] - stones[0] + 1, stones[n - 1] - stones[1] + 1) - (n - 1);
        int mi = n;
        for (int i = 0, j = 0; i < n && j + 1 < n; ++i) {
            while (j + 1 < n && stones[j + 1] - stones[i] <= n - 1) {
                ++j;
            }
            //结束条件1： j + 1 >= n 遍历到上界了
            //结束条件2： 取值临界状态： stones[j] - stones[i] <= n - 1  && stones[j + 1] - stones[i] <= n - 1

            //下标i是起始值，下标j是符合要求的终点， j+1要么越界要么大于 stones[i] + n - 1
            if (j - i + 1 == n - 1 && stones[j] - stones[i] + 1 == n - 1) {
                // j - i + 1 == n - 1  连续的n-1个下标
                // stones[j] - stones[i] + 1 == n - 1  空位数1个
                mi = Math.min(mi, 2);
            } else {
                //连续下标个数： j - i + 1
                mi = Math.min(mi, n - (j - i + 1));
            }
        }
        return new int[]{mi, ma};
    }

    public int[] numMovesStonesII01(int[] stones) {
        Arrays.sort(stones);
        //以最大值为结束数字的起始数字：stones[stones.length - 1] - (stones.length - 1)
        //以最小值为起始数字：stones[0]
        //移动完之后一共出现的情况： stones[stones.length - 1] - (stones.length - 1) - stones[0] + 1
        int countSize = stones[stones.length - 1] - (stones.length - 1) - stones[0] + 1;
        //保存移动次数最小值和最大值
        int[] ans = new int[]{ Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i <  countSize; i++){
            //小于下界个数
            int less = 0;
            for (int j = 0; j < stones.length; j++){
                if(stones[j] < stones[0] + i){
                    less++;
                }else {
                    break;
                }
            }
            //大于上界个数
            int more = 0;
            for (int j = stones.length - 1; j >= 0; j--){
                if(stones[j] > stones[0] + i + stones.length - 1){
                    more++;
                }else {
                    break;
                }
            }
            if(less + more < ans[0]){
                ans[0] = less + more;
            }
            if(less + more > ans[1]){
                ans[1] = less + more;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] stones = new int[]{7,4,9};
        NumMovesStonesII1040 numMovesStonesII1040 = new NumMovesStonesII1040();
        System.out.println(numMovesStonesII1040.numMovesStonesII(stones));
    }
}
