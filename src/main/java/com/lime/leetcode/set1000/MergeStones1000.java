package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/4/4.
 */
public class MergeStones1000 {

    public int mergeStones(int[] stones, int k) {
        if(stones.length < k || (k != 2 && stones.length % (k -1) != 1)){
            return -1;
        }else if( stones.length == k){
            return sumStones(stones, 0, stones.length - 1);
        }
        int ans = 0;
        return ans;
    }

    public int sumStones(int[] stones, int start, int end){
        int sum = 0;
        for (int i = start; i <= end; i++){
            sum += stones[i];
        }
        return sum;
    }

    public int minMergeStones(int[] stones, int k){
        int min  = Integer.MAX_VALUE;
        int minValue  = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = 0; i < stones.length; i++){
            int sum = sumStones(stones, i, i + k);
            if(minValue > sum){
                minValue = sum;
                min  = i;
            }
        }
        return ans;
    }
}
