package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/5/10.
 */
public class SmallestRepunitDivByK1015 {

    public int smallestRepunitDivByK(int k) {
        // 若 k 能被 2 或 5 整除，则无解，返回 -1
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        // 初始化余数为 1，表示一个数的最低位是 1
        int resid = 1 % k, len = 1;
        // 若余数不为 0，继续迭代
        while (resid != 0) {
            // 计算下一个数的余数，下一个数在当前余数后加一个 1
            resid = (resid * 10 + 1) % k;
            len++;
        }
        // 返回数字 1 的最小重复次数
        return len;
    }

    public static void main(String[] args) {
        SmallestRepunitDivByK1015 smallestRepunitDivByK1015 = new SmallestRepunitDivByK1015();
        System.out.println(smallestRepunitDivByK1015.smallestRepunitDivByK(17));
    }
}
