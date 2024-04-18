package com.lime.leetcode.week;

/**
 * Created by YuHang on 2024/3/31.
 */
public class Week20240331 {

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0;
        int i = x;
        while (i > 0){
            sum += (i % 10);
            i /= 10;
        }
        return (x % sum == 0)? sum: -1;
    }

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int fullBottles = numBottles;
        int emptyBottles = 0;
        int bottleDrunk = 0;
        while (fullBottles > 0 || emptyBottles >= numExchange){
            //喝完
            bottleDrunk += fullBottles;
            emptyBottles += fullBottles;
            fullBottles = 0;
            //兑换
            while (emptyBottles >= numExchange){
                //兑换
                emptyBottles -= numExchange;
                fullBottles++;
                numExchange++;
            }
        }
        return bottleDrunk;
    }

    public long countAlternatingSubarrays(int[] nums) {
        long sum = 0;
        int start = 0;
        int pre = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == pre){
                long length = i - start;
                sum += ((length + 1) * length / 2);
                start = i;
            }
            pre = nums[i];
        }
        long length = nums.length - start;
        sum += ((length + 1) * length / 2);
        return sum;
    }

    public static void main(String[] args) {
        Week20240331 week20240331 = new Week20240331();
        System.out.println(week20240331.countAlternatingSubarrays(new int[]{0,1,1,1}));
    }
}
