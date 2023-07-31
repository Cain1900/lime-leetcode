package com.lime.leetcode.set;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/7/10.
 */
public class ThreeSumClosest16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                if(nums[i] + nums[left] + nums[right] > target){
                    if(Math.abs(nums[i] + nums[left] + nums[right] - target) < min){
                        ans = nums[i] + nums[left] + nums[right];
                        min = Math.abs(nums[i] + nums[left] + nums[right] - target);
                    }
                    right--;
                }else if(nums[i] + nums[left] + nums[right] < target){
                    if(Math.abs(nums[i] + nums[left] + nums[right] - target) < min){
                        ans = nums[i] + nums[left] + nums[right];
                        min = Math.abs(nums[i] + nums[left] + nums[right] - target);
                    }
                    left++;
                }else {
                    min = 0;
                    ans = nums[i] + nums[left] + nums[right];
                    break;
                }
            }
            if(min == 0){
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,2,1,-4};
        int target = 1;
        ThreeSumClosest16 threeSumClosest16 = new ThreeSumClosest16();
        System.out.println(threeSumClosest16.threeSumClosest(nums, target));
    }
}
