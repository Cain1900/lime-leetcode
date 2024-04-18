package com.lime.leetcode.set1500;

/**
 * Created by YuHang on 2024/1/1.
 */
public class MinOperationsMaxProfit1599 {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int max = 0;
        int maxIndex = -1;
        int sum = 0;
        int left = customers[0];
        int i = 0;
        while (i < customers.length || left > 0){
            i++;
            if(left >= 4){
                sum += ( 4 * boardingCost - runningCost);
                left -= 4;
            }else {
                sum += ( left * boardingCost - runningCost);
                left = 0;
            }
            if(sum > max){
                maxIndex = i;
                max = sum;
            }
            if(i < customers.length){
                left += customers[i];
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        int[] customers = {8,3};
        int boardingCost = 5;
        int runningCost = 6;
        MinOperationsMaxProfit1599 minOperationsMaxProfit1599 = new MinOperationsMaxProfit1599();
        System.out.println(minOperationsMaxProfit1599.minOperationsMaxProfit(customers, boardingCost, runningCost));
    }
}
