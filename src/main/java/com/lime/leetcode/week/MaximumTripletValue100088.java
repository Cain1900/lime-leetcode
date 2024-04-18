package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/10/1.
 */
public class MaximumTripletValue100088 {

    public long maximumTripletValue(int[] nums) {
        long preMax = nums[0];
        long diffMax = nums[0] - nums[1];
        long ans = 0;
        for(int i = 2; i < nums.length; i++){
            ans = Math.max(ans, diffMax * nums[i]);
            preMax = Math.max(preMax, nums[i - 1]);
            diffMax = Math.max(diffMax, preMax - nums[i]);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,2,1,2,1,2,1,2,1};
        int target = 83;
        MaximumTripletValue100088 maximumTripletValue100088 = new MaximumTripletValue100088();
        System.out.println(maximumTripletValue100088.minSizeSubarray(nums, target));
    }

    public int minSizeSubarray(int[] nums, int target) {
        int start = 0;
        int sum = 0;
        int minCount = Integer.MAX_VALUE;
        for (int end = 0; start <= nums.length; end++){
            sum += nums[end % nums.length];
            while (sum > target && start <= end){
                sum -= nums[start % nums.length];
                start++;
            }
            if(sum == target){
                minCount = Math.min(minCount, end - start + 1);
            }
        }
        return minCount == Integer.MAX_VALUE? -1: minCount;
    }
}
