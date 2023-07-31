package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/4/26.
 */
public class MaxSumTwoNoOverlap1031 {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(help(nums, firstLen, secondLen), help(nums, secondLen, firstLen));
    }

    public int help(int[] nums, int firstLen, int secondLen) {
        //计算左侧数组之和
        int suml = 0;
        for (int i = 0; i < firstLen; ++i) {
            suml += nums[i];
        }
        //记录左侧数组最大值
        int maxSumL = suml;
        //计算右侧数组之和
        int sumr = 0;
        for (int i = firstLen; i < firstLen + secondLen; ++i) {
            sumr += nums[i];
        }
        //记录两数组之和最大值
        int res = maxSumL + sumr;
        for (int i = firstLen + secondLen, j = firstLen; i < nums.length; ++i, ++j) {
            //左侧数组递归计算
            suml += nums[j] - nums[j - firstLen];
            //记录左侧数组最大值
            maxSumL = Math.max(maxSumL, suml);
            //右侧数组递归计算
            sumr += nums[i] - nums[i - secondLen];
            //记录两数组之和最大值
            res = Math.max(res, maxSumL + sumr);
        }
        return res;
    }

    public int maxSumTwoNoOverlap1(int[] nums, int firstLen, int secondLen) {
        int[] leftMaxSum = new int[nums.length + 1];
        for(int i = secondLen; i < nums.length + 1; i++){
            leftMaxSum[i] = Math.max(leftMaxSum[i - 1], sum(nums, i - secondLen, i));
        }
        int[] rightMaxSum = new int[nums.length + 1];
        for (int j = nums.length - secondLen; j >= 0; j--){
            rightMaxSum[j] = Math.max(rightMaxSum[j + 1], sum(nums, j, j + secondLen));
        }
        int[] maxSumTwo = new int[nums.length + 1];
        int ans = 0;
        //第一个子数组的起始位置头0，尾firstLen-1，用i表示头，则尾是 i + firstLen - 1
        for(int i = 0; i < nums.length - firstLen + 1 ; i++) {
            ans = Math.max(ans, Math.max(leftMaxSum[i], rightMaxSum[i + firstLen]) + sum(nums, i, i + firstLen));
        }
        return ans;
    }


    public int sum(int[] nums, int start, int end){
        int sum = 0;
        for (int i = start; i < end; i++){
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {0,6,5,2,2,5,1,9,4};
        MaxSumTwoNoOverlap1031 maxSumTwoNoOverlap1031 = new MaxSumTwoNoOverlap1031();
        System.out.println(maxSumTwoNoOverlap1031.maxSumTwoNoOverlap(nums, 1, 2));

    }
}
