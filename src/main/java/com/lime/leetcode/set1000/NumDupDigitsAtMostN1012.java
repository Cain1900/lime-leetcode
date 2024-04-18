package com.lime.leetcode.set1000;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/8/21.
 */
public class NumDupDigitsAtMostN1012 {

    char s[];
    int memo[][];

    /**
     * * 至少有 1 位重复的数字
     * */
    public int numDupDigitsAtMostN(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        //mask | (1 << d)， d可以取值0-9，用于记录每一位取值记录
        memo = new int[m][1 << 10];
        for (int i = 0; i < m; i++)
            Arrays.fill(memo[i], -1); // -1 表示没有计算过
        return n - dp(0, 0, true, false);
    }

    //计算没有重复位的数字
    int dp(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == s.length){
            //i从0开始，i = s.length - 1 表示个位，此时也会出现没有填数字的情况
            //i = s.length 且 isNum = true: 表明个位填写了数字，是合法数字，返回1
            //i = s.length 且 isNum = false: 表明个位未填写数字，不是合法数字，返回0
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && memo[i][mask] != -1)
            return memo[i][mask];
        int res = 0;
        //isNum = false时，表明前面的数位没有填了数字
        //1、当前位也没有填数字时，将isNum=false传递下去。
        //2、当前位为首位时，填入数字1-9，首位非零，将isNum=true传递下去。
        if (!isNum){
            //计算当前位也没有填数字的情况
            res = dp(i + 1, mask, false, false);
        }
        //当前位填数字的情况
        //查询填入数字上界，isLimit = true 时上界存在，否则为9
        int up = isLimit ? s[i] - '0' : 9; // 如果前面填的数字都和 n 的一样，那么这一位至多填数字 s[i]（否则就超过 n 啦）
        //int d = isNum ? 0 : 1 当前位为首位时 isNum=false，从1开始遍历； 当前位不是首位时，isNum=true，从0开始遍历。
        for (int d = isNum ? 0 : 1; d <= up; ++d){
            // 枚举要填入的数字 d
            //第i位设置成为d的情况, 题目条件是计算没有重复位的数字，所以mask中不能包含d
            if ((mask >> d & 1) == 0) {
                // d 不在 mask 中，也就是之前没有取值
                //mask | (1 << d): 第i位设置成为d的情况已经被讨论了，生成新的mask传递下去
                //isLimit && d == up: 存在上界且当前位在上界，下一位也要分析上界
                //isNum=true: 当前位设置数字的
                res += dp(i + 1, mask | (1 << d), isLimit && d == up, true);
            }
        }
        if (!isLimit && isNum){
            //isLimit=false: 没有上界
            //isNum=true: 前面填写过数字，非首位
            //处于上界时，填写相同数字，有效结果也是不能复用的
            //处于首位时，当前位不能填写0，有效结果也是不能复用的
            memo[i][mask] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        NumDupDigitsAtMostN1012 numDupDigitsAtMostN1012 = new NumDupDigitsAtMostN1012();
        System.out.println(numDupDigitsAtMostN1012.numDupDigitsAtMostN(415));
    }


}
