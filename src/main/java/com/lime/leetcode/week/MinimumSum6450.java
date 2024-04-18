package com.lime.leetcode.week;

import java.util.Set;

/**
 * Created by YuHang on 2023/8/20.
 */
public class MinimumSum6450 {

    public int minimumSum(int n, int k) {
        int sum = 0;
        int j = 1;
        for (int i = 1; i <= n; i++){
            while (j < k && j > k -j){
                j++;
            }
            sum += j;
            j++;
        }
        return sum;
    }

    public static void main(String[] args) {
        MinimumSum6450 minimumSum6450 = new MinimumSum6450();
        System.out.println(minimumSum6450.minimumSum(5, 4));
    }
}
