package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/12/3.
 */
public class Week20231203 {

    public int minimumAddedCoins(int[] coins, int target) {
        int highestBit = (target & (target - 1));
        highestBit = ~highestBit;
        return highestBit;

    }

    public static void main(String[] args) {
        int num = 11; // 二进制表示为 1010

        int highestBit = num & (num - 1); // 使用位与运算符找到最高位
        highestBit = ~highestBit; // 取反得到最高位的值

        System.out.println("二进制最高位为：" + highestBit);
        testMinimumAddedCoins();
    }

    private static void testMinimumAddedCoins() {
        int[] coins = {1,1,1};
        int target = 20;
        Week20231203 week20231203 = new Week20231203();
        System.out.println(week20231203.minimumAddedCoins(coins, target));
    }
}
