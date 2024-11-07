package com.lime.leetcode.set3200;

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
public class ResultsArray3255 {
    public int[] resultsArray(int[] nums, int k) {
        if(k == 1){
            return nums;
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Arrays.fill(ans, -1);
        int count = 1;
        for (int i = 1; i < n; i++){
            if(nums[i] == nums[i - 1] + 1){
                count++;
                if(count >= k){
                    ans[i - (k - 1)] = nums[i];
                }
            }else {
                count = 1;
            }
        }
        return ans;
    }
}
