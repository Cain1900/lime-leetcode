package com.lime.leetcode.set1200;

/**
 * Created by YuHang on 2023/6/26.
 */
public class MaxSumDivThree1262 {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int[] left1 = new int[2];
        left1[0] = Integer.MAX_VALUE;
        left1[1] = Integer.MAX_VALUE;
        int[] left2 = new int[2];
        left2[0] = Integer.MAX_VALUE;
        left2[1] = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(nums[i] % 3  == 1){
                for (int j = 0; j < 2; j++){
                    if(nums[i] < left1[j]){
                        int temp = nums[i];
                        nums[i] = left1[j] ;
                        left1[j] = temp;
                    }
                }
            }else if(nums[i] % 3 == 2){
                for (int j = 0; j < 2; j++){
                    if(nums[i] < left2[j]){
                        int temp = nums[i];
                        nums[i] = left2[j] ;
                        left2[j] = temp;
                    }
                }
            }
        }
        if(sum % 3 == 1){
            if(left2[0] == Integer.MAX_VALUE || left2[1] == Integer.MAX_VALUE){
                sum -= left1[0];
            }else {
                sum -= Math.min(left1[0], left2[0] + left2[1]);
            }
        }else if(sum % 3 == 2){
            if(left1[0] == Integer.MAX_VALUE || left1[1] == Integer.MAX_VALUE){
                sum -= left2[0];
            }else {
                sum -= Math.min(left1[0] + left1[1], left2[0]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,6,5,1,8};
        MaxSumDivThree1262 maxSumDivThree1262 = new MaxSumDivThree1262();
        System.out.println(maxSumDivThree1262.maxSumDivThree(nums));
    }
}
