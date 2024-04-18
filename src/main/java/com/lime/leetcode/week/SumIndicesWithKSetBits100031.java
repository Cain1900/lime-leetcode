package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by YuHang on 2023/9/17.
 */
public class SumIndicesWithKSetBits100031 {

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int sum  = 0;
        for (int i = 0; i < nums.size(); i++){
            if(number1(i) == k){
                sum += nums.get(i);
            }
        }
        return sum;
    }

    private int number1(Integer num) {
        int count = 0;
        while (num > 0){
            if(num % 2 == 1){
                count++;
            }
            num = (num >> 1);
        }
        return count;
    }

    public static void main(String[] args) {
        testCountWays();
    }

    private static void testSumIndicesWithKSetBits() {
        List<Integer> nums = Arrays.asList(5,10,1,5,2);
        int k = 1;
        SumIndicesWithKSetBits100031 sumIndicesWithKSetBits100031 = new SumIndicesWithKSetBits100031();
        System.out.println(sumIndicesWithKSetBits100031.sumIndicesWithKSetBits(nums, k));
    }

    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        //
        /*int count = (nums.get(0) > 0 ? 1 : 0) + (nums.get(0) < nums.size() ? 1 : 0);
        for (int i = 1; i < nums.size(); i++) {
            count += nums.get(i - 1) < i && nums.get(i) > i ? 1 : 0;
        }*/



        int[][] count = new int[nums.size()][nums.size() + 1];
        if( 0 < nums.get(0)){
            //第0位未选中，总数0
            count[0][0] = 1;
        }
        if( 1 > nums.get(0)){
            //第0位选中，总数1
            count[0][1] = 1;
        }
        for(int i = 1; i < nums.size(); i++){
            for(int j = 0; j <= i; j++){
                //第i位没有被选中
                if(j < nums.get(i)){
                    //前i-1位有j个同学被选中，那么第i位有j个同学被相同次数相同
                    count[i][j] += count[i - 1][j];
                }
                //第i位选中
                if(j + 1 > nums.get(i)){
                    //前i-1位有j个同学被选中，那么第i位有j+1个同学被相同次数相同
                    count[i][j + 1] += count[i - 1][j];
                    if (j == 1){
                        count[i][j] += 1;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= nums.size(); i++){
            sum += count[nums.size() - 1][i];
        }
        return sum;
    }
    private static void testCountWays() {
        List<Integer> nums = Arrays.asList(6,0,3,3,6,7,2,7);
        SumIndicesWithKSetBits100031 sumIndicesWithKSetBits100031 = new SumIndicesWithKSetBits100031();
        System.out.println(sumIndicesWithKSetBits100031.countWays(nums));
        LinkedList<Integer> linkedList = new LinkedList<>();
    }



}
