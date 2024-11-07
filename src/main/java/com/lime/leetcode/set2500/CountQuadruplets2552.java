package com.lime.leetcode.set2500;

import java.util.Arrays;

/**
 * 功能描述
 * <p>
 * $
 *
 * @author yuh109
 * @return $
 * @date $ $
 */
public class CountQuadruplets2552 {

    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        long sum = 0;
        for (int j = 0; j < n; j++){
            int more = 0;
            for(int k = n - 1; k > j; --k){
                if(nums[k] > nums[j]){
                    more++;
                }else {
                    sum += (long) pre[nums[k]] * more;
                }
            }
            for (int i = nums[j] + 1; i <= n; i++){
                ++pre[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        CountQuadruplets2552 countQuadruplets2552 = new CountQuadruplets2552();
        int[] nums = {1, 3, 5, 2, 4};
        System.out.println(countQuadruplets2552.countQuadruplets(nums));
    }
}
