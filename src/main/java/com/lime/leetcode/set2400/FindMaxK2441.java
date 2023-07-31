package com.lime.leetcode.set2400;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/5/13.
 */
public class FindMaxK2441 {

    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        int ans = -1;
        for(int i = 0, j = nums.length - 1; i < j && nums[i] < 0 && nums[j] > 0;){
            if(nums[i] + nums[j] == 0){
                return nums[j];
            }else if(nums[i] * (-1) > nums[j]){
                i++;
            }else {
                j--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-1,2,-3, 3};
        FindMaxK2441 findMaxK2441 = new FindMaxK2441();
        System.out.println(findMaxK2441.findMaxK(nums));
    }
}
