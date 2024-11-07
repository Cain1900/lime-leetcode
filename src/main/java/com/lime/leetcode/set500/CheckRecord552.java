package com.lime.leetcode.set500;

import java.util.Arrays;

public class CheckRecord552 {

    /**
     * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
     * 'A'：Absent，缺勤
     * 'L'：Late，迟到
     * 'P'：Present，到场
     * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
     *
     * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
     * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
     * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
     *
     *  */
    public int checkRecord(int n) {
        //全程未缺勤
        long[] num = new long[7];
        //缺勤
        num[0] = 1;
        //出席，未缺勤过
        num[1] = 1;
        //出席，缺勤过
        num[2] = 0;
        //连续迟到1次，未缺勤过
        num[3] = 1;
        //连续迟到2次，未缺勤过
        num[4] = 0;
        //连续迟到1次，缺勤过
        num[5] = 0;
        //连续迟到1次，缺勤过
        num[6] = 0;
        int mod = 1000000007;
        for (int i = 1; i < n; i++){
            //依次生成每一位
            long[] next = new long[7];
            //根据num计算next
            //缺勤： 未缺勤之和
            next[0] = (num[1] + num[3] + num[4]) % mod;
            //出席，未缺勤过: 未缺勤之和
            next[1] = (num[1] + num[3] + num[4]) % mod;
            //出席，缺勤过：缺勤过之和
            next[2] = (num[0] + num[2] + num[5] + num[6]) % mod;
            //连续迟到1次，未缺勤过:
            next[3] = num[1];
            //连续迟到2次，未缺勤过
            next[4] = num[3];
            //连续迟到1次，缺勤过
            next[5] = (num[0] + num[2]) % mod;
            //连续迟到1次，缺勤过
            next[6] = num[5];
            num = next;
        }
        return (int) (Arrays.stream(num).sum() % mod);
    }

    public int checkRecord01(int n) {
        //全程未缺勤
        //num[0][0]: 选择A，且连续迟到个数0
        //num[0][1]: 选择A，且连续迟到个数1
        //num[0][2]: 选择A，且连续迟到个数2
        //num[1][0]: 选择L，且连续迟到个数0
        //num[1][1]: 选择L，且连续迟到个数1
        //num[1][2]: 选择L，且连续迟到个数2
        //num[2][0]: 选择P，且连续迟到个数0
        //num[2][1]: 选择P，且连续迟到个数1
        //num[2][2]: 选择P，且连续迟到个数2
        //缺勤1次
        //num[3][0]: 选择A，且连续迟到个数0
        //num[3][1]: 选择A，且连续迟到个数1
        //num[3][2]: 选择A，且连续迟到个数2
        //num[4][0]: 选择L，且连续迟到个数0
        //num[4][0]: 选择L，且连续迟到个数1
        //num[4][0]: 选择L，且连续迟到个数2
        //num[5][0]: 选择P，且连续迟到个数0
        //num[5][0]: 选择P，且连续迟到个数1
        //num[5][0]: 选择P，且连续迟到个数2
        int[][] num = new int[6][3];
        num[1][1] = 1;
        num[2][0] = 1;
        num[3][0] = 1;
        int mod = 1000000007;
        for (int i = 1; i < n; i++){
            //依次生成每一位
            int[][] next = new int[6][3];
            //根据num计算next
            //选择缺勤，那么不可能全程未缺勤
            next[0][0] = 0;
            next[0][1] = 0;
            next[0][2] = 0;
            next[3][1] = 0;
            next[3][2] = 0;
            //可由所有全程未缺勤转化而来
            next[3][0] = (num[0][0] + num[0][1] + num[0][2] + num[1][0] + num[1][1] + num[1][2] + num[2][0] + num[2][1] + num[2][2] ) % mod;

            //选择迟到，说明前一位为P。
            next[1][0] = 0;
            next[1][1] = (num[2][0] + num[2][1] + num[2][2] ) % mod;
            next[1][2] = num[1][1];
            next[4][0] = 0;
            next[4][1] = (next[3][0] + num[5][0] + num[5][1] + num[5][2] ) % mod;
            next[4][2] = num[4][1];

            //选择到场，前一位可以为任意值
            next[2][0] = (Arrays.stream(num[0]).sum() + Arrays.stream(num[1]).sum() + Arrays.stream(num[2]).sum()) % mod;
            next[2][1] = 0;
            next[2][2] = 0;
            next[5][0] = (Arrays.stream(num[3]).sum() + Arrays.stream(num[4]).sum() + Arrays.stream(num[5]).sum()) % mod;
            next[5][1] = 0;
            next[5][2] = 0;

            num = next;
        }
        int sum = 0;
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 3; j++){
                sum += num[i][j];
            }
        }
        return sum % mod;

    }
}
