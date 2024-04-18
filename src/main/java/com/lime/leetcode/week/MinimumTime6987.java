package com.lime.leetcode.week;

import java.util.Arrays;
import java.util.List;

/**
 * Created by YuHang on 2023/8/6.
 */
public class MinimumTime6987 {

    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int[][] nums = new int[nums1.size()][];
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < nums1.size(); i++){
            nums[i] = new int[]{nums1.get(i), nums2.get(i)};
            sum1 += nums[i][0];
            sum2 += nums[i][1];
        }
        Arrays.sort(nums, (a, b) -> a[1] - b[1]);
        //置零时减去的值
        int[][] dp = new int[nums1.size() + 1][nums1.size() + 1];
        for (int i = 0; i < nums1.size(); i++){
            for (int j = 1; j <= i + 1; j++){
                //当指定列必须置零时，num2值越大，最后一步置零，这样减去的值越大
                //当第i列不置零时，仍进行j步置零，其结果等于前i-1列进行j步置零，此时 dp[i + 1][j] = dp[i][j];
                //当第i列置零时，由于第i列num2值最大，一定最后一步置零，前j-1步置零等于前i-1列执行j-1步置零结果，此时dp[i + 1][j] = dp[i][j - 1] + nums[i][0] + j * nums[i][1]
                dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - 1] + nums[i][0] + j * nums[i][1]);
            }
        }
        for (int i = 0; i <= nums2.size(); i++) {
            if (sum1 + sum2 * (long) i - dp[nums1.size()][i] <= x) {
                return i;
            }
        }
        return -1;
    }
}
