package com.lime.leetcode.set2500;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHang on 2023/7/26.
 */
public class HandleQuery2569 {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        long sumNums1 = 0l;
        long sumNums2 = 0l;
        for (int i = 0; i <  nums2.length; i++){
            sumNums1 += nums1[i];
            sumNums2 += nums2[i];
        }
        List<Long> queryList = new ArrayList<>();
        for(int i = 0; i < queries.length; i++){
            if(queries[i][0] == 1){
                sumNums1 = 0l;
                for (int j = 0; j <  nums1.length; j++){
                    if(j >= queries[i][1] && j <= queries[i][2]){
                        nums1[j] ^= 1;
                    }
                    sumNums1 += nums1[j];
                }
                //sumNums1 = dp(nums1, sumNums1, queries[i][1], queries[i][2]);
            }else if(queries[i][0] == 2){
                sumNums2 += sumNums1 * queries[i][1];
            }else if(queries[i][0] == 3){
                queryList.add(sumNums2);
            }
        }
        long[] res = new long[queryList.size()];
        for (int i = 0; i < queryList.size(); i++){
            res[i] = queryList.get(i);
        }
        return res;
    }

    private long dp(int[] nums, long sum, int left, int right) {
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(1^1);
        System.out.println(0^1);
        System.out.println(0^0);
    }
}
