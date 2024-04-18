package com.lime.leetcode.set2200;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/11/21.
 */
public class MinDeletion2216 {

    public int minDeletion(int[] nums) {
        int count = 0;
        for (int i = nums.length - 1; i >= 0;){
            if(i - 1 >= 0){
                if(nums[i] == nums[i - 1]){
                    count++;
                    i--;
                }else {
                    i -= 2;
                }
            }else {
                i--;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {418,421,309,442,80,305,166,884,791,353};
        MinDeletion2216 minDeletion2216 = new MinDeletion2216();
        System.out.println(minDeletion2216.findMaximumLength(nums));
    }

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int hMax = 2;
        int pre = 2;
        for(int i = 1; i < hBars.length; i++){
            if(hBars[i - 1] + 1 == hBars[i]){
                pre++;
            }else {
                hMax = Math.max(hMax, pre);
                pre = 2;
            }
        }

        hMax = Math.max(hMax, pre);
        int vMax = 2;
        pre = 2;
        for(int i = 1; i < vBars.length; i++){
            if(vBars[i - 1] + 1 == vBars[i]){
                pre++;
            }else {
                vMax = Math.max(vMax, pre);
                pre = 2;
            }
        }
        vMax = Math.max(vMax, pre);
        int size = Math.min(hMax, vMax);
        return size * size;
    }

    public int minimumCoins(int[] prices) {
        int[] cost = new int[prices.length + 1];
        Arrays.fill(cost, 100000000);
        cost[0] = 0;
        for (int i = 1; i <= prices.length; i++){
            //购买第i个
            int sum = Math.min(cost[i - 1] + prices[i - 1], cost[i] + prices[i - 1]);
            for (int j = i; j <= i + i && j <= prices.length; j++){
                cost[j] = Math.min(cost[j], sum);
            }
        }
        return cost[prices.length];
    }

    public int findMaximumLength(int[] nums) {
        int length = 0;
        int pre = 0;
        int sum = 0;
        int first = 0;
        for (int i = 0; i < nums.length; i++){
            if(nums[i] + sum >= pre){
                //满足非递减
                length++;
                for(int j = first; j <= i; j++){
                    if(nums[i] + sum - nums[j] >= pre + nums[j]){
                        sum -= nums[j];
                        pre += nums[j];
                    }else {
                        break;
                    }
                }
                pre = nums[i] + sum;
                sum = 0;
                first = i + 1;
            }else {
                sum += nums[i];
            }
        }
        return length;
    }
}
